package com.example.api.mbanking.api.auth.web;

import com.example.api.mbanking.api.user.validator.email.EmailUnique;
import com.example.api.mbanking.api.user.validator.password.PasswordIsStrong;
import com.example.api.mbanking.api.user.validator.password.PasswordMatched;
import com.example.api.mbanking.api.user.validator.role.RoleIdConstraint;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.util.List;

@Builder
@PasswordMatched(password = "password", confirmPassword = "confirmedPassword")
public record RegisterDto(
        @NotBlank(message = "Email is required.")
        @EmailUnique
                @Email
        String email,
        @NotBlank(message = "Password is required.")
                @PasswordIsStrong
        String password,
        @NotBlank(message = "Confirmed Password is required.")
                @PasswordIsStrong
        String confirmedPassword,
        @NotNull(message = "Roles are required.")
                @RoleIdConstraint
        List<Integer> roleIds

) {
}
