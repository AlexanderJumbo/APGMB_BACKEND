package com.lectura.apgmb.dtos.lecture;

import java.io.Serializable;
import java.util.Date;

public class LectureRequest implements Serializable {
    private Long prevLecture;
    private Long currentLecture;
    private String observation;
    //private Date dateLecture;
    private Long accountLecture;
    private Long operator;

    public Long getPrevLecture() {
        return prevLecture;
    }

    public void setPrevLecture(Long prevLecture) {
        this.prevLecture = prevLecture;
    }

    public Long getCurrentLecture() {
        return currentLecture;
    }

    public void setCurrentLecture(Long currentLecture) {
        this.currentLecture = currentLecture;
    }

    public String getObservation() {
        return observation;
    }

    public void setObservation(String observation) {
        this.observation = observation;
    }

    /*public Date getDateLecture() {
        return dateLecture;
    }

    public void setDateLecture(Date dateLecture) {
        this.dateLecture = dateLecture;
    }*/

    public Long getAccountLecture() {
        return accountLecture;
    }

    public void setAccountLecture(Long accountLecture) {
        this.accountLecture = accountLecture;
    }

    public Long getOperator() {
        return operator;
    }

    public void setOperator(Long operator) {
        this.operator = operator;
    }
}
