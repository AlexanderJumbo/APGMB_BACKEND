package com.lectura.apgmb.entities.secutiry;

import jakarta.persistence.*;

import java.util.Date;

@Entity
public class GrantedPermission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    //private String name;
    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;

    @ManyToOne
    @JoinColumn(name = "operation_id")
    private Operation operation;
    private Date CreateAt;
    private Long userCreated;
    private Date dateModified;
    private Long userModified;
    private Date dateElimination;
    private Long userElimination;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Operation getOperation() {
        return operation;
    }

    public void setOperation(Operation operation) {
        this.operation = operation;
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

    @Override
    public String toString() {
        return "GrantedPermission{" +
                "id=" + id +
                ", role=" + role +
                ", operation=" + operation +
                '}';
    }
}
