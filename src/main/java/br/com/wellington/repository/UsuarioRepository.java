package br.com.wellington.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import br.com.wellington.models.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
	
	Optional<Usuario> findByLogin(String login);

}
