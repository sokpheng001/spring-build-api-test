package com.example.api.mbanking.api.useraccount.web;

import lombok.Builder;

@Builder
public record UpdateUserAccountIsDisabledDto(boolean isDisabled) {
}
