package com.example.simplepostcollector.infrastructure.persistence.jpa;


import com.example.simplepostcollector.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
interface SpringJpaPostRepository extends JpaRepository<Post,Long> {

    @Query("select p from Post p where p.deleted = false and p.title like %:title%")
    List<Post> getByTitleContaining(@Param("title") String text);
}
