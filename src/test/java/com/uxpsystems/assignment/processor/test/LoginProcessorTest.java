package com.uxpsystems.assignment.processor.test;

import java.util.Optional;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.uxpsystems.assignment.Util.ValidateUtil;
import com.uxpsystems.assignment.controller.test.LoginControllerTest;
import com.uxpsystems.assignment.dao.impl.UserCredentialDaoImpl;
import com.uxpsystems.assignment.dto.UserCredentialResponse;
import com.uxpsystems.assignment.model.UserCredential;
import com.uxpsystems.assignment.processor.LoginProcessor;

@RunWith(MockitoJUnitRunner.class)
public class LoginProcessorTest {

    private static final Logger logger = LoggerFactory.getLogger(LoginControllerTest.class);

    @InjectMocks
    LoginProcessor loginProcessor = new LoginProcessor();

    @Mock
    UserCredentialDaoImpl userCredentialDao;

    @Mock
    ValidateUtil validateUtil;

    @Test
    public void saveCredentialTest() {
        Optional<UserCredential> userCredential = Optional.of(new UserCredential());
        userCredential.get().setUserid("test");
        userCredential.get().setUsename("testUsername");
        userCredential.get().setPassword("testPassword");
        try {
            Mockito.when(userCredentialDao.findOne(Mockito.anyString())).thenReturn(userCredential);
            Mockito.doNothing().when(userCredentialDao).save(userCredential);
            Mockito.when(validateUtil.generateAuthorization("test")).thenReturn("userId");
            UserCredentialResponse userResponse = loginProcessor.saveCredential("test", "userName", "password");
            Assert.assertNotNull(userResponse);
        } catch (Exception e) {
            throw e;
        }
    }

    @Test
    public void saveCredentialTest1() {
        Optional<UserCredential> userCredential = Optional.empty();
        try {
            Mockito.when(userCredentialDao.findOne(Mockito.anyString())).thenReturn(userCredential);
            Mockito.doNothing().when(userCredentialDao).save(userCredential);
            Mockito.when(validateUtil.generateAuthorization("test")).thenReturn("userId");
            UserCredentialResponse userResponse = loginProcessor.saveCredential("test", "userName", "password");
            Assert.assertNotNull(userResponse.getUserId());
        } catch (Exception e) {
            throw e;
        }
    }

}
