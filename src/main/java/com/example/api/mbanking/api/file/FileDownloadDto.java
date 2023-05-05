package com.example.api.mbanking.api.file;

import lombok.Builder;

@Builder
public record FileDownloadDto(
        String name,
        String url,
        String downloadUrl,
        String extension,
        long size
) { };
