package com.example.users_management.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.users_management.Model.User;


public interface UserRepository extends JpaRepository<User, Integer>{
    User findUserById(Integer id);
    User findUserByEmail(String email); 
    List<User> findUserByRole(String role); 
    List<User> findUserByAgeGreaterThanEqual(int age);

    @Query("select u from User u where u.username = :username AND u.password = :password")
    User findByUsernameAndPassword(@Param("username") String username,@Param("password") String password); 

}
