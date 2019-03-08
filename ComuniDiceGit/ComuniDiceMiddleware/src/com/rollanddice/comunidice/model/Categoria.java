package com.rollanddice.comunidice.model;

public class Categoria extends AbstractValueObject{
	
	private Integer id = null;
	private String nombre = null;
	
	public Categoria() {
		
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	@Override
	public boolean equals(Object o) {
		
		if(o==null || !(o instanceof Categoria)) {
			return false;
		}
		if(this.getId()== null) {
			return false;
		}
		Categoria c = (Categoria) o;
		if(!this.getId().equals(c.getId())) {
			return false;
		}
		return true;
	}

	@Override
	public int hashCode() {
		return id.hashCode();
	}
}
