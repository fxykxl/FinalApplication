package com.example.demo.repository;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.entity.Organizations;

public interface OrganizationsRepository extends JpaRepository<Organizations , String>{
	
	Organizations findByIdOrganization(Long idOrganization);
	
	List<Organizations> findByIdManager(String email);

}
