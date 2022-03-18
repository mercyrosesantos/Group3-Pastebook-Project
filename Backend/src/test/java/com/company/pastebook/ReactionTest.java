package com.company.pastebook;

import com.company.pastebook.models.Post;
import com.company.pastebook.models.Reaction;
import com.company.pastebook.services.ReactionService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static junit.framework.TestCase.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class ReactionTest {

    @Mock
    ReactionService reactionService;

    //createReaction Test
    @Test
    public void testCreateReaction() {

        Reaction reaction  = new Reaction();

        Mockito.when(reactionService.createReaction(reaction)).thenReturn(new ResponseEntity("Reaction Recorded", HttpStatus.OK));
        ResponseEntity response = reactionService.createReaction(reaction);
        assertEquals(response.getStatusCode(), HttpStatus.OK);
        Mockito.verify(reactionService).createReaction(reaction);
    }

    //getCommentsByPost Test
    @Test
    public void testGetCommentsByPost() {

        Post post  = new Post();

        Mockito.when(reactionService.getCommentsByPost(post.getId())).thenReturn(new ResponseEntity("Reaction Recorded", HttpStatus.OK));
        ResponseEntity response = reactionService.getCommentsByPost(post.getId());
        assertEquals(response.getStatusCode(), HttpStatus.OK);
        Mockito.verify(reactionService).getCommentsByPost(post.getId());
    }

    //getLikesByPost Test
    @Test
    public void testGetLikesByPost() {

        Post post  = new Post();

        Mockito.when(reactionService.getLikesByPost(post.getId())).thenReturn(new ResponseEntity("Reaction Recorded", HttpStatus.OK));
        ResponseEntity response = reactionService.getLikesByPost(post.getId());
        assertEquals(response.getStatusCode(), HttpStatus.OK);
        Mockito.verify(reactionService).getLikesByPost(post.getId());
    }
}
