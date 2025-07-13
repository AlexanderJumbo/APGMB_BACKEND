package com.lectura.apgmb.dtos.lecture;

import java.io.Serializable;

public class LectureResponse implements Serializable {
    private Long idLecture;
    private String message;

    public Long getIdLecture() {
        return idLecture;
    }

    public void setIdLecture(Long idLecture) {
        this.idLecture = idLecture;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
