package com.exam.controller;

import com.exam.configuration.JwtUtil;
import com.exam.impl.UserDetailsServiceImpl;
import com.exam.models.JwtRequest;
import com.exam.models.JwtResponse;
import com.exam.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@CrossOrigin("*")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsServiceImpl userDetailsServiceImpl;

    @Autowired
    private JwtUtil jwtUtil;

//    jwt authenticate end-point
    @PostMapping("/generate_token")
    public ResponseEntity<?> generateToken(@RequestBody JwtRequest jwtRequest) throws Exception {
        try{
            authenticate(jwtRequest.getUsername(), jwtRequest.getPassword());
        }catch (UsernameNotFoundException e){
            e.printStackTrace();
            throw  new Exception("username not found exception");
        }

//        user is authenticate
//        load user
        UserDetails userDetails = this.userDetailsServiceImpl.loadUserByUsername(jwtRequest.getUsername());
//        generate token
        String token = this.jwtUtil.generateToken(userDetails);

//        send token to user
        return ResponseEntity.ok(new JwtResponse(token));
    }

//    authenticate user with username and password
    private void authenticate(String username, String password) throws Exception {
        try {
            Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
            System.out.println("authentication : " + authenticate.getName());

        }catch (DisabledException exception){
            throw new Exception("user disable exception "+ exception.getMessage());
        }catch (BadCredentialsException exception){
            throw new Exception("bad credientail exception "+ exception.getMessage());
        }
    }

//    get the current logged-in user
    @GetMapping("/current_user")
    public User getCurrentUser(Principal principal){
        System.out.println("priciple contails " + principal.getName());
//        return user
         return ((User) this.userDetailsServiceImpl.loadUserByUsername(principal.getName()));
    }
}
