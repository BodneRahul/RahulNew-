package com.myblog.blogapp.Service;

import com.myblog.blogapp.Payload.CommentDto;

import java.util.List;

public interface CommentService {

    CommentDto createComment(long postId,CommentDto commentDto);

    List<CommentDto> getCommentBYPostId(long postId);

    CommentDto updateComment(long postId, long id, CommentDto commentDto);

    void deleteComment(long postId, long commentId);
}
