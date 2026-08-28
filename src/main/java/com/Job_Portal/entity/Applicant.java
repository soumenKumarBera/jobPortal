package com.Job_Portal.entity;


import com.Job_Portal.dto.ApplicantDTO;
import com.Job_Portal.dto.ApplicationStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Base64;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Applicant {
    private Long applicantId;
    private String name;
    private String email;
    private Long phone;
    private String website;
    private byte[] resume;
    private String coverLetter;
    private LocalDateTime timestamp;
    private ApplicationStatus applicationStatus;
    private LocalDateTime interviewTime;

    public ApplicantDTO toDto(){
        return new ApplicantDTO(this.applicantId, this.name, this.email, this.phone, this.website, this.resume !=null ? Base64.getEncoder().encodeToString(this.resume) : null,this.coverLetter,this.timestamp, this.applicationStatus, this.interviewTime);
    }


}
