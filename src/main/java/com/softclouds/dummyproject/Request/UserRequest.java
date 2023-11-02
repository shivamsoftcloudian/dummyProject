package com.softclouds.dummyproject.Request;

import com.softclouds.dummyproject.model.Enum.Role;
import lombok.Data;

@Data
public class UserRequest {

    private String name;
    private String userName;
    private String password;
    private Role role;
}
