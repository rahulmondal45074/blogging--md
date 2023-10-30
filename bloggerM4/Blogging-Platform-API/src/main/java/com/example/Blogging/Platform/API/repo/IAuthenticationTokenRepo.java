package com.example.Blogging.Platform.API.repo;

import com.example.Blogging.Platform.API.model.AuthenticationToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAuthenticationTokenRepo extends JpaRepository<AuthenticationToken,Integer> {


    AuthenticationToken findFirstByTokenValue(String tokenValue);
}
