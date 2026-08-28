package com.Job_Portal.entity;



import com.Job_Portal.dto.JobDTO;
import com.Job_Portal.dto.JobStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Document(collection = "jobs")
public class Job {
    @Id
    private Long id;
    private String jobTitle;
    private String company;
    private List<Applicant> applicants;
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

    public JobDTO toDto() {
        return new JobDTO(this.id, this.jobTitle, this.company,this.applicants != null ? this.applicants.stream().map(x->x.toDto()).toList(): null, this.about,
                this.experience, this.jobType , this.location, this.packageOffered, this.postTime,
                this.description, this.skillsRequired, this.jobStatus, this.postedBy);

    }
}
