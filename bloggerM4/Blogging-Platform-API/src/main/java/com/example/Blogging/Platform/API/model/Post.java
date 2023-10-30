package com.example.Blogging.Platform.API.model;


import com.example.Blogging.Platform.API.model.enums.PostType;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,scope = Post.class,property = "postId")

public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer postId;

    @NotBlank(message = "postCaption should not be null")
    private String postCaption;

    @Enumerated(value = EnumType.STRING)
    private PostType postType;
    private LocalDateTime postCreatedTimeStamp;


    @ManyToOne
    @JoinColumn(name = "fk_owner_user_id")
    private User postOwner;


}
