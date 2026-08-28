package com.Job_Portal.services;

import com.Job_Portal.dto.NotificationDto;
import com.Job_Portal.dto.NotificationStatus;
import com.Job_Portal.entity.Notification;
import com.Job_Portal.jobPortalException.JobPortalException;
import com.Job_Portal.repositry.NotificationRepository;
import com.Job_Portal.utility.Utilities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service("notificationService")
public class NotificationServiceImpl implements NotificationService{

    @Autowired
    private NotificationRepository notificationRepository;


    @Override
    public void sendNotification(NotificationDto notificationDto) throws Exception {
      notificationDto.setId(Utilities.getNextSequence("notification"));
      notificationDto.setTimestamp(LocalDateTime.now());
      notificationDto.setStatus(NotificationStatus.UNREAD);
      notificationRepository.save(notificationDto.toEntity());


    }

    @Override
    public List<Notification> grtUserIdNotification(Long userId) {


        return notificationRepository.findByUserIdAndStatus(userId, NotificationStatus.UNREAD);
    }

    @Override
    public void readNotification(Long id) throws JobPortalException {

        Notification noti = notificationRepository.findById(id).orElseThrow(()-> new JobPortalException("NO_NOTIFICATION_FOUND"));

        noti.setStatus(NotificationStatus.READ);
        notificationRepository.save(noti);




    }
}
