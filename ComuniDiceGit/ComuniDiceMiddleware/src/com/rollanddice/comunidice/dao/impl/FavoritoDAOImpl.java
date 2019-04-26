package com.rollanddice.comunidice.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.rollanddice.comunidice.dao.spi.FavoritoDAO;
import com.rollanddice.comunidice.dao.util.JDBCUtils;
import com.rollanddice.comunidice.exception.DataException;
import com.rollanddice.comunidice.exception.DuplicateInstanceException;
import com.rollanddice.comunidice.exception.InstanceNotFoundException;
import com.rollanddice.comunidice.model.Favorito;

public class FavoritoDAOImpl implements FavoritoDAO{

	@Override
	public List<Favorito> findByFavoritosUsuario(Connection c, Integer idUsuario, String idioma) 
			throws InstanceNotFoundException, DataException{
		
		List<Favorito> favoritos = new ArrayList<Favorito>();
		Favorito f = null;
		
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {

			String sql;
			sql =  "SELECT V.ID_PRODUCTO, V.ID_USUARIO, V.VALORACION, V.FAVORITO, P.NOMBRE "
				  +" FROM VALORACION_PRODUCTO_FAVORITOS V INNER JOIN IDIOMA_PRODUCTO P ON(V.ID_PRODUCTO=P.ID_PRODUCTO) "
				  +" WHERE V.ID_USUARIO = ? AND P.IDIOMA LIKE ? AND FAVORITO=1 ";
			
			preparedStatement = c.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			
			int i = 1;
			preparedStatement.setInt(i++, idUsuario);
			preparedStatement.setString(i++, idioma);
			
			resultSet = preparedStatement.executeQuery();	
			
			if (resultSet.next()) {				
				do{
					f = loadNext(resultSet);
					favoritos.add(f);
				}while(resultSet.next()); 
			} else {
				throw new InstanceNotFoundException(idUsuario, "FavoritoDAOImpl.findByFavoritosUsuario");
			}				
			return favoritos;
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
	public List<Favorito> findByValoracionesUsuario(Connection c, Integer idUsuario, String idioma) 
			throws InstanceNotFoundException, DataException {
		
		List<Favorito> favoritos = new ArrayList<Favorito>();
		Favorito f = null;
		
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {

			String sql;
			sql =  "SELECT V.ID_PRODUCTO, V.ID_USUARIO, V.VALORACION, V.FAVORITO, P.NOMBRE "
				  +" FROM VALORACION_PRODUCTO_FAVORITOS V INNER JOIN IDIOMA_PRODUCTO P ON(V.ID_PRODUCTO=P.ID_PRODUCTO)"
				  +" WHERE V.ID_USUARIO = ? AND P.IDIOMA LIKE ? AND ";
			
			preparedStatement = c.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			
			int i = 1;
			preparedStatement.setInt(i++, idUsuario);
			preparedStatement.setString(i++, idioma);
			resultSet = preparedStatement.executeQuery();	
			
			if (resultSet.next()) {				
				do{
					f = loadNext(resultSet);
					favoritos.add(f);
				}while(resultSet.next());
			} else {
				throw new InstanceNotFoundException(idUsuario,"FavoritoDAOImpl.findValoracionesUsuario");
			}				
			return favoritos;
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
	public Double countFavoritos(Connection c, Integer idProducto) throws InstanceNotFoundException, DataException {
		
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
				throw new InstanceNotFoundException(idProducto, "FavoritoDAOImpl.countFavoritos");
			}				
			return f;
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
	public List<Double> findValoracionesByProducto(Connection c, Integer idProducto) 
			throws InstanceNotFoundException, DataException {
		
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
				do {	
					f = loadNextP(resultSet);
					fs.add(f);
				}while(resultSet.next());
			} else {
				throw new InstanceNotFoundException(idProducto, "FavoritoDAOImpl.findValoracionesByProducto");
			}				
			return fs;
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
	public Double mediaValoraciones(Connection c, Integer idProducto) throws InstanceNotFoundException, DataException {
		
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
				throw new InstanceNotFoundException(idProducto, "FavoritoDAOImpl.mediaValoraciones");
			}				
			return f;
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
	public void create(Connection c, Favorito favorito, Integer fav) throws DuplicateInstanceException, DataException {
		
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		StringBuilder sql = null;
		try {

			sql = new StringBuilder(
					"INSERT INTO VALORACION_PRODUCTO_FAVORITOS (ID_PRODUCTO, ID_USUARIO,  FAVORITO");
			
			if(favorito.getValoracion()!=null) {
				sql.append(", VALORACION) VALUES (?, ?, ?, ?) "); 
			} else {
				sql.append(") VALUES (?, ?, ?) ");
			}
			preparedStatement = c.prepareStatement(sql.toString());
			int i = 1;
			preparedStatement.setInt(i++, favorito.getProducto());
			preparedStatement.setInt(i++, favorito.getUsuario());
			preparedStatement.setInt(i++, fav);
			if(favorito.getValoracion()!=null) {
				preparedStatement.setDouble(i++, favorito.getValoracion());
			}
			
			int insertedRows = preparedStatement.executeUpdate();	
			
			if(insertedRows == 0) {
				throw new DuplicateInstanceException(favorito, "FavoritoDAOImpl.create");
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
	public void update(Connection c, Favorito favorito, Integer fav) 
			throws InstanceNotFoundException, DataException {
		
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {

			StringBuilder sql;
			sql = new StringBuilder(
					"UPDATE `VALORACION_PRODUCTO_FAVORITOS` "
					+"SET `FAVORITO` = ? ");
			if(favorito.getValoracion()!=null) {
				sql.append(", `VALORACION` = ? WHERE ID_PRODUCTO = ? AND ID_USUARIO = ?");
			} else {
				sql.append(" WHERE ID_PRODUCTO = ? AND ID_USUARIO = ? ");
			}
			
			preparedStatement = c.prepareStatement(sql.toString());
			
			int i = 1;
			preparedStatement.setInt(i++, fav);
			if(favorito.getValoracion()!=null) {
				preparedStatement.setDouble(i++, favorito.getValoracion());
			}
			preparedStatement.setInt(i++, favorito.getProducto());
			preparedStatement.setInt(i++, favorito.getUsuario());	
			
			int insertedRows = preparedStatement.executeUpdate();	
			
			if(insertedRows == 0) {
				throw new InstanceNotFoundException(favorito, "FavoritoDAOImpl.update");
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
	public void delete(Connection c, Favorito favorito) 
			throws InstanceNotFoundException, DataException {
		
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {

			String sql;
			sql =  "DELETE FROM `VALORACION_PRODUCTO_FAVORITOS`"
				  +" WHERE ID_PRODUCTO = ? AND ID_USUARIO = ?";
			
			preparedStatement = c.prepareStatement(sql);
			
			int i = 1;
			preparedStatement.setInt(i++, favorito.getProducto());
			preparedStatement.setInt(i++, favorito.getUsuario());
			int deletedRows = preparedStatement.executeUpdate();
			
			if(deletedRows == 0) {
				throw new InstanceNotFoundException(favorito, "FavoritoDAOImpl.delete");
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
	public Favorito exist(Connection c, Integer idUsuario, Integer idProducto) 
			throws InstanceNotFoundException, DataException {
	
		Favorito f = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {

			String sql;
			sql =  "SELECT FAVORITO "
				  +" FROM VALORACION_PRODUCTO_FAVORITOS "
				  +" WHERE ID_USUARIO = ? AND ID_PRODUCTO = ? ";
			
			preparedStatement = c.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			
			int i = 1;
			preparedStatement.setInt(i++, idUsuario);
			preparedStatement.setInt(i++, idProducto);
			
			resultSet = preparedStatement.executeQuery();	
			
			if (resultSet.next()) {				
					f = loadNextE(resultSet);
			} else {
				throw new InstanceNotFoundException(idProducto, "FavoritoDAOImpl.exist");
			}				
			return f;
		} 
		catch (SQLException ex) {
			throw new DataException(ex);
		}	finally {            
			JDBCUtils.closeResultSet(resultSet);
			JDBCUtils.closeStatement(preparedStatement);
		}  	
	}
	
	private Favorito loadNextE(ResultSet resultSet) throws SQLException{
		
		Favorito f = new Favorito();
		Boolean b = null;
		int i = 1;
		Integer favorito = resultSet.getInt(i++);
		if(favorito==1) {
			b = true;
		} else {
			b = false;
		}
		f.setFavorito(b);
		return f;
	}

	private Favorito loadNext(ResultSet resultSet) throws SQLException{
		
		Favorito f = new Favorito();
		
		int i = 1;
		Integer idProducto = resultSet.getInt(i++);
		Integer idUsuario = resultSet.getInt(i++);
		Double valoracion = resultSet.getDouble(i++);
		Integer favoritoInt = resultSet.getInt(i++);
		Boolean favorito = false;
		if(favoritoInt==1) {
			favorito = true;
		} 
		String nombreProducto = resultSet.getString(i++);

		f.setProducto(idProducto);
		f.setUsuario(idUsuario);
		f.setValoracion(valoracion);
		f.setFavorito(favorito);
		f.setNombreProducto(nombreProducto);
		
		return f;
	}
	
	private Double loadNextP(ResultSet resultSet) throws SQLException{
		
		int i = 1;
		Double f = resultSet.getDouble(i++);
		
		return f;
		
	}

}
