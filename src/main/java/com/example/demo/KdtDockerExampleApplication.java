package com.example.demo;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.demo.domain.Account;
import com.example.demo.repository.AccountRepository;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class KdtDockerExampleApplication {

	@Autowired 
	private AccountRepository accountRepository;
	public static void main(String[] args) {
		SpringApplication.run(KdtDockerExampleApplication.class, args);
	}

	@PostConstruct
	public void init() {
		Account root = new Account("root", "root", new BCryptPasswordEncoder().encode("1234"));
		Account mockStudent = new Account("insang@hansung.ac.kr", "insang", new BCryptPasswordEncoder().encode("1234"));
		accountRepository.saveAndFlush(root);
		accountRepository.saveAndFlush(mockStudent);
	}
	
}
