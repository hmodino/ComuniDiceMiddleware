package com.rollanddice.comunidice.dao.spi;

import java.sql.Connection;
import java.util.List;

import com.rollanddice.comunidice.model.Compra;

public interface CompraDAO {
	
	public Compra findById(Connection c, Integer id)
		throws Exception;
	
	public List<Compra> findByUsuario(Connection c, Integer idUsuario)
		throws Exception;
	
	public void create(Connection c, Compra compra)
		throws Exception;
	
	public void delete(Connection c, Compra compra)
		throws Exception;
}
