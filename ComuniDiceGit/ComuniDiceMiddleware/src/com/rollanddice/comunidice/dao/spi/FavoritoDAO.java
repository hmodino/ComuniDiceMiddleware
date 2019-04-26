package com.rollanddice.comunidice.dao.spi;

import java.sql.Connection;
import java.util.List;

import com.rollanddice.comunidice.exception.DataException;
import com.rollanddice.comunidice.exception.DuplicateInstanceException;
import com.rollanddice.comunidice.exception.InstanceNotFoundException;
import com.rollanddice.comunidice.model.Favorito;

public interface FavoritoDAO {
	
	public List<Favorito> findByFavoritosUsuario(Connection c, Integer idUsuario, String idioma)
		throws InstanceNotFoundException, DataException;

	public List<Favorito> findByValoracionesUsuario(Connection c, Integer idUsuario, String idioma)
		throws InstanceNotFoundException, DataException;
	
	public Double countFavoritos(Connection c, Integer idProducto)
		throws InstanceNotFoundException, DataException;
	
	public List<Double> findValoracionesByProducto(Connection c, Integer idProducto)
		throws InstanceNotFoundException, DataException;
	
	public Double mediaValoraciones(Connection c, Integer idProducto)
		throws InstanceNotFoundException, DataException;
	
	public void create(Connection c, Favorito favorito, Integer fav)
		throws DuplicateInstanceException, DataException;
	
	public void update(Connection c, Favorito favorito, Integer fav)
		throws InstanceNotFoundException, DataException;
	
	public void delete(Connection c, Favorito favorito)
		throws InstanceNotFoundException, DataException;
	
	public Favorito exist(Connection c, Integer idUsuario, Integer idProducto)
		throws DataException;
}
