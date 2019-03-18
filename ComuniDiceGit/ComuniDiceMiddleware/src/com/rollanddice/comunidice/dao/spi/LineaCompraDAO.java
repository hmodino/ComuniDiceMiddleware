package com.rollanddice.comunidice.dao.spi;

import java.sql.Connection;
import java.util.List;

import com.rollanddice.comunidice.exception.DataException;
import com.rollanddice.comunidice.exception.DuplicateInstanceException;
import com.rollanddice.comunidice.exception.InstanceNotFoundException;
import com.rollanddice.comunidice.model.LineaCompra;

public interface LineaCompraDAO {
	
	public List<LineaCompra> findByCompra(Connection c, Integer idCompra)
		throws InstanceNotFoundException, DataException;
	
	public void create(Connection c, LineaCompra lc, Integer idCompra)
		throws DuplicateInstanceException, DataException;
	
	public void delete(Connection c, LineaCompra lc, Integer idCompra)
		throws InstanceNotFoundException, DataException;

}
