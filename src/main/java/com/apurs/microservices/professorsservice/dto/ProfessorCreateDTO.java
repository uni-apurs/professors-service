package com.apurs.microservices.professorsservice.dto;

import java.time.ZonedDateTime;

public class ProfessorCreateDTO {
	private String firstName;
	private String lastName;
	private ZonedDateTime createdAt;
	private ZonedDateTime updatedAt;
	
	public ProfessorCreateDTO(String firstName, String lastName) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.setCreatedAt(ZonedDateTime.now());
		this.setUpdatedAt(ZonedDateTime.now());
	}

	public String getFirstName() {
		return firstName;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public ZonedDateTime getCreatedAt() {
		return createdAt;
	}
	
	public void setCreatedAt(ZonedDateTime createdAt) {
		this.createdAt = createdAt;
	}
	
	public ZonedDateTime getUpdatedAt() {
		return updatedAt;
	}
	
	public void setUpdatedAt(ZonedDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}
}
