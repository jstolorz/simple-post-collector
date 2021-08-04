package com.example.simplepostcollector.domain;

import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PostsRepository {
    Long save(Post post);
    Post findById(Long id);
    List<Post> getByTitleContaining(String text);
}
