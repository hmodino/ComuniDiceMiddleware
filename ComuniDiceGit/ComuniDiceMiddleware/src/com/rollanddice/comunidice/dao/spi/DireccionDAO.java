package com.rollanddice.comunidice.dao.spi;

import java.sql.Connection;

import com.rollanddice.comunidice.model.Direccion;

public interface DireccionDAO {
	
	public Direccion findByUsuario(Connection c, Integer idUsuario)
		throws Exception;
	
	public void create(Connection c, Direccion d, Integer idUsuario)
			throws Exception;
	
	public void delete(Connection c, Direccion d)
		throws Exception;
}
