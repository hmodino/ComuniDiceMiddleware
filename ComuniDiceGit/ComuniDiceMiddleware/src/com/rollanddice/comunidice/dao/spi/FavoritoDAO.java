package com.rollanddice.comunidice.dao.spi;

import java.sql.Connection;
import java.util.List;

import com.rollanddice.comunidice.model.Favorito;

public interface FavoritoDAO {
	
	public List<Favorito> findByFavoritosUsuario(Connection c, Integer idUsuario)
		throws Exception;

	public List<Favorito> findByValoracionesUsuario(Connection c, Integer idUsuario)
		throws Exception;
	
	public Double countFavoritos(Connection c, Integer idProducto)
		throws Exception;
	
	public List<Double> findValoracionesByProducto(Connection c, Integer idProducto)
		throws Exception;
	
	public Double mediaValoraciones(Connection c, Integer idProducto)
		throws Exception;
	
	public void create(Connection c, Favorito favorito, Integer idUsuario)
		throws Exception;
	
	public void update(Connection c, Favorito favorito, Integer idUsuario, Integer idProducto)
		throws Exception;
	
	public void delete(Connection c, Favorito favorito, Integer idUsuario, Integer idProducto)
		throws Exception;
}
