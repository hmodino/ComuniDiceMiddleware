package com.rollanddice.comunidice.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.rollanddice.comunidice.dao.spi.MensajeDAO;
import com.rollanddice.comunidice.dao.util.JDBCUtils;
import com.rollanddice.comunidice.exception.DataException;
import com.rollanddice.comunidice.exception.InstanceNotFoundException;
import com.rollanddice.comunidice.model.Mensaje;

public class MensajeDAOImpl implements MensajeDAO{

	@Override
	public Mensaje findById(Connection c, Integer id) throws InstanceNotFoundException, DataException{
	
		Mensaje m = null;
		
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {

			String sql;
			sql =  "SELECT ID_MENSAJE, ID_USUARIO1, ID_USUARIO2, CONTENIDO, FECHA_HORA "
				  +" FROM MENSAJE "
				  +" WHERE ID_MENSAJE = ? ";
			
			preparedStatement = c.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			
			int i = 1;
			preparedStatement.setInt(i++, id);
			resultSet = preparedStatement.executeQuery();			
			
			if (resultSet.next()) {				
				m = loadNext(resultSet);				
			} else {
				throw new InstanceNotFoundException(id, "MensajeDAOImpl.findById");
			}				
		} 
		catch (SQLException ex) {
			throw new DataException(ex);
		} 
		finally {            
			JDBCUtils.closeResultSet(resultSet);
			JDBCUtils.closeStatement(preparedStatement);
		}  	
			
		return m;
	}

	@Override
	public List<Mensaje> findByEmisor(Connection c, Integer id) throws InstanceNotFoundException, DataException{
		
		Mensaje m = null;
		List<Mensaje> ms = new ArrayList<Mensaje>();
		
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {

			String sql;
			sql =  "SELECT ID_MENSAJE, ID_USUARIO1, ID_USUARIO2, CONTENIDO, FECHA_HORA "
				  +" FROM MENSAJE "
				  +" WHERE ID_USUARIO1 = ? ";
			
			preparedStatement = c.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			
			int i = 1;
			preparedStatement.setInt(i++, id);
			resultSet = preparedStatement.executeQuery();			

			if(resultSet.next()) {
				do {
					m = loadNext(resultSet);
					ms.add(m);
				}while(resultSet.next());
			}else {
				throw new InstanceNotFoundException(id, "MensajeDAOImpl.findByEmisor");
			}
		} 
		catch (SQLException ex) {
			throw new DataException(ex);
		} 
		finally {            
			JDBCUtils.closeResultSet(resultSet);
			JDBCUtils.closeStatement(preparedStatement);
		}  	
			
		return ms;
	}

	@Override
	public List<Mensaje> findByReceptor(Connection c, Integer idReceptor) throws InstanceNotFoundException, DataException{
		
		Mensaje m = null;
		List<Mensaje> ms = new ArrayList<Mensaje>();
		
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {

			String sql;
			sql =  "SELECT ID_MENSAJE, ID_USUARIO1, ID_USUARIO2, CONTENIDO, FECHA_HORA "
				  +" FROM MENSAJE "
				  +" WHERE ID_USUARIO2 = ?";
			
			preparedStatement = c.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			
			int i = 1;
			preparedStatement.setInt(i++, idReceptor);
			resultSet = preparedStatement.executeQuery();			
			if(resultSet.next()) {
				do{
					m = loadNext(resultSet);
					ms.add(m);
				}while(resultSet.next());
			}else {
				throw new InstanceNotFoundException(idReceptor, "MensajeDAOImp`l.findByReceptor");
			}
		} 
		catch (SQLException ex) {
			throw new DataException(ex);
		} 
		finally {            
			JDBCUtils.closeResultSet(resultSet);
			JDBCUtils.closeStatement(preparedStatement);
		}  	
			
		return ms;
	}

	@Override
	public void create(Connection c, Mensaje mensaje) throws DataException {
		
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			String sql;
			sql =  "INSERT INTO MENSAJE (ID_USUARIO1, ID_USUARIO2, CONTENIDO, FECHA_HORA) "
				  +" VALUES (?, ?, ?, ?) ";
			
			preparedStatement = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			
			int i = 1;
			preparedStatement.setInt(i++, mensaje.getUsuarioEmisor());
			preparedStatement.setInt(i++, mensaje.getUsuarioReceptor());
			preparedStatement.setString(i++, mensaje.getContenido());
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
	public void delete(Connection c, Integer id) throws InstanceNotFoundException, DataException{
		
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {

			String sql;
			sql =  "DELETE FROM `MENSAJE`"
				  +" WHERE ID_MENSAJE = ? ";
			
			preparedStatement = c.prepareStatement(sql);
			
			int i = 1;
			preparedStatement.setInt(i++, id);
			int deletedRows = preparedStatement.executeUpdate();
			if(deletedRows==0) {
				throw new InstanceNotFoundException(id, "MensajeDAOImpl.delete");
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
	
	private Mensaje loadNext(ResultSet resultSet) throws SQLException {
		
		Mensaje m = new Mensaje();

		int i = 1;
		Integer id = resultSet.getInt(i++);
		Integer idUsuario1 = resultSet.getInt(i++);
		Integer idUsuario2 = resultSet.getInt(i++);
		String contenido = resultSet.getString(i++);
		Date fecha = resultSet.getDate(i++);

		m.setIdMensaje(id);
		m.setUsuarioEmisor(idUsuario1);
		m.setUsuarioReceptor(idUsuario2);
		m.setContenido(contenido);
		m.setFechaHora(fecha);
		
		return m;
	}

}
