package com.rollanddice.comunidice.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.rollanddice.comunidice.dao.spi.ProductoDAO;
import com.rollanddice.comunidice.dao.util.DaoUtils;
import com.rollanddice.comunidice.dao.util.JDBCUtils;
import com.rollanddice.comunidice.model.Criteria;
import com.rollanddice.comunidice.model.Producto;

public class ProductoDAOImpl implements ProductoDAO{

	@Override
	public Producto findById(Connection c, Integer id, String idioma) throws Exception {
		
		Producto p= null;
		
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {

			String sql;
			sql =  " SELECT P.ID_PRODUCTO, P.ID_CATEGORIA, P.PRECIO, P.FECHA_ENTRADA, P.STOCK, P.IMAGEN, I.DESCRIPCION, I.NOMBRE "
				  +" FROM PRODUCTO P INNER JOIN IDIOMA_PRODUCTO I ON(P.ID_PRODUCTO = I.ID_PRODUCTO) "
				  +" WHERE P.ID_PRODUCTO = ? AND I.IDIOMA LIKE ?";
			
			preparedStatement = c.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			
			int i = 1;
			preparedStatement.setInt(i++, id);	
			preparedStatement.setString(i++, idioma);
			resultSet = preparedStatement.executeQuery();			
			
			if (resultSet.next()) {				
				p = loadNext(c, resultSet);				
			} else {
				throw new Exception("El producto que buscas no existe");
			}				
		} 
		catch (Exception ex) {
 
		} 
		finally {            
			JDBCUtils.closeResultSet(resultSet);
			JDBCUtils.closeStatement(preparedStatement);
		}  	
			
		return p;
}	

	@Override
	public List<Producto> findByCriteria(Connection c, Criteria criteria, String idioma) throws Exception {
		
		Producto p= null;
		List<Producto> productos = new ArrayList<Producto>();
		
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		StringBuilder sql = null;
		try {

			sql = new StringBuilder(
					" SELECT P.ID_PRODUCTO, P.ID_CATEGORIA, P.PRECIO, P.FECHA_ENTRADA, P.STOCK, P.IMAGEN, I.DESCRIPCION, I.NOMBRE "
					+" FROM PRODUCTO P INNER JOIN IDIOMA_PRODUCTO I ON(P.ID_PRODUCTO = I.ID_PRODUCTO) "
					+" WHERE I.IDIOMA LIKE ? ");
			
			boolean first = false;
			
				if(criteria.getIdCategoria()!=null) {
					DaoUtils.anadir(sql, first, "P.ID_CATEGORIA = ?");
					first = false;
				}
				if(criteria.getNombre() !=null) {
					DaoUtils.anadir(sql, first, " P.NOMBRE LIKE ? ");
					first = false;
				}
				if(criteria.getPrecioDesde()!=null) {
					DaoUtils.anadir(sql, first, "P.PRECIO >= ?");
					first = false;
				}
				if(criteria.getPrecioHasta()!=null) {
					DaoUtils.anadir(sql, first, "P.PRECIO <=?");
					first = false;
				}
				if(criteria.getFechaMaxima()!=null) {
					DaoUtils.anadir(sql, first, "P.FECHA_ENTRADA <= ?");
					first = false;
				}
				if(criteria.getFechaMinima()!=null) {
					DaoUtils.anadir(sql, first, "P.FECHA_ENTRADA >= ?");
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
				preparedStatement.setDate(i++, (java.sql.Date)criteria.getFechaMaxima());
			}
			if(criteria.getFechaMinima()!=null) {
				preparedStatement.setDate(i++, (java.sql.Date)criteria.getFechaMinima());
			}
			if(criteria.getNumeroFavoritos()!=null) {
				preparedStatement.setInt(i++, criteria.getNumeroFavoritos());
			}
			if(criteria.getValoracion()!=null) {
				preparedStatement.setInt(i++, criteria.getValoracion());
			}
			
			resultSet = preparedStatement.executeQuery();			
			
			if (resultSet.next()) {				
				while(resultSet.next()) {
					p = loadNext(c, resultSet);
					productos.add(p);
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
			
		return productos;
	}
	

	@Override
	public void create(Connection c, Producto p) throws Exception {
		System.out.println("" + p);
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {

			String sql;
			sql =  "INSERT INTO PRODUCTO (ID_CATEGORIA, PRECIO, FECHA_ENTRADA, STOCK, IMAGEN) "
				  +" VALUES (?, ?, ?, ?, ?)";
			System.out.println(sql);
			preparedStatement = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			
			int i = 1;
			preparedStatement.setInt(i++, p.getIdCategoria());
			preparedStatement.setDouble(i++,  p.getPrecio());
			preparedStatement.setDate(i++, (new java.sql.Date(new Date().getTime())));
			preparedStatement.setInt(i++, p.getStock());
			preparedStatement.setString(i++,  p.getUrlImagen());
			System.out.println(preparedStatement);
			
			int insertedRows = preparedStatement.executeUpdate();	
			
			if(insertedRows == 0) {
				throw new SQLException("Operación fallida");
			}
			
			resultSet = preparedStatement.getGeneratedKeys();
			if(resultSet.next()) {
				p.setIdProducto(resultSet.getInt(1));
			}
			System.out.println(p.getIdProducto().toString());
		} 
		catch (SQLException ex) {
			ex.printStackTrace();
			throw new Exception();
			
		} 
		finally {            
			JDBCUtils.closeResultSet(resultSet);
			JDBCUtils.closeStatement(preparedStatement);
		}  	
	}

	@Override
	public void createIdioma(Connection c, Producto p, String idioma) throws Exception {
		System.out.println("" + p);
		System.out.println(idioma);
		
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {

			String sql;
			sql =  "INSERT INTO IDIOMA_PRODUCTO (IDIOMA, ID_PRODUCTO, NOMBRE, DESCRIPCION) "
				  +" VALUES (?, ?, ?, ?)";
			System.out.println(sql);
			preparedStatement = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			
			int i = 1;
			preparedStatement.setString(i++, idioma);
			preparedStatement.setInt(i++,  p.getIdProducto());
			preparedStatement.setString(i++, p.getNombre());
			preparedStatement.setString(i++,  p.getDescripcion());

			
			int insertedRows = preparedStatement.executeUpdate();	
			
			if(insertedRows == 0) {
				throw new SQLException("Operación fallida");
			}
			
			resultSet = preparedStatement.getGeneratedKeys();
		} 
		catch (SQLException ex) {
			throw new Exception();
		} 
		finally {            
			JDBCUtils.closeResultSet(resultSet);
			JDBCUtils.closeStatement(preparedStatement);
		}  	
	}
	
	private Producto loadNext(Connection c, ResultSet resultSet) throws Exception {
		
		Producto p= new Producto();
		 
		int i = 1;
		Integer id = resultSet.getInt(i++);
		Integer categoria = resultSet.getInt(i++);
		Double precio = resultSet.getDouble(i++);
		Date fechaEntrada = resultSet.getDate(i++);
		Integer stock = resultSet.getInt(i++);
		String imagen = resultSet.getString(i++);
		String descripcion = resultSet.getString(i++);
		String nombre = resultSet.getString(i++);		
		p.setIdProducto(id);
		p.setIdCategoria(categoria);
		p.setNombre(nombre);
		p.setPrecio(precio);
		p.setDescripcion(descripcion);
		p.setFechaEntrada(fechaEntrada);
		p.setStock(stock);
		p.setUrlImagen(imagen);
		
		return p;
	}
}