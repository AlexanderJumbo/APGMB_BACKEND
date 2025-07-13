package com.lectura.apgmb.dtos.account;

import java.io.Serializable;

public class AccountResponse implements Serializable {
    private Long accountId;
    private String message;

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
