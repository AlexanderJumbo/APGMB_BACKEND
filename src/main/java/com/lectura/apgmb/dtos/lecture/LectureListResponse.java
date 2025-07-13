package com.lectura.apgmb.dtos.lecture;

import java.io.Serializable;
import java.util.Date;

public class LectureListResponse implements Serializable {
    private Long idLecture;
    private Date dateLecture;
    private Long currentLecture;
    private Long prevLecture;
    private String observation;
    private Long accountId;
    private String accountUser;
    private String accountMeter;
    private String operatorName;
    private Long totalConsumption;
    private String nameSector;

    public LectureListResponse(Long idLecture, Date dateLecture, Long currentLecture, Long prevLecture, String observation, Long accountId, String accountUser, String accountMeter, String operatorName, Long totalConsumption, String nameSector) {
        this.idLecture = idLecture;
        this.dateLecture = dateLecture;
        this.currentLecture = currentLecture;
        this.prevLecture = prevLecture;
        this.observation = observation;
        this.accountId = accountId;
        this.accountUser = accountUser;
        this.accountMeter = accountMeter;
        this.operatorName = operatorName;
        this.totalConsumption = totalConsumption;
        this.nameSector = nameSector;
    }

    public Long getIdLecture() {
        return idLecture;
    }

    public void setIdLecture(Long idLecture) {
        this.idLecture = idLecture;
    }

    public Date getDateLecture() {
        return dateLecture;
    }

    public void setDateLecture(Date dateLecture) {
        this.dateLecture = dateLecture;
    }

    public Long getCurrentLecture() {
        return currentLecture;
    }

    public void setCurrentLecture(Long currentLecture) {
        this.currentLecture = currentLecture;
    }

    public Long getPrevLecture() {
        return prevLecture;
    }

    public void setPrevLecture(Long prevLecture) {
        this.prevLecture = prevLecture;
    }

    public String getObservation() {
        return observation;
    }

    public void setObservation(String observation) {
        this.observation = observation;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public String getAccountUser() {
        return accountUser;
    }

    public void setAccountUser(String accountUser) {
        this.accountUser = accountUser;
    }

    public String getAccountMeter() {
        return accountMeter;
    }

    public void setAccountMeter(String accountMeter) {
        this.accountMeter = accountMeter;
    }

    public String getOperatorName() {
        return operatorName;
    }

    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName;
    }

    public Long getTotalConsumption() {
        return totalConsumption;
    }

    public void setTotalConsumption(Long totalConsumption) {
        this.totalConsumption = totalConsumption;
    }

    public String getNameSector() {
        return nameSector;
    }

    public void setNameSector(String nameSector) {
        this.nameSector = nameSector;
    }
}
