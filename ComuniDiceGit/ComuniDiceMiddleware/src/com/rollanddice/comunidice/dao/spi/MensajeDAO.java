package com.rollanddice.comunidice.dao.spi;

import java.sql.Connection;
import java.util.List;

import com.rollanddice.comunidice.model.Mensaje;

public interface MensajeDAO {
	
	public Mensaje findById(Connection c, Integer id)
		throws Exception;
	
	public List<Mensaje> findByEmisor(Connection c, Integer id)
		throws Exception;
	
	public List<Mensaje> findByReceptor(Connection c, Integer idReceptor)
		throws Exception;
	
	public void create(Connection c, Mensaje mensaje)
		throws Exception;
	
	public void delete(Connection c, Mensaje mensaje) 
		throws Exception;

}
