package com.lectura.apgmb.services;

import com.lectura.apgmb.dtos.account.*;
import com.lectura.apgmb.entities.Account;

import java.util.List;
import java.util.Optional;

public interface AccountService {

    Optional<AccountUserResponse> getUserByMeterId(Long meterId);
    Optional<Account> getAccountByMeterId(Long meterId);
    AccountResponse createAccount(AccountRequest accountRequest);
    List<AccountListResponse> getAllAccounts(Boolean isActive, String sector);
    AccountResponse updateAccount(AccountRequestUpdate accountRequest);
    AccountResponse getAccountByUserId(Long userId);
    Boolean deleteLogicAccount(Long idAccount);
    Optional<LectureDataPrev> getPreviousMonthLecture(Long meterId);
    List<AccountListResponse> getActiveAccounts();
}
