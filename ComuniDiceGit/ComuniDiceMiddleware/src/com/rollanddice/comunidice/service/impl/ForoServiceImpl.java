package com.rollanddice.comunidice.service.impl;

import java.sql.Connection;
import java.util.List;

import com.rollanddice.comunidice.dao.impl.ComentarioDAOImpl;
import com.rollanddice.comunidice.dao.impl.ForoDAOImpl;
import com.rollanddice.comunidice.dao.spi.ComentarioDAO;
import com.rollanddice.comunidice.dao.spi.ForoDAO;
import com.rollanddice.comunidice.dao.util.ConnectionManager;
import com.rollanddice.comunidice.dao.util.JDBCUtils;
import com.rollanddice.comunidice.model.Comentario;
import com.rollanddice.comunidice.model.Criteria;
import com.rollanddice.comunidice.model.Foro;
import com.rollanddice.comunidice.service.spi.ForoService;

	public class ForoServiceImpl implements ForoService{
	
	ForoDAO dao = null;
	ComentarioDAO comentarioDao = null;
	
	public ForoServiceImpl() {
		
		dao = new ForoDAOImpl();
		comentarioDao = new ComentarioDAOImpl();
	}

	@Override
	public Foro findById(Integer id) throws Exception {
		
		boolean commit = false;
		Connection c = null;
		
		try {
			c = ConnectionManager.getConnection();
			c.setAutoCommit(false);
			Foro f = dao.findById(c, id);
			List<Comentario> comentarios = comentarioDao.findByProductoOForo(c, null, id);
			f.setComentarios(comentarios);
			commit = true;
			System.out.println(f);
			return f;
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
	public List<Foro> findByCriteria(Criteria criteria) throws Exception {
		
		boolean commit = false;
		Connection c = null;
		
		try {
			c = ConnectionManager.getConnection();
			c.setAutoCommit(false);
			List<Foro> f = dao.findByCriteria(c, criteria);
			for(Foro foro:f) {
				List<Comentario> comentarios = comentarioDao.findByProductoOForo(c, null, foro.getIdForo());
				foro.setComentarios(comentarios);
			}
			
			commit = true;
			for(Foro foro:f) {
				System.out.println(foro);
			}
			return f;
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
	public void create(Foro foro) throws Exception {
		
		boolean commit = false;
		Connection c = null;
		
		try {
			c = ConnectionManager.getConnection();
			c.setTransactionIsolation(
                    Connection.TRANSACTION_READ_COMMITTED);
			c.setAutoCommit(false);
			dao.create(c, foro);
			
			commit = true;
			System.out.println("Foro creado con éxito");
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
	public void delete(Foro foro) throws Exception {
		
		boolean commit = false;
		Connection c = null;
		
		try {
			c = ConnectionManager.getConnection();
			c.setTransactionIsolation(
                    Connection.TRANSACTION_READ_COMMITTED);
			c.setAutoCommit(false);
			comentarioDao.findByProductoOForo(c, null, foro.getIdForo());
			dao.delete(c, foro);
			
			commit = true;
			System.out.println("Foro eliminado con éxito");
		}
		catch (Exception ex) {
			ex.printStackTrace();
		} 
		finally { 
			JDBCUtils.closeConnection(c, commit);
		}
	}
	
	

}
