package com.example.api.mbanking.api.user.web;

import lombok.Builder;

import javax.swing.text.StyledEditorKit;

@Builder
public record UserDto(String name, String gender, String studentCardId, Boolean isStudent) {
}
