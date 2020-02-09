package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import javax.annotation.PostConstruct;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.domain.Account;
import com.example.demo.domain.Grade;
import com.example.demo.domain.Student;
import com.example.demo.repository.AccountRepository;
import com.example.demo.repository.StudentRepository;

@Service
public class AccountService {

	@Autowired
	private AccountRepository accountRepository;
	
	@Autowired
	private StudentService studentService;

	public void addStudent(Student student) {
		Account acct = new Account(student.getMail(), student.getName(), new BCryptPasswordEncoder().encode("1234"));
		studentService.computeGrade(student);
		acct.setStudent(student);
		accountRepository.saveAndFlush(acct);
	}
	
	public Account find(String mail) {
		Optional<Account> account = accountRepository.findById(mail);
		 Account a;
		if (account.isPresent())
			a = account.get();
		else a = null;
		return a;
	}
	
	public void save(Account acct) {
		accountRepository.saveAndFlush(acct);
	}
	
	public void deleteStudent(String mail) {
		accountRepository.deleteById(mail);
	}
	
	public void deleteAll() {
		accountRepository.deleteAll();
		
	}
}