package com.Job_Portal.api;

import com.Job_Portal.dto.LoginDto;
import com.Job_Portal.dto.ResponseDto;
import com.Job_Portal.dto.UserDto;
import com.Job_Portal.entity.User;
import com.Job_Portal.jobPortalException.JobPortalException;
import com.Job_Portal.repositry.UserRepository;
import com.Job_Portal.services.UserServiceImpl;
import com.Job_Portal.services.UserServices;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@RestController
@CrossOrigin
@RequestMapping("/users")
public class UserApi {

    @Autowired
    private UserServices userServices;

    @PostMapping("/register")
    public ResponseEntity<UserDto> registerUser(@RequestBody @Valid UserDto userDto) throws Exception {

     UserDto dto = userServices.register(userDto);

        return new ResponseEntity<>(dto, HttpStatus.CREATED);

    }

    @PostMapping("/login")
    public ResponseEntity<UserDto> loginUser(@RequestBody @Valid LoginDto loginDto) throws Exception {

        return new ResponseEntity<>(userServices.loginUser(loginDto), HttpStatus.OK);

    }

    @PostMapping("/sendOtp/{email}")
    public ResponseEntity<ResponseDto> senOtp(@PathVariable @Email(message = "Email is invalid.") String email) throws Exception {

        userServices.SendOtp(email);

        return new ResponseEntity<>(new ResponseDto("OTP sent Successfully"), HttpStatus.OK);

    }

    @GetMapping("/verifyOtp/{email}/{otp}")
    public ResponseEntity<ResponseDto> verifyOtp(@PathVariable String email, @PathVariable @Pattern(regexp = "^[0-9]{6}$", message = "OTP is invalid") String otp) throws JobPortalException {

        userServices.verifyOtp(email, otp);

        return new ResponseEntity<>(new ResponseDto("OTP is Verified"), HttpStatus.OK);

    }

    @PostMapping("/changePass")
    public ResponseEntity<ResponseDto> changePassword(@RequestBody @Valid LoginDto loginDto) throws Exception {

        return new ResponseEntity<>(userServices.changePassword(loginDto), HttpStatus.OK);

    }

}
