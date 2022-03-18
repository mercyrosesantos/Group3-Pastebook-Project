package com.company.pastebook;
import com.company.pastebook.models.Album;
import com.company.pastebook.models.User;
import com.company.pastebook.services.AlbumService;
import junit.framework.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


@RunWith(MockitoJUnitRunner.class)
public class AlbumTest extends TestCase{

    @Mock
    private AlbumService albumService;


    //createAlbum Test
    @Test
    public void testCreate() {

        Album album = new Album();
        album.setAlbumName("test");

        Mockito.when(albumService.createAlbum(album)).thenReturn(new ResponseEntity("Added", HttpStatus.OK));
        ResponseEntity response = albumService.createAlbum(album);
        assertEquals(response.getStatusCode() , HttpStatus.OK);
        Mockito.verify(albumService).createAlbum(album);

    }

    //addPhoto Test
    @Test
    public void testAdd() throws IOException {

        MultipartFile [] files = new MultipartFile[1];
        User user = new User();
        Album album = new Album();
        album.setAlbumName("test");

        Mockito.when(albumService.addPhotos(files, user.getId(), album.getId())).thenReturn(new ResponseEntity("Added", HttpStatus.OK));
        ResponseEntity response = albumService.addPhotos(files, user.getId(), album.getId());
        assertEquals(response.getStatusCode() , HttpStatus.OK);
        Mockito.verify(albumService).addPhotos(files, user.getId(), album.getId());
    }

    //deleteAlbum Test
    @Test
    public void testDelete() {

        Album album = new Album();
        album.setAlbumName("test");

        Mockito.when(albumService.deleteAlbum(album.getId())).thenReturn(new ResponseEntity("Added", HttpStatus.OK));
        ResponseEntity response = albumService.deleteAlbum(album.getId());
        assertEquals(response.getStatusCode() , HttpStatus.OK);
        Mockito.verify(albumService).deleteAlbum(album.getId());

    }

    //updateAlbum Test
    @Test
    public void testUpdate() {

        Album album = new Album();
        album.setAlbumName("test");

        Mockito.when(albumService.updateAlbum(album)).thenReturn(new ResponseEntity("Added", HttpStatus.OK));
        ResponseEntity response = albumService.updateAlbum(album);
        assertEquals(response.getStatusCode() , HttpStatus.OK);
        Mockito.verify(albumService).updateAlbum(album);
    }

    //getAlbum Test
    @Test
    public void testGetAlbum() {

        User user = new User();

        Mockito.when(albumService.getAlbum(user.getId())).thenReturn(new ResponseEntity("Added", HttpStatus.OK));
        ResponseEntity response = albumService.getAlbum(user.getId());
        assertEquals(response.getStatusCode() , HttpStatus.OK);
        Mockito.verify(albumService).getAlbum(user.getId());
    }

    //getAlbumById Test
    @Test
    public void testGetAlbumById() {

        Album album = new Album();

        Mockito.when(albumService.getAlbumById(album.getId())).thenReturn(new ResponseEntity("Added", HttpStatus.OK));
        ResponseEntity response = albumService.getAlbumById(album.getId());
        assertEquals(response.getStatusCode() , HttpStatus.OK);
        Mockito.verify(albumService).getAlbumById(album.getId());
    }



}
