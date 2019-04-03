package com.rollanddice.comunidice.dao.spi;

import java.sql.Connection;
import java.util.List;

import com.rollanddice.comunidice.exception.DataException;
import com.rollanddice.comunidice.exception.InstanceNotFoundException;
import com.rollanddice.comunidice.model.Mensaje;

public interface MensajeDAO {
	
	public Mensaje findById(Connection c, Integer id)
		throws InstanceNotFoundException, DataException;
	
	public List<Mensaje> findByEmisor(Connection c, Integer id)
		throws InstanceNotFoundException, DataException;
	
	public List<Mensaje> findByReceptor(Connection c, Integer idReceptor)
		throws InstanceNotFoundException, DataException;
	
	public void create(Connection c, Mensaje mensaje)
		throws DataException;
	
	public void delete(Connection c, Integer id) 
		throws InstanceNotFoundException, DataException;

}
