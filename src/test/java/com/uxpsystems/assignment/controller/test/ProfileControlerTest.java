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
import com.uxpsystems.assignment.controller.ProfileControler;
import com.uxpsystems.assignment.dto.ProfileIDResponseDto;
import com.uxpsystems.assignment.dto.ProfileRequestDto;
import com.uxpsystems.assignment.dto.ProfileResponseDto;
import com.uxpsystems.assignment.processor.ProfileProcessor;

@RunWith(MockitoJUnitRunner.class)
public class ProfileControlerTest {
    private static final Logger logger = LoggerFactory.getLogger(ProfileControlerTest.class);
    @InjectMocks
    ProfileControler profileControler = new ProfileControler();

    @Mock
    ProfileProcessor profileProcessor;

    @Mock
    ValidateUtil validateUtil;

    @Test
    public void addProfileTest() {
        logger.info("Inside add Profile method");
        String userId = "test";
        ProfileRequestDto profileRequestDto = new ProfileRequestDto();
        profileRequestDto.setAddress("testAddress");
        profileRequestDto.setPhoneNumber("testPhomeNumber");
        ProfileIDResponseDto profileId = new ProfileIDResponseDto();
        profileId.setProfileId("test");
        try {
            Mockito.doNothing().when(validateUtil).validate(Mockito.anyString(), Mockito.anyString());
            Mockito.when(profileProcessor.saveProfile(Mockito.anyString(), Mockito.anyString(), Mockito.anyString()))
                    .thenReturn(profileId);
            ResponseEntity<ProfileIDResponseDto> userCredentialId = profileControler.createProfile(profileRequestDto, userId);
            Assert.assertEquals(200, userCredentialId.getStatusCodeValue());
            logger.info("addProfileTest execute successfully");
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void UpdateProfileTest() {
        String profileId = "testDb";
        logger.info("Inside add Profile method");
        ProfileRequestDto profileRequestDto = new ProfileRequestDto();
        profileRequestDto.setAddress("testAddress");
        profileRequestDto.setPhoneNumber("testPhomeNumber");
        try {
        Mockito.doNothing().when(profileProcessor).UpdateProfile(Mockito.anyString(), Mockito.anyString(), Mockito.anyString());
        ResponseEntity<String> response = profileControler.updateProfile(profileRequestDto, profileId);
        Assert.assertEquals(204, response.getStatusCodeValue());
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void getProfileDataTest() {
        String profileId = "test";
        ProfileResponseDto profileResponse = new ProfileResponseDto();
        profileResponse.setProfileId("testProfileId");
        profileResponse.setAddress("testAddress");
        profileResponse.setPhoneNumber("testPhoneNumber");
        try {
        Mockito.when(profileProcessor.getProfile(Mockito.anyString())).thenReturn(profileResponse);
        ResponseEntity<ProfileResponseDto> profileResponseDto = profileControler.getProfile(profileId);
        Assert.assertEquals(200, profileResponseDto.getStatusCodeValue());
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void deleteProfileDataTest() {
        String profileId="test";
        Mockito.doNothing().when(profileProcessor).deleteProfile(Mockito.anyString());
        ResponseEntity<String> response = profileControler.deleteProfile(profileId);
        Assert.assertEquals(204, response.getStatusCodeValue());
    }
}
