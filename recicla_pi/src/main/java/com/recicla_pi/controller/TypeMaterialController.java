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

import com.recicla_pi.model.TypeMaterial;
import com.recicla_pi.repoitory.TypeMaterialRepository;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class TypeMaterialController {
	
	@Autowired
	TypeMaterialRepository typeMaterialRepository;
	
	@PostMapping("/type/material")
	public ResponseEntity<TypeMaterial> createTypeMaterial(@RequestBody TypeMaterial typeMaterial) {
		try {
			typeMaterialRepository.save(typeMaterial);
			return ResponseEntity.status(HttpStatus.CREATED).body(typeMaterial);
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
	}
	
	@GetMapping("/type/material")
	public ResponseEntity<List<TypeMaterial>> listTypeMaterial() {
		return ResponseEntity.status(HttpStatus.OK).body(typeMaterialRepository.findAll());
	}
	
	@GetMapping("/type/material/{id}")
	public ResponseEntity<Optional<TypeMaterial>> getTypeMaterial(@PathVariable Integer id) {
		return ResponseEntity.status(HttpStatus.OK).body(typeMaterialRepository.findById(id));
	}
	
	@PutMapping("/type/material/{id}")
	public ResponseEntity<String> updateMaterial(@PathVariable Integer id, @RequestBody TypeMaterial typeMaterial){
		try {
			typeMaterial.setId(id);
			typeMaterialRepository.save(typeMaterial);
			return ResponseEntity.status(HttpStatus.OK).body("Informações do tipo de material alterado com sucesso");
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
		
	}
	
	@DeleteMapping("/type/material/{id}")
	public ResponseEntity<String> deleteMaterial(@PathVariable Integer id){
		try {
			typeMaterialRepository.deleteById(id);
			return ResponseEntity.status(HttpStatus.OK).body("Tipo de material deletado com sucesso!!!");
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
		
	}

}
