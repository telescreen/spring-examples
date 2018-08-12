package com.buiha.service;

import com.buiha.models.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends PagingAndSortingRepository<User, Long> {
    Page<User> findAll(Pageable pageable);
    
    @EntityGraph(value = "User.roles.privileges", type = EntityGraph.EntityGraphType.LOAD)
    User readByUsername(@Param("userName") String userName);

    long count();
}
