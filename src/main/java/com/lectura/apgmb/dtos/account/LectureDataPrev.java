package com.lectura.apgmb.dtos.account;

import java.io.Serializable;

public class LectureDataPrev implements Serializable {
    private Long accountId;
    private Long userId;
    private String name;
    private String lastName;
    private Long meterId;
    private Long previousLecture;

    public LectureDataPrev(Long accountId, Long userId, String name, String lastName, Long meterId, Long previousLecture) {
        this.accountId = accountId;
        this.userId = userId;
        this.name = name;
        this.lastName = lastName;
        this.meterId = meterId;
        this.previousLecture = previousLecture;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Long getMeterId() {
        return meterId;
    }

    public void setMeterId(Long meterId) {
        this.meterId = meterId;
    }

    public Long getPreviousLecture() {
        return previousLecture;
    }

    public void setPreviousLecture(Long previousLecture) {
        this.previousLecture = previousLecture;
    }
}
