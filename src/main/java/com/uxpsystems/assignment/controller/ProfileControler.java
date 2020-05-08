package com.uxpsystems.assignment.controller;

import java.util.Base64;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.uxpsystems.assignment.Util.ValidateUtil;
import com.uxpsystems.assignment.dto.ProfileIDResponseDto;
import com.uxpsystems.assignment.dto.ProfileRequestDto;
import com.uxpsystems.assignment.dto.ProfileResponseDto;
import com.uxpsystems.assignment.exception.BadRequestException;
import com.uxpsystems.assignment.exception.NotFoundException;
import com.uxpsystems.assignment.exception.UnauthorizedException;
import com.uxpsystems.assignment.processor.ProfileProcessor;

@RestController
public class ProfileControler {
    private final Logger logger = LoggerFactory.getLogger(ProfileControler.class);

    @Autowired
    ProfileProcessor profileProcessor;

    @Autowired
    ValidateUtil validateUtil;

    @RequestMapping(value = "/assignment/create/profile", consumes = "application/json", produces = "application/json", method = RequestMethod.POST)
    public ResponseEntity<ProfileIDResponseDto> createProfile(@RequestBody ProfileRequestDto profileRequestDto,
            @RequestHeader(value = "USER-ID") String userId) {
        try {
            logger.debug("Inside Create Profile method");
            logger.info("Decode the UserID ");
            String decodedUserId = new String(Base64.getDecoder().decode(userId));
            validateUtil.validateProfile(profileRequestDto.getAddress(), profileRequestDto.getPhoneNumber());
            logger.info("validation sucessfully completed");
            ProfileIDResponseDto profileId = profileProcessor.saveProfile(decodedUserId, profileRequestDto.getAddress(),
                    profileRequestDto.getPhoneNumber());
            return new ResponseEntity<ProfileIDResponseDto>(profileId, HttpStatus.OK);
        } catch (BadRequestException | UnauthorizedException e) {
            logger.error(e.getMessage());
            throw e;
        }
    }

    @RequestMapping(value = "/assignment/update/profile/{profileId}", consumes = "application/json", produces = "application/json", method = RequestMethod.PUT)
    public ResponseEntity<String> updateProfile(@RequestBody ProfileRequestDto profileRequestDto,
            @PathVariable("profileId") String profileId) {
        try {
            logger.debug("Going to Update profile");
            logger.info("Decode the profileId");
            String decodedProfileId = new String(Base64.getDecoder().decode(profileId));
            logger.info("Calling update profile method ");
            profileProcessor.UpdateProfile(decodedProfileId, profileRequestDto.getAddress(), profileRequestDto.getPhoneNumber());
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (NotFoundException e) {
            logger.error(e.getMessage());
            throw e;
        }
    }

    @RequestMapping(value = "/assignment/delete/profile/{profileId}", consumes = "application/json", produces = "application/json", method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteProfile(@PathVariable("profileId") String profileId) {
        try {
            logger.debug("Going to delete profile");
            logger.info("Decode the profileId");
            String decodedProfileId = new String(Base64.getDecoder().decode(profileId));
            logger.info("Calling delete profile method ");
            profileProcessor.deleteProfile(decodedProfileId);
            logger.info("Profile deleted successfully");
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (NotFoundException e) {
            logger.error(e.getMessage());
            throw e;
        }
    }

    @RequestMapping(value = "/assignment/get/profile/{profileId}", consumes = "application/json", produces = "application/json", method = RequestMethod.GET)
    public ResponseEntity<ProfileResponseDto> getProfile(@PathVariable("profileId") String profileId) {
        try {
            logger.info("Going to fetch profileData");
            String decodedProfileId = new String(Base64.getDecoder().decode(profileId));
            logger.info("Calling getProfile method ");
            ProfileResponseDto profileDto = profileProcessor.getProfile(decodedProfileId);
            logger.info("profile fetch successfully");
            return new ResponseEntity<ProfileResponseDto>(profileDto, HttpStatus.OK);
        } catch (NotFoundException e) {
            logger.error(e.getMessage());
            throw e;
        }
    }
}
