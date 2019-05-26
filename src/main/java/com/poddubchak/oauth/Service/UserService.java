package com.poddubchak.oauth.Service;

import com.poddubchak.oauth.Forms.UserForm;
import com.poddubchak.oauth.Model.Role;
import com.poddubchak.oauth.Model.User;
import com.poddubchak.oauth.Model.UserState;
import com.poddubchak.oauth.Repositories.RoleRepository;
import com.poddubchak.oauth.Repositories.UserRepository;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {



    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(@NonNull String username) throws UsernameNotFoundException {
        return userRepository.findOneByUsername(username).orElseThrow(() ->
                new UsernameNotFoundException("user " + username + " was not found!"));
    }

    public Optional<User> findById(@NonNull Long id) {
        return userRepository.findById(id);
    }

    public void signUp(UserForm userForm) {
        String hashPassword = passwordEncoder.encode(userForm.getPassword());

        Role defaultRole = roleRepository.findOneByName("USER");
        ArrayList<Role> defaultRoleList = new ArrayList<Role>();
        defaultRoleList.add(defaultRole);

        User user = User.builder()
                .username(userForm.getUsername())
                .password(hashPassword)
                .authorities(defaultRoleList)
                .state(UserState.ACTIVE)
                .accountNonExpired(true)
                .accountNonLocked(true)
                .credentialsNonExpired(false)
                .enabled(true)
                .build();

        userRepository.save(user);
    }

}

