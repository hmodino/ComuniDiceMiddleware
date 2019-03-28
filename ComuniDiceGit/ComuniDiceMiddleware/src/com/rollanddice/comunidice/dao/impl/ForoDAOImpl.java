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
import com.rollanddice.comunidice.exception.DataException;
import com.rollanddice.comunidice.exception.DuplicateInstanceException;
import com.rollanddice.comunidice.exception.InstanceNotFoundException;
import com.rollanddice.comunidice.model.Criteria;
import com.rollanddice.comunidice.model.Foro;

public class ForoDAOImpl implements ForoDAO{

	@Override
	public Foro findById(Connection c, Integer id) throws InstanceNotFoundException, DataException{
		
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
				f = loadNext(resultSet);				
			} else {
				throw new InstanceNotFoundException(id, "ForoDAOImpl.findById");
			}				
		} 
		catch (SQLException ex) {
			throw new DataException(ex);
		} 
		finally {            
			JDBCUtils.closeResultSet(resultSet);
			JDBCUtils.closeStatement(preparedStatement);
		}  	
			
		return f;
	}

	@Override
	public List<Foro> findByCriteria(Connection c, Criteria criteria) throws InstanceNotFoundException, DataException{
		
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
				
			if(resultSet.next()) {
				do {
					f = loadNext(resultSet);
					foros.add(f);
				}while(resultSet.next());
			}		 
		} 
		catch (SQLException ex) {
			throw new DataException(ex);
		} 
		finally {            
			JDBCUtils.closeResultSet(resultSet);
			JDBCUtils.closeStatement(preparedStatement);
		}  	
			
		return foros;
	}

	@Override
	public void create(Connection c, Foro foro) throws DuplicateInstanceException, DataException {
		
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
				throw new DuplicateInstanceException(foro, "ForoDAOImpl.create");
			}
			
			resultSet = preparedStatement.getGeneratedKeys();
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
	public void delete(Connection c, Foro foro) throws InstanceNotFoundException, DataException{
		
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
				throw new InstanceNotFoundException(foro, "ForoDAOImpl.delete");
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
	
	private Foro loadNext(ResultSet resultSet) throws SQLException{
		
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
