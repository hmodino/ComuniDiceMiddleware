package com.rollanddice.comunidice.service.spi;

import java.util.List;

import com.rollanddice.comunidice.model.LineaVenta;
import com.rollanddice.comunidice.model.Venta;

public interface VentaService {
	
	public Venta findById(Integer id)
		throws Exception;
			
	public List<Venta> findByUsuario(Integer idUsuario)
		throws Exception;
			
	public void create(Venta venta, List<LineaVenta>lvs)
		throws Exception;
			
	public void delete(Venta venta, List<LineaVenta>lvs)
		throws Exception;

}
