package com.rollanddice.comunidice.dao.spi;

import java.sql.Connection;
import java.util.List;

import com.rollanddice.comunidice.exception.DataException;
import com.rollanddice.comunidice.exception.DuplicateInstanceException;
import com.rollanddice.comunidice.exception.InstanceNotFoundException;
import com.rollanddice.comunidice.model.Comentario;

public interface ComentarioDAO {
	
	public Comentario findById(Connection c, Integer idComentario)
		throws InstanceNotFoundException, DataException;
	
	public List<Comentario> findByProductoOForo(Connection c, Integer idProducto, Integer idForo)
		throws InstanceNotFoundException, DataException;
	
	public List<Comentario> findByUsuarioProductoOForo(Connection c, Integer idUsuario, Integer idProducto, Integer idForo)
		throws InstanceNotFoundException, DataException;
	
	public List<Comentario> findByUsuarioTipo(Connection c, Integer idUsuario, Boolean booleano)
			throws InstanceNotFoundException, DataException;
	
	public void create(Connection c, Comentario comentario)
		throws DataException;
	
	public void delete(Connection c, Integer comentario)
		throws InstanceNotFoundException, DataException;
}
