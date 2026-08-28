package com.Job_Portal.api;

import com.Job_Portal.dto.ResponseDto;
import com.Job_Portal.entity.Notification;
import com.Job_Portal.jobPortalException.JobPortalException;
import com.Job_Portal.services.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/notification")
public class NotificationApi {

    @Autowired
    private NotificationService notificationService;

    @GetMapping("/get/{userId}")
    public ResponseEntity<List<Notification>> getNotification(@PathVariable Long userId){

        return  new ResponseEntity<>(notificationService.grtUserIdNotification(userId), HttpStatus.OK);

    }

    @PutMapping("/read/{id}")
    public ResponseEntity<ResponseDto> readNotification(@PathVariable Long id) throws JobPortalException {

        notificationService.readNotification(id);

        return  new ResponseEntity<>(new ResponseDto("Success"), HttpStatus.OK);

    }
}
