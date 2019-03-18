package com.rollanddice.comunidice.dao.spi;

import java.sql.Connection;
import java.util.List;

import com.rollanddice.comunidice.exception.DataException;
import com.rollanddice.comunidice.exception.InstanceNotFoundException;
import com.rollanddice.comunidice.model.Venta;

public interface VentaDAO {
	
	public Venta findById(Connection c, Integer id)
		throws InstanceNotFoundException, DataException;
		
	public List<Venta> findByUsuario(Connection c, Integer idUsuario)
		throws InstanceNotFoundException, DataException;
		
	public void create(Connection c, Venta venta)
		throws InstanceNotFoundException, DataException;
		
	public void delete(Connection c, Venta venta)
		throws InstanceNotFoundException, DataException;

}
