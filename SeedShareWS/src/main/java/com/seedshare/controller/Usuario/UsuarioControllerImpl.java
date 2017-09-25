package com.seedshare.controller.Usuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.seedshare.entity.Usuario;
import com.seedshare.service.Usuario.UsuarioServiceImpl;

/**
 * @author joao.silva
 */
@RestController
@RequestMapping("/user")
public class UsuarioControllerImpl{
	@Autowired
    UsuarioServiceImpl usuarioService;
	
	@PutMapping(value = "/changePassword")
    public Usuario mudarSenha(@RequestBody Usuario usuario) {
       Usuario usuarioBD = usuarioService.findOne(usuario.getId());
       usuarioBD.setSenha(usuario.getSenha());
       return usuarioService.save(usuario);   
    }
}
