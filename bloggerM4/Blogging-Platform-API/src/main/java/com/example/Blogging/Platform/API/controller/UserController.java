package com.example.Blogging.Platform.API.controller;

import com.example.Blogging.Platform.API.model.Post;
import com.example.Blogging.Platform.API.model.User;
import com.example.Blogging.Platform.API.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@RestController
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("user/signUp")
    public String userSignUp ( @RequestBody User newUser){
        return userService.userSignUp(newUser);
    }

    @PostMapping("user/signIn/{email}/{password}")
    public String userSignIn(  @Valid @PathVariable String email, @PathVariable String password){
        return userService.userSignIn(email,password);
    }

    @DeleteMapping("user/signOut")
    public String userSignOut(@RequestParam String email, @RequestParam String token)
    {
        return userService.userSignOut(email,token);
    }

    @PostMapping("follow/user/{targetUserId}")
    public String followTarget(@RequestParam String email, @RequestParam String tokenValue, @PathVariable Integer targetUserId)
    {
        return userService.followTarget(email,tokenValue,targetUserId);
    }

}
