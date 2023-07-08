package com.myblog.blogapp.Service.impl;

import com.myblog.blogapp.Entities.Comment;
import com.myblog.blogapp.Entities.Post;
import com.myblog.blogapp.Exception.BlogAPIException;
import com.myblog.blogapp.Exception.ResponceNotFoundException;
import com.myblog.blogapp.Payload.CommentDto;
import com.myblog.blogapp.Repository.CommentRepository;
import com.myblog.blogapp.Repository.PostRepository;
import com.myblog.blogapp.Service.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {

    private CommentRepository commentRepository;
    private PostRepository postRepository;
    private ModelMapper modelMapper;

    public CommentServiceImpl(CommentRepository commentRepository, PostRepository postRepository,ModelMapper modelMapper) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
        this.modelMapper=modelMapper;
    }

    @Override
    public CommentDto createComment(long postId,CommentDto commentDto) {
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new ResponceNotFoundException("post", "id", postId)
        );
        Comment comment=mapToComment(commentDto);
        comment.setPost( post);

        Comment newComment = commentRepository.save(comment);


        return mapToDto(newComment);
    }

    @Override
    public List<CommentDto> getCommentBYPostId(long postId) {
        List<Comment> comments = commentRepository.findByPostId(postId);

        return comments.stream().map(comment -> mapToDto(comment)).collect(Collectors.toList());

    }

    @Override
    public CommentDto updateComment(long postId, long id, CommentDto commentDto) {
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new ResponceNotFoundException("post", "id", postId)
        );

        Comment comment = commentRepository.findById(id).orElseThrow(
                () -> new ResponceNotFoundException("comment", "id", id)
        );
        if(!Objects.equals(comment.getPost().getId(), post.getId())){
            throw new BlogAPIException(HttpStatus.BAD_REQUEST, "Comment does not belong to post");
        }

        comment.setName(commentDto.getName());
        comment.setBody(commentDto.getBody());
        comment.setEmail(commentDto.getEmail());

        Comment updatedComment = commentRepository.save(comment);

        return mapToDto(updatedComment);
    }

    @Override
    public void deleteComment(long postId, long commentId) {
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new ResponceNotFoundException("post", "id", postId)
        );
        Comment comment = commentRepository.findById(commentId).orElseThrow(
                () -> new ResponceNotFoundException("comment", "id", commentId)
        );
        if(!Objects.equals(comment.getPost().getId(), post.getId())){
            throw new BlogAPIException(HttpStatus.BAD_REQUEST, "Comment does not belong to post");
        }
        commentRepository.deleteById(commentId);
    }


    Comment mapToComment (CommentDto commentDto){
        Comment comment = modelMapper.map(commentDto, Comment.class);
//        Comment comment= new Comment();
//        comment.setName(commentDto.getName());
//        comment.setBody(commentDto.getBody());
//        comment.setEmail(commentDto.getEmail());

     return comment;
    }

    CommentDto mapToDto(Comment comment){
        CommentDto commentDto = modelMapper.map(comment, CommentDto.class);
//        CommentDto commentDto = new CommentDto();
//        commentDto.setId(comment.getId());
//        commentDto.setName(comment.getName());
//        commentDto.setEmail(comment.getEmail());
//        commentDto.setBody(comment.getBody());
        return commentDto;


    }

}
