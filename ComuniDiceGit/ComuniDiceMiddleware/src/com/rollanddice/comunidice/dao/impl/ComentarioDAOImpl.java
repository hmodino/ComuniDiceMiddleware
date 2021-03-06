package com.rollanddice.comunidice.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.rollanddice.comunidice.dao.spi.ComentarioDAO;
import com.rollanddice.comunidice.dao.util.DaoUtils;
import com.rollanddice.comunidice.dao.util.JDBCUtils;
import com.rollanddice.comunidice.exception.DataException;
import com.rollanddice.comunidice.exception.InstanceNotFoundException;
import com.rollanddice.comunidice.model.Comentario;

public class ComentarioDAOImpl implements ComentarioDAO{

	@Override
	public Comentario findById(Connection c, Integer idComentario) throws InstanceNotFoundException, DataException {
		
		Comentario coment = null;
		
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		StringBuilder sql = null;
		try {

			sql = new StringBuilder(
				"SELECT C.ID_COMENTARIO, C.ID_USUARIO, C.ID_FORO, C.ID_PRODUCTO, C.CONTENIDO, C.FECHA, U.NOMBRE_USUARIO "
				+" FROM COMENTARIO C INNER JOIN USUARIO U ON (C.ID_USUARIO=U.ID_USUARIO) "
				+" WHERE ID_COMENTARIO = ? ");

			preparedStatement = c.prepareStatement(sql.toString(), ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			
			int i = 1;
			preparedStatement.setInt(i++, idComentario);
			resultSet = preparedStatement.executeQuery();	
			
			if (resultSet.next()) {				
				coment = loadNext(resultSet);
			} else {
				throw new InstanceNotFoundException(idComentario, "ComentarioDAOImpl.findById");
			}				

		} 
		catch (SQLException ex) {
			throw new DataException(ex);
		} 
		finally {            
			JDBCUtils.closeResultSet(resultSet);
			JDBCUtils.closeStatement(preparedStatement);
		}  	
		
		return coment;
	}

	@Override
	public List<Comentario> findByProductoOForo(Connection c, Integer idProducto, Integer idForo) 
			throws InstanceNotFoundException, DataException {
		
		List<Comentario> comentarios = new ArrayList<Comentario>();
		Comentario coment = null;
		StringBuilder sql = null;
		Integer id = null;
		
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			sql = new StringBuilder(
					"SELECT C.ID_COMENTARIO, C.ID_USUARIO, C.ID_FORO, C.ID_PRODUCTO, C.CONTENIDO, C.FECHA, U.NOMBRE_USUARIO "
					+" FROM COMENTARIO C INNER JOIN USUARIO U ON(C.ID_USUARIO=U.ID_USUARIO) ");
			
			boolean first = true;
			if(idProducto!=null) {
				DaoUtils.anadir(sql, first, " C.ID_PRODUCTO = ? ");
				id = idProducto;
				first = false;
			}
			if(idForo != null) {
				DaoUtils.anadir(sql, first, " C.ID_FORO = ? ");
				id = idForo;
				first = false;
			}
			preparedStatement = c.prepareStatement(sql.toString(), ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			
			int i = 1;
			if(idProducto != null) {
				preparedStatement.setInt(i++, idProducto);
			}
			if(idForo != null) {
				preparedStatement.setInt(i++, idForo);
			}
			resultSet = preparedStatement.executeQuery();	
						
			if(resultSet.next()) {
				do {
					coment = loadNext(resultSet);
					comentarios.add(coment);
				}while(resultSet.next());
			}
		} 
		catch (SQLException ex) {
			throw new DataException(ex);
		} 
		finally {            
			JDBCUtils.closeResultSet(resultSet);
			JDBCUtils.closeStatement(preparedStatement);
		}  	
		
		return comentarios;
	}

	@Override
	public List<Comentario> findByUsuarioProductoOForo(Connection c, Integer idUsuario, Integer idProducto, Integer idForo)
			throws InstanceNotFoundException, DataException {
		
		List<Comentario> comentarios = new ArrayList<Comentario>();
		Comentario coment = null;
		StringBuilder sql = null;
		Integer id = null;
		
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			sql = new StringBuilder(
					"SELECT C.ID_COMENTARIO, C.ID_USUARIO, C.ID_FORO, C.ID_PRODUCTO, C.CONTENIDO, C.FECHA, U.NOMBRE_USUARIO "
					+" FROM COMENTARIO C INNER JOIN USUARIO U ON (C.ID_USUARIO=U.ID_USUARIO) "
					+" WHERE ID_USUARIO = ? ");
			
			boolean first = false;
			if(idProducto!=null) {
				DaoUtils.anadir(sql, first, " C.ID_PRODUCTO = ? ");
				id = idProducto;
			}
			if(idForo != null) {
				DaoUtils.anadir(sql, first, " C.ID_FORO = ? ");
				id = idForo;
			}
			preparedStatement = c.prepareStatement(sql.toString(), ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			
			int i = 1;
			preparedStatement.setInt(i++, idUsuario);
			if(idProducto != null) {
				preparedStatement.setInt(i++, idProducto);
			}
			if(idForo != null) {
				preparedStatement.setInt(i++, idForo);
			}
			resultSet = preparedStatement.executeQuery();	
						
			if(resultSet.next()) {
				do {
					coment = loadNext(resultSet);
					comentarios.add(coment);
				}while(resultSet.next());
			}else {
				throw new InstanceNotFoundException(id, "ComentarioDAOImpl.findByUsuarioProductoOForo");
			}
		} 
		catch (SQLException ex) {
			throw new DataException(ex);
		} 
		finally {            
			JDBCUtils.closeResultSet(resultSet);
			JDBCUtils.closeStatement(preparedStatement);
		}  	
		
		return comentarios;
	}
	
	@Override
	public List<Comentario> findByUsuarioTipo(Connection c, Integer idUsuario, Boolean booleano) 
			throws InstanceNotFoundException, DataException {
		
		List<Comentario> comentarios = new ArrayList<Comentario>();
		Comentario coment = null;
		StringBuilder sql = null;
		String tipo = null;
		
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			sql = new StringBuilder(
					"SELECT C.ID_COMENTARIO, C.ID_USUARIO, C.ID_PRODUCTO, C.ID_FORO, C.CONTENIDO, C.FECHA, U.NOMBRE_USUARIO "
					+" FROM COMENTARIO C INNER JOIN USUARIO U ON (C.ID_USUARIO=U.ID_USUARIO) "
					+" WHERE C.ID_USUARIO = ? ");
			
			boolean first = false;
			if(booleano==true) {
				DaoUtils.anadir(sql, first, " C.ID_PRODUCTO IS NOT NULL ");
				tipo = "producto";
			}
			if(booleano==false) {
				DaoUtils.anadir(sql, first, " C.ID_FORO IS NOT NULL ");
				tipo = "foro";
			}
			preparedStatement = c.prepareStatement(sql.toString(), ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			
			int i = 1;
			preparedStatement.setInt(i++, idUsuario);
			resultSet = preparedStatement.executeQuery();	
			
			if(resultSet.next()) {
				do {
					coment = loadNext(resultSet);
					comentarios.add(coment);
				}while(resultSet.next());		
			}else {
				throw new InstanceNotFoundException(tipo, "ComentarioDAOImpl.findByUsuarioTipo");
			}
		}
		catch (SQLException ex) {
			throw new DataException(ex);
		} 
		finally {            
			JDBCUtils.closeResultSet(resultSet);
			JDBCUtils.closeStatement(preparedStatement);
		}  	
		
		return comentarios;
	}

	@Override
	public void create(Connection c, Comentario comentario) 
			throws DataException {
		
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {

			String sql;
			if(comentario.getProducto()!=null) {
				sql =  "INSERT INTO COMENTARIO (ID_USUARIO, ID_PRODUCTO, CONTENIDO, FECHA) "
						+" VALUES (?, ?, ?, ?) ";
			}
			else{
				sql =  "INSERT INTO COMENTARIO (ID_USUARIO, ID_FORO, CONTENIDO, FECHA) "
						+" VALUES (?, ?, ?, ?) ";
			}
			
			preparedStatement = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			
			int i = 1;
			preparedStatement.setInt(i++, comentario.getUsuario());
			if(comentario.getProducto()!=null) {
			preparedStatement.setInt(i++, comentario.getProducto());
			}
			if(comentario.getForo()!=null) {
			preparedStatement.setInt(i++, comentario.getForo());
			}
			preparedStatement.setString(i++, comentario.getContenido());
			preparedStatement.setDate(i++, (new java.sql.Date(new Date().getTime())));	
			
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
	public void delete(Connection c, Integer comentario) throws InstanceNotFoundException, DataException {
		

		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {

			String sql;
			sql =  "DELETE FROM `COMENTARIO` "
				  +" WHERE ID_COMENTARIO = ? ";
			
			preparedStatement = c.prepareStatement(sql);
			
			int i = 1;
			preparedStatement.setInt(i++, comentario);
			int deletedRows = preparedStatement.executeUpdate();
			
			if(deletedRows == 0) {
				throw new InstanceNotFoundException(comentario, "ComentarioDAOImpl.delete");
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
	
	private Comentario loadNext(ResultSet resultSet) throws SQLException{
		
		Comentario coment = new Comentario();

		int i = 1;
		Integer id = resultSet.getInt(i++);
		Integer usuario = resultSet.getInt(i++);
		Integer producto = resultSet.getInt(i++);
		Integer foro = resultSet.getInt(i++);
		String contenido = resultSet.getString(i++);
		Date fecha = resultSet.getDate(i++);
		String nombre = resultSet.getString(i++);
		
		coment.setIdComentario(id);
		coment.setUsuario(usuario);
		coment.setForo(foro);
		coment.setProducto(producto);
		coment.setContenido(contenido);
		coment.setFecha(fecha);
		coment.setNombreUsuario(nombre);
		
		return coment;
	}

}
