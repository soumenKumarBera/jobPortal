package com.Job_Portal.jwt;

import com.Job_Portal.jobPortalException.JobPortalException;
import org.springframework.stereotype.Component;


import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {


    @Autowired
    private JwtHelper jwtHelper;

    @Autowired
    private MyUserDetailService myUserDetailService;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String token = request.getHeader("Authorization");


        if(token != null && token.startsWith("Bearer")){
            token = token.substring(7);
            try{
                boolean expired = jwtHelper.isExpired(token);

                if (!expired){

                    String username = jwtHelper.getUsernameFromToken(token);

                    if(username != null && SecurityContextHolder.getContext().getAuthentication() == null ){

                         UserDetails userDetails =  myUserDetailService.loadUserByUsername(username);

                             UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, token, userDetails.getAuthorities());

                             SecurityContextHolder.getContext().setAuthentication(authenticationToken);


                    }else {
                        throw new JobPortalException("USER_NOT-FOUND");
                    }


                }else {
                    throw new JobPortalException("TOKEN_IS_EXPIRED");
                }






            }catch (IllegalArgumentException e){
                e.printStackTrace();

            }catch (ExpiredJwtException e){
                e.printStackTrace();
            }catch (MalformedJwtException e){
                e.printStackTrace();
            }catch (Exception e){
                e.printStackTrace();
            }

        }
//        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null){
//            UserDetails userDetails = userDetailsService.loadUserByUsername(username);
//            Boolean validateToken = this.jwtHelper.validateToken(token,userDetails.getUsername());
//            if(validateToken) {
//                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null,userDetails.getAuthorities());
//                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
//                SecurityContextHolder.getContext().setAuthentication(authentication);
//            }
//        }
        filterChain.doFilter(request, response);

    }
}
