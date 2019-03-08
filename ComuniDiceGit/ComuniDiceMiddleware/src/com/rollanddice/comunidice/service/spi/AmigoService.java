package com.rollanddice.comunidice.service.spi;

import java.util.List;

import com.rollanddice.comunidice.exception.DataException;
import com.rollanddice.comunidice.exception.ServiceException;
import com.rollanddice.comunidice.model.Amigo;

public interface AmigoService {
	
	public List<Amigo> findAmigos(Integer id)
		throws ServiceException, DataException;
		
	public Amigo findByEmailAmigo(String email, Integer id)
		throws ServiceException, DataException;
		
	public Amigo findByNombreAmigo(String nombreUsuarioAmigo, Integer id)
		throws ServiceException, DataException;
	
	public void create(Integer idUsuario, Integer idAmigo)
		throws ServiceException, DataException;
	
	public void delete(Integer idUsuario, Integer idAmigo)
		throws ServiceException, DataException;

}
