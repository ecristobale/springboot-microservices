package com.ecristobale.springboot.app.usuarios.models.dao;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import com.ecristobale.springboot.app.usuarios.commons.models.entity.Usuario;

@RepositoryRestResource(path="usuarios")
public interface UsuarioDao extends PagingAndSortingRepository<Usuario, Long> {

	// Spring Data Query method: JPQL: select u from Usuario u where u.username=?
	@RestResource(path = "buscar-username")
	public Usuario findByUsername(@Param("usrname") String username);
	
	// Other way (NOT native query): @Query("select u from Usuario u where u.username=?1")
}
