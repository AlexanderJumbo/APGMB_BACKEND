package com.lectura.apgmb.services;

import com.lectura.apgmb.dtos.lecture.LectureListResponse;
import com.lectura.apgmb.dtos.lecture.LectureRequest;
import com.lectura.apgmb.dtos.lecture.LectureResponse;
import com.lectura.apgmb.dtos.lecture.LectureUpdate;
import com.lectura.apgmb.entities.Account;
import com.lectura.apgmb.entities.MeterReading;
import com.lectura.apgmb.entities.secutiry.User;
import com.lectura.apgmb.repositories.AccountRepository;
import com.lectura.apgmb.repositories.MeterReadingRepository;
import com.lectura.apgmb.repositories.auth.UserRepository;
import com.lectura.apgmb.services.auth.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MeterReadingServiceImpl implements MeterReadingService{

    @Autowired
    private MeterReadingRepository meterReadingRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private AccountRepository accountRepository;

    @Override
    public LectureResponse registerLecture(LectureRequest lectureRequest) {
        Optional<User> operatorFound = userService.findById(lectureRequest.getOperator());
        Optional<Account> accountFound = accountRepository.findById(lectureRequest.getAccountLecture());
        if(accountFound.isEmpty() || operatorFound.isEmpty()){
            return getLectureResponse(0L, "Ocurrió un error mientras se registraba la lectura, valide que el usuario y/o cuenta existan o inténtelo nuevamente");
        }
        return getLectureResponse(mapRequestLecture(lectureRequest, accountFound, operatorFound).getId(), "Lectura guardada correctamente");
    }

    @Override
    public List<LectureListResponse> getAllLecture() {

        int currentMonth = LocalDate.now().getMonthValue();
        int currentYear = LocalDate.now().getYear();

        return meterReadingRepository.findAll().stream().filter(l -> {
                    Date lectureDate = l.getLectureDate();
                    LocalDate localDate = lectureDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                    return localDate.getMonthValue() == currentMonth && localDate.getYear() == currentYear;
                })
                . map(l -> new LectureListResponse(
                l.getId(),
                l.getLectureDate(),
                l.getNextLecture(),
                l.getPreviousLecture(),
                l.getObservation(),
                l.getAccount().getAccountId(),
                l.getAccount().getUser().getName() + " " +  l.getAccount().getUser().getLastName(),
                l.getAccount().getWaterMeter().getMeterNumber(),
                l.getOperador().getName() + l.getOperador().getLastName(),
                        l.getTotalConsumption() == null ? 0 : l.getTotalConsumption(),
                        l.getAccount().getSector().getNameSector()
        )).collect(Collectors.toList());
    }

    @Override
    public LectureResponse updateLecture(LectureUpdate lectureUpdate) {
        Optional<MeterReading> lectureFound = meterReadingRepository.findById(lectureUpdate.getIdLecture());
        if(lectureFound.isEmpty()) return getLectureResponse(0L, "Registro no encontrado");
        lectureFound.get().setNextLecture(lectureUpdate.getCurrentLecture());
        lectureFound.get().setTotalConsumption(lectureUpdate.getCurrentLecture() - lectureFound.get().getPreviousLecture());
        meterReadingRepository.save(lectureFound.get());
        return getLectureResponse(lectureFound.get().getId(), "Lectura actualizada con éxito");
    }

    @Override
    public Page<LectureListResponse> getAllLecturesWithFilters(int pageNumber, int numberRecordsPerPage, String orderRecordsByField, String orderRecordsByDirection, LocalDate startDate, LocalDate endDate, String user) {
        Sort sort = orderRecordsByDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(orderRecordsByField).ascending() : Sort.by(orderRecordsByField).descending();
        Pageable pageable = PageRequest.of(pageNumber, numberRecordsPerPage, sort);

        Page<MeterReading> readings;

        LocalDateTime startDateTime = startDate.atStartOfDay();
        LocalDateTime endDateTime = endDate.plusDays(1).atStartOfDay().minusNanos(1);

        if (startDate != null && endDate != null && user != null && !user.isBlank()) {
            readings = meterReadingRepository.findByLectureDateBetweenAndUser(
                    startDateTime, endDateTime, user, pageable);
            //readings = meterReadingRepository.findByLectureDateBetween(startDateTime, endDateTime, pageable);
        } else if (startDateTime != null && endDateTime != null) {
            readings = meterReadingRepository.findByLectureDateBetween(startDateTime, endDateTime, pageable);
        } else if (user != null && !user.isBlank()) {
            readings = meterReadingRepository.findLectureByUser(user, pageable);
        } else {
            readings = meterReadingRepository.findAll(pageable);
        }
        return readings.map(l -> new LectureListResponse(
                l.getId(),
                l.getLectureDate(),
                l.getNextLecture(),
                l.getPreviousLecture(),
                l.getObservation(),
                l.getAccount().getAccountId(),
                l.getAccount().getUser().getName() + " " + l.getAccount().getUser().getLastName(),
                l.getAccount().getWaterMeter().getMeterNumber(),
                l.getOperador().getName() + l.getOperador().getLastName(),
                l.getTotalConsumption() == null ? 0 : l.getTotalConsumption(),
                l.getAccount().getSector().getNameSector()
        ));
    }

    @Override
    public boolean existCurrentLecture(Long accountId) {
        int currentMonth = LocalDate.now().getMonthValue();
        int currentYear = LocalDate.now().getYear();

        Optional<MeterReading> lecture = meterReadingRepository.getLastLecture(accountId);

        if(lecture.isEmpty()) return false;

        Date lectureDate = lecture.get().getLectureDate();
        LocalDate localDate = lectureDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        return localDate.getMonthValue() == currentMonth && localDate.getYear() == currentYear;
    }

    private MeterReading mapRequestLecture(LectureRequest lectureRequest, Optional<Account> accountFound, Optional<User> operatorFound) {
        MeterReading newLecture = new MeterReading();
        newLecture.setPreviousLecture(lectureRequest.getPrevLecture());
        newLecture.setNextLecture(lectureRequest.getCurrentLecture());
        newLecture.setObservation(lectureRequest.getObservation());
        newLecture.setLectureDate(new Date());
        newLecture.setAccount(accountFound.get());
        newLecture.setOperador(operatorFound.get());
        newLecture.setTotalConsumption(lectureRequest.getCurrentLecture() - lectureRequest.getPrevLecture());
        return meterReadingRepository.save(newLecture);
    }

    private static LectureResponse getLectureResponse(Long idLecture, String message) {
        LectureResponse result = new LectureResponse();
        result.setIdLecture(idLecture);
        result.setMessage(message);
        return result;
    }
}
