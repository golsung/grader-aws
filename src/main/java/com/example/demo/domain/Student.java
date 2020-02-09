package com.example.demo.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.*;
import org.hibernate.validator.constraints.*;

@Entity
@Table(name="student")
public class Student {
//	@Id
//	@GeneratedValue(strategy=GenerationType.AUTO)
//	private Long id;

	@Id
	@Column(nullable=false)
	private String mail;
	
	@Column(nullable=false)
	private String name;
	
	@Column(nullable=false)
	private String major;
	
	@Min(value=0)
	@Max(value=100)
	private int midScore;
	
	@Min(value=0)
	@Max(value=100)
	private int finalScore;
	
	@Min(value=0)
	@Max(value=100)
	private int hwScore;
	
	@Min(value=0)
	@Max(value=100)
	private double totalScore;
	
	private Grade grade;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMajor() {
		return major;
	}

	public void setMajor(String major) {
		this.major = major;
	}

	public int getMidScore() {
		return midScore;
	}

	public void setMidScore(int midScore) {
		this.midScore = midScore;
	}

	public int getFinalScore() {
		return finalScore;
	}

	public void setFinalScore(int finalScore) {
		this.finalScore = finalScore;
	}

	public int getHwScore() {
		return hwScore;
	}

	public void setHwScore(int hwScore) {
		this.hwScore = hwScore;
	}

	public double getTotalScore() {
		return totalScore;
	}

	public void setTotalScore(double totalScore) {
		this.totalScore = totalScore;
	}

	public Grade getGrade() {
		return grade;
	}

	public void setGrade(Grade grade) {
		this.grade = grade;
	}


	public String getMail() {
		return mail;
	}


	public void setMail(String mail) {
		this.mail = mail;
	}
}

