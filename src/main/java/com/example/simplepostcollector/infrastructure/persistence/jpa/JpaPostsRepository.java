package com.example.simplepostcollector.infrastructure.persistence.jpa;

import com.example.simplepostcollector.domain.Post;
import com.example.simplepostcollector.domain.PostsRepository;
import lombok.Data;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Data
@Repository
class JpaPostsRepository implements PostsRepository {

    private final SpringJpaPostRepository springJpaPostRepository;

    @Override
    public Long save(final Post post) {
        return springJpaPostRepository.save(post).getId();
    }

    @Override
    public Optional<Post> findById(final Long id) {
        return springJpaPostRepository.findById(id);
    }

    @Override
    public List<Post> getByTitleContaining(final String text) {
        return springJpaPostRepository.getByTitleContaining(text);
    }

    @Override
    public void saveAndFlush(final Post post) {
        springJpaPostRepository.saveAndFlush(post);
    }
}
