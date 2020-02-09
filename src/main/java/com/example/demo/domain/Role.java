package com.example.demo.domain;

public enum Role {
	ADMIN("ROLE_ADMIN"),
    STUDENT("ROLE_STUENT");

    private String value;
    
    private Role(String value) {
    	this.value = value;
    }
    
    public String getValue() {
    	return this.value;
    }
}
