package com.rollanddice.comunidice.service.spi;

import java.util.List;

import com.rollanddice.comunidice.model.Mensaje;

public interface MensajeService {
	
	public Mensaje findById(Integer id)
			throws Exception;
		
		public List<Mensaje> findByEmisor(Integer id)
			throws Exception;
		
		public List<Mensaje> findByReceptor(Integer idReceptor)
			throws Exception;
		
		public void create(Mensaje mensaje)
			throws Exception;

		public void delete(Integer id) 
			throws Exception;
}
