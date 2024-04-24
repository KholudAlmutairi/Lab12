package com.example.lab12.Controller;

import com.example.lab12.Model.Blog;
import com.example.lab12.Model.User;
import com.example.lab12.Service.BlogService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/blog")
@RequiredArgsConstructor
public class BlogController {

    private final BlogService blogService;
    //Get All user blogs
    @GetMapping("/get-all")//admin
    public ResponseEntity getAllBlogs() {
        return ResponseEntity.status(200).body(blogService.getAllBlogs());

    }

    //Get blog by Id
    @GetMapping("/get")
    public ResponseEntity getMyBlogs(@AuthenticationPrincipal User user) {
        return ResponseEntity.status(200).body(blogService.getMyBlogs(user.getId()));

    }

    // Get blog by title
    @GetMapping("/getBlogBytitle/{title}")
    public Blog getBlogByTitle(@AuthenticationPrincipal @PathVariable String title) {
        return blogService.getBlogByTitle(title);
    }


    @PostMapping("/add")
    public ResponseEntity addBlogs(@AuthenticationPrincipal User user, @RequestBody @Valid Blog blog){
        blogService.addBlog(user.getId(), blog);
        return ResponseEntity.status(200).body("add blog");
    }

    @DeleteMapping("/delete/{blogId}")
    public ResponseEntity deleteBlog(@AuthenticationPrincipal User user,@PathVariable Integer blogId ){
        blogService.deleteBlog(user.getId(), blogId);
        return ResponseEntity.status(200).body("delete blog");
    }

    @PostMapping("/update/{blogId}")
    public ResponseEntity updateBlog(@AuthenticationPrincipal User user,@RequestBody @Valid Blog blog,@PathVariable Integer blogId){
        blogService.updateBlog(user.getId(), blog,blogId);
        return ResponseEntity.status(200).body("update");
    }







}
