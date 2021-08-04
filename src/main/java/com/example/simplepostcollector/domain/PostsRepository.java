package com.example.simplepostcollector.domain;

import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PostsRepository {
    Long save(Post post);
    Optional<Post> findById(Long id);
    List<Post> getByTitleContaining(String text);

    void saveAndFlush(Post post);
}
