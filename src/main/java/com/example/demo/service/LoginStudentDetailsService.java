package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.domain.Account;
import com.example.demo.domain.Role;
import com.example.demo.domain.Student;
import com.example.demo.repository.AccountRepository;

@Service
public class LoginStudentDetailsService implements UserDetailsService {
    @Autowired
    AccountRepository accountRepository;

    @Override
    public UserDetails loadUserByUsername(String mail) throws UsernameNotFoundException {
        Optional<Account> user = accountRepository.findById(mail);
		if (user.isPresent()) {
			Account acct = user.get();
			if (acct.getMail().equals("root")) return new User("root", acct.getPassword(), AuthorityUtils.createAuthorityList(Role.ADMIN.getValue()));
			else return new User(acct.getMail(), acct.getPassword(), AuthorityUtils.createAuthorityList(Role.STUDENT.getValue()));
		}
		else throw new UsernameNotFoundException("The requested user is not found.");
    }
}