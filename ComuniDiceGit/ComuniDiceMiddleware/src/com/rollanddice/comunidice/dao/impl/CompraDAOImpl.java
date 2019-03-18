package com.rollanddice.comunidice.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.mysql.jdbc.Statement;
import com.rollanddice.comunidice.dao.spi.CompraDAO;
import com.rollanddice.comunidice.dao.util.JDBCUtils;
import com.rollanddice.comunidice.exception.DataException;
import com.rollanddice.comunidice.exception.InstanceNotFoundException;
import com.rollanddice.comunidice.model.Compra;

public class CompraDAOImpl implements CompraDAO{

	@Override
	public Compra findById(Connection c, Integer id) throws InstanceNotFoundException, DataException{
		
		Compra compra= null;
		
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {

			String sql;
			sql =  "SELECT ID_COMPRA, ID_USUARIO, SUBTOTAL, TOTAL, MODO_PAGO, FECHA_COMPRA, GASTOS_ENVIO "
				  +" FROM COMPRA "
				  +" WHERE ID_COMPRA = ? ";
			
			preparedStatement = c.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			
			int i = 1;
			preparedStatement.setInt(i++, id);
			resultSet = preparedStatement.executeQuery();			
			
			if (resultSet.next()) {				
				compra = loadNext(resultSet);				
			}else {
				throw new InstanceNotFoundException(id, "CompraDAOImpl.findById");
			}				
		} 
		catch (SQLException ex) {
			throw new DataException(ex);
		} 
		finally {            
			JDBCUtils.closeResultSet(resultSet);
			JDBCUtils.closeStatement(preparedStatement);
		}  	
			
		return compra;
	}

	@Override
	public List<Compra> findByUsuario(Connection c, Integer idUsuario) throws InstanceNotFoundException, DataException {
		
		Compra compra= null;
		List<Compra> compras = new ArrayList<Compra>();
		
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {

			String sql;
			sql =  "SELECT ID_COMPRA, ID_USUARIO, SUBTOTAL, TOTAL, MODO_PAGO, FECHA_COMPRA, GASTOS_ENVIO "
				  +" FROM COMPRA "
				  +" WHERE ID_USUARIO = ? ";
			
			preparedStatement = c.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			
			int i = 1;
			preparedStatement.setInt(i++, idUsuario);
			resultSet = preparedStatement.executeQuery();			
			
			if (resultSet.next()) {
				do {
					compra = loadNext(resultSet);
					compras.add(compra);
				}while(!resultSet.isLast());
			} else {
				throw new InstanceNotFoundException(idUsuario, "CompraDAOImpl.findByUsuario");
			}				
		} 
		catch (SQLException ex) {
			throw new DataException();
		} 
		finally {            
			JDBCUtils.closeResultSet(resultSet);
			JDBCUtils.closeStatement(preparedStatement);
		}  	
			
		return compras;
	}

	@Override
	public void create(Connection c, Compra compra) throws DataException {
		
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {

			String sql;
			sql =  "INSERT INTO COMPRA (ID_USUARIO, SUBTOTAL, TOTAL, MODO_PAGO, FECHA_COMPRA, GASTOS_ENVIO) "
				  +" VALUES (?, ?, ?, ?, ?, ?) ";
			
			preparedStatement = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			
			int i = 1;
			preparedStatement.setInt(i++, compra.getIdUsuario());
			preparedStatement.setDouble(i++, compra.getSubtotal());
			preparedStatement.setDouble(i++, compra.getTotal());
			preparedStatement.setInt(i++, 1);
			preparedStatement.setDate(i++, (new java.sql.Date(new Date().getTime())));
			preparedStatement.setDouble(i++, compra.getGastosEnvio());	
			
			int insertedRows = preparedStatement.executeUpdate();	
			
			if(insertedRows == 0) {
				throw new DataException();
			}
			
			resultSet = preparedStatement.getGeneratedKeys();
			if(resultSet.next()) {
				compra.setIdCompra(resultSet.getInt(1));
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
	public void delete(Connection c, Compra compra) throws InstanceNotFoundException, DataException {
		
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {

			String sql;
			sql =  "DELETE FROM `COMPRA`"
				  +" WHERE ID_COMPRA = ? ";
			
			preparedStatement = c.prepareStatement(sql);
			
			int i = 1;
			preparedStatement.setInt(i++, compra.getIdCompra());
			
			int deletedRows = preparedStatement.executeUpdate();	
			
			if(deletedRows == 0) {
				throw new InstanceNotFoundException(compra, "CompraDAOImpl.delete");
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
	
	private Compra loadNext(ResultSet resultSet) throws SQLException{
		
		Compra compra = new Compra();
		
		int i = 1;
		Integer id = resultSet.getInt(i++);
		Integer idUsuario = resultSet.getInt(i++);
		Double subtotal = resultSet.getDouble(i++);
		Double total = resultSet.getDouble(i++);
		Integer modoPago= resultSet.getInt(i++);
		Date fechaCompra = resultSet.getDate(i++);
		Double gastosEnvio = resultSet.getDouble(i++);

		compra.setIdCompra(id);
		compra.setIdUsuario(idUsuario);
		compra.setSubtotal(subtotal);
		compra.setGastosEnvio(gastosEnvio);
		compra.setTotal(total);
		compra.setModoPago(modoPago);
		compra.setFechaCompra(fechaCompra);
		
		return compra;
	}

}
