package com.example.simplepostcollector.domain;

interface PostsRepository {
    Long save(Post post);
    Post findById(Long id);
}
