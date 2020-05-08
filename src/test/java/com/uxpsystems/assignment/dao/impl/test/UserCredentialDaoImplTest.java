package com.uxpsystems.assignment.dao.impl.test;

import java.util.Optional;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.uxpsystems.assignment.dao.impl.UserCredentialDaoImpl;
import com.uxpsystems.assignment.model.UserCredential;
import com.uxpsystems.assignment.repository.UserCredentialRepo;

@RunWith(MockitoJUnitRunner.class)
public class UserCredentialDaoImplTest {

    @InjectMocks
    UserCredentialDaoImpl userCredentialDaoImpl = new UserCredentialDaoImpl();

    @Mock
    UserCredentialRepo userCredentialRepoMock;

    @Test
    public void saveTest() {
        Optional<UserCredential> userCredential = Optional.of(new UserCredential());
        userCredential.get().setUserid("testUserId");
        userCredential.get().setUsename("testUsername");
        userCredential.get().setPassword("testPassword");
        Mockito.when(userCredentialRepoMock.save(userCredential.get())).thenReturn(userCredential.get());
        userCredentialDaoImpl.save(userCredential);
    }

    @Test
    public void deleteTest() {
        UserCredential userCredential = new UserCredential();
        userCredential.setUserid("testUserId");
        userCredential.setUsename("testUsername");
        userCredential.setPassword("testPassword");
        Mockito.doNothing().when(userCredentialRepoMock).delete(userCredential);
        userCredentialDaoImpl.delete(userCredential);
    }

    @Test
    public void findOneTest() {
        String userID = "testUserId";
        Optional<UserCredential> userCredential = Optional.of(new UserCredential());
        userCredential.get().setUserid("testUserId");
        userCredential.get().setUsename("testUsername");
        userCredential.get().setPassword("testPassword");
        Mockito.when(userCredentialRepoMock.findById(userID)).thenReturn(userCredential);
        userCredential = userCredentialDaoImpl.findOne(userID);
        Assert.assertNotNull(userCredential);
    }
}
