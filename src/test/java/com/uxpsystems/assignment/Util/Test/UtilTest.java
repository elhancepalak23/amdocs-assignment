package com.uxpsystems.assignment.Util.Test;

import java.util.Base64;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.uxpsystems.assignment.Util.ValidateUtil;
import com.uxpsystems.assignment.exception.BackendErrorCode;
import com.uxpsystems.assignment.exception.BadRequestException;


@RunWith(MockitoJUnitRunner.class)
public class UtilTest {
    private static final Logger logger = LoggerFactory.getLogger(UtilTest.class);

    @InjectMocks
    ValidateUtil validateUtil = new ValidateUtil();

    @Test
    public void UserNameMissingTest() {
        try{
        String username = "";
        String password = "testPassword";
        validateUtil.validate(username, password);
        } catch (BadRequestException e) {
            Assert.assertEquals(BackendErrorCode.USERNAME_PASSWORD_MISSING.getErrorMessage(), e.getMessage());
            logger.info("Exception is : {} ", e.getMessage());
        }
    }

    @Test
    public void passwordMissingTest() {
        try {
            String username = "testUsername";
            String password = "";
            validateUtil.validate(username, password);
        } catch (BadRequestException e) {
            Assert.assertEquals(BackendErrorCode.USERNAME_PASSWORD_MISSING.getErrorMessage(), e.getMessage());
            logger.info("Exception is : {} ", e.getMessage());
        }
    }

    @Test
    public void usernamePasswordMissingTest() {
        try {
            String username = "";
            String password = "";
            validateUtil.validate(username, password);
        } catch (BadRequestException e) {
            Assert.assertEquals(BackendErrorCode.USERNAME_PASSWORD_MISSING.getErrorMessage(), e.getMessage());
            logger.info("Exception is : {} ", e.getMessage());
        }
    }

    @Test
    public void addressMissingTest() {
        try {
            String address = "";
            String phoneNumber = "12345";
            validateUtil.validateProfile(address, phoneNumber);
        } catch (BadRequestException e) {
            Assert.assertEquals(BackendErrorCode.PROFILE_DATA_MISSING.getErrorMessage(), e.getMessage());
            logger.info("Exception is : {} ", e.getMessage());
        }
    }

    @Test
    public void phoneNumberMissingTest() {
        try {
            String address = "abcd";
            String phoneNumber = "";
            validateUtil.validateProfile(address, phoneNumber);
        } catch (BadRequestException e) {
            Assert.assertEquals(BackendErrorCode.PROFILE_DATA_MISSING.getErrorMessage(), e.getMessage());
            logger.info("Exception is : {} ", e.getMessage());
        }
    }

    @Test
    public void addressPhoneNumberMissingTest() {
        try {
            String address = "";
            String phoneNumber = "";
            validateUtil.validateProfile(address, phoneNumber);
        } catch (BadRequestException e) {
            Assert.assertEquals(BackendErrorCode.PROFILE_DATA_MISSING.getErrorMessage(), e.getMessage());
            logger.info("Exception is : {} ", e.getMessage());
        }
    }

    @Test
    public void generateAuthorizationTest() {
        String id = "testId";
        String encodedString = Base64.getEncoder().encodeToString(id.getBytes());
        String responseString = validateUtil.generateAuthorization(id);
        Assert.assertEquals(encodedString, responseString);
    }
}
