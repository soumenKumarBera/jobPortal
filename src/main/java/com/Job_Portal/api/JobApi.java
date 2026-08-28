package com.Job_Portal.api;


import com.Job_Portal.dto.*;
import com.Job_Portal.jobPortalException.JobPortalException;
import com.Job_Portal.services.JobServices;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@RestController
@CrossOrigin
@RequestMapping("/job")
public class JobApi {

    @Autowired
    private JobServices jobServices;

    @PostMapping("/post")
    public ResponseEntity<JobDTO> postJob(@RequestBody @Valid JobDTO jobDTO) throws Exception {

        return new ResponseEntity<>(jobServices.postJob(jobDTO), HttpStatus.CREATED);

    }

    @GetMapping("/getAll")
    public ResponseEntity<List<JobDTO>> getAllJobs() throws JobPortalException {


        return new ResponseEntity<>(jobServices.getAllJobs(), HttpStatus.OK);

    }

    @GetMapping("/get/{id}")
    public ResponseEntity<JobDTO> getJob(@PathVariable Long id) throws JobPortalException {


        return new ResponseEntity<>(jobServices.getJob(id), HttpStatus.OK);

    }

    @PostMapping("/apply/{id}")
    public ResponseEntity<ResponseDto> applyJob(@PathVariable Long id, @RequestBody ApplicantDTO applicantDTO) throws JobPortalException {
        jobServices.applyJob(id, applicantDTO);

        return new ResponseEntity<>(new ResponseDto("Apply Successfully"), HttpStatus.OK);

    }

    @GetMapping("/postedBy/{id}")
    public ResponseEntity<List<JobDTO>> getJobPostedBy(@PathVariable Long id) throws JobPortalException {


        return new ResponseEntity<>(jobServices.getJobsPostedBY(id), HttpStatus.OK);

    }

    @PostMapping("/changeApplicationStatus")
    public ResponseEntity<ResponseDto> changeApplicationStatus(@RequestBody ApplicationDto applicationDto) throws JobPortalException {

        jobServices.changeAppStatus(applicationDto);

        return new ResponseEntity<>(new ResponseDto("Application Status Change Successfully."), HttpStatus.OK);

    }



}
