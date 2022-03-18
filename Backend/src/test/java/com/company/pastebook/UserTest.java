package com.company.pastebook;

import com.company.pastebook.models.FriendRequest;
import com.company.pastebook.models.User;
import com.company.pastebook.services.PostService;
import com.company.pastebook.services.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Optional;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;

@RunWith(MockitoJUnitRunner.class)
public class UserTest {

    @Mock
    UserService userService;

    //findByEmail Test
    @Test
    public void testFindByEmail() {

        User user = new User();

        Mockito.when(userService.findByEmail("User Found")).thenReturn(Optional.ofNullable(new User()));
        Optional<User> email = userService.findByEmail("User Found");
        assertNotNull(email);
        Mockito.verify(userService).findByEmail("User Found");

    }

    //getOnlineFriends Test
    @Test
    public void testGetOnlineFriends() {

        User user = new User();
        Mockito.when(userService.getOnlineFriends(user.getId())).thenReturn(new ResponseEntity("User Found", HttpStatus.OK));
        ResponseEntity response = userService.getOnlineFriends(user.getId());
        assertEquals(response.getStatusCode(), HttpStatus.OK);
        Mockito.verify(userService).getOnlineFriends(user.getId());
    }
    //getUserProfile Test
    @Test
    public void testGetUserProfile() {

        User user = new User();

        Mockito.when(userService.getUserProfile(user.getId())).thenReturn(new ResponseEntity("User Found", HttpStatus.OK));
        ResponseEntity response = userService.getUserProfile(user.getId());
        assertEquals(response.getStatusCode(), HttpStatus.OK);
        Mockito.verify(userService).getUserProfile(user.getId());
    }

    //getUserProfileByUrl Test
    @Test
    public void testGetUserProfileByUrl() {

        Mockito.when(userService.getUserProfileByUrl("User Found")).thenReturn(new ResponseEntity("User Found", HttpStatus.OK));
        ResponseEntity response = userService.getUserProfileByUrl("User Found");
        assertEquals(response.getStatusCode(), HttpStatus.OK);
        Mockito.verify(userService).getUserProfileByUrl("User Found");
    }

    //updateUserInfo Test
    @Test
    public void testUpdateUserInfo() {

        User user = new User();

        Mockito.when(userService.updateUserInfo(user.getId(), user)).thenReturn(new ResponseEntity("User Info Updated", HttpStatus.OK));
        ResponseEntity response = userService.updateUserInfo(user.getId(), user);
        assertEquals(response.getStatusCode(), HttpStatus.OK);
        Mockito.verify(userService).updateUserInfo(user.getId(), user);
    }

    //updateUserEmail Test
    @Test
    public void testUpdateUserEmail() {

        User user = new User();

        Mockito.when(userService.updateUserEmail(user.getId(), user)).thenReturn(new ResponseEntity("User Info Updated", HttpStatus.OK));
        ResponseEntity response = userService.updateUserEmail(user.getId(), user);
        assertEquals(response.getStatusCode(), HttpStatus.OK);
        Mockito.verify(userService).updateUserEmail(user.getId(), user);
    }

    //updateUserPassword Test
    @Test
    public void testUpdateUserPassword() {

        User user = new User();

        Mockito.when(userService.updateUserPassword(user.getId(),new HashMap<>())).thenReturn(new ResponseEntity("User Info Updated", HttpStatus.OK));
        ResponseEntity response = userService.updateUserPassword(user.getId(),new HashMap<>());
        assertEquals(response.getStatusCode(), HttpStatus.OK);
        Mockito.verify(userService).updateUserPassword(user.getId(),new HashMap<>());
    }

    //getUser Test
    @Test
    public void testGetUser() {

        User user = new User();

        Mockito.when(userService.getUser(user.getId())).thenReturn(new ResponseEntity("User Found", HttpStatus.OK));
        ResponseEntity response = userService.getUser(user.getId());
        assertEquals(response.getStatusCode(), HttpStatus.OK);
        Mockito.verify(userService).getUser(user.getId());
    }

    //updateAboutMe Test
    @Test
    public void testUpdateAboutMe() {

        User user = new User();

        Mockito.when(userService.updateAboutMe("About Me", user.getId())).thenReturn(new ResponseEntity("About Me Updated", HttpStatus.OK));
        ResponseEntity response = userService.updateAboutMe("About Me", user.getId());
        assertEquals(response.getStatusCode(), HttpStatus.OK);
        Mockito.verify(userService).updateAboutMe("About Me", user.getId());
    }
}
