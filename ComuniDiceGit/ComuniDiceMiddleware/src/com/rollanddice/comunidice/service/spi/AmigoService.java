package com.rollanddice.comunidice.service.spi;

import java.util.List;

import com.rollanddice.comunidice.exception.DataException;
import com.rollanddice.comunidice.exception.ServiceException;
import com.rollanddice.comunidice.model.Usuario;

public interface AmigoService {
	
	public List<Usuario> findAmigos(Integer id)
		throws ServiceException, DataException;
		
	public Usuario findByEmailAmigo(String email, Integer id)
		throws ServiceException, DataException;
		
	public Usuario findByNombreAmigo(String nombreUsuarioAmigo, Integer id)
		throws ServiceException, DataException;
	
	public void create(Integer idUsuario, Integer idAmigo)
		throws ServiceException, DataException;
	
	public void delete(Integer idUsuario, Integer idAmigo)
		throws ServiceException, DataException;

}
