package com.uxpsystems.assignment.controller.test;


import static org.junit.Assert.fail;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;

import com.uxpsystems.assignment.Util.ValidateUtil;
import com.uxpsystems.assignment.controller.LoginController;
import com.uxpsystems.assignment.dto.UserCredentialDto;
import com.uxpsystems.assignment.dto.UserCredentialResponse;
import com.uxpsystems.assignment.processor.LoginProcessor;

@RunWith(MockitoJUnitRunner.class)
public class LoginControllerTest {

    private static final Logger logger = LoggerFactory.getLogger(LoginControllerTest.class);
    
    @InjectMocks
    LoginController login = new LoginController();
    
    @Mock
    ValidateUtil validateUtil;

    @Mock
    LoginProcessor loginProcessor;

    @Test
    public void addUserTest() {
        logger.info("Inside add user method");
        String userId="test";
        UserCredentialDto userCredetialDto = new UserCredentialDto();
        userCredetialDto.setUsername("test");
        userCredetialDto.setPassword("test");
        UserCredentialResponse userResponse= new UserCredentialResponse();
        userResponse.setUserId(userId);
        try {
        Mockito.doNothing().when(validateUtil).validate(Mockito.anyString(), Mockito.anyString());
        Mockito.when(loginProcessor.saveCredential(userId, userCredetialDto.getUsername(), userCredetialDto.getPassword()))
                .thenReturn(userResponse);
            ResponseEntity<UserCredentialResponse> userCredentialId = login.loginUser(userCredetialDto);
            Assert.assertEquals(200, userCredentialId.getStatusCodeValue());
            logger.info("addUserTest method execute successfully");
        } catch (Exception e) {
            fail();
        }
    }
}
