package com.udemy.rest.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.udemy.rest.entities.Post;

public interface PostRepository extends JpaRepository<Post, Long> {

}
