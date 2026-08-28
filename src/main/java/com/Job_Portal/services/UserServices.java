package com.Job_Portal.services;

import com.Job_Portal.dto.LoginDto;
import com.Job_Portal.dto.ResponseDto;
import com.Job_Portal.dto.UserDto;
import com.Job_Portal.jobPortalException.JobPortalException;
import jakarta.mail.MessagingException;
import jakarta.validation.Valid;

public interface UserServices {
    public UserDto register(UserDto userDto) throws Exception;

    UserDto loginUser( LoginDto loginDto) throws JobPortalException;

    boolean SendOtp(String email) throws Exception;

    boolean verifyOtp(String email, String otp) throws JobPortalException;

    ResponseDto changePassword( LoginDto loginDto) throws Exception;

   public UserDto getUserByEmail(String email) throws JobPortalException;
}
