package com.rollanddice.comunidice.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.rollanddice.comunidice.dao.spi.CategoriaDAO;
import com.rollanddice.comunidice.dao.util.JDBCUtils;
import com.rollanddice.comunidice.exception.DataException;
import com.rollanddice.comunidice.exception.InstanceNotFoundException;
import com.rollanddice.comunidice.model.Categoria;

public class CategoriaDAOImpl implements CategoriaDAO{
	
	@Override
	public List<Categoria> findAll(Connection c) throws InstanceNotFoundException, DataException {
		
		List<Categoria> categorias = new ArrayList<Categoria>();
		Categoria categoria = null;
		
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {

			String sql;
			sql =  "SELECT ID_CATEGORIA, NOMBRE"
				  +"FROM CATEGORIA";
			
			preparedStatement = c.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			
			resultSet = preparedStatement.executeQuery();	
			
			if (resultSet.next()) {				
				do {
					categoria = loadNext(resultSet);
					categorias.add(categoria);
				}while(resultSet.next());
			} else {
				throw new InstanceNotFoundException("Categorias", "CategoriaDAOImpl.findById");
			}				

		} 
		catch (SQLException ex) {
			throw new DataException(ex);
		} 
		finally {            
			JDBCUtils.closeResultSet(resultSet);
			JDBCUtils.closeStatement(preparedStatement);
		}  	
		
		return categorias;
	}
	
	private Categoria loadNext(ResultSet resultSet) throws SQLException{
		
		Categoria categoria = new Categoria();
		
		int i = 1;
		Integer id = resultSet.getInt(i++);
		String nombre = resultSet.getString(i++);
		
		categoria.setId(id);
		categoria.setNombre(nombre);
		
		return categoria;
		
	}

}
