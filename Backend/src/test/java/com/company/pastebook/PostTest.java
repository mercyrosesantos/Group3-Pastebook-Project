package com.company.pastebook;

import com.company.pastebook.models.Post;
import com.company.pastebook.models.User;
import com.company.pastebook.services.PostService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;

@RunWith(MockitoJUnitRunner.class)
public class PostTest {

    @Mock
    PostService postService;

    //findByTimelineUserIdOrderByPostTimestampDesc Test
    @Test
    public void testFindByTimelineUserIdOrderByPostTimestampDesc() {

        User user = new User();

        Mockito.when(postService.findByTimelineUserIdOrderByPostTimestampDesc(user.getId(),1l)).thenReturn(new ArrayList<Post>());
        Iterable<Post> posts = postService.findByTimelineUserIdOrderByPostTimestampDesc(user.getId(),1l);
        assertNotNull(posts);
        Mockito.verify(postService).findByTimelineUserIdOrderByPostTimestampDesc(user.getId(),1l);
    }

    //createPost Test
    @Test
    public void testCreatePost() {

        Post post  = new Post();

        Mockito.when(postService.createPost(post)).thenReturn(new ResponseEntity("Post created", HttpStatus.OK));
        ResponseEntity response = postService.createPost(post);
        assertEquals(response.getStatusCode(), HttpStatus.OK);
        Mockito.verify(postService).createPost(post);
    }

    //getFeed Test
    @Test
    public void testGetFeed() {

        User user  = new User();

        Mockito.when(postService.getFeed(user.getId())).thenReturn(new ResponseEntity("Post created", HttpStatus.OK));
        ResponseEntity response = postService.getFeed(user.getId());
        assertEquals(response.getStatusCode(), HttpStatus.OK);
        Mockito.verify(postService).getFeed(user.getId());
    }

    //getPost Test
    @Test
    public void testGetPost() {

        Post post  = new Post();

        Mockito.when(postService.getPost(post.getId())).thenReturn(new ResponseEntity("Post created", HttpStatus.OK));
        ResponseEntity response = postService.getPost(post.getId());
        assertEquals(response.getStatusCode(), HttpStatus.OK);
        Mockito.verify(postService).getPost(post.getId());
    }

}
