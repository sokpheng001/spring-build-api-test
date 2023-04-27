package com.example.api.mbanking.api.user.web;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.swing.text.StyledEditorKit;

@Builder
public record UserDto(String name, String gender, String studentCardId, Boolean isStudent) {
}
