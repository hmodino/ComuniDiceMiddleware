package com.rollanddice.comunidice.dao.spi;

import java.sql.Connection;
import java.util.List;

import com.rollanddice.comunidice.model.Pais;

public interface PaisDAO {
	
	public Pais findById(Connection c, Integer id)
		throws Exception;
	
	public List<Pais> findAll(Connection c)
		throws Exception;

}
