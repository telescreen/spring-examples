package com.buiha.repository;

import com.buiha.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

/**
 * Created by tal on 2/28/17.
 */
public interface UserRepository extends CrudRepository<User, Long> {

    @Query("SELECT u FROM User u JOIN FETCH u.roles WHERE u.username = :userName")
    User findByUsername(@Param("userName") String userName);
}
