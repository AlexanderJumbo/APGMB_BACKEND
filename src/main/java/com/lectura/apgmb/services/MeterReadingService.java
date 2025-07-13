package com.lectura.apgmb.services;

import com.lectura.apgmb.dtos.lecture.LectureListResponse;
import com.lectura.apgmb.dtos.lecture.LectureRequest;
import com.lectura.apgmb.dtos.lecture.LectureResponse;
import com.lectura.apgmb.dtos.lecture.LectureUpdate;
import org.springframework.data.domain.Page;

import java.time.LocalDate;
import java.util.List;

public interface MeterReadingService {

    LectureResponse registerLecture(LectureRequest lectureRequest);
    List<LectureListResponse> getAllLecture();
    LectureResponse updateLecture(LectureUpdate lectureUpdate);
    Page<LectureListResponse> getAllLecturesWithFilters(int pageNumber, int numberRecordsPerPage, String orderRecordsByField, String orderRecordsByDirection, LocalDate startDate, LocalDate endDate, String user);
    boolean existCurrentLecture(Long accountId);
}
