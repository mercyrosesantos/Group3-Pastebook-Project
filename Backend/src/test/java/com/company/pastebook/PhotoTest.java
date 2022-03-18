package com.company.pastebook;

import com.company.pastebook.models.Photo;
import com.company.pastebook.services.PhotoService;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@RunWith(MockitoJUnitRunner.class)

public class PhotoTest extends TestCase {

    @Mock
    private PhotoService photoService;

    //uploadPhoto Test
    @Test
    public void testUploadPhoto() {

        Photo photo = new Photo();

        Mockito.when(photoService.uploadPhoto(photo)).thenReturn(new ResponseEntity("Photo uploaded", HttpStatus.OK));
        ResponseEntity response = photoService.uploadPhoto(photo);
        assertEquals(response.getStatusCode() , HttpStatus.OK);
        Mockito.verify(photoService).uploadPhoto(photo);
    }

    //getPhoto Test
    @Test
    public void testGetPhoto() {

        Photo photo = new Photo();

        Mockito.when(photoService.getPhoto(photo.getId())).thenReturn(new ResponseEntity("Photo uploaded", HttpStatus.OK));
        ResponseEntity response = photoService.getPhoto(photo.getId());
        assertEquals(response.getStatusCode() , HttpStatus.OK);
        Mockito.verify(photoService).getPhoto(photo.getId());
    }

}
