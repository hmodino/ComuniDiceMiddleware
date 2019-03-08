package com.rollanddice.comunidice.dao.spi;

import java.sql.Connection;

import com.rollanddice.comunidice.model.Usuario;

public interface UsuarioDAO {
	
	public Usuario findById(Connection c, Integer id)
			throws Exception;
		
	public Usuario findByNombre(Connection c, String nombreUsuario)
		throws Exception;
		
	public Usuario findByEmail(Connection c, String email)
		throws Exception;
		
	public void create (Connection c, Usuario u)
			throws Exception;
		
	public void update(Connection c, Usuario u)
		throws Exception;
		
	public void delete(Connection c, Usuario u)
		throws Exception;
}
