package com.rollanddice.comunidice.dao.spi;

import java.sql.Connection;
import java.util.List;

import com.rollanddice.comunidice.model.Venta;

public interface VentaDAO {
	
	public Venta findById(Connection c, Integer id)
		throws Exception;
		
	public List<Venta> findByUsuario(Connection c, Integer idUsuario)
		throws Exception;
		
	public void create(Connection c, Venta venta)
		throws Exception;
		
	public void delete(Connection c, Venta venta)
		throws Exception;

}
