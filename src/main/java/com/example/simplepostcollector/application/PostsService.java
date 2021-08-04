package com.example.simplepostcollector.application;

import com.example.simplepostcollector.domain.Post;
import com.example.simplepostcollector.domain.PostsRepository;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.java.Log;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@ConfigurationProperties(prefix = "posts-service")
@Service
@Log
@Setter
@RequiredArgsConstructor
public class PostsService {

    private final PostsRepository postsRepository;
    private final RestTemplate restTemplate;
    private final PostsMapper postsMapper;

   public List<Post> getPostsByTitleContaining(String text){
       return postsRepository.getByTitleContaining(text);
   }



}
