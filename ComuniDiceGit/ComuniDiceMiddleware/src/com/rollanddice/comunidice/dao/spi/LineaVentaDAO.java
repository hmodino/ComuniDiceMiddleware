package com.rollanddice.comunidice.dao.spi;

import java.sql.Connection;
import java.util.List;

import com.rollanddice.comunidice.model.LineaVenta;

public interface LineaVentaDAO {
	
	public List<LineaVenta> findByVenta(Connection c, Integer idVenta)
			throws Exception;
		
		public void create(Connection c, LineaVenta lv, Integer idVenta)
			throws Exception;
		
		public void delete(Connection c, LineaVenta lv, Integer idVenta)
			throws Exception;

}
