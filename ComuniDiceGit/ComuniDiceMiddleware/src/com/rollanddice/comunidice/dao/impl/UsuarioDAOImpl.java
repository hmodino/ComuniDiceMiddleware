package com.rollanddice.comunidice.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.rollanddice.comunidice.dao.spi.UsuarioDAO;
import com.rollanddice.comunidice.dao.util.DaoUtils;
import com.rollanddice.comunidice.dao.util.JDBCUtils;
import com.rollanddice.comunidice.exception.DataException;
import com.rollanddice.comunidice.exception.DuplicateInstanceException;
import com.rollanddice.comunidice.exception.InstanceNotFoundException;
import com.rollanddice.comunidice.model.Usuario;
import com.rollanddice.comunidice.util.PasswordEncryptionUtil;

public class UsuarioDAOImpl implements UsuarioDAO{
	
	private static Logger logger = LogManager.getLogger(UsuarioDAOImpl.class);

	@Override
	public Usuario findById(Connection c, Integer id) throws InstanceNotFoundException, DataException{
		
		if(logger.isDebugEnabled()) {
			logger.debug("id = "+id);
		}
		
		Usuario u = null;
		
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {

			String sql;
			sql =  " SELECT ID_USUARIO, EMAIL, CONTRASENA, NOMBRE, APELLIDO1, APELLIDO2, NOMBRE_USUARIO, FECHA_ALTA, DESCRIPCION "
				  +" FROM USUARIO "
				  +" WHERE ID_USUARIO = ? ";
			
			logger.debug("Query = "+sql);
			preparedStatement = c.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			
			int i = 1;
			preparedStatement.setInt(i++, id);
			resultSet = preparedStatement.executeQuery();			
			
			if (resultSet.next()) {				
				u = loadNext(resultSet);				
			} else {
				throw new InstanceNotFoundException(id, "UsuarioDAOImpl.findById");
			}				
		} 
		catch (SQLException ex) {
			throw new DataException();
		} 
		finally {            
			JDBCUtils.closeResultSet(resultSet);
			JDBCUtils.closeStatement(preparedStatement);
		}  	
			
		return u;
	}

	@Override
	public Usuario findByNombre(Connection c, String nombreUsuario) throws InstanceNotFoundException, DataException{
		
		Usuario u = null;
		
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {

			String sql;
			sql =  " SELECT ID_USUARIO, EMAIL, CONTRASENA, NOMBRE, APELLIDO1, APELLIDO2, NOMBRE_USUARIO, FECHA_ALTA, "
					+" DESCRIPCION, TELEFONO "
					+" FROM USUARIO "
					+" WHERE NOMBRE_USUARIO LIKE ? ";
			
			preparedStatement = c.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			
			int i = 1;
			preparedStatement.setString(i++, nombreUsuario);
			resultSet = preparedStatement.executeQuery();			
			
			if (resultSet.next()) {				
				u = loadNext(resultSet);				
			} else {
				throw new InstanceNotFoundException(nombreUsuario, "UsuarioDAOImpl.findByNombre");
			}				
		} 
		catch (SQLException ex) {
			throw new DataException();
		} 
		finally {            
			JDBCUtils.closeResultSet(resultSet);
			JDBCUtils.closeStatement(preparedStatement);
		}  	
			
		return u;
	}

	@Override
	public Usuario findByEmail(Connection c, String email) throws InstanceNotFoundException, DataException{
		
		if(logger.isDebugEnabled()) {
			logger.debug("Email = "+email==null);
		}
		
		Usuario u = null;
		
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {

			String sql;
			sql =  " SELECT ID_USUARIO, EMAIL, CONTRASENA, NOMBRE, APELLIDO1, APELLIDO2, NOMBRE_USUARIO, FECHA_ALTA, "
					+" DESCRIPCION, TELEFONO "
					+" FROM USUARIO "
					+" WHERE EMAIL LIKE ? ";
			
			preparedStatement = c.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			
			int i = 1;
			preparedStatement.setString(i++, email);
			resultSet = preparedStatement.executeQuery();			
			
			if (resultSet.next()) {				
				u = loadNext(resultSet);				
			} else {
				throw new InstanceNotFoundException(email, "UsuarioDAOImpl.findByEmail");
			}				
		} 
		catch (SQLException ex) {
			throw new DataException();
		} 
		finally {            
			JDBCUtils.closeResultSet(resultSet);
			JDBCUtils.closeStatement(preparedStatement);
		}  	
			
		return u;
	}

	@Override
	public void create(Connection c, Usuario u) throws DuplicateInstanceException, DataException{
		
		if(logger.isDebugEnabled()) {
				logger.debug("id = "+u.getIdUsuario()+" email = "+(u.getEmail()==null)+" contraseña = "+(u.getContrasenha()==null)+
				" nombre = "+u.getNombre()+" apellido = "+u.getApellido1()+" apellido = "+u.getApellido2()+" nombre usuario "+
				u.getNombreUsuario()+" fecha alta "+u.getFechaAlta()+" descripción "+u.getDescripcion()+" telefono "+u.getTelefono());
		}
		
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {

			String sql;
			sql =  "INSERT INTO USUARIO (EMAIL , CONTRASENA, NOMBRE, APELLIDO1, APELLIDO2, NOMBRE_USUARIO, FECHA_ALTA, "
					+" DESCRIPCION, TELEFONO) "
					+" VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
			
			preparedStatement = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			
			int i = 1;
			preparedStatement.setString(i++, u.getEmail());
			preparedStatement.setString(i++, PasswordEncryptionUtil.encryptPassword(u.getContrasenha()));
			preparedStatement.setString(i++, u.getNombre());
			preparedStatement.setString(i++, u.getApellido1());
			preparedStatement.setString(i++, u.getApellido2());
			preparedStatement.setString(i++, u.getNombreUsuario());
			preparedStatement.setDate(i++, (new java.sql.Date(new Date().getTime())));
			preparedStatement.setString(i++, u.getDescripcion());
			preparedStatement.setString(i++, u.getTelefono());
			
			int insertedRows = preparedStatement.executeUpdate();	
			
			if(insertedRows == 0) {
				throw new DuplicateInstanceException(u, "UsuarioDAOImpl.create");
			}
			
			resultSet = preparedStatement.getGeneratedKeys();
			
			if(logger.isDebugEnabled()) {
				logger.debug("id = "+u.getIdUsuario()+" email = "+(u.getEmail()==null)+" contraseña = "+(u.getContrasenha()==null)+
				" nombre = "+u.getNombre()+" apellido = "+u.getApellido1()+" apellido = "+u.getApellido2()+" nombre usuario "+
				u.getNombreUsuario()+" fecha alta "+u.getFechaAlta()+" descripción "+u.getDescripcion()+" telefono "+u.getTelefono());
		}
		} 
		catch (SQLException ex) {
			throw new DataException();
		} 
		finally {            
			JDBCUtils.closeResultSet(resultSet);
			JDBCUtils.closeStatement(preparedStatement);
		}  	

	}

	@Override
	public void update(Connection c, Usuario u) throws InstanceNotFoundException, DataException{

		if(logger.isDebugEnabled()) {
				logger.debug("id = "+u.getIdUsuario()+" email = "+(u.getEmail()==null)+" contraseña = "+(u.getContrasenha()==null)+
				" nombre = "+u.getNombre()+" apellido = "+u.getApellido1()+" apellido = "+u.getApellido2()+" nombre usuario "+
				u.getNombreUsuario()+" fecha alta "+u.getFechaAlta()+" descripción "+u.getDescripcion()+" telefono "+u.getTelefono());
		}
		
		StringBuilder sql = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {

			sql =  new StringBuilder("UPDATE USUARIO ");
			
			boolean first = true;
			
			if(u.getContrasenha()!=null) {
				DaoUtils.update(sql, first, " CONTRASENA = ?");
				first = false;
			}
			if(u.getNombre()!=null) {
				DaoUtils.update(sql, first, " NOMBRE = ?");
				first = false;
			}
			if(u.getApellido1()!=null) {
				DaoUtils.update(sql, first, " APELLIDO1 = ?");
				first = false;
			}
			if(u.getApellido2()!=null) {
				DaoUtils.update(sql, first, " APELLIDO2 = ?");
				first = false;
			}
			if(u.getNombreUsuario()!=null) {
				DaoUtils.update(sql, first, " NOMBRE_USUARIO = ?");
				first = false;
			}
			if(u.getDescripcion()!=null) {
				DaoUtils.update(sql, first, " DESCRIPCION = ?");
				first = false;
			}
			if(u.getTelefono()!=null) {
				DaoUtils.update(sql, first, " TELEFONO = ?");
				first = false;
			}
			
			sql.append(" WHERE ID_USUARIO = ?");
			
			preparedStatement = c.prepareStatement(sql.toString());
			int i = 1;

			if(u.getContrasenha()!=null) {
				preparedStatement.setString(i++, PasswordEncryptionUtil.encryptPassword(u.getContrasenha()));
			}
			if(u.getNombre()!=null) {
				preparedStatement.setString(i++, u.getNombre());
			}
			if(u.getApellido1()!=null) {
				preparedStatement.setString(i++, u.getApellido1());
			}
			if(u.getApellido2()!=null) {
				preparedStatement.setString(i++, u.getApellido2());
			}
			if(u.getNombreUsuario()!=null) {
				preparedStatement.setString(i++, u.getNombreUsuario());
			}
			if(u.getDescripcion()!=null) {
				preparedStatement.setString(i++, u.getDescripcion());
			}
			if(u.getTelefono()!=null) {
				preparedStatement.setString(i++, u.getTelefono());
			}

			preparedStatement.setInt(i++, u.getIdUsuario());
			
			int insertedRows = preparedStatement.executeUpdate();	
			
			if(insertedRows == 0) {
				throw new InstanceNotFoundException(u, "UsuarioDAOImpl.update");
			}
		} 
		catch (SQLException ex) {
			throw new DataException();

		} 
		finally {            
			JDBCUtils.closeResultSet(resultSet);
			JDBCUtils.closeStatement(preparedStatement);
		}
	}

	@Override
	public void delete(Connection c, Usuario u) throws InstanceNotFoundException, DataException{
		
		if(logger.isDebugEnabled()) {
				logger.debug("id = "+u.getIdUsuario()+" email = "+(u.getEmail()==null)+" contraseña = "+(u.getContrasenha()==null)+
				" nombre = "+u.getNombre()+" apellido = "+u.getApellido1()+" apellido = "+u.getApellido2()+" nombre usuario "+
				u.getNombreUsuario()+" fecha alta "+u.getFechaAlta()+" descripción "+u.getDescripcion()+" telefono "+u.getTelefono());
		}
		
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {

			String sql;
			sql =  "DELETE FROM `USUARIO`"
				  +" WHERE ID_USUARIO = ? ";
			
			preparedStatement = c.prepareStatement(sql);
			
			int i = 1;
			preparedStatement.setInt(i++, u.getIdUsuario());
			int deletedRows = preparedStatement.executeUpdate();
			
			if(deletedRows == 0) {
				throw new InstanceNotFoundException(u, "UsuarioDAOImpl.delete");
			}
		} 
		catch (SQLException ex) {
			logger.warn(ex.getMessage(), ex);
			throw new DataException();
		} 
		finally {            
			JDBCUtils.closeResultSet(resultSet);
			JDBCUtils.closeStatement(preparedStatement);
		}  	
	}
	
	private Usuario loadNext(ResultSet resultSet) throws SQLException {
		
		Usuario u = new Usuario();
		
		int i = 1;
		Integer id = resultSet.getInt(i++);
		String email = resultSet.getString(i++);
		String contrasenha = resultSet.getString(i++);
		String nombre = resultSet.getString(i++);
		String apellido1 = resultSet.getString(i++);
		String apellido2 = resultSet.getString(i++);
		String nombreUsuario = resultSet.getString(i++);
		Date fechaAlta = resultSet.getDate(i++);
		String descripcion = resultSet.getString(i++);
		
		u.setIdUsuario(id);
		u.setEmail(email);
		u.setContrasenha(contrasenha);
		u.setNombre(nombre);
		u.setApellido1(apellido1);
		u.setApellido2(apellido2);
		u.setNombreUsuario(nombreUsuario);
		u.setFechaAlta(fechaAlta);
		u.setDescripcion(descripcion);

		
		return u;
	}
}
