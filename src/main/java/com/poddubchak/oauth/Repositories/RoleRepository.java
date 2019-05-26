package com.poddubchak.oauth.Repositories;


import com.poddubchak.oauth.Model.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * 09.05.2019
 * RoleRepository
 *
 * @author Poddubchak Vladimir
 * @version v1.0
 */


@RepositoryRestResource
public interface RoleRepository extends CrudRepository<Role, Long> {
    Role findOneByName(String role);
}
