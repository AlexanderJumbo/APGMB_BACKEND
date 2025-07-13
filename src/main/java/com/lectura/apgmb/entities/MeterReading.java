package com.lectura.apgmb.entities;

import com.lectura.apgmb.entities.secutiry.User;
import jakarta.persistence.*;

import java.util.Date;

@Entity
public class MeterReading {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long previousLecture;
    private Long nextLecture;
    private String observation;
    private Long totalConsumption;
    private Date lectureDate;
    private Date CreateAt;
    private Long userCreated;
    private Date dateModified;
    private Long userModified;
    private Date dateElimination;
    private Long userElimination;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;

    @ManyToOne
    @JoinColumn(name = "operador_id")
    private User operador;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPreviousLecture() {
        return previousLecture;
    }

    public void setPreviousLecture(Long previousLecture) {
        this.previousLecture = previousLecture;
    }

    public Long getNextLecture() {
        return nextLecture;
    }

    public void setNextLecture(Long nextLecture) {
        this.nextLecture = nextLecture;
    }

    public String getObservation() {
        return observation;
    }

    public void setObservation(String observation) {
        this.observation = observation;
    }

    public Long getTotalConsumption() {
        return totalConsumption;
    }

    public void setTotalConsumption(Long totalConsumption) {
        this.totalConsumption = totalConsumption;
    }

    public Date getLectureDate() {
        return lectureDate;
    }

    public void setLectureDate(Date lectureDate) {
        this.lectureDate = lectureDate;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public User getOperador() {
        return operador;
    }

    public void setOperador(User operador) {
        this.operador = operador;
    }

    public Date getCreateAt() {
        return CreateAt;
    }

    public void setCreateAt(Date createAt) {
        CreateAt = createAt;
    }

    public Long getUserCreated() {
        return userCreated;
    }

    public void setUserCreated(Long userCreated) {
        this.userCreated = userCreated;
    }

    public Date getDateModified() {
        return dateModified;
    }

    public void setDateModified(Date dateModified) {
        this.dateModified = dateModified;
    }

    public Long getUserModified() {
        return userModified;
    }

    public void setUserModified(Long userModified) {
        this.userModified = userModified;
    }

    public Date getDateElimination() {
        return dateElimination;
    }

    public void setDateElimination(Date dateElimination) {
        this.dateElimination = dateElimination;
    }

    public Long getUserElimination() {
        return userElimination;
    }

    public void setUserElimination(Long userElimination) {
        this.userElimination = userElimination;
    }
}
