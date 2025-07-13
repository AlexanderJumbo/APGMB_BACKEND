package com.lectura.apgmb.repositories;

import com.lectura.apgmb.dtos.account.AccountUserResponse;
import com.lectura.apgmb.dtos.account.LectureDataPrev;
import com.lectura.apgmb.entities.Account;
import com.lectura.apgmb.entities.secutiry.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long> {

    // Para obtener los datos de cuenta mediante el ID del medidor
    Optional<Account> findByWaterMeter_MeterId(Long meterId);

    // Para obtener los datox del usuario mediante el ID del medidor
    @Query("SELECT a.user FROM Account a WHERE a.waterMeter.meterId = :meterId")
    Optional<User> findUserByMeterId(@Param("meterId") Long meterId);
    @Query("SELECT a FROM Account a WHERE a.user.id = :userId")
    Optional<Account> findAccountByUserId(@Param("userId") Long userId);

    @Query(value = "SELECT new com.lectura.apgmb.dtos.account.LectureDataPrev(" +
            "a.accountId, u.id, u.name, u.lastName, wm.meterId, m.nextLecture) " +
            "FROM WaterMeter wm " +
            "JOIN wm.account a " +
            "JOIN a.user u " +
            "LEFT JOIN MeterReading m ON m.account = a AND m.lectureDate BETWEEN :startDate AND :endDate " +
            "WHERE wm.meterId = :meterId " +
            "ORDER BY m.lectureDate DESC",
            nativeQuery = false)
    Optional<LectureDataPrev> findAccountBywaterMeter(@Param("meterId") Long meterId,
                                                      @Param("startDate") Date startDate,
                                                      @Param("endDate") Date endDate);
}
