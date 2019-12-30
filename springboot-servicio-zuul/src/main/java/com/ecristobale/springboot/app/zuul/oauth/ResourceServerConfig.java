package com.ecristobale.springboot.app.zuul.oauth;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

	@Override
	public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
		resources.tokenStore(tokenStore());
	}

	@Override
	public void configure(HttpSecurity http) throws Exception {
		// Protect the endpoints
		http.authorizeRequests().antMatchers("/api/security/oauth/**").permitAll()
			.antMatchers(HttpMethod.GET, "/api/productos/list", "/api/items/list", "/api/usuarios/usuarios").permitAll()
			.antMatchers(HttpMethod.GET, "/api/productos/show/{id}", 
					"/api/items/show/{id}/{units}", 
					"/api/usuarios/usuarios/{id}").hasAnyRole("ADMIN", "USER")
			.antMatchers("/api/productos/**", "/api/items/**", "/api/usuarios/**").hasRole("ADMIN")
			.anyRequest().authenticated();
//			.antMatchers(HttpMethod.POST, "/api/productos/create", "/api/items/create", "/api/usuarios/usuarios").hasRole("ADMIN")
//			.antMatchers(HttpMethod.PUT, "/api/productos/update/{id}", "/api/items/update/{id}", "/api/usuarios/usuarios/{id}").hasRole("ADMIN")
//			.antMatchers(HttpMethod.DELETE, "/api/productos/delete/{id}", "/api/items/delete/{id}", "/api/usuarios/usuarios/{id}").hasRole("ADMIN");
		super.configure(http);
	}

	@Bean
	public JwtTokenStore tokenStore() {
		return new JwtTokenStore(accessTokenConverter());
	}

	@Bean
	public JwtAccessTokenConverter accessTokenConverter() {
		JwtAccessTokenConverter tokenConverter = new JwtAccessTokenConverter();
		tokenConverter.setSigningKey("some_secret_code");
		return tokenConverter;
	}
}
