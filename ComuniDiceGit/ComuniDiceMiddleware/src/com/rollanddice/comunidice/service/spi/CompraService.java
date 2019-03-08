package com.rollanddice.comunidice.service.spi;

import java.util.List;

import com.rollanddice.comunidice.model.Compra;
import com.rollanddice.comunidice.model.LineaCompra;

public interface CompraService {
	
	public Compra findById(Integer id)
		throws Exception;
		
	public List<Compra> findByUsuario(Integer idUsuario)
		throws Exception;
		
	public void create(Compra compra, List<LineaCompra> lcs)
		throws Exception;
		
	public void delete(Compra compra, List<LineaCompra> lcs)
		throws Exception;

}
