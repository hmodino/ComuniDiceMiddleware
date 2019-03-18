package com.rollanddice.comunidice.dao.spi;

import java.sql.Connection;
import java.util.List;

import com.rollanddice.comunidice.exception.DataException;
import com.rollanddice.comunidice.exception.InstanceNotFoundException;
import com.rollanddice.comunidice.model.Compra;

public interface CompraDAO {
	
	public Compra findById(Connection c, Integer id)
		throws InstanceNotFoundException, DataException;
	
	public List<Compra> findByUsuario(Connection c, Integer idUsuario)
		throws InstanceNotFoundException, DataException;
	
	public void create(Connection c, Compra compra)
		throws DataException;;
	
	public void delete(Connection c, Compra compra)
		throws InstanceNotFoundException, DataException;
}
