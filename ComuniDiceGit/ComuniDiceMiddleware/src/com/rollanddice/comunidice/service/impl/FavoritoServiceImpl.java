package com.rollanddice.comunidice.service.impl;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import com.rollanddice.comunidice.dao.impl.FavoritoDAOImpl;
import com.rollanddice.comunidice.dao.spi.FavoritoDAO;
import com.rollanddice.comunidice.dao.util.ConnectionManager;
import com.rollanddice.comunidice.dao.util.JDBCUtils;
import com.rollanddice.comunidice.model.Favorito;
import com.rollanddice.comunidice.service.spi.FavoritoService;

public class FavoritoServiceImpl implements FavoritoService{
	
	FavoritoDAO dao = null;
	
	public FavoritoServiceImpl() {
		dao = new FavoritoDAOImpl();
	}

	@Override
	public Favorito exist(Integer idUsuario, Integer idProducto) throws Exception {
		
		Favorito f = new Favorito();;
		
		boolean commit = false;
		Connection c = null;
		try {
			c = ConnectionManager.getConnection();
			c.setAutoCommit(false);
			
			f = dao.exist(c, idUsuario, idProducto);
			commit = true;
			
			return f;
			
		} catch (Exception e) {
			
			e.printStackTrace();
			throw e;
			
		} finally {
			JDBCUtils.closeConnection(c, commit);
		}
	}

	@Override
	public void create(Favorito f, Integer fav) throws Exception {
		
		boolean commit = false;
		Connection c = null;
		try {
			c = ConnectionManager.getConnection();
			c.setAutoCommit(false);
			dao.create(c, f, fav);
			commit = true;
		}catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtils.closeConnection(c, commit);
		}
	}

	@Override
	public void update(Favorito f, Integer fav) throws Exception {
		
		boolean commit = false;
		Connection c = null;
		try {
			c = ConnectionManager.getConnection();
			c.setAutoCommit(false);
			dao.update(c, f, fav);
			commit = true;
		}catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtils.closeConnection(c, commit);
		}
	}

	@Override
	public List<Favorito> findByUsuario(Integer idUsuario, String idioma) throws Exception {
		
		boolean commit = false;
		Connection c =null;
		List<Favorito> favoritos = new ArrayList<Favorito>();
		try {
			c = ConnectionManager.getConnection();
			c.setAutoCommit(false);
			favoritos = dao.findByFavoritosUsuario(c, idUsuario, idioma);
			commit = true;
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtils.closeConnection(c, commit);
		}
		return favoritos;
	}
}
