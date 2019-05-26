package com.poddubchak.oauth.Repositories;


import com.poddubchak.oauth.Model.User ;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Optional;

/**
 * 09.05.2019
 * UserRepository
 *
 * @author Poddubchak Vladimir
 * @version v1.0
 */

@RepositoryRestResource
public interface UserRepository extends CrudRepository<User,Long> {


    Optional<User> findOneByUsername(String username);

}
