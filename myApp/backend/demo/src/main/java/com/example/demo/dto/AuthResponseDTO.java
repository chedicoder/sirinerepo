package com.example.demo.dto;

import lombok.Data;

@Data

public class AuthResponseDTO {
    
	public AuthResponseDTO(String token) {
		this.accessToken=token;
	}
	private String accessToken;
    private String tokenType = "Bearer ";
    private String role;
    private int user_id;
    
}
