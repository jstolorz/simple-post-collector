package com.example.simplepostcollector.infrastructure.rest;

import com.example.simplepostcollector.application.PostNotFoundException;
import com.example.simplepostcollector.application.PostsMapper;
import com.example.simplepostcollector.application.PostsService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/posts")
@RestController
@RequiredArgsConstructor
class PostController {

    private final PostsService postsService;
    private final PostsMapper postsMapper;

    @ApiOperation(value = "Get all posts", notes = "all posts from repository optional by title")
    @GetMapping
    public List<PostDto> getPosts(@ApiParam(value = "post title", example = "some text")
                                      @RequestParam(name = "title", defaultValue = "") String text){
           var posts = postsService.getPostsByTitleContaining(text);
           return postsMapper.toDtos(posts);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{id}")
    public void update(@RequestBody PostDto postDto, @PathVariable Long id){
        postDto.setId(id);
        var newPostVersion = postsMapper.toModel(postDto);
        postsService.update(newPostVersion);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deletePost(@PathVariable Long id){
        postsService.softDeleteById(id);
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(PostNotFoundException.class)
    public void  onPostNotFound(PostNotFoundException exceptionv){
    }

}
