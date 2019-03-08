package com.rollanddice.comunidice.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.Statement;
import com.rollanddice.comunidice.dao.spi.ForoDAO;
import com.rollanddice.comunidice.dao.util.DaoUtils;
import com.rollanddice.comunidice.dao.util.JDBCUtils;
import com.rollanddice.comunidice.model.Criteria;
import com.rollanddice.comunidice.model.Foro;

public class ForoDAOImpl implements ForoDAO{

	@Override
	public Foro findById(Connection c, Integer id) throws Exception {
		
		Foro f= null;
		
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {

			String sql;
			sql =  " SELECT ID_FORO, ID_CATEGORIA, NOMBRE, ID_USUARIO_CREADOR "
				  +" FROM FORO "
				  +" WHERE ID_FORO = ? ";
			
			preparedStatement = c.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			
			int i = 1;
			preparedStatement.setInt(i++, id);
			resultSet = preparedStatement.executeQuery();			
			
			if (resultSet.next()) {				
				f = loadNext(c, resultSet);				
			} else {
				throw new Exception("El foro que buscas no existe");
			}				
		} 
		catch (Exception ex) {
			throw new Exception(ex);
		} 
		finally {            
			JDBCUtils.closeResultSet(resultSet);
			JDBCUtils.closeStatement(preparedStatement);
		}  	
			
		return f;
	}

	@Override
	public List<Foro> findByCriteria(Connection c, Criteria criteria) throws Exception {
		
		Foro f = null;
		List<Foro> foros = new ArrayList<Foro>();
		
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		StringBuilder sql = null;
		try {

			sql = new StringBuilder(
					"SELECT ID_FORO, ID_CATEGORIA, NOMBRE, ID_USUARIO_CREADOR "
					+" FROM FORO ");
			
			boolean first = true;
			
			if(criteria.getIdCategoria()!=null) {
				DaoUtils.anadir(sql, first, "ID_CATEGORIA = ?");
				first = false;
			}
			if(criteria.getNombre() !=null) {
				DaoUtils.anadir(sql, first, "NOMBRE LIKE ? ");
				first = false;
			}
			
			int i = 1;
			preparedStatement = c.prepareStatement(sql.toString(), ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			
			if(criteria.getIdCategoria()!=null) {
				preparedStatement.setInt(i++, criteria.getIdCategoria());
			}
			if(criteria.getNombre() !=null) {
				preparedStatement.setString(i++, criteria.getNombre());
			}
			
			resultSet = preparedStatement.executeQuery();			
					
				while(resultSet.next()) {
					f = loadNext(c, resultSet);
					foros.add(f);
			} 
		} 
		catch (Exception ex) {
			throw new Exception(ex);
		} 
		finally {            
			JDBCUtils.closeResultSet(resultSet);
			JDBCUtils.closeStatement(preparedStatement);
		}  	
			
		return foros;
	}

	@Override
	public void create(Connection c, Foro foro) throws Exception {
		
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {

			String sql;
			sql =  "INSERT INTO FORO (ID_CATEGORIA, ID_USUARIO_CREADOR, NOMBRE) "
				  +" VALUES (?, ?, ?) ";
			
			preparedStatement = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			
			int i = 1;

			preparedStatement.setInt(i++, foro.getCategoria());
			preparedStatement.setInt(i++, foro.getIdUsuario());
			preparedStatement.setString(i++, foro.getNombre());
		

			int insertedRows = preparedStatement.executeUpdate();	
			
			if(insertedRows == 0) {
				throw new SQLException("Operación fallida");
			}
			
			resultSet = preparedStatement.getGeneratedKeys();
		} 
		catch (SQLException ex) {
			throw new Exception(ex);
		} 
		finally {            
			JDBCUtils.closeResultSet(resultSet);
			JDBCUtils.closeStatement(preparedStatement);
		}  	
	}

	@Override
	public void delete(Connection c, Foro foro) throws Exception {
		
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {

			String sql;
			sql =  "DELETE FROM FORO "
				  +" WHERE ID_FORO = ? ";
			
			preparedStatement = c.prepareStatement(sql);
			
			int i = 1;
			preparedStatement.setInt(i++, foro.getIdForo());
			
			int deletedRows = preparedStatement.executeUpdate();	
			
			if(deletedRows == 0) {
				throw new SQLException("Operación fallida");
			}
		} 
		catch (SQLException ex) {
			throw new Exception(ex);
		} 
		finally {            
			JDBCUtils.closeResultSet(resultSet);
			JDBCUtils.closeStatement(preparedStatement);
		}  	
	}
	
	private Foro loadNext(Connection c, ResultSet resultSet) throws Exception {
		
		Foro f = new Foro();
		
		int i = 1;
		Integer id = resultSet.getInt(i++);
		Integer categoria = resultSet.getInt(i++);
		String nombre = resultSet.getString(i++);
		Integer idUsuario = resultSet.getInt(i++);
		
		f.setIdForo(id);
		f.setCategoria(categoria);
		f.setNombre(nombre);
		f.setIdUsuario(idUsuario);
		
		return f;
	}

}
