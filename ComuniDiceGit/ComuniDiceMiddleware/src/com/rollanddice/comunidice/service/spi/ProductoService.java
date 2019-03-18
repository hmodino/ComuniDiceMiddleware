package com.rollanddice.comunidice.service.spi;

import com.rollanddice.comunidice.model.Criteria;
import com.rollanddice.comunidice.model.Juego;
import com.rollanddice.comunidice.model.Producto;
import com.rollanddice.comunidice.model.Results;

public interface ProductoService {
	
	public Producto findById(Integer id, String idioma)
		throws Exception;
		
	public Results<Producto> findByCriteria(Criteria criteria, String idioma, int startIndex, int count)
		throws Exception;

	public Juego findJuegoById(Integer id) 
		throws Exception;

	public Results<Juego> findJuegoByCriteria(Criteria criteria, int startIndex, int count) 
		throws Exception;
	
	public void create(Producto p, String idioma)
		throws Exception;
	
	public void createJuego(Juego j)
		throws Exception;

}
