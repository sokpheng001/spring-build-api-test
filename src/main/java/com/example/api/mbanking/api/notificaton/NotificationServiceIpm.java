package com.example.api.mbanking.api.notificaton;

import com.example.api.mbanking.api.notificaton.web.CreateNotificationDto;
import com.example.api.mbanking.api.notificaton.web.NotificationDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
public class NotificationServiceIpm implements NotificationService {
    private RestTemplate restTemplate;
    @Value("${oneSignal.app-id}")
    private String appId;
    @Value("${oneSignal.rest-api-key}")
    private String restApiKey;
    @Autowired
    public void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
    @Override
    public boolean pushNotification(CreateNotificationDto createNotificationDto){
        HttpHeaders headers = new HttpHeaders();
        headers.set("accept", "application/json");
        headers.set("Authorization", "Basic " + restApiKey);
        headers.set("content-type", "application/json");
        NotificationDto notificationDto = NotificationDto.builder()
                .contents(createNotificationDto.contents())
                .appId(appId)
                .includedSegments(createNotificationDto.includedSegments()).build();
        HttpEntity<NotificationDto> httpEntity = new HttpEntity<>(notificationDto, headers);
        ResponseEntity<?> responseEntity = restTemplate.postForEntity(
                "https://onesignal.com/api/v1/notifications"
                ,httpEntity
                ,Map.class
        );
        System.out.println(responseEntity.getStatusCode());
        System.out.println(responseEntity.getBody());
        return (responseEntity.getStatusCode()== HttpStatus.OK);
    }
    //
//    {
//        //        OkHttpClient client = new OkHttpClient();
    //
////        MediaType mediaType = MediaType.parse("application/json");
////        RequestBody body = RequestBody.create(mediaType, "{\"included_segments\":[\"Subscribed Users\"],\"contents\":{\"en\":\"English or Any Language Message\",\"es\":\"Spanish Message\"},\"name\":\"INTERNAL_CAMPAIGN_NAME\"}");
////        Request request = new Request.Builder()
////                .url("https://onesignal.com/api/v1/notifications")
////                .post(body)
////                .addHeader("accept", "application/json")
////                .addHeader("Authorization", "Basic YOUR_REST_API_KEY")
////                .addHeader("content-type", "application/json")
////                .build();
////        Response response = client.newCall(request).execute();
//    }
}
