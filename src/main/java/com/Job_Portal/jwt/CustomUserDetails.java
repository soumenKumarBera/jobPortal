package com.Job_Portal.jwt;

import com.Job_Portal.dto.AccountType;
import com.Job_Portal.dto.UserDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.jspecify.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;

@Data
@AllArgsConstructor

public class CustomUserDetails implements UserDetails {


    private static final long serialVersionUID = 1L;
    private Long id;
    private String name;
    private String username;
    private String password;
    private Long profileId;
    private AccountType accountType;
    private Collection<?extends GrantedAuthority> authorities;


//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        HashSet<GrantedAuthority> set = new HashSet<>();
//        set.add(new SimpleGrantedAuthority(userDto.getAccountType().name()));
//        return set;
//
//
//
//
//    }
//
//    @Override
//    public @Nullable String getPassword() {
//        return userDto.getPassword();
//    }
//
//    @Override
//    public String getUsername() {
//        return userDto.getEmail();
//    }




}
