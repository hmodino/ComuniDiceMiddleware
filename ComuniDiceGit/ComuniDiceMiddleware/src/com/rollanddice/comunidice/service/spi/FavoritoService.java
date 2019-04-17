package com.rollanddice.comunidice.service.spi;

import com.rollanddice.comunidice.model.Favorito;

public interface FavoritoService {
	
	public Boolean exist(Integer idUsuario, Integer idProducto)
		throws Exception;
	
	public void create(Favorito f)
		throws Exception;
	
	public void update(Favorito f)
		throws Exception;

}
