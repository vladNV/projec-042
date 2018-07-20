package com.example.app.service.impl;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.core.io.ByteArrayResource;

@Getter
@Builder
public class StreamByteArray {
    private ByteArrayResource byteArrayResource;
    private String filename;
}
