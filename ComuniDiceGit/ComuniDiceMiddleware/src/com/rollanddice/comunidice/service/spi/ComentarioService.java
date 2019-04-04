package com.rollanddice.comunidice.service.spi;

import java.util.List;

import com.rollanddice.comunidice.model.Comentario;

public interface ComentarioService {
	
	public Comentario findById(Integer id)
		throws Exception;
		
	public List<Comentario> findByProductoOForo(Integer idProducto, Integer idForo)
		throws Exception;
		
	public List<Comentario> findByUsuarioProductoOForo(Integer idUsuario, Integer idProducto, Integer idForo)
		throws Exception;
		
	public List<Comentario> findByUsuarioTipo(Integer idUsuario, Boolean booleano)
		throws Exception;

	public void create(Comentario comentario)
		throws Exception;
	
	public void delete(Integer comentario)
		throws Exception;
}
