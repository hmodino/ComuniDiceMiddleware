package com.rollanddice.comunidice.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.rollanddice.comunidice.dao.spi.PaisDAO;
import com.rollanddice.comunidice.dao.util.JDBCUtils;
import com.rollanddice.comunidice.exception.DataException;
import com.rollanddice.comunidice.exception.InstanceNotFoundException;
import com.rollanddice.comunidice.model.Pais;

public class PaisDAOImpl implements PaisDAO{

	@Override
	public Pais findById(Connection c, Integer id) throws InstanceNotFoundException, DataException{
		
		Pais p = null;
		
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {

			String sql;
			sql =  "SELECT ID_PAIS, NOMBRE_PAIS "
				  +" FROM PAIS "
				  +" WHERE ID_PAIS = ? ";
			
			preparedStatement = c.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			
			int i = 1;
			preparedStatement.setInt(i++, id);
			resultSet = preparedStatement.executeQuery();			
			
			if (resultSet.next()) {				
				p = loadNext(resultSet);				
			} else {
				throw new InstanceNotFoundException(id, "PaisDAOImpl.findById");
			}				
		} 
		catch (SQLException ex) {
			throw new DataException(ex);
		} 
		finally {            
			JDBCUtils.closeResultSet(resultSet);
			JDBCUtils.closeStatement(preparedStatement);
		}  	
			
		return p;
	}

	@Override
	public List<Pais> findAll(Connection c) throws DataException{
		
		Pais p = null;
		List<Pais> ps = new ArrayList<Pais>();
		
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {

			String sql;
			sql =  "SELECT ID_PAIS, NOMBRE_PAIS "
				  +" FROM PAIS ";
			
			preparedStatement = c.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			
			resultSet = preparedStatement.executeQuery();			
			
			if (resultSet.next()) {	
				do{
					p = loadNext(resultSet);
					ps.add(p);
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
			
		return ps;
	}

	private Pais loadNext(ResultSet resultSet) throws SQLException {
		
		Pais p = new Pais();
		
		int i = 1;
		Integer id = resultSet.getInt(i++);
		String nombre = resultSet.getString(i++);
		
		p.setIdPais(id);
		p.setNombre(nombre);
		
		return p;
	}

}
