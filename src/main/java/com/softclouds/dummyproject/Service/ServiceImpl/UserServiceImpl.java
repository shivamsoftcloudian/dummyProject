package com.softclouds.dummyproject.Service.ServiceImpl;

import com.softclouds.dummyproject.Request.UserRequest;
import com.softclouds.dummyproject.Service.UserService;
import com.softclouds.dummyproject.base.exceptionHandling.ResourceNotFoundException;
import com.softclouds.dummyproject.model.User;
import com.softclouds.dummyproject.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public User createUser(UserRequest request) {

        logger.info("Adding New User...");
        User user = new User();
        user.setUserName(request.getUserName());
        user.setName(request.getName());
        user.setRole(request.getRole());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user = userRepository.save(user);

        logger.info("User Added... " + user);
        return user;
    }

    @Override
    public User getUserById(Long userId) {
        logger.info("vendor id: " + userId);
        User user = userRepository.findById(userId).orElseThrow(() ->
                new ResourceNotFoundException("User Not Found with ID: " + userId));

        return user;
    }

    @Override
    public List<User> getAllUser() {
        logger.info("getting all users...");
        List<User> allUser = userRepository.findAll();
        return allUser;
    }

}
