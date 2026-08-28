package com.Job_Portal.services;

import com.Job_Portal.dto.ApplicantDTO;
import com.Job_Portal.dto.ApplicationDto;
import com.Job_Portal.dto.JobDTO;
import com.Job_Portal.dto.ProfileDTO;
import com.Job_Portal.jobPortalException.JobPortalException;

import java.util.List;

public interface JobServices {
   public JobDTO postJob( JobDTO jobDTO) throws Exception;

  public  List<JobDTO> getAllJobs();

  public JobDTO getJob(Long id) throws JobPortalException;

  void applyJob(Long id, ApplicantDTO applicantDTO) throws JobPortalException;

   public List<JobDTO>  getJobsPostedBY(Long id);

   public void changeAppStatus(ApplicationDto applicationDto) throws JobPortalException;


}
