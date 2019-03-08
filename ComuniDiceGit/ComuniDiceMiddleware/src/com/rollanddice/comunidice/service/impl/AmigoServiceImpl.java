package com.rollanddice.comunidice.service.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.rollanddice.comunidice.dao.impl.AmigoDAOImpl;
import com.rollanddice.comunidice.dao.spi.AmigoDAO;
import com.rollanddice.comunidice.dao.util.ConnectionManager;
import com.rollanddice.comunidice.dao.util.JDBCUtils;
import com.rollanddice.comunidice.exception.DataException;
import com.rollanddice.comunidice.exception.ServiceException;
import com.rollanddice.comunidice.model.Amigo;
import com.rollanddice.comunidice.service.spi.AmigoService;

public class AmigoServiceImpl implements AmigoService{
	
	private AmigoDAO dao = null;
	public static Logger logger = LogManager.getLogger(AmigoServiceImpl.class);
	
	public AmigoServiceImpl() {
		
	dao = new AmigoDAOImpl();
	}

	@Override
	public List<Amigo> findAmigos(Integer id) throws ServiceException, DataException {
		
		boolean commit = false;
		Connection c = null;
		
		try {
			c = ConnectionManager.getConnection();
			c.setAutoCommit(false);
			List<Amigo> a = dao.findAmigos(c, id);
			
			commit = true;
			logger.info(a);
			return a;
		}
		catch (SQLException ex) {
			logger.debug(ex);
			throw new ServiceException();
		} 
		finally { 
			JDBCUtils.closeConnection(c, commit);
		}  	
	}

	@Override
	public Amigo findByEmailAmigo(String email, Integer id) throws ServiceException, DataException {
		
		boolean commit = false;
		Connection c = null;
		
		try {
			c = ConnectionManager.getConnection();
			c.setAutoCommit(false);
			email = email.toUpperCase();
			Amigo a = dao.findByEmailAmigo(c, email, id);
			
			commit = true;
			
			logger.info(a);
			return a;
		}
		catch (Exception ex) {
			logger.debug(ex);
			throw new ServiceException();
		} 
		finally { 
			JDBCUtils.closeConnection(c, commit);
		}  	
	}

	@Override
	public Amigo findByNombreAmigo(String nombreUsuarioAmigo, Integer id) throws ServiceException, DataException {
		
		boolean commit = false;
		Connection c = null;
		
		try {
			c = ConnectionManager.getConnection();
			c.setAutoCommit(false);
			Amigo a = dao.findByNombreAmigo(c, nombreUsuarioAmigo, id);
			
			commit = true;
			
			logger.info(a);
			return a;
		}
		catch (Exception ex) {
			logger.debug(ex);
			throw new ServiceException();
		} 
		finally { 
			JDBCUtils.closeConnection(c, commit);
		}
	}

	@Override
	public void create(Integer idUsuario, Integer idAmigo) throws ServiceException, DataException {
		
		boolean commit = false;
		Connection c = null;
		
		try {
			c = ConnectionManager.getConnection();
			c.setTransactionIsolation(
                    Connection.TRANSACTION_READ_COMMITTED);
			c.setAutoCommit(false);
			dao.create(c, idUsuario, idAmigo);
			
			commit = true;
			System.out.println("El amigo ha sido añadido");	
		}
		catch (Exception ex) {
			logger.debug(ex);
			throw new ServiceException();
		} 
		finally { 
			JDBCUtils.closeConnection(c, commit);
		} 
	}

	@Override
	public void delete(Integer idUsuario, Integer idAmigo) throws ServiceException, DataException {
		
		boolean commit = false;
		Connection c = null;
		
		try {
			c = ConnectionManager.getConnection();
			c.setTransactionIsolation(
                    Connection.TRANSACTION_READ_COMMITTED);
			c.setAutoCommit(false);
			dao.delete(c, idUsuario, idAmigo);
			
			commit = true;
			System.out.println("El amigo ha sido eliminado");	

		}
		catch (Exception ex) {
			logger.debug(ex);
			throw new ServiceException();
		} 
		finally { 
			JDBCUtils.closeConnection(c, commit);
		}
	}
}