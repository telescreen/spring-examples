package com.buiha.service;

import com.buiha.form.UserForm;
import com.buiha.model.Role;
import com.buiha.model.User;
import com.buiha.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by tal on 3/5/17.
 */

@Service
public class UserService implements UserDetailsService {

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = repository.findByUsername(username);

        Set<GrantedAuthority> grantedAuthoritySet = new HashSet<>();
        for (Role role: user.getRoles()) {
            grantedAuthoritySet.add(new SimpleGrantedAuthority(role.getRole()));
        }

        return new org.springframework.security.core.userdetails.User(
                user.getUsername(), user.getPassword(), grantedAuthoritySet);
    }

    public User save(UserForm userForm) {
        userForm.setPassword(passwordEncoder.encode(userForm.getPassword()));
        User user = new User(userForm.getUsername(), userForm.getPassword());
        user.getRoles().add(new Role("USER", user));
        return repository.save(user);
    }
}
