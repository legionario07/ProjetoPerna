package br.com.projetoperna.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.projetoperna.model.Perfil;
import br.com.projetoperna.model.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

	Usuario findByUsuario(String usuario);
	
	@Query("select u from Usuario u where usuario = ?1 and senha = md5(?2)")
	Usuario findByUsuarioAndSenha(String login, String senha);

	@Query("select u from Usuario u join u.perfil where u.perfil = ?1")
	List<Usuario> findByPerfil(Perfil perfil);

	
}
