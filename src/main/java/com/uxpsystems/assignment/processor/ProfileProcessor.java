package com.uxpsystems.assignment.processor;

import java.util.Optional;
import java.util.Random;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uxpsystems.assignment.Util.ValidateUtil;
import com.uxpsystems.assignment.dao.impl.ProfileDaoImpl;
import com.uxpsystems.assignment.dao.impl.UserCredentialDaoImpl;
import com.uxpsystems.assignment.dto.ProfileIDResponseDto;
import com.uxpsystems.assignment.dto.ProfileResponseDto;
import com.uxpsystems.assignment.exception.BackendErrorCode;
import com.uxpsystems.assignment.exception.NotFoundException;
import com.uxpsystems.assignment.exception.UnauthorizedException;
import com.uxpsystems.assignment.model.Profile;
import com.uxpsystems.assignment.model.UserCredential;

@Service
public class ProfileProcessor {
    private final Logger logger = LoggerFactory.getLogger(ProfileProcessor.class);

    @Autowired
    UserCredentialDaoImpl userCredentialDao;

    @Autowired
    ProfileDaoImpl profileDoa;

    @Autowired
    ValidateUtil validateUtil;

    public ProfileIDResponseDto saveProfile(String userId, String address, String phoneNumber) {
        logger.info("Inside save profile method ");
        String profileId = null;
        Random random = new Random();
        ProfileIDResponseDto profileDto = new ProfileIDResponseDto();
        logger.info("check user is authenticate or not ");
        Optional<UserCredential> usercredential = userCredentialDao.findOne(userId);
        if (!usercredential.isPresent()) {
            logger.info("user is not authenticate");
            throw new UnauthorizedException(BackendErrorCode.USER_NOT_AUTHENTICATE.getErrorMessage());
        } else {
            logger.info("User is authenticate now add user profile in system");
            Optional<Profile> profile = Optional.of(new Profile());
            profile.get().setAddress(address);
            profile.get().setPhoneNumber(phoneNumber);
            profileId = userId + ":" + random.nextInt(1000);
            profile.get().setProfileId(profileId);
            profileDoa.save(profile);
            logger.info("Profile Created successfully");
        }
        String encodedProfileId = validateUtil.generateAuthorization(profileId);
        profileDto.setProfileId(encodedProfileId);
        return profileDto;

    }

    public void UpdateProfile(String profileId, String address, String phoneNumber) {

        logger.info("Check profile exist in system for updation or not ");
        Optional<Profile> profile = profileDoa.findOne(profileId);
        if (!profile.isPresent()) {
            logger.info("No profile present with given profile_id");
            throw new NotFoundException(BackendErrorCode.PROFILE_NOT_FOUND.getErrorMessage());
        }
        logger.info("Proile is present in system with given profile_id");
        if (StringUtils.isNotBlank(address)) {
            profile.get().setAddress(address);
        }
        if (StringUtils.isNotBlank(phoneNumber)) {
            profile.get().setPhoneNumber(phoneNumber);
        }
        profileDoa.save(profile);
        logger.info("Profile Update successfully");
    }

    public void deleteProfile(String profileId) {
        logger.info("Check profile exist in system for deletion or not ");
        Optional<Profile> profile = profileDoa.findOne(profileId);
        if (!profile.isPresent()) {
            logger.info("No profile present with given user_id");
            throw new NotFoundException(BackendErrorCode.PROFILE_NOT_FOUND.getErrorMessage());
        } else {
            logger.info("Proile is present in system with given profile_id");
            profileDoa.delete(profile.get());
        }
    }

    public ProfileResponseDto getProfile(String profileId) {
        ProfileResponseDto profileDto = new ProfileResponseDto();
        logger.info("Check profile exist in system for deletion or not ");
        Optional<Profile> profile = profileDoa.findOne(profileId);
        if (!profile.isPresent()) {
            logger.info("No profile present with given user_id");
            throw new NotFoundException(BackendErrorCode.PROFILE_NOT_FOUND.getErrorMessage());
        } else {
            logger.info("Proile is present in system with given profile_id");
            profileDto.setProfileId(profile.get().getProfileId());
            profileDto.setAddress(profile.get().getAddress());
            profileDto.setPhoneNumber(profile.get().getPhoneNumber());
        }
        return profileDto;
    }
}
