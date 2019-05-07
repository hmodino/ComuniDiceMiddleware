package com.rollanddice.comunidice.dao.spi;

import java.sql.Connection;
import java.util.List;

import com.rollanddice.comunidice.exception.DataException;
import com.rollanddice.comunidice.exception.DuplicateInstanceException;
import com.rollanddice.comunidice.exception.InstanceNotFoundException;
import com.rollanddice.comunidice.model.Criteria;
import com.rollanddice.comunidice.model.Juego;
import com.rollanddice.comunidice.model.Results;

public interface JuegoDAO {
	
	public Juego findById(Connection c, Integer id)
		throws InstanceNotFoundException, DataException;
	
	public Results<Juego> findByCriteria(Connection c, Criteria criteria, int startIndex, int count, String idioma)
		throws InstanceNotFoundException, DataException;
	
	public void create(Connection c, Juego j)
		throws DuplicateInstanceException, DataException;

}
