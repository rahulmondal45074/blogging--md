package com.example.Blogging.Platform.API.controller;

import com.example.Blogging.Platform.API.model.Comment;
import com.example.Blogging.Platform.API.model.Post;
import com.example.Blogging.Platform.API.service.CommentService;
import com.example.Blogging.Platform.API.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@RestController
public class CommentController {

    @Autowired
    CommentService commentService;

    @PostMapping("comment/post/{postId}")
    public String addComment(@RequestParam String email, @RequestParam String tokenValue, @PathVariable Integer postId,@RequestBody Comment commentBody  )
    {
        return commentService.addComment(email,tokenValue,commentBody,postId);
    }


    @GetMapping("comment/{commentId}")
    public Comment getCommentById(@RequestParam String email, @RequestParam String tokenValue , @PathVariable Integer commentId){

        return commentService.getCommentById(email,tokenValue,commentId);
    }


    @PutMapping("comment/{commentId}")
    public String updateCommentById (@RequestParam String email, @RequestParam String tokenValue, @PathVariable Integer commentId, @RequestBody String commentBody){
        return commentService.updateCommentById(email,tokenValue,commentId,commentBody);
    }



    @DeleteMapping("post/comment/{commentId}")
    public String removeComment(@RequestParam String email, @RequestParam String tokenValue,
                                @PathVariable Integer commentId)
    {
        return commentService.removeComment(email,tokenValue,commentId);
    }

//    @GetMapping("comments/{postId}")
//    public List<Comment> getCommentsById(@RequestParam String email, @RequestParam String tokenValue, @PathVariable Integer postId){
//        return commentService.getCommentsById(email,tokenValue,postId);
//    }

//
//    @PutMapping("post/{postId}/{caption}")
//    public String  updatePostById(@RequestParam String email, @RequestParam String tokenValue , @PathVariable Integer postId,String caption){
//        return postService.updatePostById(email,tokenValue,postId,caption);
//    }
//
//    @DeleteMapping("post/{postId}")
//    public String removePostById(@RequestParam String email, @RequestParam String tokenValue , @PathVariable Integer postId){
//        return postService.removePostById(email,tokenValue,postId);
//    }
}
