package com.rollanddice.comunidice.dao.spi;

import java.sql.Connection;
import java.util.List;

import com.rollanddice.comunidice.model.Criteria;
import com.rollanddice.comunidice.model.Juego;

public interface JuegoDAO {
	
	public Juego findById(Connection c, Integer id)
		throws Exception;
	
	public List<Juego> findByCriteria(Connection c, Criteria criteria)
		throws Exception;
	
	public void create(Connection c, Juego j)
		throws Exception;

}
