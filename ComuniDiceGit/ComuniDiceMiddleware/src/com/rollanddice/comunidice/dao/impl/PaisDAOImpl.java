package com.rollanddice.comunidice.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.rollanddice.comunidice.dao.spi.PaisDAO;
import com.rollanddice.comunidice.dao.util.JDBCUtils;
import com.rollanddice.comunidice.model.Pais;

public class PaisDAOImpl implements PaisDAO{

	@Override
	public Pais findById(Connection c, Integer id) throws Exception {
		
		Pais p = null;
		
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {

			String sql;
			sql =  "SELECT ID_PAIS, NOMBRE "
				  +" FROM PAIS "
				  +" WHERE ID_PAIS = ? ";
			
			preparedStatement = c.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			
			int i = 1;
			preparedStatement.setInt(i++, id);
			resultSet = preparedStatement.executeQuery();			
			
			if (resultSet.next()) {				
				p = loadNext(resultSet);				
			} else {
				throw new Exception("La b�squeda que has introducido no ha producido ning�n resultado");
			}				
		} 
		catch (Exception ex) {
			throw new Exception(ex);
		} 
		finally {            
			JDBCUtils.closeResultSet(resultSet);
			JDBCUtils.closeStatement(preparedStatement);
		}  	
			
		return p;
	}

	@Override
	public List<Pais> findAll(Connection c) throws Exception {
		
		Pais p = null;
		List<Pais> ps = new ArrayList<Pais>();
		
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {

			String sql;
			sql =  "SELECT ID_PAIS, NOMBRE "
				  +" FROM PAIS ";
			
			preparedStatement = c.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			
			resultSet = preparedStatement.executeQuery();			
			
			if (resultSet.next()) {	
				while(resultSet.next()) {
					p = loadNext(resultSet);
					ps.add(p);
				}				
			} else {
				throw new Exception("La b�squeda que has introducido no ha producido ning�n resultado");
			}				
		} 
		catch (Exception ex) {
			throw new Exception(ex);
		} 
		finally {            
			JDBCUtils.closeResultSet(resultSet);
			JDBCUtils.closeStatement(preparedStatement);
		}  	
			
		return ps;
	}

	private Pais loadNext(ResultSet resultSet) throws Exception {
		
		Pais p = new Pais();
		
		int i = 1;
		Integer id = resultSet.getInt(i++);
		String nombre = resultSet.getString(i++);
		
		p.setIdPais(id);
		p.setNombre(nombre);
		
		return p;
	}

}
