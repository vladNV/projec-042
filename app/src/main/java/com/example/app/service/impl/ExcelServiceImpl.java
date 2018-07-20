package com.example.app.service.impl;

import com.example.app.model.BusinessTrip;
import com.example.app.service.ExcelService;
import com.example.app.service.TripService;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.IntStream;

@Service
public class ExcelServiceImpl implements ExcelService {
    private static final int COLUMNS_SIZE = 17;
    private static final String TEMP_UPLOADER_DIR = "temp/";
    private static final String FORMAT = ".xlsx";

    @Autowired
    private TripService tripService;

    @Override
    public StreamByteArray saveDateToExcelAndDownloadIt() throws IOException {
        List<BusinessTrip> businessTripList = tripService.retrieveAllBusinessTripsBySort();

        Workbook workbook = new XSSFWorkbook();

        Sheet sheet = workbook.createSheet("Business");

        Font headerFont = workbook.createFont();
        headerFont.setBold(true);
        headerFont.setFontHeightInPoints((short) 14);
        headerFont.setColor(IndexedColors.BLACK.getIndex());

        CellStyle cellStyle = workbook.createCellStyle();
        cellStyle.setFont(headerFont);

        createHeaderRow(sheet, cellStyle);

        IntStream.range(1, businessTripList.size() + 1).forEach(i -> setRow(businessTripList.get(i - 1), sheet.createRow(i)));
        IntStream.range(0, COLUMNS_SIZE).forEach(sheet::autoSizeColumn);

        String key = String.valueOf(System.currentTimeMillis());
        FileOutputStream fileOutputStream = new FileOutputStream(TEMP_UPLOADER_DIR + key + FORMAT);

        workbook.write(fileOutputStream);

        fileOutputStream.close();
        workbook.close();

        String filename = key + FORMAT;
        Path path = Paths.get(TEMP_UPLOADER_DIR + filename);
        ByteArrayResource resource = new ByteArrayResource(Files.readAllBytes(path));

        return StreamByteArray.builder().byteArrayResource(resource).filename(filename).build();
    }

    private static void setRow(BusinessTrip businessTrip, Row row) {
        row.createCell(0).setCellValue(businessTrip.getPrime());
        row.createCell(1).setCellValue(businessTrip.getEmployeeId());
        row.createCell(2).setCellValue(businessTrip.getName());
        row.createCell(3).setCellValue(businessTrip.getPhone());
        row.createCell(4).setCellValue(businessTrip.getQualification());
        row.createCell(5).setCellValue(businessTrip.getPassportNumber());
        row.createCell(6).setCellValue(businessTrip.getPosition().name());
        row.createCell(7).setCellValue(businessTrip.getDescription());
        row.createCell(8).setCellValue(businessTrip.getFrom().toString());
        row.createCell(9).setCellValue(businessTrip.getTill().toString());
        row.createCell(10).setCellValue(businessTrip.getRateNumber());
        row.createCell(11).setCellValue(businessTrip.getTicketPrice());
        row.createCell(12).setCellValue(businessTrip.getTransportType().name());
        row.createCell(13).setCellValue(businessTrip.getTransportTitle());
        row.createCell(14).setCellValue(businessTrip.getFacilityTitle());
        row.createCell(15).setCellValue(businessTrip.getFacilityDirection());
        row.createCell(16).setCellValue(businessTrip.getDepartureDate().toString());
    }

    private void createHeaderRow(Sheet sheet, CellStyle cellStyle) {
        Row headerRow = sheet.createRow(0);

        Cell cell0 = headerRow.createCell(0);
        Cell cell1 = headerRow.createCell(1);
        Cell cell2 = headerRow.createCell(2);
        Cell cell3 = headerRow.createCell(3);
        Cell cell4 = headerRow.createCell(4);
        Cell cell5 = headerRow.createCell(5);
        Cell cell6 = headerRow.createCell(6);
        Cell cell7 = headerRow.createCell(7);
        Cell cell8 = headerRow.createCell(8);
        Cell cell9 = headerRow.createCell(9);
        Cell cell10 = headerRow.createCell(10);
        Cell cell11 = headerRow.createCell(11);
        Cell cell12 = headerRow.createCell(12);
        Cell cell13 = headerRow.createCell(13);
        Cell cell14 = headerRow.createCell(14);
        Cell cell15 = headerRow.createCell(15);
        Cell cell16 = headerRow.createCell(16);

        cell0.setCellValue("Prime key");
        cell0.setCellStyle(cellStyle);

        cell1.setCellValue("Employee id");
        cell1.setCellStyle(cellStyle);

        cell2.setCellValue("Full name");
        cell2.setCellStyle(cellStyle);

        cell3.setCellValue("Phone");
        cell3.setCellStyle(cellStyle);

        cell4.setCellValue("Qualification");
        cell4.setCellStyle(cellStyle);

        cell5.setCellValue("Number of passport");
        cell5.setCellStyle(cellStyle);

        cell6.setCellValue("Position");
        cell6.setCellStyle(cellStyle);

        cell7.setCellValue("Description");
        cell7.setCellStyle(cellStyle);

        cell8.setCellValue("From");
        cell8.setCellStyle(cellStyle);

        cell9.setCellValue("Till");
        cell9.setCellStyle(cellStyle);

        cell10.setCellValue("Number of rate of bussiness trip");
        cell10.setCellStyle(cellStyle);

        cell11.setCellValue("Price");
        cell11.setCellStyle(cellStyle);

        cell12.setCellValue("Transport type");
        cell12.setCellStyle(cellStyle);

        cell13.setCellValue("Transport title");
        cell13.setCellStyle(cellStyle);

        cell14.setCellValue("Location name");
        cell14.setCellStyle(cellStyle);

        cell15.setCellValue("Direction");
        cell15.setCellStyle(cellStyle);

        cell16.setCellValue("Departure");
        cell16.setCellStyle(cellStyle);
    }
}
