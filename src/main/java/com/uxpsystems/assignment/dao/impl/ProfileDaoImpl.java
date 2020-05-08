package com.uxpsystems.assignment.dao.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.uxpsystems.assignment.model.Profile;
import com.uxpsystems.assignment.repository.ProfileRepo;

@Component
public class ProfileDaoImpl {

    @Autowired
    ProfileRepo profileRepo;

    public void save(Optional<Profile> profile) {
        profileRepo.save(profile.get());
    }

    public void delete(Profile profile) {
        profileRepo.delete(profile);
    }

    public Optional<Profile> findOne(String profileId) {
        return profileRepo.findById(profileId);
    }

}
