 package com.example.demo.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Student {
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private int Id;
	private String name;
	private String address;
	private int age;
	private String email;
	
	public Student(int id, String name, String address, int age, String email) {
		
		this.Id = id;
		this.name = name;
		this.address = address;
		this.age = age;
		this.email = email;
	}

	
	
	public Student() {
		super();
		// TODO Auto-generated constructor stub
	}



	public int getId() {
		return Id;
	}



	public void setId(int id) {
		Id = id;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public String getAddress() {
		return address;
	}



	public void setAddress(String address) {
		this.address = address;
	}



	public int getAge() {
		return age;
	}



	public void setAge(int age) {
		this.age = age;
	}



	public String getEmail() {
		return email;
	}



	public void setEmail(String email) {
		this.email = email;
	}
	
	
	
	
	

}
