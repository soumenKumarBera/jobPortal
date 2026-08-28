package com.Job_Portal.services;

import com.Job_Portal.dto.ProfileDTO;
import com.Job_Portal.entity.Profile;
import com.Job_Portal.jobPortalException.JobPortalException;
import com.Job_Portal.repositry.ProfileRepository;
import com.Job_Portal.utility.Utilities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("profileService")
public class ProfileServiceIMPL implements ProfileService{

    @Autowired
    private ProfileRepository profileRepository;


    @Override
    public Long createProfile(String email) throws Exception {

        Profile profile = new Profile();

        profile.setId(Utilities.getNextSequence("profiles"));
        profile.setEmail(email);
        profile.setSkills(new ArrayList<>());
        profile.setExperiences(new ArrayList<>());
        profile.setExperiences(new ArrayList<>());

        profileRepository.save(profile);

        return profile.getId();
    }

    @Override
    public ProfileDTO getProfile(Long id) throws JobPortalException {
        return profileRepository.findById(id).orElseThrow(()-> new JobPortalException("PROFILE_NOT_FOUND")).toDto();
    }

    @Override
    public ProfileDTO updateProfile(ProfileDTO profileDTO) throws JobPortalException {

        profileRepository.findById(profileDTO.getId()).orElseThrow(()-> new JobPortalException("PROFILE_NOT_FOUND"));
        profileRepository.save(profileDTO.toEntity());


        return profileDTO;
    }

    @Override
    public List<ProfileDTO> getAllProfile() {
        return profileRepository.findAll().stream().map((x) -> x.toDto()).toList();
    }
}
