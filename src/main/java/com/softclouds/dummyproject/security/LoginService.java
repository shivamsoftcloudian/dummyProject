package com.softclouds.dummyproject.security;

import com.softclouds.dummyproject.security.jwt.JwtResponse;

public interface LoginService {

    void generateUsers();

    JwtResponse authenticateUser(JwtRequest jwtRequest);

}
