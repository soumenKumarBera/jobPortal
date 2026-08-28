package com.Job_Portal.services;

import com.Job_Portal.dto.ProfileDTO;
import com.Job_Portal.jobPortalException.JobPortalException;

import java.util.List;

public interface ProfileService {
    public Long createProfile(String email) throws Exception;
    public ProfileDTO getProfile(Long id) throws JobPortalException;
    public ProfileDTO updateProfile(ProfileDTO profileDTO) throws JobPortalException;

   public List<ProfileDTO> getAllProfile();
}
