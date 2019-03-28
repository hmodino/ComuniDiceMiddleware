package com.rollanddice.comunidice.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.rollanddice.comunidice.dao.spi.AmigoDAO;
import com.rollanddice.comunidice.dao.util.JDBCUtils;
import com.rollanddice.comunidice.exception.DataException;
import com.rollanddice.comunidice.exception.DuplicateInstanceException;
import com.rollanddice.comunidice.exception.InstanceNotFoundException;
import com.rollanddice.comunidice.model.Amigo;

public class AmigoDAOImpl implements AmigoDAO{

	@Override
	public void create(Connection c, Integer idUsuario, Integer idAmigo) throws DuplicateInstanceException, DataException {
		
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {

			String sql;
			sql =  "INSERT INTO AMIGO(ID_USUARIO, ID_USUARIO2, FECHA) "
				  +" VALUES (?, ?, ?) ";
			
			preparedStatement = c.prepareStatement(sql);
			
			int i = 1;
			preparedStatement.setInt(i++, idUsuario);
			preparedStatement.setInt(i++, idAmigo);
			preparedStatement.setDate(i++, new java.sql.Date( new Date().getTime()));			

			int insertedRows = preparedStatement.executeUpdate();	
			
			if(insertedRows == 0) {
				throw new DuplicateInstanceException(idAmigo, "AmigoDAOImpl.create");
			}
		} 
		catch (SQLException ex) {
			throw new DataException(ex);
		} 
		finally {            
			JDBCUtils.closeResultSet(resultSet);
			JDBCUtils.closeStatement(preparedStatement);
		}  	
		
	}

	@Override
	public void delete(Connection c, Integer idUsuario, Integer idAmigo) throws InstanceNotFoundException, DataException {
		
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {

			String sql;
			sql =  "DELETE FROM `AMIGO` "
				  +" WHERE ID_USUARIO = ? AND ID_USUARIO2 = ? ";
			
			preparedStatement = c.prepareStatement(sql);
			
			int i = 1;
			preparedStatement.setInt(i++, idUsuario);
			preparedStatement.setInt(i++, idAmigo);
			
			int deletedRows = preparedStatement.executeUpdate();	
			
			if(deletedRows == 0) {
				throw new InstanceNotFoundException(idAmigo, "AmigoDAOImpl.delete");
			}
		} 
		catch (SQLException ex) {
			throw new DataException(ex);
		} 
		finally {            
			JDBCUtils.closeResultSet(resultSet);
			JDBCUtils.closeStatement(preparedStatement);
		}  	
	}

	@Override
	public List<Amigo> findAmigos(Connection c, Integer id) throws InstanceNotFoundException, DataException {
		
		List<Amigo> amigos = new ArrayList<Amigo>();
		Amigo a = null;
		
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {

			String sql;
			sql =  "SELECT ID_USUARIO2, FECHA "
				  +" FROM AMIGO "
				  +" WHERE ID_USUARIO = ? ";
			
			preparedStatement = c.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			
			int i = 1;
			preparedStatement.setInt(i++, id);
			resultSet = preparedStatement.executeQuery();	
			if(resultSet.next()) {
				do {
					a = loadNext(resultSet);
					amigos.add(a);
				}while(resultSet.next());
			}else {
				throw new InstanceNotFoundException(id, "AmigoDAOImpl.delete");
			}

		} 
		catch (Exception ex) {
			throw new DataException(ex);
		} 
		finally {            
			JDBCUtils.closeResultSet(resultSet);
			JDBCUtils.closeStatement(preparedStatement);
		}  	
		
		return amigos;
	}

	@Override
	public Amigo findByEmailAmigo(Connection c, String email, Integer id) throws InstanceNotFoundException, DataException {
		
		Amigo a = null;
		
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {

			String sql;
			sql =  "SELECT A.ID_USUARIO2, A.FECHA "
				  +" FROM AMIGO A INNER JOIN USUARIO U ON(A.ID_USUARIO2 = U.ID_USUARIO) "
				  +" WHERE A.ID_USUARIO = ? AND U.EMAIL LIKE ? ";
			
			preparedStatement = c.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			
			int i = 1;
			preparedStatement.setInt(i++, id);
			preparedStatement.setString(i++, email);
			resultSet = preparedStatement.executeQuery();	
			
			if (resultSet.next()) {				
				a = loadNext(resultSet);
			} else {
				throw new InstanceNotFoundException(a, "AmigoDAOImpl.findByEmailAmigo");
			}				

		} 
		catch (Exception ex) {
			throw new DataException(ex);
		} 
		finally {            
			JDBCUtils.closeResultSet(resultSet);
			JDBCUtils.closeStatement(preparedStatement);
		}  	
		
		return a;
	}

	@Override
	public Amigo findByNombreAmigo(Connection c, String nombreUsuarioAmigo, Integer id) 
			throws InstanceNotFoundException, DataException {
		
		Amigo a = null;
		
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {

			String sql;
			sql =  "SELECT A.ID_USUARIO2, A.FECHA "
				  +" FROM AMIGO A INNER JOIN USUARIO U ON(A.ID_USUARIO2 = U.ID_USUARIO) "
				  +" WHERE A.ID_USUARIO = ? AND U.NOMBRE_USUARIO LIKE ? ";
			
			preparedStatement = c.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			
			int i = 1;
			preparedStatement.setInt(i++, id);
			preparedStatement.setString(i++, nombreUsuarioAmigo);
			resultSet = preparedStatement.executeQuery();	
			
			if (resultSet.next()) {				
				a = loadNext(resultSet);
			} else {
				throw new InstanceNotFoundException(a, "AmigoDAOImpl.findByNombreAmigo");
			}				

		} 
		catch (Exception ex) {
			throw new DataException(ex);
		} 
		finally {            
			JDBCUtils.closeResultSet(resultSet);
			JDBCUtils.closeStatement(preparedStatement);
		}  	
		
		return a;
	}
	
	private Amigo loadNext(ResultSet resultSet) throws SQLException{
		
		Amigo amigo = new Amigo();
		
		int i = 1;
		Integer id = resultSet.getInt(i++);
		Date fecha = resultSet.getDate(i++);
		
		amigo.setAmigo(id);
		amigo.setFecha(fecha);
		
		return amigo;
		
	}
}
