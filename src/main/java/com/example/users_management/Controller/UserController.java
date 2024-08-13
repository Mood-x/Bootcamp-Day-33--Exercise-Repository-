package com.example.users_management.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.users_management.API.ApiResponse;
import com.example.users_management.Model.User;
import com.example.users_management.Service.UserService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    public ResponseEntity getUsers(){
        if(userService.getUsers().isEmpty()){
            return ResponseEntity.status(400).body(new ApiResponse("Not found Users")); 
        }

        return ResponseEntity.status(200).body(userService.getUsers()); 
    }

    @PostMapping("/add")
    public ResponseEntity addUser(@Valid @RequestBody User user, Errors err){
        if(err.hasErrors()){
            String message = err.getFieldError().getDefaultMessage(); 
            return ResponseEntity.status(400).body(message); 
        }
        userService.addUser(user);
        return ResponseEntity.status(200).body(new ApiResponse("User added successfully")); 
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateUser(@PathVariable Integer id, @Valid @RequestBody User user, Errors err){
        if(err.hasErrors()){
            String message = err.getFieldError().getDefaultMessage(); 
            return ResponseEntity.status(400).body(message); 
        }

        userService.updateUser(id, user);
        return ResponseEntity.status(200).body(new ApiResponse("User updated successfully")); 

    }

    @PutMapping("/delete/{id}")
    public ResponseEntity updateUser(@PathVariable Integer id){
        userService.deleteUser(id);
        return ResponseEntity.status(200).body(new ApiResponse("User deleted successfully")); 
    }

    @GetMapping("/email/{email}")
    public ResponseEntity getUserByEmail(@PathVariable String email){
        User user = userService.getUserByEmail(email); 
        return ResponseEntity.status(200).body(user); 
    }

    @GetMapping("/role/{role}")
    public ResponseEntity getUsersBySpecificRole(@PathVariable String role){
        return ResponseEntity.status(200).body(userService.getUsersBySpecificRole(role)); 
    }

    @GetMapping("/ages/{age}")
    public ResponseEntity getUsersBySpecificAgeOrAbove(@PathVariable int age){
        return ResponseEntity.status(200).body(userService.getUsersBySpecificAgeOrAbove(age)); 
    }

    @GetMapping("/search/{username}/{passwod}")
    public ResponseEntity getUserByUsernameAndPassword(@PathVariable String username, @PathVariable String password){
        return ResponseEntity.status(200).body(userService.getUserByUsernameAndPassword(username, password)); 
    }

}
