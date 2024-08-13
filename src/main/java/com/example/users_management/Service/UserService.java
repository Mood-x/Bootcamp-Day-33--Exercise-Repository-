package com.example.users_management.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.users_management.API.ApiException;
import com.example.users_management.Model.User;
import com.example.users_management.Repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository; 

    public List<User> getUsers(){
        return userRepository.findAll(); 
    }

    public void addUser(User user){
        userRepository.save(user); 
    }

    public void updateUser(Integer id, User user){
        User userId = userRepository.findUserById(id); 

        if(userId == null){
            throw new ApiException("User whit this id" + id + ", Not found"); 
        }

        userId.setName(user.getName());
        userId.setUsername(user.getUsername());
        userId.setPassword(user.getPassword());
        userId.setEmail(user.getEmail());
        userId.setAge(user.getAge());
        userId.setRole(user.getRole());
        userRepository.save(user); 
    }

    public void deleteUser(Integer id){
        User userId = userRepository.findUserById(id); 

        if(userId == null){
            throw new ApiException("User whit this id" + id + ", Not found"); 
        }
        userRepository.delete(userId); 
    }

    public User getUserByEmail(String email){
        User user = userRepository.findUserByEmail(email); 

        if(user == null){
            throw new ApiException("User with this email" + email + ", Not found"); 
        }

        return user; 
    }

    public List<User> getUsersBySpecificRole(String role){
        List<User> users = userRepository.findUserByRole(role); 

        if(users == null){
            throw new ApiException("Not found users with this role: " + role);
        }

        return users; 
    }

    public List<User> getUsersBySpecificAgeOrAbove(int age){
        List<User> users = userRepository.findUserByAgeGreaterThanEqual(age);

        if(users == null){
            throw new ApiException("Users with this range of ages " + age + ", Not found"); 
        }
        return users; 
    }
    

    public User getUserByUsernameAndPassword(String username, String password){
        User user = userRepository.findByUsernameAndPassword(username, password); 

        if(user == null){
            throw new ApiException("User with this username: " + username + " and Password: " +  password + ", Not found"); 
        }

        return user; 
    }
}
