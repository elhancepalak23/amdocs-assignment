package com.uxpsystems.assignment.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "USER_CREDENTIAL")
@Data
public class UserCredential {

    @Id
    @Column(name = "USER_ID")
    private String userid;
    @Column(name = "USER_NAME")
    private String usename;
    @Column(name = "PASSWORD")
    private String password;
}
