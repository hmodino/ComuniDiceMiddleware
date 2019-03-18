package com.rollanddice.comunidice.dao.spi;

import java.sql.Connection;
import java.util.List;

import com.rollanddice.comunidice.exception.DataException;
import com.rollanddice.comunidice.exception.InstanceNotFoundException;
import com.rollanddice.comunidice.model.Pais;

public interface PaisDAO {
	
	public Pais findById(Connection c, Integer id)
		throws InstanceNotFoundException, DataException;
	
	public List<Pais> findAll(Connection c)
		throws DataException;

}
