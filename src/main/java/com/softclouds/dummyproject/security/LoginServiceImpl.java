package com.softclouds.dummyproject.security;

import com.softclouds.dummyproject.base.exceptionHandling.ResourceNotFoundException;
import com.softclouds.dummyproject.model.Enum.Role;
import com.softclouds.dummyproject.model.User;
import com.softclouds.dummyproject.repository.UserRepository;
import com.softclouds.dummyproject.security.jwt.JwtResponse;
import com.softclouds.dummyproject.security.jwt.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LoginServiceImpl implements LoginService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    private final JwtUtil jwtUtil;

    @Override
    public void generateUsers() {

        if (userRepository.findByUserName("admin").isEmpty()) {
            var user = new User();
            user.setUserName("admin");
            user.setPassword(passwordEncoder.encode("secret"));
            user.setRole(Role.ROLE_ADMIN);
            userRepository.save(user);
        }

        if (userRepository.findByUserName("user").isEmpty()) {
            var user = new User();
            user.setUserName("user");
            user.setPassword(passwordEncoder.encode("secret"));
            user.setRole(Role.ROLE_USER);
            userRepository.save(user);
        }
    }

    @Override
    public JwtResponse authenticateUser(JwtRequest jwtRequest) throws BadCredentialsException {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(jwtRequest.getUsername(), jwtRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        Set<String> role = userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toSet());
        String token = jwtUtil.generateJwtToken(authentication);
        return new JwtResponse(token, "Bearer", userDetails.getId(), userDetails.getUsername(), role);
    }

    public User getAuthenticatedUser() {
        return getCurrentUser();
    }

    private User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        return userRepository.findById(userDetails.getId())
                .orElseThrow(() -> new ResourceNotFoundException("User Not Found"));
    }

}
