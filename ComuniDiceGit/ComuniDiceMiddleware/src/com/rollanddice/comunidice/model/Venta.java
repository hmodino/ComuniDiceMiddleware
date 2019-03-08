package com.rollanddice.comunidice.model;

import java.util.Date;
import java.util.List;

public class Venta extends AbstractValueObject{
	
	private Integer idCompra = null;
	private List<LineaVenta> nLinea = null;
	private Integer idUsuario = null;
	private Double total = null;
	private Date fechaVenta = null;
	
	public Venta() {
		
	}

	public Integer getIdCompra() {
		return idCompra;
	}

	public void setIdCompra(Integer idCompra) {
		this.idCompra = idCompra;
	}

	public List<LineaVenta> getnLinea() {
		return nLinea;
	}

	public void setnLinea(List<LineaVenta> nLinea) {
		this.nLinea = nLinea;
	}

	public Integer getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
	}

	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

	public Date getFechaVenta() {
		return fechaVenta;
	}

	public void setFechaVenta(Date fechaVenta) {
		this.fechaVenta = fechaVenta;
	}
	

}
