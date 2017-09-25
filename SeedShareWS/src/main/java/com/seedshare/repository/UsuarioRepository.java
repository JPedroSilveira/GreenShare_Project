package com.seedshare.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.seedshare.entity.Usuario;
/**
 * Classe de repositório para Usuario
 * @author joao.silva
 */
@Repository
public interface  UsuarioRepository extends CrudRepository<Usuario, Long>{
	Usuario findOneByEmail(String email);
}
