package com.rollanddice.comunidice.service.impl;

import java.sql.Connection;
import java.util.List;

import com.rollanddice.comunidice.dao.impl.ComentarioDAOImpl;
import com.rollanddice.comunidice.dao.spi.ComentarioDAO;
import com.rollanddice.comunidice.dao.util.ConnectionManager;
import com.rollanddice.comunidice.dao.util.JDBCUtils;
import com.rollanddice.comunidice.model.Comentario;
import com.rollanddice.comunidice.service.spi.ComentarioService;

public class ComentarioServiceImpl implements ComentarioService{
	
	private ComentarioDAO dao = null;
	
	public ComentarioServiceImpl() {
		
	dao = new ComentarioDAOImpl();
	
	}
		
	@Override
	public Comentario findById(Integer id) throws Exception {
		
		boolean commit = false;
		Connection c = null;
		
		try {
			c = ConnectionManager.getConnection();
			c.setAutoCommit(false);
			Comentario coment = dao.findById(c, id);
			
			commit = true;
			System.out.println(coment);
			return coment;
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
	public List<Comentario> findByProductoOForo(Integer idProducto, Integer idForo) throws Exception {
		
		boolean commit = false;
		Connection c = null;
		
		try {
			c = ConnectionManager.getConnection();
			c.setAutoCommit(false);
			List<Comentario> coment = dao.findByProductoOForo(c, idProducto, idForo);
			
			commit = true;
			for(Comentario comentario:coment) {
				System.out.println(comentario);	
			}
			return coment;
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
	public List<Comentario> findByUsuarioProductoOForo(Integer idUsuario, Integer idProducto, Integer idForo) throws Exception {
	
		boolean commit = false;
		Connection c = null;
		
		try {
			c = ConnectionManager.getConnection();
			c.setAutoCommit(false);
			List<Comentario> coment = dao.findByUsuarioProductoOForo(c, idUsuario, idProducto, idForo);
			
			commit = true;
			for(Comentario comentario:coment) {
				System.out.println(comentario);	
			}
			return coment;
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
	public List<Comentario> findByUsuarioTipo(Integer idUsuario, Boolean booleano) throws Exception{
		
		boolean commit = false;
		Connection c = null;
		
		try {
			c = ConnectionManager.getConnection();
			c.setAutoCommit(false);
			List<Comentario> coment = dao.findByUsuarioTipo(c, idUsuario, booleano);
			
			commit = true;
			for(Comentario comentario:coment) {
				System.out.println(comentario);	
			}
			return coment;
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
	public void create(Comentario comentario, Integer idProducto, Integer idForo) throws Exception {
		
		boolean commit = false;
		Connection c = null;
		try {
			c = ConnectionManager.getConnection();
			c.setTransactionIsolation(
                    Connection.TRANSACTION_READ_COMMITTED);
			c.setAutoCommit(false);
			dao.create(c, comentario, idProducto, idForo);
			
			commit = true;
			System.out.println("Comentario añadido con éxito");
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
	public void delete(Comentario comentario) throws Exception {
		
		boolean commit = false;
		Connection c = null;
		try {
			c = ConnectionManager.getConnection();
			c.setTransactionIsolation(
                    Connection.TRANSACTION_READ_COMMITTED);
			c.setAutoCommit(false);
			dao.delete(c, comentario);
			
			commit = true;
			System.out.println("Comentario eliminado con éxito");
		}
		catch (Exception ex) {
			ex.printStackTrace();
		} 
		finally { 
			JDBCUtils.closeConnection(c, commit);
		}
	}
}


