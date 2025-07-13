package com.lectura.apgmb.controllers;

import com.lectura.apgmb.dtos.account.*;
import com.lectura.apgmb.entities.Account;
import com.lectura.apgmb.entities.secutiry.User;
import com.lectura.apgmb.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/account")
public class AccountController {
    @Autowired
    private AccountService accountService;

    @GetMapping("/userbymeter/{meterId}")
    public ResponseEntity<AccountUserResponse> getUserByMeterId(@PathVariable Long meterId) {
        return accountService.getUserByMeterId(meterId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/by-meter/{meterId}")
    public ResponseEntity<LectureDataPrev> getPreviousMonthLecture(@PathVariable Long meterId) {
        return accountService.getPreviousMonthLecture(meterId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/all")
    public ResponseEntity<List<AccountListResponse>> getAllAccounts(
            @RequestParam(value = "isActive", required = false) Boolean isActive,
            @RequestParam(value = "sector", required = false) String sector) {
        return ResponseEntity.status(HttpStatus.OK).body(accountService.getAllAccounts(isActive,sector));
    }

    @PostMapping
    public ResponseEntity<AccountResponse> createAccountToClient(@RequestBody AccountRequest accountRequest) {
        System.out.println("AccountRequest" + accountRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(accountService.createAccount(accountRequest));
    }

    @PutMapping("/update")
    public ResponseEntity<AccountResponse> updateAccountToClient(@RequestBody AccountRequestUpdate accountRequest) {
        return ResponseEntity.status(HttpStatus.OK).body(accountService.updateAccount(accountRequest));
    }

    @GetMapping("/by-user/{userId}")
    public ResponseEntity<AccountResponse> getAccountByUserId(@PathVariable Long userId) {
        return ResponseEntity.status(HttpStatus.OK).body(accountService.getAccountByUserId(userId));
    }

    @PutMapping("/delete/{accountId}")
    public ResponseEntity<Boolean> deleteAccount(@PathVariable Long accountId){
        return ResponseEntity.status(HttpStatus.OK).body(accountService.deleteLogicAccount(accountId));
    }
}
