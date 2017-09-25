package com.seedshare.service.Usuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.seedshare.entity.Usuario;
import com.seedshare.repository.UsuarioRepository;
import com.seedshare.security.UserSecurityConfig;

/**
 * Implementação da interface de serviço do Usuario
 * @author joao.silva
 */
@Service
public class UsuarioServiceImpl implements UsuarioService{
	
	@Autowired
    UsuarioRepository usuarioRepository;
    
    @Autowired
    UsuarioServiceImpl usuarioService;
    
    @Autowired
    private UserSecurityConfig userSecurityConfig;
    
    @Override
    public Usuario create(Usuario usuario) {
    	Usuario usuarioBD = usuarioService.findOneByEmail(usuario.getEmail());
    	if(usuarioBD == null) {
        	Usuario novoUsuario =  new Usuario(usuario.getCpf(), usuario.getEmail(), usuario.getSenha(), usuario.getFotoUrl(), usuario.isPessoaJuridica());
        	novoUsuario.setSenha(userSecurityConfig.passwordEncoder().encode(novoUsuario.getSenha()));
        	return usuarioRepository.save(novoUsuario);
    	}
    	return null;
    }
    
	@Override
	public Usuario save(Usuario usuario) {
		return usuarioRepository.save(usuario);
	}

	@Override
	public void delete(Usuario usuario) {
		usuarioRepository.delete(usuario);
	}

	@Override
	public Usuario findOne(Long id) {
		return usuarioRepository.findOne(id);
	}

	@Override
	public Usuario findOneByEmail(String email) {
		return usuarioRepository.findOneByEmail(email);
	}

}
