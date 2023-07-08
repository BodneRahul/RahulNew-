package com.myblog.blogapp.Test;

import com.myblog.blogapp.Payload.PostDto;
import com.myblog.blogapp.Service.PostService;
import org.junit.runner.RunWith;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.testng.annotations.Test;
@RunWith(SpringRunner.class)
@SpringBootTest
class BlogappApplicationTests {


private PostService postService;
    @Test
    void contextLoads( PostDto postDto) {

        PostDto byId = postService.findById(postDto.getId());
        System.out.println(byId);

    }



}
