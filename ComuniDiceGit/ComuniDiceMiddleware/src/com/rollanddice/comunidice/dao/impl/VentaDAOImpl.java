package com.rollanddice.comunidice.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.mysql.jdbc.Statement;
import com.rollanddice.comunidice.dao.spi.VentaDAO;
import com.rollanddice.comunidice.dao.util.JDBCUtils;
import com.rollanddice.comunidice.model.Venta;

public class VentaDAOImpl implements VentaDAO{

	@Override
	public Venta findById(Connection c, Integer id) throws Exception {
		
		Venta v = null;
		
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {

			String sql;
			sql =  "SELECT ID_VENTA, ID_USUARIO, TOTAL, FECHA "
				  +" FROM VENTA "
				  +" WHERE ID_VENTA = ? ";
			
			preparedStatement = c.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			
			int i = 1;
			preparedStatement.setInt(i++, id);
			resultSet = preparedStatement.executeQuery();			
			
			if (resultSet.next()) {				
				v = loadNext(c, resultSet);				
			} else {
				throw new Exception("La búsqueda que has introducido no ha producido ningún resultado");
			}				
		} 
		catch (Exception ex) {
			throw new Exception(ex);
		} 
		finally {            
			JDBCUtils.closeResultSet(resultSet);
			JDBCUtils.closeStatement(preparedStatement);
		}  	
			
		return v;
	}

	@Override
	public List<Venta> findByUsuario(Connection c, Integer idUsuario) throws Exception {
		
		Venta v = null;
		List<Venta> vs = new ArrayList<Venta>();
		
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {

			String sql;
			sql =  "SELECT ID_VENTA, ID_USUARIO, TOTAL, FECHA "
				  +" FROM VENTA "
				  +" WHERE ID_USUARIO = ? ";
			
			preparedStatement = c.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			
			int i = 1;
			preparedStatement.setInt(i++, idUsuario);
			resultSet = preparedStatement.executeQuery();			
			
			if (resultSet.next()) {	
				while(resultSet.next()) {
					v = loadNext(c, resultSet);
					vs .add(v);
				}				
			} else {
				throw new Exception("La búsqueda que has introducido no ha producido ningún resultado");
			}				
		} 
		catch (Exception ex) {
			throw new Exception(ex);
		} 
		finally {            
			JDBCUtils.closeResultSet(resultSet);
			JDBCUtils.closeStatement(preparedStatement);
		}  	
			
		return vs;
	}

	@Override
	public void create(Connection c, Venta venta) throws Exception {
		
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {

			String sql;
			sql =  "INSERT INTO VENTA (ID_USUARIO, TOTAL, FECHA "
				  +" VALUES (?, ?, ?, ?, ?, ?) ";
			
			preparedStatement = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			
			int i = 1;
			preparedStatement.setInt(i++, venta.getIdUsuario());
			preparedStatement.setDouble(i++, venta.getTotal());
			preparedStatement.setDate(i++, (java.sql.Date)venta.getFechaVenta());
			
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
	public void delete(Connection c, Venta venta) throws Exception {
		
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {

			String sql;
			sql =  "DELETE FROM `VENTA`"
				  +" WHERE VENTA = ? ";
			
			preparedStatement = c.prepareStatement(sql);
			
			int i = 1;
			preparedStatement.setInt(i++, venta.getIdCompra());
			
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
	
	private Venta loadNext(Connection c, ResultSet resultSet) throws Exception {
		
		Venta v = new Venta(); 
		
		int i = 1;
		Integer id = resultSet.getInt(i++);
		Integer idUsuario = resultSet.getInt(i++);
		Double total = resultSet.getDouble(i++);
		Date fechaCompra = resultSet.getDate(i++);

		v.setIdCompra(id);
		v.setIdUsuario(idUsuario);
		v.setTotal(total);
		v.setFechaVenta(fechaCompra);
		
		return v;

	}
}
