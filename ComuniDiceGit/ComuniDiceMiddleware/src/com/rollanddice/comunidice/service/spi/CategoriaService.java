package com.rollanddice.comunidice.service.spi;

import java.util.List;

import com.rollanddice.comunidice.exception.DataException;
import com.rollanddice.comunidice.exception.ServiceException;
import com.rollanddice.comunidice.model.Categoria;

public interface CategoriaService {
	
	public List<Categoria> findAll()
			throws ServiceException, DataException;

}
