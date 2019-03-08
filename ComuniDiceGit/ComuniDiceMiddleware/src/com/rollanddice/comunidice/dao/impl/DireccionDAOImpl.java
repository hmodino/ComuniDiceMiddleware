package com.rollanddice.comunidice.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.rollanddice.comunidice.dao.spi.DireccionDAO;
import com.rollanddice.comunidice.dao.util.JDBCUtils;
import com.rollanddice.comunidice.model.Direccion;

public class DireccionDAOImpl implements DireccionDAO{

	@Override
	public Direccion findByUsuario(Connection c, Integer idUsuario) throws Exception {
		
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
				throw new Exception("No tienes ninguna direcci�n asociada a este usuario");
			}				
		} 
		catch (Exception ex) {
			throw new Exception(ex);
		} 
		finally {            
			JDBCUtils.closeResultSet(resultSet);
			JDBCUtils.closeStatement(preparedStatement);
		}  	
			
		return d;
	}

	@Override
	public void create(Connection c, Direccion d, Integer idUsuario) throws Exception {
		
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {

			String sql;
			sql =  "INSERT INTO DIRECCION (ID_REGION, ID_USUARIO, MUNICIPIO, LOCALIDAD, CP, CALLE, NUMERO, PORTAL, PISO, OTROS) "
				  +" VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			
			preparedStatement = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			
			int i = 1;
			preparedStatement.setInt(i++, d.getRegion());
			preparedStatement.setInt(i++, idUsuario);
			preparedStatement.setString(i++, d.getMunicipio());
			preparedStatement.setString(i++, d.getLocalidad());
			preparedStatement.setString(i++, d.getCp());
			preparedStatement.setString(i++, d.getCalle());
			preparedStatement.setInt(i++, d.getNumero());
			preparedStatement.setString(i++, d.getPortal());
			preparedStatement.setInt(i++, d.getPiso());
			preparedStatement.setString(i++, d.getOtros());	
			
			int insertedRows = preparedStatement.executeUpdate();	
			
			if(insertedRows == 0) {
				throw new SQLException("Operaci�n fallida");
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
	public void delete(Connection c, Direccion d) throws Exception {
		
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {

			String sql;
			sql =  "DELETE FROM `DIRECCION`"
				  +"WHERE ID_DIRECCION = ? ";
			
			preparedStatement = c.prepareStatement(sql);
			
			int i = 1;
			preparedStatement.setInt(i++, d.getIdDireccion());
			int deletedRows = preparedStatement.executeUpdate();	
			
			if(deletedRows == 0) {
				throw new SQLException("Operaci�n fallida");
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
	
	private Direccion loadNext(ResultSet resultSet) throws Exception {
		
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
