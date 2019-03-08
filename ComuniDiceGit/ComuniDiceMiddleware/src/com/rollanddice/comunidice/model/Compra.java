package com.rollanddice.comunidice.model;

import java.util.Date;
import java.util.List;


public class Compra extends AbstractValueObject{
	
	private Integer idCompra = null;
	private List<LineaCompra> nLinea = null;
	private Integer idUsuario = null;
	private Double subtotal = null;
	private Double gastosEnvio = null;
	private Double total = null;
	private Integer modoPago = null;
	private Date fechaCompra = null;
	
	public Compra() {
		
	}

	public Integer getIdCompra() {
		return idCompra;
	}

	public void setIdCompra(Integer idCompra) {
		this.idCompra = idCompra;
	}

	public List<LineaCompra> getnLinea() {
		return nLinea;
	}

	public void setnLinea(List<LineaCompra> nLinea) {
		this.nLinea = nLinea;
	}

	public Integer getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
	}

	public Double getSubtotal() {
		return subtotal;
	}

	public void setSubtotal(Double subtotal) {
		this.subtotal = subtotal;
	}

	public Double getGastosEnvio() {
		return gastosEnvio;
	}

	public void setGastosEnvio(Double gastosEnvio) {
		this.gastosEnvio = gastosEnvio;
	}

	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

	public Integer getModoPago() {
		return modoPago;
	}

	public void setModoPago(Integer modoPago) {
		this.modoPago = modoPago;
	}

	public Date getFechaCompra() {
		return fechaCompra;
	}

	public void setFechaCompra(Date fechaCompra) {
		this.fechaCompra = fechaCompra;
	}

}
