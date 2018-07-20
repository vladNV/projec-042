package com.example.app.controller;

import com.example.app.service.ExcelService;
import com.example.app.service.impl.StreamByteArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
public class ExportingController {

    @Autowired
    private ExcelService excelService;

    @GetMapping(value = Routes.EXPORT_TO_EXCEL)
    public ResponseEntity<Resource> downloadFile() throws IOException {
        StreamByteArray stream = excelService.saveDateToExcelAndDownloadIt();

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachement;filename=" + stream.getFilename())
                .contentLength(stream.getByteArrayResource().contentLength())
                .contentType(MediaType.parseMediaType("text/csv"))
                .body(stream.getByteArrayResource());
    }

}
