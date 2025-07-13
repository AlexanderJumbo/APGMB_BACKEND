package com.lectura.apgmb.services;

import com.lectura.apgmb.dtos.account.*;
import com.lectura.apgmb.dtos.meter.UpdateMeterRequest;
import com.lectura.apgmb.dtos.meter.UpdateMeterResponse;
import com.lectura.apgmb.dtos.register.RegisterRequest;
import com.lectura.apgmb.dtos.user.ClientUpdateRequest;
import com.lectura.apgmb.dtos.user.UpdateResponse;
import com.lectura.apgmb.entities.Account;
import com.lectura.apgmb.entities.Sector;
import com.lectura.apgmb.entities.WaterMeter;
import com.lectura.apgmb.entities.secutiry.User;
import com.lectura.apgmb.exceptions.ObjectNotFoundException;
import com.lectura.apgmb.repositories.AccountRepository;
import com.lectura.apgmb.repositories.SectorRepository;
import com.lectura.apgmb.services.auth.UserService;
import jakarta.persistence.NoResultException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AccountServiceImpl implements AccountService{

    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private WaterMeterService meterService;
    @Autowired
    private SectorRepository sectorRepository;

    @Override
    public Optional<AccountUserResponse> getUserByMeterId(Long meterId) {

        Optional<User> user = accountRepository.findUserByMeterId(meterId);
        AccountUserResponse accountUserResponse = new AccountUserResponse();
        accountUserResponse.setFullname(user.get().getName() + " " + user.get().getLastName());
        accountUserResponse.setIdUsuario(user.get().getId());
        return Optional.of(accountUserResponse);
    }

    @Override
    public Optional<Account> getAccountByMeterId(Long meterId) {
        return accountRepository.findByWaterMeter_MeterId(meterId);
    }

    //Obtener datos de usuario, cuenta y última lectura para tomar lectura actual
    @Override
    public Optional<LectureDataPrev> getPreviousMonthLecture(Long meterId) {
        LocalDate now = LocalDate.now();
        LocalDate firstDayLastMonth = now.minusMonths(1).withDayOfMonth(1);
        LocalDate lastDayLastMonth = now.withDayOfMonth(1).minusDays(1);

        Date startDate = Date.from(firstDayLastMonth.atStartOfDay(ZoneId.systemDefault()).toInstant());
        Date endDate = Date.from(lastDayLastMonth.atTime(LocalTime.MAX).atZone(ZoneId.systemDefault()).toInstant());

        try {
            Optional<LectureDataPrev> result = accountRepository.findAccountBywaterMeter(
                    meterId, startDate, endDate
            );
            return Optional.ofNullable(result.get());
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }

    @Override
    public List<AccountListResponse> getActiveAccounts() {
        return accountRepository.findAll().stream().map( a -> new AccountListResponse(
                a.getAccountId(),
                a.getUser().getId(),
                a.getSector().getSectorId(),
                a.getUser().getName(),
                a.getUser().getLastName(),
                a.getUser().getAddress(),
                a.getUser().getDni(),
                a.getUser().getNumberPhone(),
                a.getUser().getEmail(),
                a.getWaterMeter().getMeterId(),
                a.getWaterMeter().getMeterNumber(),
                a.getWaterMeter().getMeterMark(),
                a.getPredialCode(),
                a.getCreateAt(),
                a.isActive(),
                a.getSector().getNameSector()
        )).collect(Collectors.toList());
    }

    @Override
    public AccountResponse createAccount(AccountRequest accountRequest) {
        try{
            boolean userExist = userService.findByEmail(accountRequest.getEmail()).isPresent();
            boolean meterExist = meterService.findByMeterNumber(accountRequest.getMeterNumber()).isPresent();
            boolean sectorExist = sectorRepository.findByNameSector(accountRequest.getNameSector()).isPresent();

            if(userExist || meterExist || !sectorExist) return validateDontExistMeterUser(userExist, meterExist);

            User userRegistered = userService.registerUser(mapUserDTO(accountRequest));

            WaterMeter meterRegistered = meterService.createMeter(mapWaterDTO(accountRequest));

            Sector sector = sectorRepository.findByNameSector(accountRequest.getNameSector()).get();

            Account accountCreated = accountRepository.save(mapAccountDTO(userRegistered, meterRegistered, sector, accountRequest));
            return getAccountResponse(accountCreated);
        }catch (Exception ex){
            ex.printStackTrace();
            return getAccountResponse(new Account());
        }
    }

    @Override
    public List<AccountListResponse> getAllAccounts(Boolean isActive, String sector) {
        return accountRepository.findAll().stream()
                .filter(a -> isActive == null || a.isActive() == isActive)
                .filter(a -> sector == null || a.getSector().getNameSector().equals(sector))
                .map(a -> new AccountListResponse(
                    a.getAccountId(),
                    a.getUser().getId(),
                    a.getSector().getSectorId(),
                    a.getUser().getName(),
                    a.getUser().getLastName(),
                    a.getUser().getAddress(),
                    a.getUser().getDni(),
                    a.getUser().getNumberPhone(),
                    a.getUser().getEmail(),
                    a.getWaterMeter().getMeterId(),
                    a.getWaterMeter().getMeterNumber(),
                    a.getWaterMeter().getMeterMark(),
                    a.getPredialCode(),
                    a.getCreateAt(),
                    a.isActive(),
                    a.getSector().getNameSector()
            )).collect(Collectors.toList());
    }

    @Override
    public AccountResponse updateAccount(AccountRequestUpdate accountRequest) {
        //Account accountFound = accountRepository.findById(accountRequest.getId()).get();
        Optional<Account> accountFound = accountRepository.findById(accountRequest.getAccountId());
        AccountResponse response = new AccountResponse();
        if(accountFound.isEmpty()){
            response.setAccountId(accountRequest.getAccountId());
            response.setMessage("Cuenta no existe");
            return response;
        }

        boolean userExist = userService.findById(accountRequest.getUserId()).isPresent();
        boolean meterExist = meterService.findById(accountRequest.getMeterId()).isPresent();

        if(!userExist || !meterExist) return genericResponse();

        UpdateResponse userUpdate = updateClientFromAccount(accountRequest);
        UpdateMeterResponse meterUpdate = updateMeterFromAccount(accountRequest);

        if(userUpdate.getIdUser() == 0 || meterUpdate.getIdMeter() == 0){
            response.setAccountId(0L);
            response.setMessage("Error al actualizar datos de cliente y/o medidor");
            return response;
        }
        accountFound.get().setPredialCode(accountRequest.getPredialCode());
        accountRepository.save(accountFound.get());
        return getAccountResponse(accountFound.get());
        //return accountRepository.save(mapAccountDTO(accountFound));
    }

    @Override
    public AccountResponse getAccountByUserId(Long userId) {
        Optional<Account> accountFound = accountRepository.findAccountByUserId(userId);
        return getAccountByUserResponse(accountFound);
    }

    @Override
    public Boolean deleteLogicAccount(Long idAccount) {
        Optional<Account> accountToDelete = accountRepository.findById(idAccount);
        if(accountToDelete.isEmpty()) return false;
        accountToDelete.get().setActive(false);
        return !accountRepository.save(accountToDelete.get()).isActive();
    }

    private static AccountResponse getAccountByUserResponse(Optional<Account> accountFound) {
        AccountResponse response = new AccountResponse();
        if(accountFound.isEmpty()){
            response.setAccountId(0L);
            response.setMessage("La cuenta no existe en nuestros registros");
        }
        else{
            response.setAccountId(accountFound.get().getAccountId());
            response.setMessage("Cuenta encontrada");
        }
        return response;
    }

    private UpdateResponse updateClientFromAccount(AccountRequestUpdate accountRequest) {
        ClientUpdateRequest clientUpdateRequest = new ClientUpdateRequest();
        clientUpdateRequest.setUserId(accountRequest.getUserId());
        clientUpdateRequest.setName(accountRequest.getName());
        clientUpdateRequest.setLastname(accountRequest.getLastname());
        clientUpdateRequest.setAddress(accountRequest.getAddress());
        clientUpdateRequest.setDni(accountRequest.getDni());
        clientUpdateRequest.setEmail(accountRequest.getEmail());
        clientUpdateRequest.setPhone(accountRequest.getPhone());

        return userService.updateClient(clientUpdateRequest);
    }

    private UpdateMeterResponse updateMeterFromAccount(AccountRequestUpdate accountRequest) {
        UpdateMeterRequest updateMeterRequest = new UpdateMeterRequest();
        updateMeterRequest.setIdMeter(accountRequest.getMeterId());
        updateMeterRequest.setMarkMeter(accountRequest.getMeterMark());
        updateMeterRequest.setNumberMeter(accountRequest.getMeterNumber());
        return meterService.updateMeter(updateMeterRequest);
    }

    private static AccountResponse validateDontExistMeterUser(boolean userExist, boolean meterExist) {
        AccountResponse response = new AccountResponse();
            String message = "";
            if(userExist && meterExist) {
                response.setMessage("Cliente y medidor ya existen, por lo tanto no se podrá registrar esta cuenta");
                response.setAccountId(0L);
            }
            else{
                if(userExist) message = "Cliente";
                if(meterExist) message += " y medidor";
                response.setMessage(message + " ya existen, por lo que no se puede registrar esta cuenta!");
            }
            return response;
    }

    private static AccountResponse genericResponse() {
        AccountResponse response = new AccountResponse();
        response.setMessage("Los datos de medidor y/o cliente no existen en nustros registros, por favor validarlos");
        response.setAccountId(0L);
        return response;
    }
    private static RegisterRequest mapUserDTO(AccountRequest accountRequest) {
        RegisterRequest newUser = new RegisterRequest();
        newUser.setName(accountRequest.getName());
        newUser.setUserName(accountRequest.getName()); // para cliente se coloca por default el name como username
        newUser.setLastname(accountRequest.getLastname());
        newUser.setEmail(accountRequest.getEmail());
        newUser.setDni(accountRequest.getDni());
        newUser.setAddress(accountRequest.getAddress());
        newUser.setNumberPhone(accountRequest.getPhone());
        newUser.setRole(accountRequest.getRole());
        newUser.setPassword("");
        return newUser;
    }

    private static WaterMeter mapWaterDTO(AccountRequest accountRequest) {
        WaterMeter newMeter = new WaterMeter();
        newMeter.setMeterNumber(accountRequest.getMeterNumber());
        newMeter.setMeterMark(accountRequest.getMeterMark());
        newMeter.setCreateAt(new Date());
        newMeter.setActive(true);
        return newMeter;
    }

    private static Account mapAccountDTO(User userRegistered, WaterMeter meterRegistered, Sector sector, AccountRequest accountRequest) {
        Account newAccount = new Account();
        newAccount.setUser(userRegistered);
        newAccount.setWaterMeter(meterRegistered);
        newAccount.setPredialCode(accountRequest.getPredialCode());
        newAccount.setSector(sector);
        newAccount.setActive(true);
        newAccount.setCreateAt(new Date());
        return newAccount;
    }

    private static AccountResponse getAccountResponse(Account accountCreated) {
        AccountResponse result = new AccountResponse();
        if(accountCreated.getAccountId() == null || accountCreated.getAccountId() == 0){
            result.setAccountId(0L);
            result.setMessage("Ocurrió un error al intentar crear la cuenta, inténtelo más tarde!");
        }
        result.setAccountId(accountCreated.getAccountId());
        result.setMessage("Cuenta registrada correctamente");
        return result;
    }
}
