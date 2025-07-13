package com.lectura.apgmb.dtos.lecture;

import java.io.Serializable;

public class LectureUpdate implements Serializable {

    private Long idLecture;
    private Long currentLecture;

    public Long getIdLecture() {
        return idLecture;
    }

    public void setIdLecture(Long idLecture) {
        this.idLecture = idLecture;
    }

    public Long getCurrentLecture() {
        return currentLecture;
    }

    public void setCurrentLecture(Long currentLecture) {
        this.currentLecture = currentLecture;
    }
}
