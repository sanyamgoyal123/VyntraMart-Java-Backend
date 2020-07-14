package com.shopping.vo.response;

import lombok.Data;


@Data
public class JwtResponse {
    private String token;
    private String type = "Bearer";
    private String account;
    private String name;
    private String role;

    public JwtResponse(String token, String account, String name, String role) {
        this.account = account;
        this.name = name;
        this.token = token;
        this.role = role;
    }
   
	@Override
	public String toString() {
		return "JwtResponse [token=" + token + ", type=" + type + ", account=" + account + ", name=" + name + ", role="
				+ role + "]";
	}
    
}
