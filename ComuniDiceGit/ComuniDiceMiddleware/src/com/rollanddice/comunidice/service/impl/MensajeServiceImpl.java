package com.rollanddice.comunidice.service.impl;

import java.sql.Connection;
import java.util.List;

import com.rollanddice.comunidice.dao.impl.MensajeDAOImpl;
import com.rollanddice.comunidice.dao.spi.MensajeDAO;
import com.rollanddice.comunidice.dao.util.ConnectionManager;
import com.rollanddice.comunidice.dao.util.JDBCUtils;
import com.rollanddice.comunidice.model.Mensaje;
import com.rollanddice.comunidice.service.spi.MensajeService;

public class MensajeServiceImpl implements MensajeService{
		
		MensajeDAO dao = null;
		
		public MensajeServiceImpl() {
			
			dao = new MensajeDAOImpl();
		}

		@Override
		public Mensaje findById(Integer id) throws Exception {
			
			boolean commit = false;
			Connection c = null;
			
			try {
				c = ConnectionManager.getConnection();
				c.setAutoCommit(false);
				Mensaje m = dao.findById(c, id);
				
				commit = true;
				System.out.println(m);
				return m;
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
		public List<Mensaje> findByEmisor(Integer id) throws Exception {
			
			boolean commit = false;
			Connection c = null;
			
			try {
				c = ConnectionManager.getConnection();
				c.setAutoCommit(false);
				List<Mensaje> m = dao.findByEmisor(c, id);
				
				commit = true;
				for(Mensaje mensaje:m) {
					System.out.println(mensaje);
				}
				return m;
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
		public List<Mensaje> findByReceptor(Integer idReceptor) throws Exception {
			
			boolean commit = false;
			Connection c = null;
			
			try {
				c = ConnectionManager.getConnection();
				c.setAutoCommit(false);
				List<Mensaje> m = dao.findByReceptor(c, idReceptor);
				
				commit = true;
				for(Mensaje mensaje:m) {
					System.out.println(mensaje);
				}
				return m;
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
		public void create(Mensaje mensaje) throws Exception {
			
			boolean commit = false;
			Connection c = null;
			
			try {
				c = ConnectionManager.getConnection();
				c.setAutoCommit(false);
				dao.create(c, mensaje);
				
				commit = true;
				System.out.println("Mensaje enviado");
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
		public void delete(Integer id) throws Exception{
			
			boolean commit = false;
			Connection c = null;
			
			try {
				c = ConnectionManager.getConnection();
				c.setAutoCommit(false);
				dao.delete(c, id);
				
				commit = true;
				System.out.println("Mensaje eliminado");
			}
			catch (Exception ex) {
				ex.printStackTrace();
				throw ex;
			} 
			finally { 
				JDBCUtils.closeConnection(c, commit);
			}
		}

}
