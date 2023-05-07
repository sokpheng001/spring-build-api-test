package com.example.api.mbanking.api.file;

import lombok.Builder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.RequestEntity;

import java.net.URI;
import java.net.URL;

@Builder

public record FileDownloadDto<T>(
        String name,
        String url,
        String downloadUrl,
        T data,
        String extension,
        long size
) { };
