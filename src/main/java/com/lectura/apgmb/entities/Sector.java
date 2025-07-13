package com.lectura.apgmb.entities;

import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

@Entity
public class Sector {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long sectorId;
    private String nameSector;
    private Boolean active;
    private Date CreateAt;
    private Long userCreated;
    private Date dateModified;
    private Long userModified;
    private Date dateElimination;
    private Long userElimination;
    //@OneToMany(mappedBy = "sector")
    //private List<Account> accounts;

    public Long getSectorId() {
        return sectorId;
    }

    public void setSectorId(Long sectorId) {
        this.sectorId = sectorId;
    }

    public String getNameSector() {
        return nameSector;
    }

    public void setNameSector(String nameSector) {
        this.nameSector = nameSector;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
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
