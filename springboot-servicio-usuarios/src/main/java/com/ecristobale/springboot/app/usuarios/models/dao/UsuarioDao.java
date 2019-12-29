package com.ecristobale.springboot.app.usuarios.models.dao;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.ecristobale.springboot.app.usuarios.models.entity.Usuario;

public interface UsuarioDao extends PagingAndSortingRepository<Usuario, Long> {

	// Spring Data Query method: JPQL: select u from Usuario u where u.username=?
	public Usuario findByUsername(String username);
	
	// Other way (NOT native query): @Query("select u from Usuario u where u.username=?1")
}
