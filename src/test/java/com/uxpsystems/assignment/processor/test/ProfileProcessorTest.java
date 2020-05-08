package com.uxpsystems.assignment.processor.test;

import static org.junit.Assert.fail;

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
import com.uxpsystems.assignment.controller.test.ProfileControlerTest;
import com.uxpsystems.assignment.dao.impl.ProfileDaoImpl;
import com.uxpsystems.assignment.dao.impl.UserCredentialDaoImpl;
import com.uxpsystems.assignment.dto.ProfileIDResponseDto;
import com.uxpsystems.assignment.dto.ProfileResponseDto;
import com.uxpsystems.assignment.exception.BackendErrorCode;
import com.uxpsystems.assignment.exception.NotFoundException;
import com.uxpsystems.assignment.exception.UnauthorizedException;
import com.uxpsystems.assignment.model.Profile;
import com.uxpsystems.assignment.model.UserCredential;
import com.uxpsystems.assignment.processor.ProfileProcessor;

@RunWith(MockitoJUnitRunner.class)
public class ProfileProcessorTest {
    private static final Logger logger = LoggerFactory.getLogger(ProfileControlerTest.class);

    @InjectMocks
    ProfileProcessor profileProcessor = new ProfileProcessor();

    @Mock
    UserCredentialDaoImpl userCredentialDao;

    @Mock
    ProfileDaoImpl profileDoa;

    @Mock
    ValidateUtil validateUtil;

    @Test
    public void saveProfileTest() {
        String userId = "test";
        String address = "address";
        String phoneNumber = "pphoneNumber";
        try {
            Optional<UserCredential> userCredential = Optional.empty();
            Mockito.when(userCredentialDao.findOne(Mockito.anyString())).thenReturn(userCredential);
            profileProcessor.saveProfile(userId, address, phoneNumber);
        } catch (UnauthorizedException e) {
            Assert.assertEquals(BackendErrorCode.USER_NOT_AUTHENTICATE.getErrorMessage(), e.getMessage());
            logger.error("error is : {}", e.getMessage());
        }
    }

    @Test
    public void saveProfileTest1() {
        Optional<UserCredential> userCredential = Optional.of(new UserCredential());
        Optional<Profile> profile = Optional.of(new Profile());
        userCredential.get().setUserid("test");
        userCredential.get().setUsename("testUsername");
        userCredential.get().setPassword("testPassword");
        profile.get().setProfileId("testProfileId");
        profile.get().setAddress("testAddress");
        profile.get().setPhoneNumber("testPhoneNumber");
        try {
            Mockito.when(userCredentialDao.findOne(Mockito.anyString())).thenReturn(userCredential);
            Mockito.doNothing().when(profileDoa).save(profile);
            Mockito.when(validateUtil.generateAuthorization("test")).thenReturn("profile");
            ProfileIDResponseDto profileId = profileProcessor.saveProfile("testUserId", "testAddress", "testPhoneNumber");
            Assert.assertNotNull(profileId);
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void deleteProfileTest() {
        try {
            Optional<Profile> profile = Optional.of(new Profile());
            profile.get().setProfileId("test");
            profile.get().setAddress("testAddress");
            profile.get().setPhoneNumber("testPhoneNumber");
            Mockito.when(profileDoa.findOne(Mockito.anyString())).thenReturn(profile);
            Mockito.doNothing().when(profileDoa).delete(profile.get());
            profileProcessor.deleteProfile("test");
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void profileNotPresentInDbForDelete() {
        try {
            Optional<Profile> profile = Optional.empty();
            Mockito.when(profileDoa.findOne(Mockito.anyString())).thenReturn(profile);
            profileProcessor.deleteProfile("test");
        } catch (NotFoundException e) {
            Assert.assertEquals(BackendErrorCode.PROFILE_NOT_FOUND.getErrorMessage(), e.getMessage());
            logger.error("error is : {}", e.getMessage());
        }
    }

    @Test
    public void getProfile() {
        try {
            Optional<Profile> profile = Optional.of(new Profile());
            profile.get().setProfileId("testProfileId");
            profile.get().setAddress("testAddress");
            profile.get().setPhoneNumber("PhoneNumber");
            Mockito.when(profileDoa.findOne(Mockito.anyString())).thenReturn(profile);
            ProfileResponseDto profileResponseDto = profileProcessor.getProfile("testPRofileId");
            Assert.assertNotNull(profileResponseDto);
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void profileNotPresentInDbDuringGet() {
        try {
            Optional<Profile> profile = Optional.empty();
            Mockito.when(profileDoa.findOne(Mockito.anyString())).thenReturn(profile);
            profileProcessor.getProfile("test");
        } catch (NotFoundException e) {
            Assert.assertEquals(BackendErrorCode.PROFILE_NOT_FOUND.getErrorMessage(), e.getMessage());
            logger.error("error is : {}", e.getMessage());
        }
    }
}
