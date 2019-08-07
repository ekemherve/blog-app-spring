package io.learning.blogappspring.controller;

import io.learning.blogappspring.dataaccess.entity.UserEntity;
import io.learning.blogappspring.model.UserCredential;
import io.learning.blogappspring.model.Payload;
import io.learning.blogappspring.security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/login")
@CrossOrigin(value = "*")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenProvider tokenProvider;

    @PostMapping
    public ResponseEntity generateToken(@RequestBody UserCredential userCredential) {

        final Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(userCredential.getUsername(), userCredential.getPassword())
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        final String token = tokenProvider.generateToken(authentication);
        UserEntity user = (UserEntity) authentication.getPrincipal();
        return ResponseEntity.ok(new Payload(token, user.getUsername(), user.getId()));
    }
}
