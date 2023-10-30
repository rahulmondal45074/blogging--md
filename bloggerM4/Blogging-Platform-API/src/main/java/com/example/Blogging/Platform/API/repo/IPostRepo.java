package com.example.Blogging.Platform.API.repo;

import com.example.Blogging.Platform.API.model.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;


public interface IPostRepo  extends JpaRepository<Post,Integer> {


}
