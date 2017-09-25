package com.seedshare.service.Usuario;

import com.seedshare.entity.Usuario;
import com.seedshare.service.BasicService;

/**
 * Interface de serviço do usuário
 * @author joao.silva
 */
public interface UsuarioService extends BasicService<Usuario,Long>{
	Usuario findOneByEmail(String email);
	Usuario create(Usuario usuario);
}
