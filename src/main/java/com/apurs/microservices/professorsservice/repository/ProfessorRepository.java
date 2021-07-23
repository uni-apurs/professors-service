package com.apurs.microservices.professorsservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.apurs.microservices.professorsservice.model.Professor;

@Repository
public interface ProfessorRepository extends JpaRepository<Professor, Integer> {
	List<Professor>findByFirstNameContainingIgnoreCase(String firstName);
	List<Professor>findByLastNameContainingIgnoreCase(String lastName);
}
