package com.uxpsystems.assignment.dao.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.uxpsystems.assignment.model.UserCredential;
import com.uxpsystems.assignment.repository.UserCredentialRepo;

@Component
public class UserCredentialDaoImpl {

    @Autowired
    UserCredentialRepo usercredentialRepo;

    public void save(Optional<UserCredential> userCredential) {
        usercredentialRepo.save(userCredential.get());
        }

    public void delete(UserCredential userCredential) {
        usercredentialRepo.delete(userCredential);
    }

    public Optional<UserCredential> findOne(String userid) {
        return usercredentialRepo.findById(userid);
    }
}


