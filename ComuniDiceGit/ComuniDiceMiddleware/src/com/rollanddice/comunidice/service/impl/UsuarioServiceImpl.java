package com.rollanddice.comunidice.service.impl;

import java.sql.Connection;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.rollanddice.comunidice.dao.impl.DireccionDAOImpl;
import com.rollanddice.comunidice.dao.impl.UsuarioDAOImpl;
import com.rollanddice.comunidice.dao.spi.DireccionDAO;
import com.rollanddice.comunidice.dao.spi.UsuarioDAO;
import com.rollanddice.comunidice.dao.util.ConnectionManager;
import com.rollanddice.comunidice.dao.util.JDBCUtils;
import com.rollanddice.comunidice.model.Direccion;
import com.rollanddice.comunidice.model.Usuario;
import com.rollanddice.comunidice.service.spi.MailService;
import com.rollanddice.comunidice.service.spi.UsuarioService;
import com.rollanddice.comunidice.util.PasswordEncryptionUtil;

public class UsuarioServiceImpl implements UsuarioService{
	
	UsuarioDAO dao = null;
	MailService aviso = null;
	DireccionDAO direccion = null;
	
	public static Logger logger = LogManager.getLogger(UsuarioServiceImpl.class);
	
	public UsuarioServiceImpl() {
		
		dao = new UsuarioDAOImpl();
		aviso = new MailServiceImpl();
		direccion = new DireccionDAOImpl();
	}

	@Override
	public Usuario findById(Integer id) throws Exception {
		
		boolean commit = false;
		Connection c = null;
		
		try {
			c = ConnectionManager.getConnection();
			c.setAutoCommit(false);
			Usuario u = dao.findById(c, id);
			logger.info(u);
			commit = true;
			return u;
		}
		catch (Exception ex) {
			logger.warn(ex.getMessage(), ex);
			throw ex;
		} 
		finally { 
			JDBCUtils.closeConnection(c, commit);
		}
	}

	@Override
	public Usuario findByNombre(String nombreUsuario) throws Exception {
		
		boolean commit = false;
		Connection c = null;
		
		try {
			c = ConnectionManager.getConnection();
			c.setAutoCommit(false);
			Usuario u = dao.findByNombre(c, nombreUsuario);
			
			commit = true;
			logger.info(u);
			return u;
		}
		catch (Exception ex) {
			logger.warn(ex.getMessage(), ex);
			throw ex;
		} 
		finally { 
			JDBCUtils.closeConnection(c, commit);
		}
	}

	@Override
	public Usuario findByEmail(String email) throws Exception {
		
		boolean commit = false;
		Connection c = null;
		
		try {
			c = ConnectionManager.getConnection();
			c.setAutoCommit(false);
			email = email.toUpperCase();
			Usuario u = dao.findByEmail(c, email);
			commit = true;
			logger.info(u);
			return u;
		}
		catch (Exception ex) {
			logger.warn(ex.getMessage(), ex);
			throw ex;
		} 
		finally { 
			JDBCUtils.closeConnection(c, commit);
		}
	}

	@Override
	public void signUp(Usuario u, Direccion d) throws Exception {
		
		boolean commit = false;
		Connection c = null;
		try {
			c = ConnectionManager.getConnection();
			c.setAutoCommit(false);
			u.setEmail(u.getEmail().toUpperCase());
			u.setContrasenha(u.getContrasenha().toUpperCase());
				
			dao.create(c, u);
			if(d!=null) {
				direccion.create(c, d, u.getIdUsuario());
			}
			aviso.emailService("Bienvenido a ComuniDice", "Bienvenido a Comunidice "+u.getNombre()+" "+
			u.getApellido1(), u.getEmail());
			commit = true;
			logger.info("Usuario creado con éxito");
			
		}
		catch (Exception ex) {
			logger.warn(ex.getMessage(), ex);
			throw ex;
		} 
		finally { 
			JDBCUtils.closeConnection(c, commit);
		} 	
	}

	@Override
	public Usuario logIn(String email, String password) throws Exception {
		
		boolean commit = false;
		Connection c = null;
		try {
			c = ConnectionManager.getConnection();
			c.setAutoCommit(false);
			email = email.toUpperCase();
			Usuario u = findByEmail(email);
			if(u!=null) {
				if(PasswordEncryptionUtil.checkPassword(password.toUpperCase(), u.getContrasenha())==true) {
					logger.info("Usuario "+u.getNombreUsuario()+" autenticado");
				}
				else {logger.info("Problema en la contraseña");}
			}
			else {logger.info("Usuario no encontrado");}
			commit = true;
			return u;
		}
		catch (Exception ex) {
			logger.warn(ex.getMessage(), ex);
			throw ex;
		} 
		finally { 
			JDBCUtils.closeConnection(c, commit);
		}
		
	}

	@Override
	public void editar(Usuario u, Direccion d) throws Exception {
		
		boolean commit = false;
		Connection c = null;
		try {
			c = ConnectionManager.getConnection();
			c.setAutoCommit(false);
			c.setTransactionIsolation(
                    Connection.TRANSACTION_READ_COMMITTED);
			if(u.getEmail()!=null) {
				u.setEmail(u.getEmail().toUpperCase());
			}
			if(u.getContrasenha()!=null) {
				u.setContrasenha(u.getContrasenha().toUpperCase());
			}
			if(u != null) {
				dao.update(c, u);
				aviso.emailService("Usuario editado", "El Usuario "+u.getNombreUsuario()+" ha sido editado con éxito ", u.getEmail());
			}
			if(d != null) {
				direccion.update(c, d);
			}
			commit = true;
			logger.info("Usuario editado con éxito");
		}
		catch (Exception ex) {
			logger.warn(ex.getMessage(), ex);
			throw ex;
		} 
		finally { 
			JDBCUtils.closeConnection(c, commit);
		}
		
	}

	@Override
	public void eliminar(Usuario u) throws Exception {
		
		boolean commit = false;
		Connection c = null;
		try {
			c = ConnectionManager.getConnection();
			c.setTransactionIsolation(
                    Connection.TRANSACTION_READ_COMMITTED);
			c.setAutoCommit(false);
			dao.delete(c, u);
			aviso.emailService("Usuario Eliminado de ComuniDice", "El usuario "+u.getNombreUsuario()+" ha sido eliminado de ComuniDice. Esperamos volver a verle pronto", u.getEmail());
			logger.info("Usuario eliminado con éxito");
			commit = true;
		}
		catch (Exception ex) {
			logger.warn(ex.getMessage(), ex);
			throw ex;
		} 
		finally { 
			JDBCUtils.closeConnection(c, commit);
		}
		
	}

}
