package com.uxpsystems.assignment.dao.impl.test;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.uxpsystems.assignment.dao.impl.ProfileDaoImpl;
import com.uxpsystems.assignment.model.Profile;
import com.uxpsystems.assignment.repository.ProfileRepo;

@RunWith(MockitoJUnitRunner.class)
public class ProfileDaoImplTest {

    @InjectMocks
    ProfileDaoImpl profileDaoImpl = new ProfileDaoImpl();

    @Mock
    ProfileRepo profileRepo;

    @Test
    public void saveProfileTest() {
        Optional<Profile> profile = Optional.of(new Profile());
        profile.get().setProfileId("testProfileId");
        profile.get().setAddress("testAddress");
        profile.get().setPhoneNumber("testPhoneNumber");
        Mockito.when(profileRepo.save(profile.get())).thenReturn(profile.get());
        profileDaoImpl.save(profile);
    }

    @Test
    public void deletePrfileTest() {
        Optional<Profile> profile = Optional.of(new Profile());
        profile.get().setProfileId("testProfileId");
        profile.get().setAddress("testAddress");
        profile.get().setPhoneNumber("testPhoneNumber");
        Mockito.doNothing().when(profileRepo).delete(profile.get());
        profileDaoImpl.delete(profile.get());
    }
    
    @Test
    public void findByIdTest() {
        String profileId = "testProfileId";
        Optional<Profile> profile = Optional.of(new Profile());
        profile.get().setProfileId("testProfileId");
        profile.get().setAddress("testAddress");
        profile.get().setPhoneNumber("testPhoneNumber");
        Mockito.when(profileRepo.findById(profileId)).thenReturn(profile);
        profileDaoImpl.findOne(profileId);
    }

}
