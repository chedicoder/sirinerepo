package com.example.demo.dto;

import lombok.Data;

@Data
public class RegisterDto {
    private String username;
    private String password;
    private String email; 
    private String phone_Number;
    private String first_Name;
	private String Last_Name;
	private String role;
}
