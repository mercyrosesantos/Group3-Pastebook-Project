package com.company.pastebook.repositories;

import com.company.pastebook.models.Post;
import com.company.pastebook.models.Reaction;
import com.company.pastebook.models.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ReactionRepository extends CrudRepository<Reaction, Object> {
    Iterable<Reaction> findByReactionTypeIdAndPostId(Long reactionTypeId, Long postId);

    @Query("SELECT distinct(r.user) From Reaction r inner join r.user u where r.post.id =:postId and r.reactionType = 1")
    public Iterable<User> findLikedUsers(@Param("postId") Long postId);
}
