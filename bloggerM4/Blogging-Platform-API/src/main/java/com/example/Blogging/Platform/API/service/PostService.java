package com.example.Blogging.Platform.API.service;


import com.example.Blogging.Platform.API.model.Post;
import com.example.Blogging.Platform.API.model.User;
import com.example.Blogging.Platform.API.repo.IPostRepo;

import com.example.Blogging.Platform.API.repo.IUserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;


@Service
public class PostService {


    @Autowired
    IPostRepo iPostRepo;


    @Autowired
    IUserRepo iUserRepo;

    @Autowired
    AuthenticationTokenService authenticationTokenService;

    public String addPost(String email, String tokenValue, Post newPost) {

        if (authenticationTokenService.authenticate(email, tokenValue)) {

            User existingUser = iUserRepo.findFirstByUserEmail(email);
            newPost.setPostOwner(existingUser);

            newPost.setPostCreatedTimeStamp(LocalDateTime.now());
            iPostRepo.save(newPost);
            return newPost.getPostType() + " posted";
        } else {
            return "un authenticated access";
        }
    }

    public Post getPostById(String email, String tokenValue, Integer postId) {
        if (authenticationTokenService.authenticate(email, tokenValue)) {

            return iPostRepo.findById(postId).orElseThrow();

        }
        return null;
    }

            //****////***** update post ***
    public String  updatePostById(String email, String tokenValue, Integer postId, String caption) {


        if (authenticationTokenService.authenticate(email, tokenValue)) {

          Post existingPost = iPostRepo.findById(postId).orElseThrow();
          if (existingPost.getPostOwner().getUserEmail().equals(email)){

              existingPost.setPostCaption(caption);
              iPostRepo.save(existingPost);
              return "Blog post updated" ;
          }

        }
        return "invalid credentials";
    }

    public String removePostById(String email, String tokenValue, Integer postId) {
        if (authenticationTokenService.authenticate(email, tokenValue)) {

            iPostRepo.deleteById(postId);
            return "post has been deleted";

        }
        return "invalid credentials";
    }
}
