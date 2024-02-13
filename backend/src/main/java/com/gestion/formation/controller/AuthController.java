package com.gestion.formation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gestion.formation.dto.LoginDTO;
import com.gestion.formation.dto.SignUpDTO;
import com.gestion.formation.service.AuthService;
import com.gestion.formation.util.Validator;

import jakarta.validation.Valid;



@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @RequestMapping(value = "/signin", method = {RequestMethod.POST})
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginDTO loginDto, BindingResult bindingResult) {
        try {
            if (bindingResult.hasErrors()) {
                return new ResponseEntity<>(Validator.getValidationErrors(bindingResult), HttpStatus.BAD_REQUEST);
            }
            String token = authService.authenticateUser(loginDto);
            return new ResponseEntity<>(token, HttpStatus.OK);
        } catch (AuthenticationException e) {
            return new ResponseEntity<>("Authentication failed", HttpStatus.UNAUTHORIZED);
        }
    }


    @PostMapping("/admin/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpDTO signUpDto, @RequestParam String userType, BindingResult bindingResult) {
        try {
            if (bindingResult.hasErrors()) {
                return new ResponseEntity<>(Validator.getValidationErrors(bindingResult), HttpStatus.BAD_REQUEST);
            }
            String response = authService.registerUser(signUpDto, userType);
            switch (response) {
                case "User registered successfully":
                    return new ResponseEntity<>(response, HttpStatus.OK);
                case "Invalid user type!":
                    return ResponseEntity.status(HttpStatus.CONFLICT).body("Invalid user type!");
                default:
                    return ResponseEntity.status(HttpStatus.CONFLICT).body("Username or Emaiil already exists.");
            }
        } catch (Exception e) {
            return new ResponseEntity<>("Registration failed", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/role")
    public String getRoleName(@RequestParam String token) {
        return authService.extractRoleFromToken(token);
    }
    
}
