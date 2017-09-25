package com.seedshare.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import com.seedshare.entity.Usuario;
import com.seedshare.service.Usuario.UsuarioServiceImpl;

/**
 * Classe de serviço para a autenticação do Usuario
 * @author joao.silva
 */
@Service
public class UserSecurityService implements UserDetailsService {
    
	@Autowired
    UsuarioServiceImpl usuarioService;
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        List<GrantedAuthority> grants = new ArrayList<>();
        Usuario usuario = usuarioService.findOneByEmail(username);
        return new User(usuario.getEmail(),usuario.getSenha(),grants);
    }
}
