package com.rollanddice.comunidice.dao.spi;

import java.sql.Connection;
import java.util.List;

import com.rollanddice.comunidice.model.Criteria;
import com.rollanddice.comunidice.model.Foro;

public interface ForoDAO {
	
	public Foro findById(Connection c, Integer id)
		throws Exception;
	
	public List<Foro> findByCriteria(Connection c, Criteria criteria)
		throws Exception;
	
	public void create(Connection c, Foro foro)
		throws Exception;
	
	public void delete(Connection c, Foro foro)
		throws Exception;

}
