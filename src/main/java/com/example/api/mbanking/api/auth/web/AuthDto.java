package com.example.api.mbanking.api.auth.web;

public record AuthDto(
        String tokenType,
        String accessToken
) {
}
