package com.lectura.apgmb.controllers;

import com.lectura.apgmb.dtos.GeneratePDFMasivo;
import com.lectura.apgmb.dtos.lecture.LectureListResponse;
import com.lectura.apgmb.services.PdfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.Month;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

@RestController
@RequestMapping("/api/pdf")
public class PdfController {

    @Autowired
    private PdfService pdfService;

    @PostMapping("/export")
    public ResponseEntity<byte[]> exportPdf(@RequestBody List<GeneratePDFMasivo> generatePDFMasivo) {

        /*List<Map<String, Object>> details = List.of(
                Map.of("date", "2025-07-01", "amount", 120.5, "observation", "Lectura mensual"),
                Map.of("date", "2025-07-10", "amount", 100.0, "observation", "Ajuste de consumo"),
                Map.of("date", "2025-07-20", "amount", 130.3, "observation", "Lectura adicional")
        );*/

        List<LectureListResponse> lecturesList =  pdfService.getDataToPdf(generatePDFMasivo);

        LocalDate now = LocalDate.now();

        int currentMonth = LocalDate.now().getMonthValue();
        Month currentMonthName = LocalDate.now().getMonth();
        int currentYear = LocalDate.now().getYear();
        String issueDate = now.format(DateTimeFormatter.ofPattern("d 'de' MMMM 'de' yyyy", new Locale("es", "ES")));
        String lectureDate = now.format(DateTimeFormatter.ofPattern("MMM '-' yyyy", new Locale("es", "ES")));
        Map<String, Object> data = Map.of(
                "issueDate", issueDate,
                "lectureDate", lectureDate,
                "details", lecturesList
        );
        byte[] pdfBytes = pdfService.generatePdf("report", data);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=consumo.pdf")
                .contentType(MediaType.APPLICATION_PDF)
                .body(pdfBytes);
    }
}
