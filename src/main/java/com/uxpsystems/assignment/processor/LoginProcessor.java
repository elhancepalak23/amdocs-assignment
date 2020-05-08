package com.uxpsystems.assignment.processor;

import java.util.Base64;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uxpsystems.assignment.Util.ValidateUtil;
import com.uxpsystems.assignment.dao.impl.UserCredentialDaoImpl;
import com.uxpsystems.assignment.dto.UserCredentialResponse;
import com.uxpsystems.assignment.model.UserCredential;

@Service
public class LoginProcessor {
    private final Logger logger = LoggerFactory.getLogger(LoginProcessor.class);

    @Autowired
    UserCredentialDaoImpl userCredentialDao;

    @Autowired
    ValidateUtil validateUtil;

    public UserCredentialResponse saveCredential(String userId, String username, String password) {
        logger.info("inside saveCredential method to save user credential in db");
        UserCredentialResponse userCredentialResponse = new UserCredentialResponse();
        logger.info("check user already exist in system or not ");
        Optional<UserCredential> usercredential = userCredentialDao.findOne(userId);
        if (!usercredential.isPresent()) {
            logger.info("User is not present in system going to create new user");
            createNewUser(userId, username, password);
        } else {
            logger.info("User is already present in DB, Update user with new data");
            updateUser(usercredential, username, password);
        }
        String authorizationCode = validateUtil.generateAuthorization(userId);
        userCredentialResponse.setUserId(authorizationCode);
        return userCredentialResponse;
    }

    public void createNewUser(String userId, String username, String password) {
        Optional<UserCredential> usercredential = Optional.of(new UserCredential());
        usercredential.get().setUserid(userId);
        usercredential.get().setUsename(username);
        usercredential.get().setPassword(Base64.getEncoder().encodeToString(password.getBytes()));
        userCredentialDao.save(usercredential);
    }

    public void updateUser(Optional<UserCredential> usercredential, String username, String password) {
        usercredential.get().setPassword(password);
        userCredentialDao.save(usercredential);
    }
}