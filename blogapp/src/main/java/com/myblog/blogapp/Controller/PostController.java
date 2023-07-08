package com.myblog.blogapp.Controller;

import com.myblog.blogapp.Payload.PostDto;
import com.myblog.blogapp.Payload.PostResponse;
import com.myblog.blogapp.Service.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/posts")
@CrossOrigin(origins = "http://localhost:4200/")
public class PostController {
    private PostService postService;
    public PostController(PostService postService) {this.postService = postService;}


  @PostMapping
    public ResponseEntity<Object>createPost(@Valid @RequestBody PostDto postDto,BindingResult bindingResult){
     if (bindingResult.hasErrors()){
         System.out.println(bindingResult.getFieldError());
         return new ResponseEntity<>(bindingResult.getFieldError().getDefaultMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
     }

      return  new ResponseEntity<> (postService.create(postDto),HttpStatus.CREATED);}

    @GetMapping("/{id}")
   public ResponseEntity<PostDto>GetById(@PathVariable("id") long id){
     return ResponseEntity.ok(postService.findById(id));

    }
    @GetMapping
    public List<PostDto>GetAll(){
        List<PostDto> getall = postService.Getall();
    return getall;
    }



}
