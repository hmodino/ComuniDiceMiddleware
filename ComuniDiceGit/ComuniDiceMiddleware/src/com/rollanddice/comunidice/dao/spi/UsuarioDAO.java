package com.rollanddice.comunidice.dao.spi;

import java.sql.Connection;

import com.rollanddice.comunidice.exception.DataException;
import com.rollanddice.comunidice.exception.DuplicateInstanceException;
import com.rollanddice.comunidice.exception.InstanceNotFoundException;
import com.rollanddice.comunidice.model.Usuario;

public interface UsuarioDAO {
	
	public Usuario findById(Connection c, Integer id)
			throws InstanceNotFoundException, DataException;
		
	public Usuario findByNombre(Connection c, String nombreUsuario)
		throws InstanceNotFoundException, DataException;
		
	public Usuario findByEmail(Connection c, String email)
		throws InstanceNotFoundException, DataException;
		
	public void create (Connection c, Usuario u)
			throws DuplicateInstanceException, DataException;
		
	public void update(Connection c, Usuario u)
		throws InstanceNotFoundException, DataException;
		
	public void delete(Connection c, Usuario u)
		throws InstanceNotFoundException, DataException;
}
