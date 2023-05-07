package com.example.api.mbanking.api.file;

import lombok.Builder;


@Builder

public record FileDownloadDto<T>(
        String name,
        String url,
        String downloadUrl,
        T data,
        String extension,
        long size
) { };
