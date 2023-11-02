package com.softclouds.dummyproject.Service;

import com.softclouds.dummyproject.Request.UserRequest;
import com.softclouds.dummyproject.model.User;

import java.util.List;

public interface UserService {
    User createUser(UserRequest request);

    User getUserById(Long userId);

    List<User> getAllUser();


}
