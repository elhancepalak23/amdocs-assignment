package com.uxpsystems.assignment.exception;

public enum BackendErrorCode {

    USERNAME_PASSWORD_MISSING("username/password are missing in incomming request"), USER_NOT_AUTHENTICATE(
            "User credential are not present in system, user is not authenticate"), PROFILE_NOT_FOUND(
                    "No profile Details present in db with the given userID"), PROFILE_DATA_MISSING(
                            "address/phoneNumber is missing in incomming request");

    private String errorMessage;

    private BackendErrorCode(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

}
