package com.rollanddice.comunidice.dao.spi;

import java.sql.Connection;

import com.rollanddice.comunidice.exception.DataException;
import com.rollanddice.comunidice.exception.InstanceNotFoundException;
import com.rollanddice.comunidice.model.Direccion;

public interface DireccionDAO {
	
	public Direccion findByUsuario(Connection c, Integer idUsuario)
		throws InstanceNotFoundException, DataException;
	
	public void create(Connection c, Direccion d, Integer idUsuario)
			throws DataException;
	
	public void delete(Connection c, Direccion d)
		throws InstanceNotFoundException, DataException;
}
