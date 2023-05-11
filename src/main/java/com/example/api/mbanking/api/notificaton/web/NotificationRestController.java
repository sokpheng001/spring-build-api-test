package com.example.api.mbanking.api.notificaton.web;

import com.example.api.mbanking.api.notificaton.NotificationService;
import com.example.api.mbanking.base.BaseRest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/v1/notifications")
@RequiredArgsConstructor
public class NotificationRestController {
    private final NotificationService notificationService;
    @PostMapping
    public BaseRest<?> pushNotification(@RequestBody CreateNotificationDto createNotificationDto){
        return BaseRest.builder()
                .code(HttpStatus.OK.value())
                .status(notificationService.pushNotification(createNotificationDto))
                .timestamp(LocalDateTime.now())
                .message("Notification has been sent.")
                .data(createNotificationDto.contents())
                .build();
    }
}
