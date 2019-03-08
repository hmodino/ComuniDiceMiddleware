package com.rollanddice.comunidice.service.spi;

import java.util.List;

import com.rollanddice.comunidice.model.Criteria;
import com.rollanddice.comunidice.model.Juego;
import com.rollanddice.comunidice.model.Producto;

public interface ProductoService {
	
	public Producto findById(Integer id, String idioma)
		throws Exception;
		
	public List<Producto> findByCriteria(Criteria criteria, String idioma)
		throws Exception;

	public Juego findJuegoById(Integer id) 
		throws Exception;

	public List<Juego> findJuegoByCriteria(Criteria criteria) 
		throws Exception;
	
	public void create(Producto p, String idioma)
		throws Exception;
	
	public void createJuego(Juego j)
		throws Exception;

}
