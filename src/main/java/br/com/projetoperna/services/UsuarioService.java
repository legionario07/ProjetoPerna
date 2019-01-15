package br.com.projetoperna.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.projetoperna.model.Perfil;
import br.com.projetoperna.model.Usuario;
import br.com.projetoperna.repository.UsuarioRepository;
import br.com.projetoperna.utils.EncryptUtil;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository UsuarioRepo;

	public Usuario find(Long id) {
		return UsuarioRepo.findById(id).get();
	}
	
	public Usuario findByUsuario(String usuario) {
		return UsuarioRepo.findByUsuario(usuario);
	}
	
	public Usuario findByUsuarioAndSenha(String usuario, String senha) {
		return UsuarioRepo.findByUsuarioAndSenha(usuario, senha);
	}

	public List<Usuario> findAll() {
		return UsuarioRepo.findAll();
	}

	public Usuario save(Usuario usuario) {
		
		usuario.setSenha(EncryptUtil.MD5(usuario.getSenha()));
		
		return UsuarioRepo.save(usuario);
	}
	
	public List<Usuario> findByPerfil(Long id) {
		Perfil perfil = new Perfil(id);
		return UsuarioRepo.findByPerfil(perfil);
	}

	public boolean delete(Long id) {
		
		try {
			UsuarioRepo.deleteById(id);
		} catch (Exception e) {
			return false;
		}

		return true;
	}

}
