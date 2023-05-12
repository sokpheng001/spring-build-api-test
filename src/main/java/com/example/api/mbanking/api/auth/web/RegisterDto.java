package com.example.api.mbanking.api.auth.web;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

@Builder
public record RegisterDto(
        @NotBlank(message = "Email is required.")
                @Email
        String email,
        @NotBlank(message = "Password is required.")
        String password,
        @NotBlank(message = "Confirmed Password is required.")
        String confirmedPassword
) {
}
