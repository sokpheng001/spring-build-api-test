package com.example.api.mbanking.api.user.web;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateUserDto(
        @NotBlank(message = "Name is required.")
        String name
        , @NotBlank  String gender
        , String studentCardId
        , @NotNull Boolean isStudent
        , String oneSignalId) {
}
