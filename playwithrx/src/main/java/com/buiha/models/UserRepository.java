package com.buiha.models;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends PagingAndSortingRepository<User, Long> {
    Page<User> findAll(Pageable pageable);

    @EntityGraph(value = "User.roles.privileges", type = EntityGraph.EntityGraphType.LOAD)
    User readByUsername(String userName);

    long count();
}
