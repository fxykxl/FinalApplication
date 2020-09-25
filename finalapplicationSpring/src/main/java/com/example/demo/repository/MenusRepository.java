package com.example.demo.repository;

import javax.validation.Valid;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Menu;

@Repository
public interface MenusRepository extends JpaRepository<Menu,Long>{

	Menu findByIdMenu(Long idMenu);

}
