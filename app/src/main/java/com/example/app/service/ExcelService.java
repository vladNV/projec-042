package com.example.app.service;

import com.example.app.service.impl.StreamByteArray;

import java.io.IOException;

public interface ExcelService {

    StreamByteArray saveDateToExcelAndDownloadIt() throws IOException;

}
