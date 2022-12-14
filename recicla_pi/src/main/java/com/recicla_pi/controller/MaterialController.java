package com.recicla_pi.controller;

import java.util.List;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.recicla_pi.model.Material;
import com.recicla_pi.repoitory.MaterialRepository;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class MaterialController {
	
	@Autowired
	MaterialRepository materialRepository;
	
	@PostMapping("/material")
	public ResponseEntity<Material> createMaterial(@RequestBody Material material){
		try {
			materialRepository.save(material);
			return ResponseEntity.status(HttpStatus.CREATED).body(material);
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
	}
	
	@GetMapping("/material")
	public ResponseEntity<List<Material>> listMaterial(){
		return ResponseEntity.status(HttpStatus.OK).body(materialRepository.findAll());
	}
	
	@GetMapping("/material/{id}")
	public ResponseEntity<Optional<Material>> getMaterial(@PathVariable Integer id){
		return ResponseEntity.status(HttpStatus.OK).body(materialRepository.findById(id));
	}
	
	@PutMapping("/material/{id}")
	public ResponseEntity<String> updateMaterial(@PathVariable Integer id, @RequestBody Material material){
		try {
			material.setId(id);
			materialRepository.save(material);
			return ResponseEntity.status(HttpStatus.OK).body("Informações do material alterado com sucesso");
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
		
	}
	
	@DeleteMapping("/material/{id}")
	public ResponseEntity<String> deleteMaterial(@PathVariable Integer id){
		try {
			materialRepository.deleteById(id);
			return ResponseEntity.status(HttpStatus.OK).body("Material deletado com sucesso!!!");
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
		
	}
	
	
	
}
