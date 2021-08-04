package com.example.simplepostcollector.infrastructure.rest;

import com.example.simplepostcollector.application.PostsMapper;
import com.example.simplepostcollector.application.PostsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/posts")
@RestController
@RequiredArgsConstructor
class PostController {

    private final PostsService postsService;
    private final PostsMapper postsMapper;

    @GetMapping
    public List<PostDto> getPosts(@RequestParam(name = "title", defaultValue = "") String text){
           var posts = postsService.getPostsByTitleContaining(text);
           return postsMapper.toDtos(posts);
    }

}
