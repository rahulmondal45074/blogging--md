package com.example.Blogging.Platform.API.model;


import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,scope = Comment.class,property = "commentId")

public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer commentId;
    private String commentBody;
    private LocalDateTime commentCreationTimeStamp;


    @ManyToOne
    @JoinColumn(name = "fk_commenter_id")
    private User commenter;

    @ManyToOne
    @JoinColumn(name = "fk_post_id")
    private Post blogPost;

//    public Comment(Integer commentId, String commentBody, LocalDateTime commentCreationTimeStamp, User commenter, Post blogPost) {
//        this.commentId = commentId;
//        this.commentBody = commentBody;
//        this.commentCreationTimeStamp = commentCreationTimeStamp;
//        this.commenter = commenter;
//        this.blogPost = blogPost;
//    }

    public Comment(Integer commentId, Comment commentBody, LocalDateTime now, User commenter, Post blogPostToBeComment) {
    }
}
