package com.rollanddice.comunidice.model;

public class Favorito extends AbstractValueObject{

	private Integer producto = null;
	private Integer usuario = null;
	private Boolean favorito = null;
	private Double valoracion = null;
	
	public Favorito() {
		
	}

	public Integer getProducto() {
		return producto;
	}

	public void setProducto(Integer producto) {
		this.producto = producto;
	}

	public Integer getUsuario() {
		return usuario;
	}

	public void setUsuario(Integer usuario) {
		this.usuario = usuario;
	}

	public Boolean getFavorito() {
		return favorito;
	}

	public void setFavorito(Boolean favorito) {
		this.favorito = favorito;
	}

	public Double getValoracion() {
		return valoracion;
	}

	public void setValoracion(Double valoracion) {
		this.valoracion = valoracion;
	}
	
}
