package com.infiniteskills.maven.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.infiniteskills.maven.entities.Post;

public interface PostRepository extends JpaRepository<Post, Integer> {

}
