package com.rollanddice.comunidice.dao.spi;

import java.sql.Connection;
import java.util.List;

import com.rollanddice.comunidice.exception.DataException;
import com.rollanddice.comunidice.exception.DuplicateInstanceException;
import com.rollanddice.comunidice.exception.InstanceNotFoundException;
import com.rollanddice.comunidice.model.LineaVenta;

public interface LineaVentaDAO {
	
	public List<LineaVenta> findByVenta(Connection c, Integer idVenta)
			throws InstanceNotFoundException, DataException;
		
		public void create(Connection c, LineaVenta lv, Integer idVenta)
			throws DuplicateInstanceException, DataException;
		
		public void delete(Connection c, LineaVenta lv, Integer idVenta)
			throws InstanceNotFoundException, DataException;

}
