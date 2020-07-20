package com.example.demo.functions;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.Managers;
import com.example.demo.entity.Organizations;
import com.example.demo.repository.IOrganizationsFunctions;
import com.example.demo.repository.OrganizationsRepository;


@Service
@Transactional 
public class OrganizationsFunctionsImp implements IOrganizationsFunctions {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private OrganizationsRepository org;
	
}
