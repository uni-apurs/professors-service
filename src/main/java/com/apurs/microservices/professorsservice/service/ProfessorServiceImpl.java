package com.apurs.microservices.professorsservice.service;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.apurs.microservices.coursesservice.dto.CourseDTO;
import com.apurs.microservices.professorsservice.dto.ProfessorCreateDTO;
import com.apurs.microservices.professorsservice.dto.ProfessorDTO;
import com.apurs.microservices.professorsservice.dto.ProfessorUpdateDTO;
import com.apurs.microservices.professorsservice.model.Professor;
import com.apurs.microservices.professorsservice.repository.ProfessorRepository;

@Service
public class ProfessorServiceImpl implements ProfessorService {

	private ProfessorRepository professorRepository;
	
	private ModelMapper modelMapper = new ModelMapper();
	private RestTemplate restTemplate = new RestTemplate();
	
	@Autowired
	private JdbcTemplate jdbcTemplate = new JdbcTemplate();
	
	@Value("${app.coursesEndpoint}")
	private String coursesEndpoint;
	
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
	
	@Override
	public List<ProfessorDTO> findAllByCourseId(Integer courseId) {
		ResponseEntity<CourseDTO> res = restTemplate.getForEntity(coursesEndpoint + courseId, CourseDTO.class);
		
		if (!res.getStatusCode().equals(HttpStatus.OK))
			return null;
		
		String sql = "SELECT * FROM \"professor\" p JOIN \"teaching\" t ON p.\"id\" = t.\"professorId\" WHERE t.\"courseId\" = " + courseId;
		List<ProfessorDTO> professors = new ArrayList<ProfessorDTO>();
		professors = jdbcTemplate.query(sql, (rs, rowNum) -> (
				new ProfessorDTO(
						rs.getInt("id"), 
						rs.getString("firstName"), 
						rs.getString("lastName")
				)));

		return professors;
	}
}
