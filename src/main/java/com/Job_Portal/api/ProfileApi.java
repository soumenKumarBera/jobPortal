package com.Job_Portal.api;

import com.Job_Portal.dto.ProfileDTO;
import com.Job_Portal.dto.ResponseDto;
import com.Job_Portal.jobPortalException.JobPortalException;
import com.Job_Portal.services.ProfileService;
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
@RequestMapping("/profiles")
public class ProfileApi {

    @Autowired
    private ProfileService profileService;


    @GetMapping("/get/{id}")
    public ResponseEntity<ProfileDTO> getProfile(@PathVariable Long id) throws JobPortalException {

        return new ResponseEntity<>(profileService.getProfile(id), HttpStatus.OK);

    }

    @PutMapping("/update")
    public ResponseEntity<ProfileDTO> UpdateProfile(@RequestBody ProfileDTO profileDTO) throws JobPortalException {

        return new ResponseEntity<>(profileService.updateProfile(profileDTO), HttpStatus.OK);

    }
    @GetMapping("/getAllProfile")
    public ResponseEntity<List<ProfileDTO>> getAllProfile() throws JobPortalException {


        return new ResponseEntity<>(profileService.getAllProfile(), HttpStatus.OK);

    }


}
