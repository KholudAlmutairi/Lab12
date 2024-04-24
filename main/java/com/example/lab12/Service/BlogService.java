package com.example.lab12.Service;

import com.example.lab12.Api.ApiException;
import com.example.lab12.Model.Blog;
import com.example.lab12.Model.User;
import com.example.lab12.Repository.AuthRepository;
import com.example.lab12.Repository.BlogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BlogService {

    private final BlogRepository blogRepository;
    private final AuthRepository authRepository;

    //• Add All CRUD
    //• Get All user blogs
    //• Get blog by Id
    //• Get blog by title




    //• Get All user blogs
    public List<Blog> getAllBlogs(){

        return blogRepository.findAll();

    }

    //• Get blog by Id
    public List<Blog> getMyBlogs(Integer userId){
        User user=authRepository.findUserById(userId);
        return blogRepository.findAllByUser(user);

    }

    //• Get blog by title
    public Blog getBlogByTitle(String title) {
        return blogRepository.findByTitle(title);
    }

    public void addBlog(Integer userId,Blog blog){
        User user=authRepository.findUserById(userId);
        blog.setUser(user);
        blogRepository.save(blog);

    }

    public void deleteBlog(Integer userId,Integer blogId){
        Blog b=blogRepository.findBlogById(blogId);
        if(b==null){
            throw new ApiException("Not found blog id");
        }
        if(!b.getUser().getId().equals(userId)){
            throw new ApiException("Not found user id");
        }
        blogRepository.delete(b);
    }


    public void updateBlog(Integer userId,Blog blog,Integer blogId) {
        Blog b=blogRepository.findBlogById(blogId);
        if(b==null){
            throw new ApiException("Not found blog id");
        }
        if(b.getUser().getId()!=userId){
            throw new ApiException("Not found user id");
        }
        b.setTitle(blog.getTitle());
        blogRepository.save(b);


    }




}
