package com.ecristobale.springboot.app.oauth.services;

import com.ecristobale.springboot.app.usuarios.commons.models.entity.Usuario;

public interface IUsuarioService {

	public Usuario findByUsername(String username);
}
