package com.company.pastebook;

import com.company.pastebook.models.FriendRequest;
import com.company.pastebook.models.Notification;
import com.company.pastebook.models.User;
import com.company.pastebook.services.NotificationService;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;

@RunWith(MockitoJUnitRunner.class)
public class NotificationTest extends TestCase {

    @Mock
    NotificationService notificationService;

    //createNotification Test
    @Test
    public void testCreateNotification() {

        FriendRequest friendRequest = new FriendRequest();

        Mockito.when(notificationService.createNotification("notification",friendRequest.getId())).thenReturn(new ResponseEntity("sent you a friend request", HttpStatus.OK));
        ResponseEntity response = notificationService.createNotification("notification",friendRequest.getId());
        assertEquals(response.getStatusCode(), HttpStatus.OK);
        Mockito.verify(notificationService).createNotification("notification",friendRequest.getId());
    }

    //getUserNotif Test
    @Test
    public void testGetUserNotif() {

        User user = new User();

        Mockito.when(notificationService.getUserNotif(user.getId())).thenReturn(new ArrayList<>());
        ArrayList<Notification> notifs = notificationService.getUserNotif(user.getId());
        assertNotNull(notifs);
        Mockito.verify(notificationService).getUserNotif(user.getId());
    }

    //getUnreadCount Test
    @Test
    public void testGetUnreadCount() {

        User user = new User();

        Mockito.when(notificationService.getUnreadCount(user.getId())).thenReturn(1);
        int notifs = notificationService.getUnreadCount(user.getId());
        assertEquals(notifs, 1);
        Mockito.verify(notificationService).getUnreadCount(user.getId());
    }

    //setAsRead Test
    @Test
    public void testSetAsRead() {

        User user = new User();

        Mockito.when(notificationService.setAsRead(user.getId())).thenReturn(new ResponseEntity("Notification set as Read", HttpStatus.OK));
        ResponseEntity response = notificationService.setAsRead(user.getId());
        assertEquals(response.getStatusCode(), HttpStatus.OK);
        Mockito.verify(notificationService).setAsRead(user.getId());
    }
}
