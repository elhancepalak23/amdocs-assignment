package com.uxpsystems.assignment.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.uxpsystems.assignment.Util.ValidateUtil;
import com.uxpsystems.assignment.dto.UserCredentialDto;
import com.uxpsystems.assignment.dto.UserCredentialResponse;
import com.uxpsystems.assignment.exception.BadRequestException;
import com.uxpsystems.assignment.processor.LoginProcessor;

@RestController
public class LoginController {
    private final Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    ValidateUtil validateUtil;

    @Autowired
    LoginProcessor loginProcessor;

    @RequestMapping(value = "/assignment/login", consumes = "application/json", produces = "application/json", method = RequestMethod.POST)
    public ResponseEntity<UserCredentialResponse> loginUser(@RequestBody UserCredentialDto userCredential) {
        try {
            logger.debug("Inside Login user method");
            validateUtil.validate(userCredential.getUsername(), userCredential.getPassword());
            logger.info("validation done successfully");
            String user_id = userCredential.getUsername() + ":amdocs";
            UserCredentialResponse userCredentialResponse = loginProcessor.saveCredential(user_id, userCredential.getUsername(),
                    userCredential.getPassword());
            logger.info("Login sucessfully completed");
            return new ResponseEntity<UserCredentialResponse>(userCredentialResponse, HttpStatus.OK);
        } catch (BadRequestException e) {
            logger.error(e.getMessage());
            throw e;
        }
    }
}
