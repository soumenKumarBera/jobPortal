package com.Job_Portal.api;


import com.Job_Portal.jwt.AuthenticationRequest;
import com.Job_Portal.jwt.AuthenticationResponse;
import com.Job_Portal.jwt.JwtHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/auth")
public class AuthApi {



    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtHelper jwtHelper;


    @PostMapping("/login")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest request){

        String email = request.getEmail();
        String password = request.getPassword();

        UserDetails userDetails = authenticate(email, password);

        String token = jwtHelper.generateToken(userDetails);


        return new ResponseEntity<>(new AuthenticationResponse(token), HttpStatus.OK);


    }

    private UserDetails authenticate(String email, String password) {

        UsernamePasswordAuthenticationToken userPass = new UsernamePasswordAuthenticationToken(email, password);
        Authentication authentication = authenticationManager.authenticate(userPass);

        return  (UserDetails) authentication.getPrincipal();
    }


}
