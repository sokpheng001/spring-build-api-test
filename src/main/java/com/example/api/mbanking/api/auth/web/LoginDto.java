package com.example.api.mbanking.api.auth.web;

import com.example.api.mbanking.api.user.validator.password.PasswordIsStrong;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record LoginDto(
        @NotBlank
                @Email
        String email,
        @NotBlank

        String password) {
}
