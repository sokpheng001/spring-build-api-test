package com.example.api.mbanking.api.file;

import lombok.Builder;

@Builder
public record FileDto(
        String name,
        String url,
        String extension,
        long size
) { };
