package com.Job_Portal.dto;


import com.Job_Portal.entity.Job;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class JobDTO {
    private Long id;
    private String jobTitle;
    private String company;
    private List<ApplicantDTO> applicants;
    private String about;
    private String experience;
    private String jobType;
    private String location;
    private Long packageOffered;
    private LocalDateTime postTime;
    private String description;
    private List<String> skillsRequired;
    private JobStatus jobStatus;
    private Long postedBy;


    public Job toEntity() {
        return new Job(this.id, this.jobTitle, this.company, this.applicants != null ? this.applicants.stream().map(x->x.toEntity()).toList(): null, this.about,
                this.experience, this.jobType , this.location, this.packageOffered, this.postTime,
                this.description, this.skillsRequired, this.jobStatus, this.postedBy);

         }

}
