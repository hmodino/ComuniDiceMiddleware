package com.rollanddice.comunidice.dao.spi;

import java.sql.Connection;
import java.util.List;

import com.rollanddice.comunidice.exception.DataException;
import com.rollanddice.comunidice.exception.DuplicateInstanceException;
import com.rollanddice.comunidice.exception.InstanceNotFoundException;
import com.rollanddice.comunidice.model.Criteria;
import com.rollanddice.comunidice.model.Foro;

public interface ForoDAO {
	
	public Foro findById(Connection c, Integer id)
		throws InstanceNotFoundException, DataException;
	
	public List<Foro> findByCriteria(Connection c, Criteria criteria)
		throws InstanceNotFoundException, DataException;
	
	public void create(Connection c, Foro foro)
		throws DuplicateInstanceException, DataException;
	
	public void delete(Connection c, Foro foro)
		throws InstanceNotFoundException, DataException;

}
