package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.domain.Student;
import com.example.demo.repository.StudentRepository;

@Service
public class demoService {
	
	@Autowired
	private StudentRepository repo; 
	
	public List<Student> listAll(){
		return repo.findAll();
	}
	
	public void save(Student std) {
		repo.save(std);
	}
	
	public Student get(long id) {
		return repo.findById(id).get();
		
	}
	
	public void delete(long id) {
		repo.deleteById(id);
	}

}
