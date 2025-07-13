package com.lectura.apgmb.repositories;

import com.lectura.apgmb.entities.MeterReading;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

public interface MeterReadingRepository extends JpaRepository<MeterReading, Long> {
    @Query("""
    SELECT m FROM MeterReading m
    WHERE LOWER(m.account.user.name) LIKE LOWER(CONCAT('%', :user, '%'))
       OR LOWER(m.account.user.lastName) LIKE LOWER(CONCAT('%', :user, '%'))
       OR LOWER(CONCAT(m.account.user.name, ' ', m.account.user.lastName)) LIKE LOWER(CONCAT('%', :user, '%'))
       OR LOWER(CONCAT(m.account.user.lastName, ' ', m.account.user.name)) LIKE LOWER(CONCAT('%', :user, '%'))
""")
    Page<MeterReading> findLectureByUser(@Param("user") String user, Pageable pageable);
    Page<MeterReading> findByLectureDateBetween(LocalDateTime startDate, LocalDateTime endDate, Pageable pageable);

    @Query("""
    SELECT m FROM MeterReading m
    WHERE m.lectureDate BETWEEN :startDate AND :endDate
      AND (
           LOWER(m.account.user.name) LIKE LOWER(CONCAT('%', :user, '%')) OR
           LOWER(m.account.user.lastName) LIKE LOWER(CONCAT('%', :user, '%')) OR
           LOWER(CONCAT(m.account.user.name, ' ', m.account.user.lastName)) LIKE LOWER(CONCAT('%', :user, '%')) OR
           LOWER(CONCAT(m.account.user.lastName, ' ', m.account.user.name)) LIKE LOWER(CONCAT('%', :user, '%'))
      )
""")
    Page<MeterReading> findByLectureDateBetweenAndUser(
            @Param("startDate") LocalDateTime startDate,
            @Param("endDate") LocalDateTime endDate,
            @Param("user") String user,
            Pageable pageable);

    // Los nombres de campos y tabla van acorde a la tabla de BD, esto por usar native QUERY
    @Query(value = "SELECT * FROM meter_reading m WHERE account_id = :accountId ORDER BY lecture_date DESC LIMIT 1", nativeQuery = true)
    Optional<MeterReading> getLastLecture(@Param("accountId") Long accountId);
}
