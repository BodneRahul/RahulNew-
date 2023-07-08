package com.myblog.blogapp.Payload;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class PostDto {
    private long id;
    @Size(min = 2,message = "Post title should have at least 2 characters")
    private String title;

    @Size(min = 10,message = "Post description should have at least 10 characters or more")
    private String description;

    private String content;

}