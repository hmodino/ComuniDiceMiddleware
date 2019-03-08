package com.rollanddice.comunidice.service.impl;

import java.sql.Connection;
import java.util.List;

import com.rollanddice.comunidice.dao.impl.ComentarioDAOImpl;
import com.rollanddice.comunidice.dao.impl.FavoritoDAOImpl;
import com.rollanddice.comunidice.dao.impl.JuegoDAOImpl;
import com.rollanddice.comunidice.dao.impl.ProductoDAOImpl;
import com.rollanddice.comunidice.dao.spi.ComentarioDAO;
import com.rollanddice.comunidice.dao.spi.FavoritoDAO;
import com.rollanddice.comunidice.dao.spi.JuegoDAO;
import com.rollanddice.comunidice.dao.spi.ProductoDAO;
import com.rollanddice.comunidice.dao.util.ConnectionManager;
import com.rollanddice.comunidice.dao.util.JDBCUtils;
import com.rollanddice.comunidice.model.Comentario;
import com.rollanddice.comunidice.model.Criteria;
import com.rollanddice.comunidice.model.Juego;
import com.rollanddice.comunidice.model.Producto;
import com.rollanddice.comunidice.service.spi.ProductoService;

public class ProductoServiceImpl implements ProductoService{
	
	ProductoDAO dao = null;
	FavoritoDAO favoritoDao = null;
	JuegoDAO juegoDao = null;
	ComentarioDAO comentarioDao = null;
	public ProductoServiceImpl() {
		
		dao = new ProductoDAOImpl();
		favoritoDao = new FavoritoDAOImpl();
		juegoDao = new JuegoDAOImpl();
		comentarioDao = new ComentarioDAOImpl();
	}

	@Override
	public Producto findById(Integer id, String idioma) throws Exception {
		
		boolean commit = false;
		Connection c = null;
		try {
			c = ConnectionManager.getConnection();
			c.setAutoCommit(false);
			Producto p = dao.findById(c, id, idioma);
			Double media = favoritoDao.mediaValoraciones(c, p.getIdProducto());
			Double favoritos = favoritoDao.countFavoritos(c, p.getIdProducto());
			List<Comentario> comentarios = comentarioDao.findByProductoOForo(c, id, null);
			p.setNumeroFavoritos(favoritos);
			p.setValoracion(media);
			p.setComentarios(comentarios);
			System.out.println(p);
			commit = true;
			return p;
		}
		catch(Exception ex) {
			System.out.println("Error");
			throw ex;
		}
		finally { 
			JDBCUtils.closeConnection(c, commit);
		}
		
	}

	@Override
	public List<Producto> findByCriteria(Criteria criteria, String idioma) throws Exception {
		
		boolean commit = false;
		Connection c = null;
		try {
			c = ConnectionManager.getConnection();
			c.setAutoCommit(false);
			List<Producto> p = dao.findByCriteria(c, criteria, idioma);
			for(Producto producto:p) {
				Double media = favoritoDao.mediaValoraciones(c, producto.getIdProducto());
				Double favoritos = favoritoDao.countFavoritos(c, producto.getIdProducto());
				List<Comentario> comentarios = comentarioDao.findByProductoOForo(c, producto.getIdProducto(), null);
				producto.setNumeroFavoritos(favoritos);
				producto.setValoracion(media);
				producto.setComentarios(comentarios);
			}
			
			commit = true;
			for(Producto producto:p) {
			System.out.println(producto);
			}
			return p;
		}
		catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		} 
		finally { 
			JDBCUtils.closeConnection(c, commit);
		}
	}
	
	@Override
	public Juego findJuegoById(Integer id) throws Exception {
		
		boolean commit = false;
		Connection c = null;
		c = ConnectionManager.getConnection();
		c.setAutoCommit(false);
		try {
			Juego j = juegoDao.findById(c, id);
			Double media = favoritoDao.mediaValoraciones(c, j.getIdProducto());
			Double favoritos = favoritoDao.countFavoritos(c, j.getIdProducto());
			List<Comentario> comentarios = comentarioDao.findByProductoOForo(c, id, null);
			j.setNumeroFavoritos(favoritos);
			j.setValoracion(media);
			j.setComentarios(comentarios);
			commit = true;
			System.out.println(j);
			return j;
		}
		catch(Exception exc) {
			throw exc;
		}
		finally { 
			JDBCUtils.closeConnection(c, commit);
		}

	}

	@Override
	public List<Juego> findJuegoByCriteria(Criteria criteria) throws Exception {
		
		boolean commit = false;
		Connection c = null;
		try {
			c = ConnectionManager.getConnection();
			c.setAutoCommit(false);
			List<Juego> j = juegoDao.findByCriteria(c, criteria);
			for(Juego juego:j) {
				Double media = favoritoDao.mediaValoraciones(c, juego.getIdProducto());
				Double favoritos = favoritoDao.countFavoritos(c, juego.getIdProducto());
				List<Comentario> comentarios = comentarioDao.findByProductoOForo(c, juego.getIdProducto(), null);
				juego.setNumeroFavoritos(favoritos);
				juego.setValoracion(media);
				juego.setComentarios(comentarios);
			}
			
			commit = true;
			for(Juego juego:j) {
				System.out.println(juego);
			}
			return j;
		}
		catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		} 
		finally { 
			JDBCUtils.closeConnection(c, commit);
		}
	}

	@Override
	public void create(Producto p, String idioma) throws Exception {
		
		boolean commit = false;
		Connection c = null;
		try {
			c = ConnectionManager.getConnection();
			c.setAutoCommit(false);
			dao.create(c, p);
			dao.createIdioma(c, p, idioma);
			commit = true;
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			JDBCUtils.closeConnection(c, commit);
		}
		
	}

	@Override
	public void createJuego(Juego j) throws Exception {
		
		boolean commit = false;
		Connection c = null;
		try {
			c = ConnectionManager.getConnection();
			c.setAutoCommit(false);
			dao.create(c, j);
			juegoDao.create(c, j);
			commit = true;
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			JDBCUtils.closeConnection(c, commit);
		}
	}
}
