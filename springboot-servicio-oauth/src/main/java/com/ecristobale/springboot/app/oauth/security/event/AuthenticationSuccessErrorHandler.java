package com.ecristobale.springboot.app.oauth.security.event;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationEventPublisher;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.ecristobale.springboot.app.oauth.services.IUsuarioService;
import com.ecristobale.springboot.app.usuarios.commons.models.entity.Usuario;

import feign.FeignException;

@Component
public class AuthenticationSuccessErrorHandler implements AuthenticationEventPublisher {

	private Logger log = LoggerFactory.getLogger(AuthenticationSuccessErrorHandler.class);
	
	@Autowired
	private IUsuarioService usuarioService;
	
	@Override
	public void publishAuthenticationSuccess(Authentication authentication) {
		UserDetails user = (UserDetails)authentication.getPrincipal();
		String message = "Success login: " + user.getUsername();
		System.out.println(message);
		log.info(message);
		
		Usuario usuario = usuarioService.findByUsername(authentication.getName());
		if(usuario.getIntentos() != null && usuario.getIntentos() > 0) {
			usuario.setIntentos(0);
			usuarioService.update(usuario, usuario.getId());
		}
		
	}

	@Override
	public void publishAuthenticationFailure(AuthenticationException exception, Authentication authentication) {
		String message = "Login error: " + exception.getMessage();
		log.error(message);
		System.out.println(message);
		
		try {
			Usuario usuario = usuarioService.findByUsername(authentication.getName());
			if(usuario.getIntentos() == null) {
				usuario.setIntentos(0);
			}
			
			log.info("Login failed, previous attemps: " + usuario.getIntentos());
			usuario.setIntentos(usuario.getIntentos() + 1);
			log.info("Login failed, total attemps: " + usuario.getIntentos());
			
			
			if(usuario.getIntentos() >= 3) {
				log.error(String.format("User %s disabled for 3 attemps made of incorrect login", usuario.getUsername()));
				usuario.setEnabled(false);
			}
			usuarioService.update(usuario, usuario.getId());
		} catch (FeignException e) {
			log.error(String.format("Username %s doesn't exist in the system", authentication.getName()));
		}
	}

}
