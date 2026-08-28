package com.Job_Portal.jwt;

import com.Job_Portal.dto.UserDto;
import com.Job_Portal.entity.User;
import com.Job_Portal.jobPortalException.JobPortalException;
import com.Job_Portal.repositry.UserRepository;
import com.Job_Portal.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class MyUserDetailService implements UserDetailsService {

    @Autowired
    private UserServices userServices;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        try {
            Optional<UserDto> dto = Optional.ofNullable(userServices.getUserByEmail(email));
            if (dto.isPresent()){

                UserDto userDto = dto.get();

                return new CustomUserDetails(userDto.getId(),userDto.getName(),userDto.getEmail(),userDto.getPassword(),userDto.getProfileId(), userDto.getAccountType() ,new ArrayList<>());



            }else {
                throw new UsernameNotFoundException("UserName is wrong.......");
            }



        } catch (JobPortalException e) {
            throw new RuntimeException(e);
        }


    }
}
