package com.rollanddice.comunidice.service.spi;

import java.util.List;

import com.rollanddice.comunidice.model.Direccion;
import com.rollanddice.comunidice.model.Pais;
import com.rollanddice.comunidice.model.Region;

public interface DireccionService {
	
	public List<Pais> findAll()
		throws Exception;
	
	public List<Region> findByPais(Integer idPais)
		throws Exception;
	
	public void create(Direccion d, Integer idUsuario)
		throws Exception;
	
	public void update(Direccion d)
		throws Exception;
	
	public void delete(Direccion d)
		throws Exception;
}
