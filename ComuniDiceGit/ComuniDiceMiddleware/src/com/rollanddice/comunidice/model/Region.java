package com.rollanddice.comunidice.model;

public class Region extends AbstractValueObject{
	
	private Integer idRegion = null;
	private String nombre = null;
	private Integer idPais = null;
	
	public Region() {	
	}

	public Integer getIdRegion() {
		return idRegion;
	}

	public void setIdRegion(Integer idRegion) {
		this.idRegion = idRegion;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Integer getIdPais() {
		return idPais;
	}

	public void setIdPais(Integer idPais) {
		this.idPais = idPais;
	}

}
