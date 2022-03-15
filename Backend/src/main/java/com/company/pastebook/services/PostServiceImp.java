package com.company.pastebook.services;

import com.company.pastebook.models.Friendship;
import com.company.pastebook.models.Post;
import com.company.pastebook.repositories.FriendshipRepository;
import com.company.pastebook.repositories.PostRepository;
import com.company.pastebook.repositories.ReactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

@Service
public class PostServiceImp implements PostService{

    @Autowired
    private PostRepository postRepository;
    @Autowired
    private FriendshipRepository friendshipRepository;

    //Get User Profile
    public Iterable<Post> findByTimelineUserIdOrderByPostTimestampDesc(Long timelineUserId,Long pageNo) {
        Pageable myPage = PageRequest.of(pageNo.intValue(),10);
        return postRepository.findByTimelineUserIdOrderByPostTimestampDesc(timelineUserId,myPage);
    }

    // Create Post
    public ResponseEntity createPost (Post post){
        post.setPostTimestamp(new Date());
        postRepository.save(post);
        return new ResponseEntity("Post created.", HttpStatus.OK);
    }

    // Get posts for newsfeed
    public ResponseEntity<Object> getFeed (Long userId) {
        ArrayList<Post> posts = new ArrayList<>();
        Long friendId;
        for(Friendship friend: friendshipRepository.findAll()){
            if (friend.getUserId().equals(userId)){
                friendId = friend.getFriend().getId();
                for(Post post: postRepository.findAll()) {
                    if (post.getUser().getId().equals(friendId)){
                        posts.add(post);
                    }
                }
            }
        }
        for (Post ownPost: postRepository.findAll()) {
            if (ownPost.getUser().getId().equals(userId)||ownPost.getTimelineUser().getId().equals(userId)) {
                posts.add(ownPost);
            }
        }
        Collections.sort(posts);
        return new ResponseEntity(posts, HttpStatus.OK);
    }

}
