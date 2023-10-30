package com.example.Blogging.Platform.API.service;

import com.example.Blogging.Platform.API.model.AuthenticationToken;
import com.example.Blogging.Platform.API.repo.IAuthenticationTokenRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationTokenService {

    @Autowired
    IAuthenticationTokenRepo authenticationTokenRepo;


    public void createtoken(AuthenticationToken token) {
        authenticationTokenRepo.save(token);
    }


    public boolean authenticate(String email, String tokenValue) {

        AuthenticationToken token=authenticationTokenRepo.findFirstByTokenValue(tokenValue);
        if (token != null){
            return token.getUser().getUserEmail().equals(email);
        }
        else{
            return false;
        }
    }

    public void deleteToken(String tokenValue) {
        AuthenticationToken token= authenticationTokenRepo.findFirstByTokenValue(tokenValue);
        authenticationTokenRepo.delete(token);
    }
}
