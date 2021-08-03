package com.example.simplepostcollector.infrastructure.persistence.jpa;

import com.example.simplepostcollector.domain.Post;
import com.example.simplepostcollector.domain.PostsRepository;
import lombok.Data;
import org.springframework.stereotype.Repository;

@Data
@Repository
class JpaPostsRepository implements PostsRepository {

    private final SpringJpaPostRepository springJpaPostRepository;

    @Override
    public Long save(final Post post) {
        return springJpaPostRepository.save(post).getId();
    }

    @Override
    public Post findById(final Long id) {
        return springJpaPostRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException());
    }
}
