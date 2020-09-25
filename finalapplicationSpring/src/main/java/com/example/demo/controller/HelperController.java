package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


import com.example.demo.entity.Helper;

import com.example.demo.repository.HelperRepository;

@CrossOrigin
@RestController
public class HelperController {

	@Autowired
	private HelperRepository helperRepository;
	
	
	@PostMapping("helper/create")
	public ResponseEntity<?> createHelper(@Valid @RequestBody Helper helper){
		
		helper.setProducts(helper.getProducts());
		
		helperRepository.save(helper);
		
		
		return new ResponseEntity<Helper>(helper,HttpStatus.CREATED);
	}
	
	@GetMapping(path="helper/helperslist")
	public List<Helper> getAllHelpers(){
		
		List<Helper> helperslist = new ArrayList<Helper>();
		
		helperRepository.findAll().forEach(helperslist :: add);
		
		return helperslist;
		
	}
	
}
