package com.rollanddice.comunidice.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.Statement;
import com.rollanddice.comunidice.dao.spi.LineaVentaDAO;
import com.rollanddice.comunidice.dao.util.JDBCUtils;
import com.rollanddice.comunidice.model.LineaVenta;

public class LineaVentaDAOImpl implements LineaVentaDAO{

	@Override
	public List<LineaVenta> findByVenta(Connection c, Integer idVenta) throws Exception {
		
		List<LineaVenta> lvs = new ArrayList<LineaVenta>();
		LineaVenta lv = null;
		
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {

			String sql;
			sql =  "SELECT 	N_LINEA_VENTA, ID_VENTA, ID_PRODUCTO, CANTIDAD, PRECIO_UNITARIO, PRECIO_TOTAL, DESCUENTO "
				  +" FROM LINEA_COMPRA "
				  +" WHERE ID_COMPRA = ? ";
			
			preparedStatement = c.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			
			int i = 1;
			preparedStatement.setInt(i++, idVenta);
			
			resultSet = preparedStatement.executeQuery();	
			
			if (resultSet.next()) {				
				while(resultSet.next()) {
					lv = loadNext(resultSet);
					lvs.add(lv);
				}
			} else {
				throw new Exception("La búsqueda que has realizado no ha producido ningún resultado");
			}				
			
			return lvs;
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
	public void create(Connection c, LineaVenta lv, Integer idVenta) throws Exception {
		
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			String sql;
			sql =  "INSERT INTO LINEA_VENTA (ID_VENTA, ID_PRODUCTO, CANTIDAD, PRECIO_TOTAL, DESCUENTO) "
				  +" VALUES (?, ?, ?, ?, ?) ";
			
			preparedStatement = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			
			int i = 1;
			preparedStatement.setInt(i++, idVenta);
			preparedStatement.setInt(i++, lv.getProducto());
			preparedStatement.setInt(i++, lv.getCantidad());
			preparedStatement.setDouble(i++, lv.getPrecioTotal());
			preparedStatement.setDouble(i++, lv.getDescuento());	
			
			int insertedRows = preparedStatement.executeUpdate();	
			
			if(insertedRows == 0) {
				throw new SQLException("Operación fallida");
			}
			
			resultSet = preparedStatement.getGeneratedKeys();
			if (resultSet.next()) {	
				lv = loadNext(resultSet);
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

	@Override
	public void delete(Connection c, LineaVenta lv, Integer idCompra) throws Exception {
		
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			String sql;
			sql =  "DELETE FROM `LINEA_VENTA` "
				  +" WHERE ID_VENTA = ? ";
			
			preparedStatement = c.prepareStatement(sql);
			
			int i = 1;
			preparedStatement.setInt(i++, idCompra);
			
			int insertedRows = preparedStatement.executeUpdate();	
			
			if(insertedRows == 0) {
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
	
	private LineaVenta loadNext(ResultSet resultSet) throws Exception {
		
		LineaVenta lv = new LineaVenta();
		
		int i = 1;
		Integer id = resultSet.getInt(i++);
		Integer idVenta = resultSet.getInt(i++);
		Integer idProducto = resultSet.getInt(i++);
		Integer cantidad = resultSet.getInt(i++);
		Double precio = resultSet.getDouble(i++);
		Double total = resultSet.getDouble(i++);
		Double descuento = resultSet.getDouble(i++);
		
		lv.setnLinea(id);
		lv.setIdVenta(idVenta);
		lv.setProducto(idProducto);
		lv.setCantidad(cantidad);
		lv.setPrecioUnitario(precio);
		lv.setPrecioTotal(total);
		lv.setDescuento(descuento);
		
		return lv;
	}

}
