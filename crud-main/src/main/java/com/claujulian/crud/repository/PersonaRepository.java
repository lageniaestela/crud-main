package com.claujulian.crud.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.claujulian.crud.model.Persona;

@Repository
public interface PersonaRepository extends JpaRepository<Persona, Long>{

}
