package com.softclouds.dummyproject.security.jwt;

import lombok.Data;

import java.util.Set;

@Data
public class JwtResponse {

    private String token;
    private Set<String> roles;
    private String tokenType;
    private Long id;
    private String userName;

    public JwtResponse(String token, String tokenType, Long id, String userName, Set<String> roles) {
        this.id = id;
        this.roles = roles;
        this.token = token;
        this.tokenType = tokenType;
        this.userName = userName;
    }
}
