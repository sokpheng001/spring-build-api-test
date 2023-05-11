package com.example.api.mbanking.api.notificaton.web;

import com.fasterxml.jackson.annotation.JsonProperty;

public record CreateNotificationDto(
        @JsonProperty("included_segments")
        String [] includedSegments,
        ContentsDto contents
) {
}
