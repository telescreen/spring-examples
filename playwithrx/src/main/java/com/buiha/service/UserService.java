package com.buiha.service;

import com.buiha.forms.UserForm;
import com.buiha.models.User;
import com.buiha.models.Role;
import com.buiha.models.Privilege;
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
import java.util.Arrays;
import java.util.Optional;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Set;

import java.util.stream.Collectors;


@Service
public class UserService implements UserDetailsService {

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PrivilegeRepository privilegeRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.readByUsername(username);
        return new org.springframework.security.core.userdetails.User(
                user.getUsername(), user.getPassword(), user.isEnabled(),
                true, true, true,
                getAuthorities(user.getRoles()));
    }

    @Transactional
    public User save(UserForm userForm) {
        userForm.setPassword(passwordEncoder.encode(userForm.getPassword()));
        User user = new User(userForm.getUsername(), userForm.getPassword(), false);
        user.setRoles(Arrays.asList(new Role("ROLE_USER")).stream().collect(Collectors.toSet()));
        return userRepository.save(user);
    }

    @Transactional
    public Role createRoleIfNotExisted(String name, Set<Privilege> privileges) {
        Optional<Role> role = roleRepository.findByName(name);
        Role newRole = null;
        if (!role.isPresent()) {
            newRole = new Role(name);
            newRole.setPrivileges(privileges);
            roleRepository.save(newRole);
        }
        return newRole;
    }

    @Transactional
    public Privilege createPrivilegeIfNotExisted(String name) {
        Optional<Privilege> privilege = privilegeRepository.findByName(name);
        Privilege newPrivilege = null;
        if (!privilege.isPresent()) {
            newPrivilege = new Privilege(name);
            privilegeRepository.save(newPrivilege);
        }
        return newPrivilege;
    }

    private Collection<? extends GrantedAuthority> getAuthorities(
            Set<Role> roles) {

        return getGrantedAuthorities(getPrivileges(roles));
    }

    private Set<String> getPrivileges(Set<Role> roles) {
        Set<String> privileges = new LinkedHashSet<>();
        Set<Privilege> collection = new LinkedHashSet<>();
        for (Role role : roles) {
            collection.addAll(role.getPrivileges());
        }
        for (Privilege item : collection) {
            privileges.add(item.getName());
        }
        return privileges;
    }

    private Set<GrantedAuthority> getGrantedAuthorities(Set<String> privileges) {
        Set<GrantedAuthority> authorities = new LinkedHashSet<>();
        for (String privilege : privileges) {
            authorities.add(new SimpleGrantedAuthority(privilege));
        }
        return authorities;
    }


}
