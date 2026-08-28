package com.Job_Portal.repositry;

import com.Job_Portal.dto.NotificationStatus;
import com.Job_Portal.entity.Notification;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface NotificationRepository extends MongoRepository<Notification, Long> {

    public List<Notification> findByUserIdAndStatus(Long id, NotificationStatus status);
}
