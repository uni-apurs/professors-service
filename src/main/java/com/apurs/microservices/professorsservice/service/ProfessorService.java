package com.apurs.microservices.professorsservice.service;

import java.util.List;

import com.apurs.microservices.professorsservice.dto.ProfessorCreateDTO;
import com.apurs.microservices.professorsservice.dto.ProfessorDTO;
import com.apurs.microservices.professorsservice.dto.ProfessorUpdateDTO;

public interface ProfessorService {
	public abstract List<ProfessorDTO> findAll();
	public abstract ProfessorDTO findOne(Integer id);
	public abstract ProfessorDTO insert(ProfessorCreateDTO professor);
	public abstract ProfessorDTO update(ProfessorUpdateDTO professor);
	public abstract boolean delete(Integer id);
}
