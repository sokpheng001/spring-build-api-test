package com.example.api.mbanking.api.auth.web;

import com.example.api.mbanking.api.user.validator.EmailUnique;
import com.example.api.mbanking.api.user.validator.RoleIdConstraint;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.util.List;

@Builder
public record RegisterDto(
        @NotBlank(message = "Email is required.")
        @EmailUnique
                @Email
        String email,
        @NotBlank(message = "Password is required.")
        String password,
        @NotBlank(message = "Confirmed Password is required.")
        String confirmedPassword,
        @NotNull(message = "Roles are required.")
                @RoleIdConstraint
        List<Integer> roleIds
) {
}
