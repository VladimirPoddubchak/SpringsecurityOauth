package com.poddubchak.oauth.Service;

import com.poddubchak.oauth.Model.Role;
import com.poddubchak.oauth.Repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    RoleRepository roleRepository;

    @Override
    public void addRole(String name) {

        Role role = Role.builder()
                .name(name.toUpperCase())
                .build();

        roleRepository.save(role);
    }

    @Override
    public Role findByName(String role) {
        return roleRepository.findOneByName(role);
    }

    @Override
    public List<Role> findAll() {
        return (List<Role>) roleRepository.findAll();
    }

//    @PostConstruct
//    private void init(){
//        addRole("USER");
//        addRole("ADMIN");
//     }
}
