package com.lectura.apgmb.dtos;

import java.io.Serializable;

public class GeneratePDFMasivo implements Serializable {

    private Long idLecture;

    public Long getIdLecture() {
        return idLecture;
    }

    public void setIdLecture(Long idLecture) {
        this.idLecture = idLecture;
    }
}
