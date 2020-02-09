package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import javax.annotation.PostConstruct;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.domain.Grade;
import com.example.demo.domain.Student;
import com.example.demo.repository.StudentRepository;

@Service
public class StudentService {

	@Autowired
	private StudentRepository studentRepository;

	public Student addStudentGrade(Student student) {
		computeGrade(student);
		Student created = studentRepository.saveAndFlush(student);
		return created;
	}

	public void computeGrade(Student student) {
		double m = student.getMidScore()*0.35;
		double f = student.getFinalScore()*0.35;
		double h = student.getHwScore()*0.3;
		
		double totalScore = m+f+h;
		
		student.setTotalScore(totalScore);
		if (totalScore>=90.0)
			student.setGrade(Grade.A);
		else if (totalScore>=80.0)
			student.setGrade(Grade.B);
		else if (totalScore>=70.0)
			student.setGrade(Grade.C);
		else if (totalScore>=60.0)
			student.setGrade(Grade.D);
		else 
			student.setGrade(Grade.F);
		
	}

	public List<Student> findAll() {
		// TODO Auto-generated method stub
		return studentRepository.findAll();
	}

	public Student find(String mail) {
		Optional<Student> student = studentRepository.findById(mail);
		Student s;
		if (student.isPresent())
			s = student.get();
		else s = null;
			
		return s;
	}
	
	public void deleteStudentGrade(String mail) {
		studentRepository.deleteById(mail);
	}

	public void deleteAll() {
		studentRepository.deleteAll();
		
	}
}