package com.rollanddice.comunidice.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.Statement;
import com.rollanddice.comunidice.dao.spi.LineaCompraDAO;
import com.rollanddice.comunidice.dao.util.JDBCUtils;
import com.rollanddice.comunidice.exception.DataException;
import com.rollanddice.comunidice.exception.DuplicateInstanceException;
import com.rollanddice.comunidice.exception.InstanceNotFoundException;
import com.rollanddice.comunidice.model.LineaCompra;

public class LineaCompraDAOImpl implements LineaCompraDAO{

	@Override
	public List<LineaCompra> findByCompra(Connection c, Integer idCompra) throws InstanceNotFoundException, DataException {
		
		List<LineaCompra> lcs = new ArrayList<LineaCompra>();
		LineaCompra lc = null;
		
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {

			String sql;
			sql =  "SELECT 	N_LINEA_COMPRA, ID_COMPRA, ID_PRODUCTO, CANTIDAD, PRECIO_UNITARIO, PRECIO_TOTAL, DESCUENTO "
				  +" FROM LINEA_COMPRA "
				  +" WHERE ID_COMPRA = ? ";
			
			preparedStatement = c.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			
			int i = 1;
			preparedStatement.setInt(i++, idCompra);
			
			resultSet = preparedStatement.executeQuery();	
			
			if (resultSet.next()) {				
				do {
					lc = loadNext(resultSet);
					lcs.add(lc);
				}
				while(resultSet.next()); 
			} else {
				throw new InstanceNotFoundException(idCompra, "LineaCompraDAOImpl.findByCompra");
			}				
			
			return lcs;
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
	public void create(Connection c, LineaCompra lc, Integer idCompra) throws DuplicateInstanceException, DataException {
		
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			String sql;
			sql =  "INSERT INTO LINEA_COMPRA (ID_COMPRA, ID_PRODUCTO, CANTIDAD, PRECIO_UNITARIO, PRECIO_TOTAL, DESCUENTO) "
				  +" VALUES (?, ?, ?, ?, ?, ?) ";
			
			preparedStatement = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			
			int i = 1;
			preparedStatement.setInt(i++, idCompra);
			preparedStatement.setInt(i++, lc.getIdProducto());
			preparedStatement.setInt(i++, lc.getCantidad());
			preparedStatement.setDouble(i++, lc.getPrecioUnitario());
			preparedStatement.setDouble(i++, lc.getPrecioTotal());
			preparedStatement.setDouble(i++, lc.getDescuento());
			
			int insertedRows = preparedStatement.executeUpdate();	
			
			if(insertedRows == 0) {
				throw new DuplicateInstanceException(lc, "LineaCompraDAOImpl.create");
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
	public void delete(Connection c, LineaCompra lc, Integer idCompra) throws InstanceNotFoundException, DataException{
		
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			String sql;
			sql =  "DELETE FROM `LINEA_COMPRA` "
				  +" WHERE ID_COMPRA = ? ";
			
			preparedStatement = c.prepareStatement(sql);
			
			int i = 1;
			preparedStatement.setInt(i++, idCompra);
			
			int insertedRows = preparedStatement.executeUpdate();	
			
			if(insertedRows == 0) {
				throw new InstanceNotFoundException(lc, "LineaCompraDAOImpl.delete");
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

	private LineaCompra loadNext(ResultSet resultSet) throws SQLException {
		
		LineaCompra lc = new LineaCompra();
		
		int i = 1;
		Integer id = resultSet.getInt(i++);
		Integer idCompra = resultSet.getInt(i++);
		Integer idProducto = resultSet.getInt(i++);
		Integer cantidad = resultSet.getInt(i++);
		Double precio = resultSet.getDouble(i++);
		Double total = resultSet.getDouble(i++);
		Double descuento = resultSet.getDouble(i++);
		
		lc.setnLinea(id);
		lc.setIdCompra(idCompra);
		lc.setIdProducto(idProducto);
		lc.setCantidad(cantidad);
		lc.setPrecioUnitario(precio);
		lc.setPrecioTotal(total);
		lc.setDescuento(descuento);
		
		return lc;
	}

}
