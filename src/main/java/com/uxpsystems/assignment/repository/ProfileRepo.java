package com.uxpsystems.assignment.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.uxpsystems.assignment.model.Profile;


public interface ProfileRepo extends JpaRepository<Profile, String> {

}
