package com.rollanddice.comunidice.dao.spi;

import java.sql.Connection;
import java.util.List;

import com.rollanddice.comunidice.model.Criteria;
import com.rollanddice.comunidice.model.Producto;

public interface ProductoDAO {
	
	public Producto findById(Connection c, Integer id, String idioma)
		throws Exception;
	
	public List<Producto> findByCriteria(Connection c, Criteria criteria, String idioma)
		throws Exception;
	
	public void create(Connection c, Producto p)
		throws Exception;
	
	public void createIdioma(Connection c, Producto p, String idioma) 
		throws Exception;
}
