package com.rollanddice.comunidice.service.impl;

import java.sql.Connection;
import java.util.List;

import com.rollanddice.comunidice.dao.impl.LineaVentaDAOImpl;
import com.rollanddice.comunidice.dao.impl.VentaDAOImpl;
import com.rollanddice.comunidice.dao.spi.LineaVentaDAO;
import com.rollanddice.comunidice.dao.spi.VentaDAO;
import com.rollanddice.comunidice.dao.util.ConnectionManager;
import com.rollanddice.comunidice.dao.util.JDBCUtils;
import com.rollanddice.comunidice.model.LineaVenta;
import com.rollanddice.comunidice.model.Venta;
import com.rollanddice.comunidice.service.spi.VentaService;

public class VentaServiceImpl implements VentaService{
	
	VentaDAO dao = null;
	LineaVentaDAO lineaDao = null;
	
	public VentaServiceImpl() {
		
		dao = new VentaDAOImpl();
		lineaDao = new LineaVentaDAOImpl();
	}

	@Override
	public Venta findById(Integer id) throws Exception {
		
		boolean commit = false;
		Connection c = null;
		
		try {
			c = ConnectionManager.getConnection();
			c.setAutoCommit(false);
			Venta v = dao.findById(c, id);
			
			commit = true;
			return v;
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
	public List<Venta> findByUsuario(Integer idUsuario) throws Exception {
		
		boolean commit = false;
		Connection c = null;
		
		try {
			c = ConnectionManager.getConnection();
			c.setAutoCommit(false);
			List<Venta> v = dao.findByUsuario(c, idUsuario);
			
			commit = true;
			return v;
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
	public void create(Venta venta, List<LineaVenta>lvs) throws Exception {
		
		boolean commit = false;
		Connection c = null;
		
		try {
			c = ConnectionManager.getConnection();
			c.setTransactionIsolation(
                    Connection.TRANSACTION_READ_COMMITTED);
			c.setAutoCommit(false);
			dao.create(c, venta);
			for(LineaVenta linea:lvs) {
				int i = lvs.indexOf(linea);
				lineaDao.create(c, lvs.get(i), venta.getIdCompra());
			}
			venta.setnLinea(lvs);
			commit = true;
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
	public void delete(Venta venta, List<LineaVenta>lvs) throws Exception {
		
		boolean commit = false;
		Connection c = null;
		
		try {
			c = ConnectionManager.getConnection();
			c.setTransactionIsolation(
                    Connection.TRANSACTION_READ_COMMITTED);
			c.setAutoCommit(false);
			for(LineaVenta linea:lvs) {
				int i = lvs.indexOf(linea);
				lineaDao.delete(c, lvs.get(i), venta.getIdCompra());
			}
			dao.delete(c, venta);
			
			commit = true;
			System.out.println("Venta cancelada con éxito");
		}
		catch (Exception ex) {
			ex.printStackTrace();
		} 
		finally { 
			JDBCUtils.closeConnection(c, commit);
		}
	}

}
