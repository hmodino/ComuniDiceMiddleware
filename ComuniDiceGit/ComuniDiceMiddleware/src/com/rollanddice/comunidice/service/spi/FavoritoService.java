package com.rollanddice.comunidice.service.spi;

import java.util.List;

import com.rollanddice.comunidice.model.Favorito;

public interface FavoritoService {
	
	public Favorito exist(Integer idUsuario, Integer idProducto)
		throws Exception;
	
	public void create(Favorito f, Integer fav)
		throws Exception;
	
	public void update(Favorito f, Integer fav)
		throws Exception;
	
	public List<Favorito> findByUsuario(Integer idUsuario, String idioma)
		throws Exception;

}
