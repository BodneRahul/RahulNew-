package com.myblog.blogapp.Repository;

import com.myblog.blogapp.Entities.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post,Long> {
}
