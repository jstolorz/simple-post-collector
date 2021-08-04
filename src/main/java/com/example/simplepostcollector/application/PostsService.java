package com.example.simplepostcollector.application;

import com.example.simplepostcollector.domain.Post;
import com.example.simplepostcollector.domain.PostsRepository;
import com.example.simplepostcollector.infrastructure.rest.PostDto;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.java.Log;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;

@ConfigurationProperties(prefix = "posts-service")
@Service
@Log
@Setter
@RequiredArgsConstructor
public class PostsService {

    private final PostsRepository postsRepository;
    private final RestTemplate restTemplate;
    private final PostsMapper postsMapper;

    private String url;

   public List<Post> getPostsByTitleContaining(String text){
       return postsRepository.getByTitleContaining(text);
   }

   @Scheduled(fixedRate = 60 * 1_000)
   public void refreshPosts(){
       log.info("Refreshing posts...");
       getPostsFromRemoteService().forEach(this::saveOrUpdate);
   }

   private List<Post> getPostsFromRemoteService(){
       try{
           var posts = restTemplate.getForObject(url, PostDto[].class);
           if(posts != null){
               return postsMapper.toModel(Arrays.asList(posts));
           }
       }catch (RestClientException exception){
           log.warning("Refresh failed: " + exception.getLocalizedMessage());
       }
       return Collections.emptyList();
   }

   private Post getPostById(Long id){
       return postsRepository.findById(id)
               .orElseThrow(PostNotFoundException::new);

   }

   public void softDeleteById(Long id){

   }

   public void update(Post newPostVersion){
       var post = getPostById(newPostVersion.getId());
       update(post,newPostVersion);
   }

   private void saveOrUpdate(Post post){
       postsRepository.findById(post.getId())
               .ifPresentOrElse(existingPost -> update(existingPost,post, this::isUpdatable),
                       () -> postsRepository.saveAndFlush(post));
   }

   private boolean isUpdatable(Post post){
       return !post.isEdited() && !post.isDeleted();
   }

   private void update (Post post, Post newPostVersion){
       postsMapper.update(newPostVersion,post);
       postsRepository.saveAndFlush(post);
   }

   private void update(Post post, Post newPostVersion, Predicate<Post> condition){
       if(condition.test(post)){
           update(post,newPostVersion);
       }
   }
}
