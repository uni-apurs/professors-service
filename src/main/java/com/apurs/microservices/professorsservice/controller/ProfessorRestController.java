package com.apurs.microservices.professorsservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.apurs.microservices.professorsservice.dto.ProfessorCreateDTO;
import com.apurs.microservices.professorsservice.dto.ProfessorDTO;
import com.apurs.microservices.professorsservice.dto.ProfessorUpdateDTO;
import com.apurs.microservices.professorsservice.service.ProfessorService;

@RestController
@RequestMapping("/professors")
public class ProfessorRestController {
	
	@Autowired
	private ProfessorService professorService;
	
	@GetMapping("")
	public List<ProfessorDTO> getProfessor() {
		return professorService.findAll();
	}
	
	@GetMapping("/{id}")
	public ProfessorDTO getProfessor(@PathVariable("id") Integer id) {
		return professorService.findOne(id);
	}
	
	@PostMapping("")
	public ResponseEntity<ProfessorDTO> insertProfessor (@RequestBody ProfessorCreateDTO professor){
		if(professorService.insert(professor) != null)
			return new ResponseEntity<>(HttpStatus.OK);
		
		return new ResponseEntity<>(HttpStatus.CONFLICT);
	}
	
	@PutMapping("")
	public ResponseEntity<ProfessorDTO> updateProfessor (@RequestBody ProfessorUpdateDTO professor){
		if (professorService.update(professor) != null)
			return new ResponseEntity<>(HttpStatus.OK);

		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<ProfessorDTO> deleteProfessor (@PathVariable("id") Integer id){
		if (professorService.delete(id))
			return new ResponseEntity<>(HttpStatus.OK);

		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

}
