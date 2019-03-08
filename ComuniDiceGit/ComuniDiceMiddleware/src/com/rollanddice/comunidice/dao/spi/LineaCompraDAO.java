package com.rollanddice.comunidice.dao.spi;

import java.sql.Connection;
import java.util.List;

import com.rollanddice.comunidice.model.LineaCompra;

public interface LineaCompraDAO {
	
	public List<LineaCompra> findByCompra(Connection c, Integer idCompra)
		throws Exception;
	
	public void create(Connection c, LineaCompra lc, Integer idCompra)
		throws Exception;
	
	public void delete(Connection c, LineaCompra lc, Integer idCompra)
		throws Exception;

}
