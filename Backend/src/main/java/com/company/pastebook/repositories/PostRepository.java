package com.company.pastebook.repositories;

import com.company.pastebook.models.Post;
import com.company.pastebook.models.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Object>, CrudRepository<Post, Object> {
    List<Post> findByTimelineUserIdOrderByPostTimestampDesc(Long timelineUserId, Pageable pageable);

}
