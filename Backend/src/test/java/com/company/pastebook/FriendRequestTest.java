package com.company.pastebook;

import com.company.pastebook.models.FriendRequest;
import com.company.pastebook.models.Friendship;
import com.company.pastebook.models.User;
import com.company.pastebook.services.FriendRequestService;
import junit.framework.TestCase;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


import java.util.Optional;

@RunWith(MockitoJUnitRunner.class)
public class FriendRequestTest extends TestCase {

    @Mock
    FriendRequestService friendRequestService;

    //findByStatus Test
    @Test
    public void testFindByStatus() {

        FriendRequest friendRequest = new FriendRequest();

        Mockito.when(friendRequestService.findByStatus("pending")).thenReturn(Optional.ofNullable(new FriendRequest()));
        Optional<FriendRequest> fr = friendRequestService.findByStatus("pending");
        assertNotNull(fr);
        Mockito.verify(friendRequestService).findByStatus("pending");

    }

    //getFriendship Test
    @Test
    public void testGetFriendship() {

        User user = new User();
        User userFriend = new User();

        Mockito.when(friendRequestService.getFriendship(user.getId(), userFriend.getId())).thenReturn(new Friendship());
        Friendship fr = friendRequestService.getFriendship(user.getId(), userFriend.getId());
        assertNotNull(fr);
        Mockito.verify(friendRequestService).getFriendship(user.getId(), userFriend.getId());
    }

    //createOrUpdateFriendRequest Test
    @Test
    public void testCreateOrUpdateFriendRequest() {

        FriendRequest friendRequest = new FriendRequest();

        Mockito.when(friendRequestService.createOrUpdateFriendRequest(friendRequest)).thenReturn(new ResponseEntity("Accepted", HttpStatus.OK));
        ResponseEntity response = friendRequestService.createOrUpdateFriendRequest(friendRequest);
        assertEquals(response.getStatusCode(), HttpStatus.OK);
        Mockito.verify(friendRequestService).createOrUpdateFriendRequest(friendRequest);
    }

    //getFriendRequest Test
    @Test
    public void testGetFriendRequest() {

        User user = new User();
        User userFriend = new User();

        Mockito.when(friendRequestService.getFriendRequest(user.getId(), userFriend.getId())).thenReturn(new FriendRequest());
        FriendRequest fr = friendRequestService.getFriendRequest(user.getId(), userFriend.getId());
        assertNotNull(fr);
        Mockito.verify(friendRequestService).getFriendRequest(user.getId(), userFriend.getId());
    }

    //getFriends Test
    @Test
    public void testGetFriends() {

        User user = new User();
        Mockito.when(friendRequestService.getFriends(user.getId())).thenReturn(new ResponseEntity("Accepted", HttpStatus.OK));
        ResponseEntity response = friendRequestService.getFriends(user.getId());
        assertEquals(response.getStatusCode(), HttpStatus.OK);
        Mockito.verify(friendRequestService).getFriends(user.getId());
    }


}
