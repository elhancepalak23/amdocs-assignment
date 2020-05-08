package com.uxpsystems.assignment.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.uxpsystems.assignment.model.UserCredential;


public interface UserCredentialRepo extends JpaRepository<UserCredential, String> {
}
