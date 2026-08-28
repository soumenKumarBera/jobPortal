package com.Job_Portal.services;

import com.Job_Portal.dto.*;
import com.Job_Portal.entity.OTP;
import com.Job_Portal.entity.User;
import com.Job_Portal.jobPortalException.JobPortalException;
import com.Job_Portal.repositry.NotificationRepository;
import com.Job_Portal.repositry.OtpRepository;
import com.Job_Portal.repositry.UserRepository;
import com.Job_Portal.utility.Data;
import com.Job_Portal.utility.Utilities;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service(value = "userServices")
public class UserServiceImpl implements UserServices{

    @Autowired
    private UserRepository userRepository;

    @Autowired
     private PasswordEncoder passwordEncoder;

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private OtpRepository otpRepository;

    @Autowired
    private ProfileService profileService;

    @Autowired
    private NotificationService notificationService;



    @Override
    public UserDto register(UserDto userDto) throws Exception {

        Optional<User> optional = userRepository.findByEmail(userDto.getEmail());
        if (optional.isPresent()){
            throw  new JobPortalException("USER_FOUND");
        }


        userDto.setId(Utilities.getNextSequence("users"));


       Long profileId = profileService.createProfile(userDto.getEmail());


        User user = User.builder()
                .id(userDto.getId())
                .name(userDto.getName())
                .email(userDto.getEmail())
                .accountType( userDto.getAccountType())
                .password(passwordEncoder.encode(userDto.getPassword()))
                .profileId(profileId)
                .build();
        //user save in database
        User userSave = userRepository.save(user);

        //user return
        return UserDto.builder()
                .id(userSave.getId())
                .name(userSave.getName())
                .password(userSave.getPassword())
                .accountType(userSave.getAccountType())
                .email(userSave.getEmail())
                .build();
    }

    @Override
    public UserDto loginUser(LoginDto loginDto) throws JobPortalException {
        User user = userRepository.findByEmail(loginDto.getEmail()).orElseThrow(() -> new JobPortalException("USER_NOT-FOUND"));

        if (!passwordEncoder.matches( loginDto.getPassword(), user.getPassword())){
            throw  new JobPortalException("INVALID_CREDENTIALS");
        }

        return UserDto.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .password(user.getPassword())
                .accountType(user.getAccountType())
                .build();
    }


    @Override
    public boolean SendOtp(String email) throws Exception {
       User user =  userRepository.findByEmail(email).orElseThrow(() -> new JobPortalException("USER_NOT-FOUND"));

        MimeMessage mm = mailSender.createMimeMessage();
        MimeMessageHelper message = new MimeMessageHelper(mm, true);
        message.setTo(email);
        message.setSubject("Your OTP Code");
        String genOtp = Utilities.generateOtp();
        OTP otp = new OTP(email, genOtp, LocalDateTime.now());
        otpRepository.save(otp);
        message.setText(Data.getMessageBody(otp.getOtpCode(), user.getName()), true);

            mailSender.send(mm);






        return true;
    }

    @Override
    public boolean verifyOtp(String email, String otp) throws JobPortalException {
        OTP otpEntity = otpRepository.findById(email).orElseThrow(()-> new JobPortalException("OTP_NOT_FOUND"));
        if (!otpEntity.getOtpCode().equals(otp)){

            throw new JobPortalException("OTP_INCORRECT");

        }

        return true;
    }

    @Override
    public ResponseDto changePassword(LoginDto loginDto) throws Exception {
        User user =  userRepository.findByEmail(loginDto.getEmail()).orElseThrow(() -> new JobPortalException("USER_NOT-FOUND"));

        user.setPassword(passwordEncoder.encode(loginDto.getPassword()));
        userRepository.save(user);


        NotificationDto noti = new NotificationDto();
        noti.setUserId(user.getId());
        noti.setMessage("Password Reset Successfully");
        noti.setAction("PassWord Reset");
        notificationService.sendNotification(noti);

        return new ResponseDto("Password change Successfully...");
    }

    @Override
    public UserDto getUserByEmail(String email) throws JobPortalException {

      return   userRepository.findByEmail(email).orElseThrow(() -> new JobPortalException("USER_NOT-FOUND")).toDto();



    }

    @Scheduled(fixedRate = 3000)
    public void removeExpiredOTPs(){
        LocalDateTime expired = LocalDateTime.now().minusMinutes(5);
        List<OTP> otp = otpRepository.findByCreationTimeBefore(expired);

        if (!otp.isEmpty()){
            otpRepository.deleteAll(otp);
            System.out.println(expired);

        }


    }
}
