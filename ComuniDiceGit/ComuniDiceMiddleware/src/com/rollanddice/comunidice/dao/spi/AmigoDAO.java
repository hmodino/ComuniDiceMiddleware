package com.rollanddice.comunidice.dao.spi;

import java.sql.Connection;
import java.util.List;

import com.rollanddice.comunidice.exception.DataException;
import com.rollanddice.comunidice.exception.DuplicateInstanceException;
import com.rollanddice.comunidice.exception.InstanceNotFoundException;
import com.rollanddice.comunidice.model.Amigo;

public interface AmigoDAO {
	
	public List<Amigo> findAmigos(Connection c, Integer id)
			throws InstanceNotFoundException, DataException;
		
	public Amigo findByEmailAmigo(Connection c, String email, Integer id)
		throws InstanceNotFoundException, DataException;
		
	public Amigo findByNombreAmigo(Connection c, String nombreUsuarioAmigo, Integer id)
			throws InstanceNotFoundException, DataException;
	
	public void create(Connection c, Integer idUsuario, Integer idAmigo)
		throws DuplicateInstanceException, DataException;
	
	public void delete(Connection c, Integer idUsuario, Integer idAmigo)
		throws InstanceNotFoundException, DataException;

}
