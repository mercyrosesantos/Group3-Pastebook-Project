package com.company.pastebook.controllers;


import com.company.pastebook.config.JwtToken;
import com.company.pastebook.models.JwtRequest;
import com.company.pastebook.models.JwtResponse;
import com.company.pastebook.models.User;
import com.company.pastebook.repositories.UserRepository;
import com.company.pastebook.services.JwtUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtToken jwtToken;

    @Autowired
    private JwtUserDetailService jwtUserDetailService;
    @Autowired
    private UserRepository userRepository;

    @RequestMapping(value="/api/authenticate", method= RequestMethod.POST)
    public ResponseEntity<?> createAuthenticateToken(@RequestBody JwtRequest authenticationRequest) throws Exception {

        authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());
        final UserDetails USER_DETAILS = jwtUserDetailService.loadUserByUsername(authenticationRequest.getUsername());
        final String token = jwtToken.generateToken(USER_DETAILS);
        Map<String, Object> response = new HashMap<String, Object>();
        User user = userRepository.findByEmail(authenticationRequest.getUsername());
        user.setPhoto(null);
        response.put("jwtToken" , new JwtResponse(token));
        response.put("user", user);
        return ResponseEntity.ok(response);
    }

    private void authenticate (String email, String password) throws Exception{

        try{
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email,password));
        }catch(DisabledException e){
            throw new Exception("USER_DISABLED", e);
        }catch (BadCredentialsException e){
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }
}
