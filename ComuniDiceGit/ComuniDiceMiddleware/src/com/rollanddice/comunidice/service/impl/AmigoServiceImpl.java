package com.rollanddice.comunidice.service.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.rollanddice.comunidice.dao.impl.AmigoDAOImpl;
import com.rollanddice.comunidice.dao.impl.UsuarioDAOImpl;
import com.rollanddice.comunidice.dao.spi.AmigoDAO;
import com.rollanddice.comunidice.dao.spi.UsuarioDAO;
import com.rollanddice.comunidice.dao.util.ConnectionManager;
import com.rollanddice.comunidice.dao.util.JDBCUtils;
import com.rollanddice.comunidice.exception.DataException;
import com.rollanddice.comunidice.exception.ServiceException;
import com.rollanddice.comunidice.model.Amigo;
import com.rollanddice.comunidice.model.Usuario;
import com.rollanddice.comunidice.service.spi.AmigoService;

public class AmigoServiceImpl implements AmigoService{
	
	private AmigoDAO dao = null;
	private UsuarioDAO usuarioDao = null;
	public static Logger logger = LogManager.getLogger(AmigoServiceImpl.class);
	
	public AmigoServiceImpl() {
		
	dao = new AmigoDAOImpl();
	usuarioDao = new UsuarioDAOImpl();
	}

	@Override
	public List<Usuario> findAmigos(Integer id) throws ServiceException, DataException {
		
		boolean commit = false;
		Connection c = null;
		
		try {
			c = ConnectionManager.getConnection();
			c.setAutoCommit(false);
			List<Amigo> a = dao.findAmigos(c, id);
			List<Usuario> usuarios = new ArrayList<Usuario>();
			for(Amigo amigo:a) {
				Usuario u = usuarioDao.findById(c, amigo.getAmigo());
				usuarios.add(u);
			}
			
			commit = true;
			logger.info(a);
			return usuarios;
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
	public Usuario findByEmailAmigo(String email, Integer id) throws ServiceException, DataException {
		
		boolean commit = false;
		Connection c = null;
		
		try {
			c = ConnectionManager.getConnection();
			c.setAutoCommit(false);
			email = email.toUpperCase();
			Amigo a = dao.findByEmailAmigo(c, email, id);
			Usuario u = usuarioDao.findById(c, a.getAmigo());
			commit = true;
			
			logger.info(a);
			return u;
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
	public Usuario findByNombreAmigo(String nombreUsuarioAmigo, Integer id) throws ServiceException, DataException {
		
		boolean commit = false;
		Connection c = null;
		
		try {
			c = ConnectionManager.getConnection();
			c.setAutoCommit(false);
			Amigo a = dao.findByNombreAmigo(c, nombreUsuarioAmigo, id);
			Usuario u = usuarioDao.findById(c, a.getAmigo());
			commit = true;
			
			logger.info(a);
			return u;
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