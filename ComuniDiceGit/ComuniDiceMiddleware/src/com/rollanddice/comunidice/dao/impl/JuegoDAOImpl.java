package com.rollanddice.comunidice.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.rollanddice.comunidice.dao.spi.JuegoDAO;
import com.rollanddice.comunidice.dao.util.DaoUtils;
import com.rollanddice.comunidice.dao.util.JDBCUtils;
import com.rollanddice.comunidice.exception.DataException;
import com.rollanddice.comunidice.exception.DuplicateInstanceException;
import com.rollanddice.comunidice.exception.InstanceNotFoundException;
import com.rollanddice.comunidice.model.Criteria;
import com.rollanddice.comunidice.model.Juego;
import com.rollanddice.comunidice.model.Producto;
import com.rollanddice.comunidice.model.Results;

public class JuegoDAOImpl implements JuegoDAO{

	private static Logger logger = LogManager.getLogger(JuegoDAOImpl.class.getName());
	
	@Override
	public Juego findById(Connection c, Integer id) throws InstanceNotFoundException, DataException{
		
		Juego j= null;
		
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {

			String sql;
			sql =  " SELECT P.ID_PRODUCTO, P.ID_CATEGORIA, J.NOMBRE, P.PRECIO, J.DESCRIPCION, P.FECHA_ENTRADA, P.STOCK, P.IMAGEN, "
					+" J.ID_USUARIO, J.FORMATO, J.PAGINAS, J.TIPO_VENDEDOR, J.ANO_PUBLICACION, J.TIPO_TAPA "
					+" FROM PRODUCTO P INNER JOIN JUEGO J ON(P.ID_PRODUCTO = J.ID_PRODUCTO) "
					+" WHERE P.ID_PRODUCTO = ? ";
			
			preparedStatement = c.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			
			int i = 1;
			preparedStatement.setInt(i++, id);
			resultSet = preparedStatement.executeQuery();			
			
			if (resultSet.next()) {				
				j = loadNext(resultSet);				
			} else {
				throw new InstanceNotFoundException(id, "JuegoDAOImpl.findById");
			}				
		} 
		catch (SQLException ex) {
			throw new DataException(ex);
		} 
		finally {            
			JDBCUtils.closeResultSet(resultSet);
			JDBCUtils.closeStatement(preparedStatement);
		}  	
			
		return j;
	}

	@Override
	public Results<Juego> findByCriteria(Connection c, Criteria criteria, int startIndex, int count, String idioma) 
			throws InstanceNotFoundException, DataException{
		
		Juego j = null;
		List<Juego> juegos = new ArrayList<Juego>();
		Results<Juego> results = null;
		
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		StringBuilder sql = null;
		try {

			sql = new StringBuilder(
					" SELECT P.ID_PRODUCTO, P.ID_CATEGORIA, J.NOMBRE, P.PRECIO, J.DESCRIPCION, P.FECHA_ENTRADA, P.STOCK, "
					+" P.IMAGEN, J.ID_USUARIO, J.FORMATO, J.PAGINAS, J.TIPO_VENDEDOR, J.ANO_PUBLICACION, J.TIPO_TAPA "
					+" FROM PRODUCTO P INNER JOIN JUEGO J ON(P.ID_PRODUCTO = J.ID_PRODUCTO) INNER JOIN IDIOMA_PRODUCTO I "
					+ " ON(I.ID_PRODUCTO=P.ID_PRODUCTO) "
					+ " WHERE I.IDIOMA LIKE ? ");
			boolean first = false;
			
			if(criteria.getIdCategoria()!=null) {
				DaoUtils.anadir(sql, first, "ID_CATEGORIA = ?");
				first = false;
			}
			if(criteria.getNombre() !=null) {
				DaoUtils.anadir(sql, first, " NOMBRE LIKE ? ");
				first = false;
			}
			if(criteria.getPrecioDesde()!=null) {
				DaoUtils.anadir(sql, first, "PRECIO >= ?");
				first = false;
			}
			if(criteria.getPrecioHasta()!=null) {
				DaoUtils.anadir(sql, first, "PRECIO <=?");
				first = false;
			}
			if(criteria.getFechaMaxima()!=null) {
				DaoUtils.anadir(sql, first, "FECHA_ENTRADA <= ?");
				first = false;
			}
			if(criteria.getFechaMinima()!=null) {
				DaoUtils.anadir(sql, first, "FECHA_ENTRADA >= ?");
				first = false;
			}
			if(criteria.getNumeroFavoritos()!=null) {
				DaoUtils.anadir(sql, first, " (SELECT COUNT(FAVORITO) FROM VALORACION_PRODUCTO_FAVORITOS) >= ? ");
				first = false;
			}
			if(criteria.getValoracion()!=null) {
				DaoUtils.anadir(sql, first, " (SELECT AVG(VALORACION) FROM VALORACION_PRODUCTO_FAVORITOS) >= ?");
				first = false;
			}
			if(criteria.getIdVendedor()!=null) {
				DaoUtils.anadir(sql, first, "ID_USUARIO = ?");
				first = false;
			}
			if(criteria.getTipoVendedor()!=null) {
				DaoUtils.anadir(sql, first, "TIPO_VENDEDOR = ?");
				first = false;
			}
			if(criteria.getAnhoPublicacionMaximo()!=null) {
				DaoUtils.anadir(sql, first, "ANO_PUBLICACION <= ?");
				first = false;
			}
			if(criteria.getAnhoPublicacionMinimo()!=null) {
				DaoUtils.anadir(sql, first, "ANO_PUBLICACION >= ?");
				first = false;
			}
			if(criteria.getFormato()!=null) {
				DaoUtils.anadir(sql, first, "FORMATO = ?");
				first = false;
			}
			if(criteria.getTipoTapa()!=null) {
				DaoUtils.anadir(sql, first, "TIPO_TAPA = ?");
				first = false;
			}
			
			int i = 1;
			preparedStatement = c.prepareStatement(sql.toString(), ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			
			preparedStatement.setString(i++, idioma);
			if(criteria.getIdCategoria()!=null) {
				preparedStatement.setInt(i++, criteria.getIdCategoria());
			}
			if(criteria.getNombre() !=null) {
				preparedStatement.setString(i++, criteria.getNombre());
			}
			if(criteria.getPrecioDesde()!=null) {
				preparedStatement.setDouble(i++, criteria.getPrecioDesde());
			}
			if(criteria.getPrecioHasta()!=null) {
				preparedStatement.setDouble(i++, criteria.getPrecioHasta());
			}
			if(criteria.getFechaMaxima()!=null) {
				preparedStatement.setDate(i++, new java.sql.Date(criteria.getFechaMaxima().getTime()));
			}
			if(criteria.getFechaMinima()!=null) {
				preparedStatement.setDate(i++, new java.sql.Date(criteria.getFechaMinima().getTime()));
			}
			if(criteria.getNumeroFavoritos()!=null) {
				preparedStatement.setInt(i++, criteria.getNumeroFavoritos());
			}
			if(criteria.getValoracion()!=null) {
				preparedStatement.setInt(i++, criteria.getValoracion());
			}
			if(criteria.getIdVendedor()!=null) {
				preparedStatement.setInt(i++, criteria.getIdVendedor());
			}
			if(criteria.getTipoVendedor()!=null) {
				preparedStatement.setInt(i++, criteria.getTipoVendedor());
			}
			if(criteria.getAnhoPublicacionMaximo()!=null) {
				preparedStatement.setInt(i++, criteria.getAnhoPublicacionMaximo());
			}
			if(criteria.getAnhoPublicacionMinimo()!=null) {
				preparedStatement.setInt(i++, criteria.getAnhoPublicacionMinimo());
			}
			if(criteria.getFormato()!=null) {
				preparedStatement.setInt(i++, criteria.getFormato());
			}
			if(criteria.getTipoTapa()!=null) {
				preparedStatement.setInt(i++, criteria.getTipoTapa());
			}
			
			resultSet = preparedStatement.executeQuery();			
			
			int currentCount = 0;

			if ((startIndex >=1) && resultSet.absolute(startIndex)) {
				do {
					j = loadNext(resultSet);
					juegos.add(j);               	
					currentCount++;                	
				} while ((currentCount < count) && resultSet.next()) ;
			}
					
			int totalRows = JDBCUtils.getTotalRows(resultSet);	
			if(totalRows == 0) {
				throw new InstanceNotFoundException(criteria, "JuegoDAOImpl.findByCriteria");
			}
			
			results = new Results<Juego>(juegos, startIndex, totalRows); 
			
			if (logger.isDebugEnabled()) {
				logger.debug("Total rows: {}", totalRows);
			}
			
			return results;				
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
	public void create(Connection c, Juego j) throws DuplicateInstanceException, DataException{
	
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {

			String sql;
			if(j.getIdVendedor()!=null) {
				sql =  "INSERT INTO JUEGO (ID_PRODUCTO, NOMBRE, DESCRIPCION, FORMATO, PAGINAS, TIPO_VENDEDOR, ANO_PUBLICACION, "
						+" TIPO_TAPA, ID_USUARIO) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
			}else {
				sql =  "INSERT INTO JUEGO (ID_PRODUCTO, NOMBRE, DESCRIPCION, FORMATO, PAGINAS, TIPO_VENDEDOR, ANO_PUBLICACION, "
						+ " TIPO_TAPA) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
			}
			preparedStatement = c.prepareStatement(sql);
			
			int i = 1;
			preparedStatement.setInt(i++, j.getIdProducto());
			preparedStatement.setString(i++,  j.getNombre());
			preparedStatement.setString(i++,  j.getDescripcion());
			preparedStatement.setInt(i++,  j.getFormato());
			preparedStatement.setInt(i++,  j.getPaginas());
			preparedStatement.setInt(i++,  j.getTipoVendedor());
			preparedStatement.setInt(i++, j.getAnhoPublicacion());
			preparedStatement.setInt(i++, j.getTipoTapa());
			if(j.getIdVendedor()!=null) {
				preparedStatement.setInt(i++,  j.getIdVendedor());
			}
			
			int insertedRows = preparedStatement.executeUpdate();	

			if(insertedRows == 0) {
				throw new DuplicateInstanceException(j, "JuegoDAOImpl.create");
			}
			
		} 
		catch (SQLException ex) {
			throw new DataException();
		} 
		finally {            
			JDBCUtils.closeResultSet(resultSet);
			JDBCUtils.closeStatement(preparedStatement);
		}  	
	}
	
	private Juego loadNext(ResultSet resultSet) throws SQLException {
		
		Juego j= new Juego();
		
		int i = 1;
		Integer id = resultSet.getInt(i++);
		Integer categoria = resultSet.getInt(i++);
		String nombre = resultSet.getString(i++);
		Double precio = resultSet.getDouble(i++);
		String descripcion = resultSet.getString(i++);
		Date fechaEntrada = resultSet.getDate(i++);
		Integer stock = resultSet.getInt(i++);
		String imagen = resultSet.getString(i++);
		Integer idVendedor = resultSet.getInt(i++);
		Integer formato = resultSet.getInt(i++);
		Integer paginas = resultSet.getInt(i++);
		Integer tipoVendedor = resultSet.getInt(i++);
		Integer anhoPublicacion = resultSet.getInt(i++);
		Integer tipoTapa = resultSet.getInt(i++);
		
		
		j.setIdProducto(id);
		j.setIdCategoria(categoria);
		j.setNombre(nombre);
		j.setPrecio(precio);
		j.setDescripcion(descripcion);
		j.setFechaEntrada(fechaEntrada);
		j.setStock(stock);
		j.setUrlImagen(imagen);
		j.setIdVendedor(idVendedor);
		j.setTipoVendedor(tipoVendedor);
		j.setPaginas(paginas);
		j.setAnhoPublicacion(anhoPublicacion);
		j.setFormato(formato);
		j.setTipoTapa(tipoTapa);
		
		return j;
	}

}
