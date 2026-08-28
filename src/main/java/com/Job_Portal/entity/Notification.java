package com.Job_Portal.entity;

import com.Job_Portal.dto.NotificationDto;
import com.Job_Portal.dto.NotificationStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "notification")
public class Notification {
    private Long id;
    private Long userId;
    private String message;
    private String action;
    private String route;
    private NotificationStatus status;
private LocalDateTime timestamp;


    public NotificationDto toEntity(){

        return new NotificationDto(this.id, this.userId, this.message,this.action,this.route,this.status,this.timestamp);
    }

}
