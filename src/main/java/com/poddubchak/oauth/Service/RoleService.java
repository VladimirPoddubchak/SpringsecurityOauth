package com.poddubchak.oauth.Service;

import com.poddubchak.oauth.Model.Role;
import org.springframework.stereotype.Service;

import java.util.List;



public interface RoleService {

    public void addRole(String name);

    public Role findByName(String name);

    public List<Role> findAll();


}
