package com.myblog.blogapp.Service;

import com.myblog.blogapp.Payload.PostDto;
import com.myblog.blogapp.Payload.PostResponse;

import java.util.List;

public interface PostService {
    PostDto create(PostDto postDto);



    PostDto findById(long id);

    List<PostDto> Getall();
}
