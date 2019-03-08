package com.rollanddice.comunidice.model;


public class LineaCompra extends AbstractValueObject{
	
	private Integer nLinea = null;
	private Integer idCompra = null;
	private Producto producto = null;
	private Integer idProducto = null;
	private Double precioUnitario = null;
	private Integer cantidad = null;
	private Double descuento = null;
	private Double precioTotal = null;
	
	public LineaCompra() {
		
	}

	public Integer getnLinea() {
		return nLinea;
	}

	public void setnLinea(Integer nLinea) {
		this.nLinea = nLinea;
	}

	public Integer getIdCompra() {
		return idCompra;
	}

	public void setIdCompra(Integer idCompra) {
		this.idCompra = idCompra;
	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}
	
	public Integer getIdProducto() {
		return idProducto;
	}

	public void setIdProducto(Integer idProducto) {
		this.idProducto = idProducto;
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

	public Double getDescuento() {
		return descuento;
	}

	public void setDescuento(Double descuento) {
		this.descuento = descuento;
	}

	public Double getPrecioTotal() {
		return precioTotal;
	}

	public void setPrecioTotal(Double precioTotal) {
		this.precioTotal = precioTotal;
	}

}
