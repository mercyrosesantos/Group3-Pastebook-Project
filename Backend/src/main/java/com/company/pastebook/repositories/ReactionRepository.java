package com.company.pastebook.repositories;

import com.company.pastebook.models.Post;
import com.company.pastebook.models.Reaction;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReactionRepository extends CrudRepository<Reaction, Object> {
    Iterable<Reaction> findByReactionTypeIdAndPostId(Long reactionTypeId, Long postId);

}
