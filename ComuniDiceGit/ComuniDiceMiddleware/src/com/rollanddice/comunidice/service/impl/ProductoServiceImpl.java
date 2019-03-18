package com.rollanddice.comunidice.service.impl;

import java.sql.Connection;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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
import com.rollanddice.comunidice.model.Results;
import com.rollanddice.comunidice.service.spi.ProductoService;

public class ProductoServiceImpl implements ProductoService{
	
	ProductoDAO dao = null;
	FavoritoDAO favoritoDao = null;
	JuegoDAO juegoDao = null;
	ComentarioDAO comentarioDao = null;
	
	public static Logger logger = LogManager.getLogger(ProductoServiceImpl.class);
	
	public ProductoServiceImpl() {
		
		dao = new ProductoDAOImpl();
		favoritoDao = new FavoritoDAOImpl();
		juegoDao = new JuegoDAOImpl();
		comentarioDao = new ComentarioDAOImpl();
	}

	@Override
	public Producto findById(Integer id, String idioma) throws Exception {
		long t1=0l, t2=0l, t3=0l, t4=0l;
		
		boolean commit = false;
		Connection c = null;
		try {
			t1 = System.currentTimeMillis();
			c = ConnectionManager.getConnection();
			t2 = System.currentTimeMillis();
			c.setAutoCommit(false);
			Producto p = dao.findById(c, id, idioma);
			Double media = favoritoDao.mediaValoraciones(c, p.getIdProducto());
			Double favoritos = favoritoDao.countFavoritos(c, p.getIdProducto());
			List<Comentario> comentarios = comentarioDao.findByProductoOForo(c, id, null);
			p.setNumeroFavoritos(favoritos);
			p.setValoracion(media);
			p.setComentarios(comentarios);
			logger.debug(p);
			commit = true;
			t3 = System.currentTimeMillis();
			return p;
		}
		catch(Exception ex) {
			logger.debug("Error");
			throw ex;
		}
		finally {
			
			JDBCUtils.closeConnection(c, commit);
			t4 = System.currentTimeMillis();
			System.out.println("Abrir conexion: "+(t2-t1)+" Proceso: "+(t3-t2)+" Cerrar conexion: "+(t4-t3));
		}
		
	}

	@Override
	public Results<Producto> findByCriteria(Criteria criteria, String idioma, int startIndex, int count) throws Exception {
		
		boolean commit = false;
		Connection c = null;
		try {
			c = ConnectionManager.getConnection();
			c.setAutoCommit(false);
			Results<Producto> p = dao.findByCriteria(c, criteria, idioma, startIndex, count);
			for(Producto producto:p.getPage()) {
				Double media = favoritoDao.mediaValoraciones(c, producto.getIdProducto());
				Double favoritos = favoritoDao.countFavoritos(c, producto.getIdProducto());
				producto.setNumeroFavoritos(favoritos);
				producto.setValoracion(media);
			}
			
			commit = true;
			for(Producto producto:p.getPage()) {
				logger.debug(producto);
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
			logger.debug(j);
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
	public Results<Juego> findJuegoByCriteria(Criteria criteria, int startIndex, int count) throws Exception {
		
		boolean commit = false;
		Connection c = null;
		try {
			c = ConnectionManager.getConnection();
			c.setAutoCommit(false);
			Results<Juego> j = juegoDao.findByCriteria(c, criteria, startIndex, count);
			for(Juego juego:j.getPage()) {
				Double media = favoritoDao.mediaValoraciones(c, juego.getIdProducto());
				Double favoritos = favoritoDao.countFavoritos(c, juego.getIdProducto());
				juego.setNumeroFavoritos(favoritos);
				juego.setValoracion(media);
			}
			
			commit = true;
			for(Juego juego:j.getPage()) {
				logger.debug(juego);
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
