package com.example.Blogging.Platform.API.service;

import com.example.Blogging.Platform.API.model.Comment;
import com.example.Blogging.Platform.API.model.Post;
import com.example.Blogging.Platform.API.model.User;
import com.example.Blogging.Platform.API.repo.ICommentRepo;
import com.example.Blogging.Platform.API.repo.IPostRepo;
import com.example.Blogging.Platform.API.repo.IUserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CommentService {

    @Autowired
    ICommentRepo iCommentRepo;

    @Autowired
    IPostRepo iPostRepo;

    @Autowired
    AuthenticationTokenService authenticationTokenService;

    @Autowired
    IUserRepo  iUserRepo;





    public String addComment(String email, String tokenValue, Comment commentBody, Integer postId) {

        if(authenticationTokenService.authenticate(email,tokenValue)){

            Post blogPostToBeComment = iPostRepo.findById(postId).orElseThrow();

            User commenter = iUserRepo.findFirstByUserEmail(email);

           // Comment newComment = new Comment(null,commentBody,LocalDateTime.now(), commenter, blogPostToBeComment);

            commentBody.setCommenter(commenter);
            commentBody.setBlogPost(blogPostToBeComment);

            iCommentRepo.save(commentBody);
            return commenter.getUserHandle()+" comment on" + postId;
        }
        else{
            return "un authorized access";
        }
    }

                ////**** delete ***

    public String removeComment(String email, String tokenValue, Integer commentId) {

        if(authenticationTokenService.authenticate(email,tokenValue)) {
            Comment comment =iCommentRepo.findById(commentId).orElseThrow();


            Post blogPostComment = comment.getBlogPost();


            if(authorizeCommentRemover(email,blogPostComment,comment))
            {
                iCommentRepo.deleteById(commentId);
                return "comment deleted";
            }
            else {
                return "Not authorized!!";
            }

        }
        else {
            return "Un Authenticated access!!!";
        }

    }

    private boolean authorizeCommentRemover(String email, Post blogPostComment, Comment comment) {
        User  potentialRemover = iUserRepo.findFirstByUserEmail(email);
        return potentialRemover.equals(blogPostComment.getPostOwner() )|| potentialRemover.equals(comment.getCommenter());
    }


    ///////****** getComment ***
    public Comment getCommentById(String email, String tokenValue, Integer commentId) {
        if(authenticationTokenService.authenticate(email,tokenValue)) {

            return iCommentRepo.findById(commentId).orElseThrow();

        }
        return null;
    }

    public String updateCommentById(String email, String tokenValue, Integer commentId, String commentBody) {

        if(authenticationTokenService.authenticate(email,tokenValue)) {
            Comment comment = iCommentRepo.findById(commentId).orElseThrow();
            comment.setCommentBody(commentBody);
            return "comment updated";
        }
        else {
            return "Un Authenticated access!!!";
        }
    }

//    public List<Comment> getCommentsById(String email, String tokenValue, Integer commentId) {
//        if(authenticationTokenService.authenticate(email,tokenValue)) {
//
//        }
//
//
//    }
}
