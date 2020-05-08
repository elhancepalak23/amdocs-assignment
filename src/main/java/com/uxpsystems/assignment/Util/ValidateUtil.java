package com.uxpsystems.assignment.Util;

import java.util.Base64;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.uxpsystems.assignment.exception.BackendErrorCode;
import com.uxpsystems.assignment.exception.BadRequestException;

@Service
public class ValidateUtil {
    private final Logger logger = LoggerFactory.getLogger(ValidateUtil.class);

    public void validate(String username, String passwod) {
        logger.info("validate user credentials");
        if (StringUtils.isBlank(username) || StringUtils.isBlank(passwod)) {
            throw new BadRequestException(BackendErrorCode.USERNAME_PASSWORD_MISSING.getErrorMessage());
        }
    }

    public String generateAuthorization(String id) {
        logger.info("going to generate encoded Id");
        return Base64.getEncoder().encodeToString(id.getBytes());
    }

    public void validateProfile(String address, String phoneNumber) {
        logger.info("Validate profile details");
        if (StringUtils.isBlank(address) || StringUtils.isBlank(phoneNumber)) {
            throw new BadRequestException(BackendErrorCode.PROFILE_DATA_MISSING.getErrorMessage());
        }
    }
}
