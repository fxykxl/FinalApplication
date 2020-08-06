package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Files;

@Repository
public interface FilesRepository extends JpaRepository<Files , Long> {
	
	Files findByIdFile(String idFile);

}
