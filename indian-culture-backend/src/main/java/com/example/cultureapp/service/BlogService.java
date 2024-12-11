package com.example.cultureapp.service;

import com.example.cultureapp.model.Blog;

import java.util.List;

public interface BlogService {
    Blog createBlog(Blog blog);
    List<Blog> getAllBlogs();
}
