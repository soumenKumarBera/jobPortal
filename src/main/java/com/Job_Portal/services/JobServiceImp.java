package com.Job_Portal.services;

import com.Job_Portal.dto.*;
import com.Job_Portal.entity.Applicant;
import com.Job_Portal.entity.Job;
import com.Job_Portal.entity.Notification;
import com.Job_Portal.jobPortalException.JobPortalException;
import com.Job_Portal.repositry.JobRepository;
import com.Job_Portal.utility.Utilities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service("jobService")
public class JobServiceImp implements  JobServices{

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private NotificationService notificationService;


    @Override
    public JobDTO postJob(JobDTO jobDTO) throws Exception {
        System.out.println(jobDTO.getId());

        if(jobDTO.getId() == 0){
            jobDTO.setId(Utilities.getNextSequence("jobs"));
            jobDTO.setPostTime(LocalDateTime.now());

            NotificationDto notificationDto = new NotificationDto();
            notificationDto.setAction("Job Posted");
            notificationDto.setMessage("Job Posted Successfully for " + jobDTO.getJobTitle());
            notificationDto.setUserId(jobDTO.getPostedBy());
            notificationDto.setRoute("/jobs/" + jobDTO.getId());
            try {
                notificationService.sendNotification(notificationDto);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

        }else {
            Job job = jobRepository.findById(jobDTO.getId()).orElseThrow(()->new JobPortalException("JOB_NOT_FOUND"));
            if(job.getJobStatus().equals(JobStatus.DRAFT) || job.getJobStatus().equals(JobStatus.CLOSED)){
                jobDTO.setPostTime(LocalDateTime.now());

            }
        }


        return jobRepository.save(jobDTO.toEntity()).toDto();
    }

    @Override
    public List<JobDTO> getAllJobs() {


        return jobRepository.findAll().stream().map(x -> x.toDto()).toList();
    }

    @Override
    public JobDTO getJob(Long id) throws JobPortalException {

        return jobRepository.findById(id).orElseThrow(() -> new JobPortalException("JOB_NOT_FOUND")).toDto();

    }

    @Override
    public void applyJob(Long id, ApplicantDTO applicantDTO) throws JobPortalException {
        Job job = jobRepository.findById(id).orElseThrow(()->new JobPortalException("JOB_NOT_FOUND"));

        List<Applicant> applicants = job.getApplicants();

        if(applicants == null) applicants = new ArrayList<>();

        if(applicants.stream().filter(x -> x.getApplicantId() == applicantDTO.getApplicantId()).toList().size() > 0) throw  new JobPortalException("JOB_APPLIED_ALREADY");

        applicantDTO.setApplicationStatus(ApplicationStatus.APPLIED);


        applicants.add(applicantDTO.toEntity());

        job.setApplicants(applicants);
        jobRepository.save(job);



    }

    @Override
    public List<JobDTO> getJobsPostedBY(Long id) {


        return jobRepository.findByPostedBy(id).stream().map((x)->x.toDto()).toList();
    }

    @Override
    public void changeAppStatus(ApplicationDto applicationDto) throws JobPortalException {

        Job job = jobRepository.findById(applicationDto.getId()).orElseThrow(()->new JobPortalException("JOB_NOT_FOUND"));

        List<Applicant> applicants = job.getApplicants().stream().map((x) -> {
            if (applicationDto.getApplicantId() == x.getApplicantId()) {
                x.setApplicationStatus(applicationDto.getApplicationStatus());

                if(applicationDto.getApplicationStatus().equals(ApplicationStatus.INTERVIEWING)){
                    x.setInterviewTime(applicationDto.getInterviewTime());
                    NotificationDto notificationDto = new NotificationDto();
                    notificationDto.setAction("Interview Scheduled");
                    notificationDto.setMessage("Interview Scheduled for job id: " + applicationDto.getId());
                    notificationDto.setUserId(applicationDto.getApplicantId());
                    notificationDto.setRoute("/job-history");
                    try {
                        notificationService.sendNotification(notificationDto);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }



            }

            return x;


        }  ).toList();

        job.setApplicants(applicants);

        jobRepository.save(job);



    }


}
