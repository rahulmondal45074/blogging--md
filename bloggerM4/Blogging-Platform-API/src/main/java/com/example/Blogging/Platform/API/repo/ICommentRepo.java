package com.example.Blogging.Platform.API.repo;

import com.example.Blogging.Platform.API.model.Comment;
import com.example.Blogging.Platform.API.model.Post;
import org.hibernate.mapping.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICommentRepo extends JpaRepository<Comment,Integer> {

}
