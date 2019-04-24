package com.rollanddice.comunidice.service.impl;

import java.sql.Connection;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.rollanddice.comunidice.dao.impl.CompraDAOImpl;
import com.rollanddice.comunidice.dao.impl.LineaCompraDAOImpl;
import com.rollanddice.comunidice.dao.spi.CompraDAO;
import com.rollanddice.comunidice.dao.spi.LineaCompraDAO;
import com.rollanddice.comunidice.dao.util.ConnectionManager;
import com.rollanddice.comunidice.dao.util.JDBCUtils;
import com.rollanddice.comunidice.model.Compra;
import com.rollanddice.comunidice.model.LineaCompra;
import com.rollanddice.comunidice.service.spi.CompraService;

public class CompraServiceImpl implements CompraService{
	private static Logger logger = LogManager.getLogger(CompraServiceImpl.class);
	private CompraDAO compraDao = null;
	private LineaCompraDAO lineaDao = null;
	
	public CompraServiceImpl() {
		
	compraDao = new CompraDAOImpl();
	lineaDao = new LineaCompraDAOImpl();
	
	}

	@Override
	public Compra findById(Integer id) throws Exception {
		
		boolean commit = false;
		Connection c = null;
		
		try {
			c = ConnectionManager.getConnection();
			c.setAutoCommit(false);
			Compra compra = compraDao.findById(c, id);
			List<LineaCompra> lc = lineaDao.findByCompra(c, id);
			
			compra.setnLinea(lc);
			
			commit = true;
			logger.info(compra);
			return compra;
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
	public List<Compra> findByUsuario(Integer idUsuario) throws Exception {
		
		boolean commit = false;
		Connection c = null;
		
		try {
			c = ConnectionManager.getConnection();
			c.setAutoCommit(false);
			List<Compra> compra = compraDao.findByUsuario(c, idUsuario);
			
			commit = true;
			logger.info(compra);
			return compra;
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
	public void create(Compra compra, List<LineaCompra>lcs) throws Exception {
		
		boolean commit = false;
		Connection c = null;
		
		try {
			c = ConnectionManager.getConnection();
			c.setAutoCommit(false);
			if(!lcs.isEmpty()) {
				compraDao.create(c, compra);
				for(LineaCompra linea:lcs) {
					lineaDao.create(c, linea, compra.getIdCompra());
				
				commit = true;
				logger.info("La compra ha sido realizada con éxito");
				}
			}
			else{logger.info("Inserta lineas de compra");}
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
	public void delete(Compra compra, List<LineaCompra>lcs) throws Exception {
		
		boolean commit = false;
		Connection c = null;
		
		try {
			c = ConnectionManager.getConnection();
			c.setTransactionIsolation(
                    Connection.TRANSACTION_READ_COMMITTED);
			c.setAutoCommit(false);
			
			for(LineaCompra linea:lcs) {
				lineaDao.delete(c, linea, compra.getIdCompra());
			}
			compraDao.delete(c, compra);
			
			commit = true;
			System.out.println("La compra ha sido cancelada con éxito");
		}
		catch (Exception ex) {
			ex.printStackTrace();
		} 
		finally { 
			JDBCUtils.closeConnection(c, commit);
		}
	}

}
