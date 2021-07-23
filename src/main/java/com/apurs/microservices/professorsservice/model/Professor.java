package com.apurs.microservices.professorsservice.model;

import java.time.ZonedDateTime;

import javax.persistence.*;

@Entity
@Table(name = "\"professor\"")
@SequenceGenerator(name = "professor_id_seq", initialValue = 1, allocationSize = 1)
public class Professor {
	@Id
	@Column(name = "\"id\"")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "professor_id_seq")
	private int id;
	
	@Column(name = "\"firstName\"")
	private String firstName;
	
	@Column(name = "\"lastName\"")
	private String lastName;
	
	@Column(name = "\"createdAt\"")
	private ZonedDateTime createdAt;
	
	@Column(name = "\"updatedAt\"")
	private ZonedDateTime updatedAt;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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
