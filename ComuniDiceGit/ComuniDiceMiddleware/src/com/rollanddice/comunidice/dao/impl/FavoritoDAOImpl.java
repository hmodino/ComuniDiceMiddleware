package com.rollanddice.comunidice.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.rollanddice.comunidice.dao.spi.FavoritoDAO;
import com.rollanddice.comunidice.dao.util.JDBCUtils;
import com.rollanddice.comunidice.model.Favorito;

public class FavoritoDAOImpl implements FavoritoDAO{

	@Override
	public List<Favorito> findByFavoritosUsuario(Connection c, Integer idUsuario) throws Exception {
		
		List<Favorito> favoritos = new ArrayList<Favorito>();
		Favorito f = null;
		
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {

			String sql;
			sql =  "SELECT ID_PRODUCTO, ID_USUARIO, VALORACION, FAVORITO "
				  +" FROM VALORACION_PRODUCTO_FAVORITOS "
				  +" WHERE ID_USUARIO = ? ";
			
			preparedStatement = c.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			
			int i = 1;
			preparedStatement.setInt(i++, idUsuario);
			
			resultSet = preparedStatement.executeQuery();	
			
			if (resultSet.next()) {				
				while(resultSet.next()) {
					f = loadNext(resultSet);
					favoritos.add(f);
				}
			} else {
				throw new Exception("Todavía no tienes ningún producto favorito");
			}				
			return favoritos;
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
	public List<Favorito> findByValoracionesUsuario(Connection c, Integer idUsuario) throws Exception {
		
		List<Favorito> favoritos = new ArrayList<Favorito>();
		Favorito f = null;
		
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {

			String sql;
			sql =  "SELECT ID_PRODUCTO, ID_USUARIO, VALORACION, FAVORITO "
				  +" FROM VALORACION_PRODUCTO_FAVORITOS "
				  +" WHERE ID_USUARIO = ? ";
			
			preparedStatement = c.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			
			int i = 1;
			preparedStatement.setInt(i++, idUsuario);
			
			resultSet = preparedStatement.executeQuery();	
			
			if (resultSet.next()) {				
				while(resultSet.next()) {
					f = loadNext(resultSet);
					favoritos.add(f);
				}
			} else {
				throw new Exception("Todavia no has valorado ningún producto");
			}				
			return favoritos;
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
	public Double countFavoritos(Connection c, Integer idProducto) throws Exception {
		
		Double f = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {

			String sql;
			sql =  "SELECT COUNT(FAVORITO) NUMERO "
				  +" FROM VALORACION_PRODUCTO_FAVORITOS "
				  +" WHERE ID_PRODUCTO = ? AND FAVORITO IS NOT NULL ";
			
			preparedStatement = c.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			
			int i = 1;
			preparedStatement.setInt(i++, idProducto);
			
			resultSet = preparedStatement.executeQuery();	
			
			if (resultSet.next()) {				
				f = loadNextP(resultSet);
			} else {
				throw new Exception("Este producto no ha sido marcado como favorito por nadie. ¡Sé el primero!");
			}				
			return f;
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
	public List<Double> findValoracionesByProducto(Connection c, Integer idProducto) throws Exception {
		
		Double f = null;
		List<Double> fs = new ArrayList<Double>();
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {

			String sql;
			sql =  "SELECT COUNT(VALORACION) NUMERO "
				  +" FROM VALORACION_PRODUCTO_FAVORITOS "
				  +" WHERE ID_PRODUCTO = ? AND VALORACION IS NOT NULL ";
			
			preparedStatement = c.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			
			int i = 1;
			preparedStatement.setInt(i++, idProducto);
			
			resultSet = preparedStatement.executeQuery();	
			
			if (resultSet.next()) {				
				while(resultSet.next()) {	
					f = loadNextP(resultSet);
					fs.add(f);
				}
			} else {
				throw new Exception("Este producto no ha sido valorado por nadie. ¡Sé el primero!");
			}				
			return fs;
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
	public Double mediaValoraciones(Connection c, Integer idProducto) throws Exception {
		
		Double f = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {

			String sql;
			sql =  "SELECT AVG(VALORACION) NUMERO "
				  +" FROM VALORACION_PRODUCTO_FAVORITOS "
				  +" WHERE ID_PRODUCTO = ? AND VALORACION IS NOT NULL ";
			
			preparedStatement = c.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			
			int i = 1;
			preparedStatement.setInt(i++, idProducto);
			
			resultSet = preparedStatement.executeQuery();	
			
			if (resultSet.next()) {				
					f = loadNextP(resultSet);
			} else {
				throw new Exception("Este producto no ha sido marcado como favorito por nadie. ¡Sé el primero!");
			}				
			return f;
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
	public void create(Connection c, Favorito favorito, Integer idUsuario) throws Exception {
		
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {

			String sql;
			sql =  "INSERT INTO COMENTARIO (ID_PRODUCTO, ID_USUARIO, VALORACION, FAVORITO "
				  +" VALUES (?, ?, ?, ?) ";
			
			preparedStatement = c.prepareStatement(sql);
			
			int i = 1;
			preparedStatement.setInt(i++, favorito.getProducto());
			preparedStatement.setInt(i++, idUsuario);
			preparedStatement.setDouble(i++, favorito.getValoracion());
			preparedStatement.setBoolean(i++, favorito.getFavorito());
			
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

	@Override
	public void update(Connection c, Favorito favorito, Integer idUsuario, Integer idProducto) throws Exception {
		
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {

			String sql;
			sql =  "UPDATE `VALORACION_PRODUCTO_FAVORITOS` "
					+"SET `VALORACION` = ?, `FAVORITO` = ? "
					+" WHERE ID_PRODUCTO = ? AND ID_USUARIO = ?";
			
			preparedStatement = c.prepareStatement(sql);
			
			int i = 1;
			preparedStatement.setDouble(i++, favorito.getValoracion());
			preparedStatement.setBoolean(i++, favorito.getFavorito());
			preparedStatement.setInt(i++, idProducto);
			preparedStatement.setInt(i++, idUsuario);	
			
			int insertedRows = preparedStatement.executeUpdate();	
			
			if(insertedRows == 0) {
				throw new SQLException("Operación fallida");
			}
			
			resultSet = preparedStatement.getResultSet();
			if (resultSet.next()) {	
				favorito = loadNext(resultSet);
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
	public void delete(Connection c, Favorito favorito, Integer idUsuario, Integer idProducto) throws Exception {
		
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {

			String sql;
			sql =  "DELETE FROM `VALORACION_PRODUCTO_FAVORITOS`"
				  +" WHERE ID_PRODUCTO = ? AND ID_USUARIO = ?";
			
			preparedStatement = c.prepareStatement(sql);
			
			int i = 1;
			preparedStatement.setInt(i++, idProducto);
			preparedStatement.setInt(i++, idUsuario);
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
	

	private Favorito loadNext(ResultSet resultSet) throws Exception {
		
		Favorito f = new Favorito();
		
		int i = 1;
		Integer idProducto = resultSet.getInt(i++);
		Integer idUsuario = resultSet.getInt(i++);
		Double valoracion = resultSet.getDouble(i++);
		Boolean favorito = resultSet.getBoolean(i++);

		f.setProducto(idProducto);
		f.setUsuario(idUsuario);
		f.setValoracion(valoracion);
		f.setFavorito(favorito);
		
		return f;
	}
	
	private Double loadNextP(ResultSet resultSet) throws Exception{
		
		int i = 1;
		Double f = resultSet.getDouble(i++);
		
		return f;
		
	}

}
