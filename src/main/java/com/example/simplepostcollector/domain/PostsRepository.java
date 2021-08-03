package com.example.simplepostcollector.domain;

public interface PostsRepository {
    Long save(Post post);
    Post findById(Long id);
}
