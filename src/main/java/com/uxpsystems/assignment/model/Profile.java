package com.uxpsystems.assignment.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import lombok.Data;

@Entity
@Table(name = "PROFILE", uniqueConstraints = @UniqueConstraint(columnNames = { "PROFILE_ID" }))
@Data
public class Profile {
    @Id
    @Column(name = "PROFILE_ID")
    private String profileId;
    @Column(name = "ADDRESS")
    private String address;
    @Column(name = "PHONE_NUMBER")
    private String phoneNumber;
}
