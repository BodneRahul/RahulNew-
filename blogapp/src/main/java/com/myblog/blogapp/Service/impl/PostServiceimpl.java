package com.myblog.blogapp.Service.impl;

import com.myblog.blogapp.Entities.Post;
import com.myblog.blogapp.Exception.ResponceNotFoundException;
import com.myblog.blogapp.Payload.PostDto;
import com.myblog.blogapp.Payload.PostResponse;
import com.myblog.blogapp.Repository.PostRepository;
import com.myblog.blogapp.Service.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PostServiceimpl implements PostService {
   private PostRepository postRepository;
   private ModelMapper mapper;

    public PostServiceimpl(PostRepository postRepository,ModelMapper mapper) {
        this.postRepository = postRepository;
        this.mapper=mapper;
       }
    public Post mapToEntity(PostDto postDto){
        Post post = mapper.map(postDto, Post.class);

//        Post post =new Post();
//        post.setTitle(postDto.getTitle());
//        post.setDescription(postDto.getDescription());
//        post.setContent(postDto.getContent());
        return post;}
    public PostDto mapToDTO(Post post){
        PostDto postDto = mapper.map(post, PostDto.class);
//        PostDto postDto=new PostDto();
//        postDto.setId(post.getId());
//        postDto.setContent(post.getContent());
//        postDto.setTitle(post.getTitle());
//        postDto.setDescription(post.getDescription());
        return postDto;

    }


    @Override
    public PostDto create(PostDto postDto) {
        Post post = mapToEntity(postDto);
        postRepository.save(post);
        PostDto dto = mapToDTO(post);
        return dto;
    }



    @Override
    public PostDto findById(long id) {
        Post post = postRepository.findById(id).orElseThrow(
                () -> new ResponceNotFoundException("Post", "id", id));
        PostDto dto = mapToDTO(post);
        return dto;
    }

    @Override
    public List<PostDto> Getall() {

        List<Post> posts = postRepository.findAll();
        List<PostDto> collect = posts.stream().map(x -> mapToDTO(x)).collect(Collectors.toList());

        return collect;
    }


}
