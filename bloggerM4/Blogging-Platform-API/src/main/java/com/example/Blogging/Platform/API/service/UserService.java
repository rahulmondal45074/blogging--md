package com.example.Blogging.Platform.API.service;

import com.example.Blogging.Platform.API.model.AuthenticationToken;
import com.example.Blogging.Platform.API.model.Post;
import com.example.Blogging.Platform.API.model.User;
import com.example.Blogging.Platform.API.repo.IUserRepo;
import com.example.Blogging.Platform.API.service.hashingUtilty.PasswordEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;

@Service
public class UserService {

    @Autowired
    IUserRepo iUserRepo;

    @Autowired
    AuthenticationTokenService authenticationTokenService;

    @Autowired
    PostService postService;


    @Autowired
    FollowService followService;



    public String userSignUp(User newUser) {

        String newEmail = newUser.getUserEmail();
        User existingUser = iUserRepo.findFirstByUserEmail(newEmail);

        if ( existingUser != null){
            return "email already in use";
        }

        String signPassword = newUser.getUserPassword();

        try {
            String encryptedPassword = PasswordEncryptor.encrypt(signPassword);

            newUser.setUserPassword(encryptedPassword);
            iUserRepo.save(newUser);
            return "Blog User register ";

        } catch (NoSuchAlgorithmException e) {
            return "Internal server issue while saving password , try again latter !!!";
        }
    }





    public String userSignIn(String email, String password) {
        User existingUser = iUserRepo.findFirstByUserEmail(email);

        if (existingUser == null){
            return "Not a valid email , please sign up first";
        }

        try {
            String encryptedPassword = PasswordEncryptor.encrypt(password);

            if (existingUser.getUserPassword().equals(password)){

                AuthenticationToken token= new AuthenticationToken(existingUser);
                authenticationTokenService.createtoken(token);

                return token.getTokenValue();
            }
            else{
                 return "invalid credential";
            }

        } catch (NoSuchAlgorithmException e) {
            return "internal server issue while saving password ,try again latter";
        }

    }

    public String userSignOut(String email, String tokenValue) {

        if (authenticationTokenService.authenticate(email,tokenValue)){
            authenticationTokenService.deleteToken(tokenValue);
            return "sign out successful";
        }
        else{
            return "un Authenticate access";
        }
    }


    public String followTarget(String email, String tokenValue, Integer targetUserId) {



            if(authenticationTokenService.authenticate(email,tokenValue)) {

                User follower = iUserRepo.findFirstByUserEmail(email);
                User target = iUserRepo.findById(targetUserId).orElseThrow();

                if(authorizeToFollow(follower,target))
                {
                    followService.startFollowing(follower,target);
                    return follower.getUserHandle() + " started following " + target.getUserHandle();
                }
                else {
                    return "Already follows, cannot re-follow";
                }

            }
            else {
                return "Un Authenticated access!!!";
            }


    }

    private boolean authorizeToFollow(User follower, User target) {

        //check if already follows or not

        boolean followingExist =  followService.findByTargetAndFollower(follower,target);

        return !followingExist && !follower.equals(target);

    }
}
