package com.example.demo.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.Account;
import com.example.demo.domain.Student;


@Repository
public interface AccountRepository extends JpaRepository<Account, String> {
}