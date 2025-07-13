package com.lectura.apgmb.services;

import com.lectura.apgmb.dtos.DataPDFMasivo;
import com.lectura.apgmb.dtos.GeneratePDFMasivo;
import com.lectura.apgmb.dtos.lecture.LectureListResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xhtmlrenderer.pdf.ITextRenderer;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class PdfService {

    @Autowired
    private MeterReadingService lectureService;
    private final SpringTemplateEngine templateEngine;

    public PdfService(SpringTemplateEngine templateEngine) {
        this.templateEngine = templateEngine;
    }


    public List<LectureListResponse> getDataToPdf(List<GeneratePDFMasivo> generatePDFMasivo){
        List<LectureListResponse> lecturesList = lectureService.getAllLecture();
        Set<Long> ids = generatePDFMasivo.stream()
                .map(GeneratePDFMasivo::getIdLecture)
                .collect(Collectors.toSet());
        return lecturesList.stream().filter(x ->  ids.contains(x.getIdLecture())).collect(Collectors.toList());
    }


    public byte[] generatePdf(String templateName, Map<String, Object> data) {
        Context context = new Context();
        context.setVariables(data);

        String html = templateEngine.process(templateName, context);

        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
            ITextRenderer renderer = new ITextRenderer();
            renderer.setDocumentFromString(html);
            renderer.layout();
            renderer.createPDF(outputStream);
            return outputStream.toByteArray();
        } catch (Exception e) {
            throw new RuntimeException("Error al generar PDF", e);
        }
    }
}
