package com.example.users_management.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id; 
    
    @NotEmpty(message = "Name should be not empty")
    @Size(min = 4, message = "Name must be more than 4 Characters")
    @Column(columnDefinition = "varchar(25) not null")
    private String name; 

    @NotEmpty(message = "Username should be not empty")
    @Size(min = 4, message = "Username must be more than 4 Characters")
    @Column(columnDefinition = "varchar(25) not null unique")
    private String username; 


    @NotEmpty(message = "Password should be not empty")
    @Column(columnDefinition = "varchar(25) not null")
    private String password; 

    @NotEmpty(message = "Password should be not empty")
    @Email(message = "Please enter a valid email")
    @Column(columnDefinition = "varchar(25) not null unique")
    private String email; 

    @Pattern(regexp = "^(USER|ADMIN)$", message = "Role must be ethier(' USER ' or ' ADMIN ')")
    @Column(columnDefinition = "enum('USER','ADMIN') not null")
    private String role; 

    @NotNull
    @Positive
    @Column(columnDefinition = "int not null")
    private int age;

}
