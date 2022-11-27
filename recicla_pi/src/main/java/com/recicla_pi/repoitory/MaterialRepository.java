package com.recicla_pi.repoitory;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.recicla_pi.model.Material;

public interface MaterialRepository extends JpaRepository<Material,Integer> {
	@Query(
			  value = "SELECT * FROM material m WHERE m.type_material_id = :id", 
			  nativeQuery = true)
	List<Material> findByTypematerialId( @Param("id") Integer id);
}
