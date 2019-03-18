package com.rollanddice.comunidice.dao.spi;

import java.sql.Connection;

import com.rollanddice.comunidice.exception.DataException;
import com.rollanddice.comunidice.exception.DuplicateInstanceException;
import com.rollanddice.comunidice.exception.InstanceNotFoundException;
import com.rollanddice.comunidice.model.Criteria;
import com.rollanddice.comunidice.model.Producto;
import com.rollanddice.comunidice.model.Results;

public interface ProductoDAO {
	
	public Producto findById(Connection c, Integer id, String idioma)
		throws InstanceNotFoundException, DataException;
	
	public Results<Producto> findByCriteria(Connection c, Criteria criteria, String idioma, int startIndex, int count)
		throws InstanceNotFoundException, DataException;
	
	public void create(Connection c, Producto p)
		throws DuplicateInstanceException, DataException;
	
	public void createIdioma(Connection c, Producto p, String idioma) 
		throws DuplicateInstanceException, DataException;
}
