package com.rollanddice.comunidice.service.spi;

import java.util.List;

import com.rollanddice.comunidice.model.Criteria;
import com.rollanddice.comunidice.model.Foro;

public interface ForoService {
	
	public Foro findById(Integer id)
		throws Exception;
		
	public List<Foro> findByCriteria(Criteria criteria)
		throws Exception;
		
	public void create(Foro foro)
		throws Exception;
		
	public void delete(Foro foro)
		throws Exception;

}
