package com.rollanddice.comunidice.service.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.rollanddice.comunidice.dao.impl.CategoriaDAOImpl;
import com.rollanddice.comunidice.dao.spi.CategoriaDAO;
import com.rollanddice.comunidice.dao.util.ConnectionManager;
import com.rollanddice.comunidice.dao.util.JDBCUtils;
import com.rollanddice.comunidice.exception.DataException;
import com.rollanddice.comunidice.exception.ServiceException;
import com.rollanddice.comunidice.model.Categoria;
import com.rollanddice.comunidice.service.spi.CategoriaService;

public class CategoriaServiceImpl implements CategoriaService{
	
	private CategoriaDAO dao = null;
	public static Logger logger = LogManager.getLogger(CategoriaServiceImpl.class);
	
	public CategoriaServiceImpl() {
		dao = new CategoriaDAOImpl();
	}

	@Override
	public List<Categoria> findAll() throws ServiceException, DataException {
		
		boolean commit = false;
		Connection c = null;
		
		try {
			c = ConnectionManager.getConnection();
			c.setAutoCommit(false);
			List<Categoria> categorias = dao.findAll(c);
			
			commit = true;
			for(Categoria cat : categorias) {
				logger.info(cat);
			}
			return categorias;
		}
		catch (SQLException ex) {
			logger.debug(ex);
			throw new ServiceException();
		} 
		finally { 
			JDBCUtils.closeConnection(c, commit);
		}  	
	}

}
