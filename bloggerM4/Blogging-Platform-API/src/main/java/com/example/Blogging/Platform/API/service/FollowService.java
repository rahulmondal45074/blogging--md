package com.example.Blogging.Platform.API.service;

import com.example.Blogging.Platform.API.model.Follow;
import com.example.Blogging.Platform.API.model.User;
import com.example.Blogging.Platform.API.repo.IFollowRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FollowService {

    @Autowired
    IFollowRepo iFollowRepo;

    public boolean findByTargetAndFollower(User follower, User target) {

        List<Follow> follows =  iFollowRepo.findByCurrentUserAndCurrentUserFollower(target,follower);

        return !follows.isEmpty();
    }

    public void startFollowing(User follower, User target) {

        Follow follow = new Follow(null,target,follower);
        iFollowRepo.save(follow);
    }
}
