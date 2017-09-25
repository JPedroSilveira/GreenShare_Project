package com.seedshare.controller.Auth;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.seedshare.entity.Usuario;
import com.seedshare.service.Usuario.UsuarioServiceImpl;

/**
 * @author joao.silva
 */
@RestController
@RequestMapping("/auth")
public class LoginController {

    @Autowired
    UsuarioServiceImpl usuarioService;
    
    @GetMapping("/login")
    public Usuario getUserDetails() {
        return Optional.ofNullable(SecurityContextHolder.getContext())
                .map(SecurityContext::getAuthentication)
                .map(Authentication::getPrincipal)
                .map(User.class::cast)
                .map(User::getUsername)
                .map(usuarioService::findOneByEmail)
                .orElse(null);
    }

    @PostMapping(value = "/register")
    public ResponseEntity<Usuario> create(@RequestBody Usuario usuario) {
       Usuario retorno = usuarioService.create(usuario); 
       if(retorno == null){
    	   return new ResponseEntity<Usuario>(HttpStatus.CONFLICT);
       }else{
    	   return new ResponseEntity<Usuario>(HttpStatus.CREATED);
       }
    }  
}       