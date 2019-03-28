package com.rollanddice.comunidice.service.impl;

import java.sql.Connection;
import java.util.List;

import com.rollanddice.comunidice.dao.impl.DireccionDAOImpl;
import com.rollanddice.comunidice.dao.impl.PaisDAOImpl;
import com.rollanddice.comunidice.dao.impl.RegionDAOImpl;
import com.rollanddice.comunidice.dao.spi.DireccionDAO;
import com.rollanddice.comunidice.dao.spi.PaisDAO;
import com.rollanddice.comunidice.dao.spi.RegionDAO;
import com.rollanddice.comunidice.dao.util.ConnectionManager;
import com.rollanddice.comunidice.dao.util.JDBCUtils;
import com.rollanddice.comunidice.model.Direccion;
import com.rollanddice.comunidice.model.Pais;
import com.rollanddice.comunidice.model.Region;
import com.rollanddice.comunidice.service.spi.DireccionService;

public class DireccionServiceImpl implements DireccionService{
	
	private DireccionDAO direccionDao = null;
	private RegionDAO regionDao = null;
	private PaisDAO paisDao = null;
	
	public DireccionServiceImpl() {
		direccionDao = new DireccionDAOImpl();
		regionDao = new RegionDAOImpl();
		paisDao = new PaisDAOImpl();
	}

	@Override
	public List<Pais> findAll() throws Exception {
		
		boolean commit = false;
		Connection c = null;
		
		try {
			c = ConnectionManager.getConnection();
			c.setAutoCommit(false);
			List<Pais> pais = paisDao.findAll(c);
			commit = true;
			return pais;
		}catch(Exception ex){
			throw ex;
		}finally {
			JDBCUtils.closeConnection(c, commit);
		}
	}

	@Override
	public List<Region> findByPais(Integer idPais) throws Exception {
		
		boolean commit = false;
		Connection c = null;
		
		try {
			c = ConnectionManager.getConnection();
			c.setAutoCommit(false);
			List<Region> region = regionDao.findByPais(c, idPais);
			commit = true;
			return region;
		}catch(Exception ex){
			throw ex;
		}finally {
			JDBCUtils.closeConnection(c, commit);
		}
	}

	@Override
	public void create(Direccion d, Integer idUsuario) throws Exception {
		
		boolean commit = false;
		Connection c = null;
		
		try {
			c = ConnectionManager.getConnection();
			c.setAutoCommit(false);
			direccionDao.create(c, d, idUsuario);
			commit = true;
		}catch(Exception ex){
			throw ex;
		}finally {
			JDBCUtils.closeConnection(c, commit);
		}
		
	}

	@Override
	public void update(Direccion d) throws Exception {
		
		boolean commit = false;
		Connection c = null;
		
		try {
			c = ConnectionManager.getConnection();
			c.setAutoCommit(false);
			direccionDao.update(c, d);
			commit = true;
		}catch(Exception ex){
			throw ex;
		}finally {
			JDBCUtils.closeConnection(c, commit);
		}
		
	}

	@Override
	public void delete(Direccion d) throws Exception {
		
		boolean commit = false;
		Connection c = null;
		
		try {
			c = ConnectionManager.getConnection();
			c.setAutoCommit(false);
			direccionDao.delete(c, d);
			commit = true;
		}catch(Exception ex){
			throw ex;
		}finally {
			JDBCUtils.closeConnection(c, commit);
		}
		
	}

}
