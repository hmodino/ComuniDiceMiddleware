package com.rollanddice.comunidice.model;

public class LineaVenta extends AbstractValueObject{

	private Integer nLinea = null;
	private Integer idVenta = null;
	private Integer producto = null;
	private Double precioUnitario = null;
	private Integer cantidad = null;
	private Double precioTotal = null;
	private Double descuento = null;
	
	public LineaVenta() {
		
	}

	public Integer getnLinea() {
		return nLinea;
	}

	public void setnLinea(Integer nLinea) {
		this.nLinea = nLinea;
	}

	public Integer getIdVenta() {
		return idVenta;
	}

	public void setIdVenta(Integer idVenta) {
		this.idVenta = idVenta;
	}

	public Integer getProducto() {
		return producto;
	}

	public void setProducto(Integer producto) {
		this.producto = producto;
	}

	public Double getPrecioUnitario() {
		return precioUnitario;
	}

	public void setPrecioUnitario(Double precioUnitario) {
		this.precioUnitario = precioUnitario;
	}

	public Integer getCantidad() {
		return cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}

	public Double getPrecioTotal() {
		return precioTotal;
	}

	public void setPrecioTotal(Double precioTotal) {
		this.precioTotal = precioTotal;
	}

	public Double getDescuento() {
		return descuento;
	}

	public void setDescuento(Double descuento) {
		this.descuento = descuento;
	}
	
}
