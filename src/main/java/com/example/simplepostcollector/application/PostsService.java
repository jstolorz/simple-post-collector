package com.example.simplepostcollector.application;

import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.java.Log;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;

@ConfigurationProperties(prefix = "posts-service")
@Service
@Log
@Setter
@RequiredArgsConstructor
class PostsService {

}
