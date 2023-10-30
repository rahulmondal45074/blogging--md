package com.example.Blogging.Platform.API.repo;

import com.example.Blogging.Platform.API.model.Follow;
import com.example.Blogging.Platform.API.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IFollowRepo  extends JpaRepository<Follow,Integer> {
    List<Follow> findByCurrentUserAndCurrentUserFollower(User target, User follower);
}
