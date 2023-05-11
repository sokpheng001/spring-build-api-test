package com.example.api.mbanking.api.notificaton;

import com.example.api.mbanking.api.notificaton.web.CreateNotificationDto;
import com.example.api.mbanking.api.notificaton.web.NotificationDto;

public interface NotificationService {
    boolean pushNotification(CreateNotificationDto createNotificationDto);
}
