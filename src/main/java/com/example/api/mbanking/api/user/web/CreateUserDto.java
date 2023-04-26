package com.example.api.mbanking.api.user.web;

public record CreateUserDto(String name, String gender, String studentCardId, Boolean isStudent, String oneSignalId) {
}
