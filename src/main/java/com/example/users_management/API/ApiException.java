package com.example.users_management.API;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ApiException extends RuntimeException{

    public ApiException(String message){
        super(message); 
    }

}
