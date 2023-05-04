package com.example.api.mbanking.api.useraccount.web;

import jakarta.validation.constraints.Null;
import lombok.Builder;

@Builder
public record UpdateUserAccountIsDisabledDto(boolean isDisabled) {
}
