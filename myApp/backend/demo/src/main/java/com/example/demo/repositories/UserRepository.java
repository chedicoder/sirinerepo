package com.example.demo.repositories;


import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
	Optional<User> findByUsername(String username);
    Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);
    @Query("SELECT u FROM User u WHERE u.username = :username AND u.role.roleName = :roleName")
    List <User> findByUsernameAndRoleName(@Param("username") String username, @Param("roleName") String roleName);
	

}
//optional=l'ensemble peut etre vide ou non
