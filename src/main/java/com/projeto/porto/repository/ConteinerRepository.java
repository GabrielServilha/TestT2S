package com.projeto.porto.repository;
import org.springframework.data.repository.CrudRepository;

import com.projeto.porto.models.Cliente;
import com.projeto.porto.models.Conteiners;

public interface ConteinerRepository extends CrudRepository<Conteiners, String>{
		Iterable<Conteiners> findByCliente(Cliente cliente);
		Conteiners findByCodigo(String codigo);
}
