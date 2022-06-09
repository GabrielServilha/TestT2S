package com.projeto.porto.repository;

import org.springframework.data.repository.CrudRepository;

import com.projeto.porto.models.Cliente;

public interface ClienteRepository extends CrudRepository<Cliente, String>{
	Cliente findByRg(long rg);
	      
}
