package com.rollanddice.comunidice.service.impl;

import java.sql.Connection;

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
	public Boolean exist(Integer idUsuario, Integer idProducto) throws Exception {
		
		Boolean b = null;
		
		boolean commit = false;
		Connection c = null;
		try {
			c = ConnectionManager.getConnection();
			c.setAutoCommit(false);
			
			b = dao.exist(c, idUsuario, idProducto);
			commit = true;
			
			return b;
			
		} catch (Exception e) {
			
			e.printStackTrace();
			throw e;
			
		} finally {
			JDBCUtils.closeConnection(c, commit);
		}
	}

	@Override
	public void create(Favorito f) throws Exception {
		
		boolean commit = false;
		Connection c = null;
		try {
			c = ConnectionManager.getConnection();
			c.setAutoCommit(false);
			dao.create(c, f);
			commit = true;
		}catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtils.closeConnection(c, commit);
		}
	}

	@Override
	public void update(Favorito f) throws Exception {
		
		boolean commit = false;
		Connection c = null;
		try {
			c = ConnectionManager.getConnection();
			c.setAutoCommit(false);
			dao.update(c, f);
			commit = true;
		}catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtils.closeConnection(c, commit);
		}
	}

}
