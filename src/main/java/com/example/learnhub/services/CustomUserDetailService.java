package com.example.learnhub.services;

import com.example.learnhub.enums.Role;
import com.example.learnhub.model.User;
import com.example.learnhub.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomUserDetailService implements UserDetailsService {

    private final UserRepository userRepository;

    @Autowired
    public CustomUserDetailService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Username not found!"));

        List<GrantedAuthority> authorities;
        if (Role.ROLE_ADMIN.equals(user.getRole())) {
            authorities = AuthorityUtils.createAuthorityList(Role.ROLE_STUDENT.name(), Role.ROLE_ADMIN.name());
        } else {
            authorities = AuthorityUtils.createAuthorityList(Role.ROLE_STUDENT.name());
        }

        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                authorities
        );
    }
}
