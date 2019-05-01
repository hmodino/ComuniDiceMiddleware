package com.rollanddice.comunidice.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.rollanddice.comunidice.dao.spi.DireccionDAO;
import com.rollanddice.comunidice.dao.util.DaoUtils;
import com.rollanddice.comunidice.dao.util.JDBCUtils;
import com.rollanddice.comunidice.exception.DataException;
import com.rollanddice.comunidice.exception.InstanceNotFoundException;
import com.rollanddice.comunidice.model.Direccion;

public class DireccionDAOImpl implements DireccionDAO{

	@Override
	public Direccion findByUsuario(Connection c, Integer idUsuario) throws InstanceNotFoundException, DataException {
		
		Direccion d = null;
		
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {

			String sql;
			sql =  "SELECT ID_DIRECCION, ID_REGION, MUNICIPIO, LOCALIDAD, CP, CALLE, NUMERO, PORTAL, PISO, OTROS "
				  +" FROM DIRECCION "
				  +" WHERE ID_USUARIO = ? ";
			
			preparedStatement = c.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			
			int i = 1;
			preparedStatement.setInt(i++, idUsuario);
			resultSet = preparedStatement.executeQuery();			
			
			if (resultSet.next()) {				
				d = loadNext(resultSet);				
			} else {
				throw new InstanceNotFoundException(idUsuario, "DireccionDAOImpl.findByUsuario");
			}				
		} 
		catch (SQLException ex) {
			throw new DataException(ex);
		} 
		finally {            
			JDBCUtils.closeResultSet(resultSet);
			JDBCUtils.closeStatement(preparedStatement);
		}  	
			
		return d;
	}

	@Override
	public void create(Connection c, Direccion d, Integer idUsuario) throws DataException {
		
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {

			StringBuilder sql;
			sql = new StringBuilder("INSERT INTO DIRECCION (ID_REGION, ID_USUARIO, MUNICIPIO, CP");
				  
			if(d.getLocalidad()!=null) {
				sql.append(", LOCALIDAD");
			}
			if(d.getCalle()!=null) {
				sql.append(", CALLE");
			}
			if(d.getNumero()!=null) {
				sql.append(", NUMERO");
			}
			if(d.getPortal()!=null) {
				sql.append(", PORTAL");
			}
			if(d.getPiso()!=null) {
				sql.append(", PISO");
			}
			if(d.getOtros()!=null) {
				sql.append(", OTROS");
			}
			sql.append(") VALUES (?, ?, ?, ?");
			if(d.getLocalidad()!=null) {
				sql.append(", ?");
			}
			if(d.getCalle()!=null) {
				sql.append(", ?");
			}
			if(d.getNumero()!=null) {
				sql.append(", ?");
			}
			if(d.getPortal()!=null) {
				sql.append(", ?");
			}
			if(d.getPiso()!=null) {
				sql.append(", ?");
			}
			if(d.getOtros()!=null) {
				sql.append(", ?");
			}
			sql.append(")");
			
			preparedStatement = c.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);
			
			int i = 1;
			preparedStatement.setInt(i++, d.getRegion());
			preparedStatement.setInt(i++, idUsuario);
			preparedStatement.setString(i++, d.getMunicipio());
			preparedStatement.setString(i++, d.getCp());
			if(d.getLocalidad()!=null) {
				preparedStatement.setString(i++, d.getLocalidad());
			}
			if(d.getCalle()!=null) {
				preparedStatement.setString(i++, d.getCalle());
			}
			if(d.getNumero()!=null) {
				preparedStatement.setInt(i++, d.getNumero());
			}
			if(d.getPortal()!=null) {
				preparedStatement.setString(i++, d.getPortal());
			}
			if(d.getPiso()!=null) {
				preparedStatement.setInt(i++, d.getPiso());
			}
			if(d.getOtros()!=null) {
				preparedStatement.setString(i++, d.getOtros());
			}
						
			int insertedRows = preparedStatement.executeUpdate();	
			
			if(insertedRows == 0) {
				throw new DataException();
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
	public void update(Connection c, Direccion d) throws InstanceNotFoundException, DataException {
		
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		StringBuilder sql = null;
		try {
			
			sql = new StringBuilder(
					"UPDATE DIRECCION ");
			boolean first = true;
			
			if(d.getRegion()!=null) {
				DaoUtils.update(sql, first, " ID_REGION = ?");
				first = false;
			}
			if(d.getMunicipio()!=null) {
				DaoUtils.update(sql, first, " MUNICIPIO = ? ");
				first = false;
			}
			if(d.getLocalidad()!=null) {
				DaoUtils.update(sql, first, " LOCALIDAD = ? ");
				first = false;
			}
			if(d.getCp()!=null) {
				DaoUtils.update(sql, first, " CP = ");
				first = false;
			}
			if(d.getCalle()!=null) {
				DaoUtils.update(sql, first, " CALLE = ");
				first = false;
			}
			if(d.getNumero()!=null) {
				DaoUtils.update(sql, first, " NUMERO = ");
				first = false;
			}
			if(d.getPortal()!=null) {
				DaoUtils.update(sql, first, " PORTAL = ");
				first = false;
			}
			if(d.getPiso()!=null) {
				DaoUtils.update(sql, first, " PISO = ");
				first = false;
			}
			if(d.getOtros()!=null) {
				DaoUtils.update(sql, first, " OTROS = ");
				first = false;
			}
			
			sql.append(" WHERE ID_DIRECCION = ? ");
			preparedStatement = c.prepareStatement(sql.toString());
			int i = 1;
			
			if(d.getRegion()!=null) {
				preparedStatement.setInt(i++, d.getRegion());
			}
			if(d.getMunicipio()!=null) {
				preparedStatement.setString(i++, d.getMunicipio());
			}
			if(d.getLocalidad()!=null) {
				preparedStatement.setString(i++, d.getLocalidad());
			}
			if(d.getCp()!=null) {
				preparedStatement.setString(i++, d.getCp());
			}
			if(d.getCalle()!=null) {
				preparedStatement.setString(i++, d.getCalle());
			}
			if(d.getNumero()!=null) {
				preparedStatement.setInt(i++, d.getNumero());
			}
			if(d.getPortal()!=null) {
				preparedStatement.setString(i++, d.getPortal());
			}
			if(d.getPiso()!=null) {
				preparedStatement.setInt(i++, d.getPiso());
			}
			if(d.getOtros()!=null) {
				preparedStatement.setString(i++, d.getOtros());
			}
			preparedStatement.setInt(i++, d.getIdDireccion());
			
			int insertedRows = preparedStatement.executeUpdate();	
			
			if(insertedRows == 0) {
				throw new InstanceNotFoundException(d, "DireccionDAOImpl.update");
			}
		}catch(SQLException ex){
			throw new DataException(ex);	
		}finally {            
			JDBCUtils.closeResultSet(resultSet);
			JDBCUtils.closeStatement(preparedStatement);
		}
		
	}

	@Override
	public void delete(Connection c, Direccion d) throws InstanceNotFoundException, DataException {
		
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {

			String sql;
			sql =  "DELETE FROM `DIRECCION` "
				  +" WHERE ID_DIRECCION = ? ";
			
			preparedStatement = c.prepareStatement(sql);
			
			int i = 1;
			preparedStatement.setInt(i++, d.getIdDireccion());
			int deletedRows = preparedStatement.executeUpdate();	
			
			if(deletedRows == 0) {
				throw new InstanceNotFoundException(d, "DireccionDAOImpl.delete");
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
	
	private Direccion loadNext(ResultSet resultSet) throws SQLException{
		
		Direccion d = new Direccion();
		
		int i = 1;
		Integer id = resultSet.getInt(i++);
		Integer region = resultSet.getInt(i++);
		String municipio = resultSet.getString(i++);
		String localidad = resultSet.getString(i++);
		String cp = resultSet.getString(i++);
		String calle = resultSet.getString(i++);
		Integer numero = resultSet.getInt(i++);
		String portal = resultSet.getString(i++);
		Integer piso = resultSet.getInt(i++);
		String otros = resultSet.getString(i++);
		
		d.setIdDireccion(id);
		d.setRegion(region);
		d.setCalle(calle);
		d.setNumero(numero);
		d.setPortal(portal);
		d.setPiso(piso);
		d.setOtros(otros);
		d.setMunicipio(municipio);
		d.setLocalidad(localidad);
		d.setCp(cp);
		
		return d;
	}
}
