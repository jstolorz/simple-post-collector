package com.example.simplepostcollector.application;

import com.example.simplepostcollector.domain.Post;
import com.example.simplepostcollector.infrastructure.rest.PostDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PostsMapper {

    @Mapping(target = "edited", ignore = true)
    @Mapping(target = "deleted", ignore = true)
    void update(Post newPostVersion, @MappingTarget Post oldPostVersion);

    Post toModel(PostDto postDto);

    List<Post> toModel(List<PostDto> postDtos);
    List<PostDto> toDtos(List<Post> posts);
}
