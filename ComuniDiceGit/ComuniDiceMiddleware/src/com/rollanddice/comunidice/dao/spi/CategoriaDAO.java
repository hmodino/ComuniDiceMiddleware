package com.rollanddice.comunidice.dao.spi;

import java.sql.Connection;
import java.util.List;

import com.rollanddice.comunidice.exception.DataException;
import com.rollanddice.comunidice.exception.InstanceNotFoundException;
import com.rollanddice.comunidice.model.Categoria;

public interface CategoriaDAO {
	
	public List<Categoria> findAll(Connection c)
		throws InstanceNotFoundException, DataException;

	Categoria findById(Connection c, Integer id) 
			throws InstanceNotFoundException, DataException;
}
