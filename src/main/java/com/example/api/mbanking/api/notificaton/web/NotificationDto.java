package com.example.api.mbanking.api.notificaton.web;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

@Builder
public record NotificationDto(
        @JsonProperty("included_segments")
        String [] includedSegments,
        ContentsDto contents,
        @JsonProperty("app_id")
        String appId
) {
}
