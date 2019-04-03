package com.rollanddice.comunidice.model;

import java.util.Date;

public class Amigo extends AbstractValueObject{
	
	private Integer amigo = null;
	private Date fecha = null;
	
	public Amigo() {

	}

	public Integer getAmigo() {
		return amigo;
	}
	public void setAmigo(Integer id) {
		this.amigo = id;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

}
