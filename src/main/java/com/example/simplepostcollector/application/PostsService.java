package com.example.simplepostcollector.application;

import com.example.simplepostcollector.domain.PostsRepository;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.java.Log;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@ConfigurationProperties(prefix = "posts-service")
@Service
@Log
@Setter
@RequiredArgsConstructor
class PostsService {

    private final PostsRepository postsRepository;
    private final RestTemplate restTemplate;
    private final PostsMapper postsMapper;



}
