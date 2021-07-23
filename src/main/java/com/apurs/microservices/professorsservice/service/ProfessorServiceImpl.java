package com.apurs.microservices.professorsservice.service;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Service;

import com.apurs.microservices.professorsservice.dto.ProfessorCreateDTO;
import com.apurs.microservices.professorsservice.dto.ProfessorDTO;
import com.apurs.microservices.professorsservice.dto.ProfessorUpdateDTO;
import com.apurs.microservices.professorsservice.model.Professor;
import com.apurs.microservices.professorsservice.repository.ProfessorRepository;

@Service
public class ProfessorServiceImpl implements ProfessorService {

	private ProfessorRepository professorRepository;
	
	private ModelMapper modelMapper = new ModelMapper();
	
	public ProfessorServiceImpl(ProfessorRepository professorRepository) {
		this.professorRepository = professorRepository;
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
	}
	
	@Override
	public List<ProfessorDTO> findAll() {
		List<Professor> professors = professorRepository.findAll();
		List<ProfessorDTO> dtos = new ArrayList<ProfessorDTO>();
		
		for (Professor professor : professors)
			dtos.add(modelMapper.map(professor, ProfessorDTO.class));
		
		return dtos;
	}

	@Override
	public ProfessorDTO findOne(Integer id) {
		Professor professor = professorRepository.getById(id);
		return modelMapper.map(professor, ProfessorDTO.class);
	}

	@Override
	public ProfessorDTO insert(ProfessorCreateDTO professor) {
		Professor createProfessor = modelMapper.map(professor, Professor.class);
		createProfessor = professorRepository.save(createProfessor);
		return modelMapper.map(createProfessor, ProfessorDTO.class);
	}

	@Override
	public ProfessorDTO update(ProfessorUpdateDTO professor) {
		if (!professorRepository.existsById(professor.getId()))
			return null;

		Professor tempProfessor = professorRepository.getById(professor.getId());
		Professor updatedProfessor = modelMapper.map(professor, Professor.class);
		updatedProfessor.setCreatedAt(tempProfessor.getCreatedAt());
		updatedProfessor = professorRepository.save(updatedProfessor);
		return modelMapper.map(updatedProfessor, ProfessorDTO.class);
	}

	@Override
	public boolean delete(Integer id) {
		if (!professorRepository.existsById(id))
			return false;

		professorRepository.deleteById(id);
		return true;
	}
}
