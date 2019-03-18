package com.rollanddice.comunidice.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.rollanddice.comunidice.dao.spi.RegionDAO;
import com.rollanddice.comunidice.dao.util.JDBCUtils;
import com.rollanddice.comunidice.exception.DataException;
import com.rollanddice.comunidice.exception.InstanceNotFoundException;
import com.rollanddice.comunidice.model.Region;

public class RegionDAOImpl implements RegionDAO{

	@Override
	public Region findById(Connection c, Integer id) throws InstanceNotFoundException, DataException{
		
		Region r = null;
		
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {

			String sql;
			sql =  "SELECT ID_REGION, ID_PAIS, NOMBRE "
				  +" FROM REGION "
				  +" WHERE ID_REGION = ? ";
			
			preparedStatement = c.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			
			int i = 1;
			preparedStatement.setInt(i++, id);
			resultSet = preparedStatement.executeQuery();			
			
			if (resultSet.next()) {				
				r = loadNext(resultSet);				
			} else {
				throw new InstanceNotFoundException(id, "RegionDAOImpl.findById");
			}				
		} 
		catch (SQLException ex) {
			throw new DataException(ex);
		} 
		finally {            
			JDBCUtils.closeResultSet(resultSet);
			JDBCUtils.closeStatement(preparedStatement);
		}  	
			
		return r;
	}

	@Override
	public List<Region> findByPais(Connection c, Integer idPais) throws InstanceNotFoundException, DataException{
			
			Region r = null;
			List<Region> rs = new ArrayList<Region>();
			
			PreparedStatement preparedStatement = null;
			ResultSet resultSet = null;
			try {

				String sql;
				sql =  "SELECT ID_REGION, ID_PAIS, NOMBRE "
					  +" FROM REGION "
					  +" WHERE ID_PAIS = ? ";
				
				preparedStatement = c.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
				
				int i = 1;
				preparedStatement.setInt(i++, idPais);
				resultSet = preparedStatement.executeQuery();			
				
				if (resultSet.next()) {
					do {
						r = loadNext(resultSet);
						rs.add(r);
					}while(!resultSet.isLast());
				} else {
					throw new InstanceNotFoundException(idPais, "RegionDAOImpl.findByPais");
				}				
			} 
			catch (SQLException ex) {
				throw new DataException(ex);
			} 
			finally {            
				JDBCUtils.closeResultSet(resultSet);
				JDBCUtils.closeStatement(preparedStatement);
			}  	
				
			return rs;
	}

	private Region loadNext(ResultSet resultSet) throws SQLException {
		
		Region r = new Region();
		
		int i = 1;
		Integer id = resultSet.getInt(i++);
		Integer idPais = resultSet.getInt(i++);
		String nombre = resultSet.getString(i++);
		
		r.setIdRegion(id);
		r.setIdPais(idPais);
		r.setNombre(nombre);
		
		return r;
		
	}
}
