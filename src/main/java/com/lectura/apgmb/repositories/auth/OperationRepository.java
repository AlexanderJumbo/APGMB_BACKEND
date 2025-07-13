package com.lectura.apgmb.repositories.auth;

import com.lectura.apgmb.entities.secutiry.Operation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface OperationRepository extends JpaRepository<Operation, Long> {
    @Query("SELECT o FROM Operation o WHERE o.permitAll = true")
    List<Operation> findPublicEndpoints();

    Optional<Operation> findByName(String nameOperation);
}
