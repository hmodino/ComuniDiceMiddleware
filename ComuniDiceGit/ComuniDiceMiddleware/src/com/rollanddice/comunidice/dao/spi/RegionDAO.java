package com.rollanddice.comunidice.dao.spi;

import java.sql.Connection;
import java.util.List;

import com.rollanddice.comunidice.exception.DataException;
import com.rollanddice.comunidice.exception.InstanceNotFoundException;
import com.rollanddice.comunidice.model.Region;

public interface RegionDAO {
	
	public Region findById(Connection c, Integer id)
		throws InstanceNotFoundException, DataException;
	
	public List<Region> findByPais(Connection c, Integer idPais)
		throws InstanceNotFoundException, DataException;

}
