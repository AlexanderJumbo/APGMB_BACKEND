package com.lectura.apgmb.controllers;

import com.lectura.apgmb.dtos.account.AccountListResponse;
import com.lectura.apgmb.dtos.account.AccountRequestUpdate;
import com.lectura.apgmb.dtos.account.AccountResponse;
import com.lectura.apgmb.dtos.lecture.LectureListResponse;
import com.lectura.apgmb.dtos.lecture.LectureRequest;
import com.lectura.apgmb.dtos.lecture.LectureResponse;
import com.lectura.apgmb.dtos.lecture.LectureUpdate;
import com.lectura.apgmb.services.MeterReadingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/lecture")
public class MeterReadingController {

    @Autowired
    private MeterReadingService meterReadingService;

    @PostMapping
    public ResponseEntity<LectureResponse> registerLecture(@RequestBody LectureRequest lectureRequest){
        return ResponseEntity.status(HttpStatus.CREATED).body(meterReadingService.registerLecture(lectureRequest));
    }

    @GetMapping("/all")
    public ResponseEntity<List<LectureListResponse>> getAllLectures() {
        return ResponseEntity.status(HttpStatus.OK).body(meterReadingService.getAllLecture());
    }

    @PutMapping("/update")
    public ResponseEntity<LectureResponse> updateLecture(@RequestBody LectureUpdate lectureUpdate) {
        return ResponseEntity.status(HttpStatus.OK).body(meterReadingService.updateLecture(lectureUpdate));
    }

    @GetMapping("/all/v2")
    public Page<LectureListResponse> getAllLecturesWithFilters(
            @RequestParam(value = "p", defaultValue =  "0" , required = false) int pageNumber,
            @RequestParam(value = "n", defaultValue = "20", required = false) int numberRecordsPerPage,
            @RequestParam(value = "f", defaultValue = "id", required = false) String orderRecordsByField,
            @RequestParam(value = "d", defaultValue = "asc", required = false) String orderRecordsByDirection,
            @RequestParam(value = "startDate", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam(value = "endDate", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate,
            @RequestParam(value = "user", required = false) String accountUser
    ){
        return meterReadingService.getAllLecturesWithFilters(pageNumber, numberRecordsPerPage, orderRecordsByField, orderRecordsByDirection, startDate, endDate, accountUser);
    }

    @GetMapping("/lastByAccount/{accountId}")
    public ResponseEntity<Boolean> getLastLectureByAccount(@PathVariable Long accountId) {
        return ResponseEntity.status(HttpStatus.OK).body(meterReadingService.existCurrentLecture(accountId));
    }
}
