package com.rollanddice.comunidice.service.spi;

import com.rollanddice.comunidice.model.Usuario;

public interface UsuarioService {
	
	public Usuario findById(Integer id)
		throws Exception;
		
	public Usuario findByNombre(String nombreUsuario)
		throws Exception;
		
	public Usuario findByEmail(String email)
		throws Exception;

	public boolean signUp(Usuario u)
		throws Exception;
	
	public Usuario logIn(String email, String password)
		throws Exception;
	
	public void editar(Usuario u)
		throws Exception;
	
	public void eliminar(Usuario u)
		throws Exception;

}
