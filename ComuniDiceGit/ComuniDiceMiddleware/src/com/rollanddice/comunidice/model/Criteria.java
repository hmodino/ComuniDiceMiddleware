package com.rollanddice.comunidice.model;

import java.util.Date;

public class Criteria {
	

	private Integer idCategoria = null;
	private String nombre = null;
	private Double precioDesde = null;
	private Double precioHasta = null;
	private Date fechaMaxima = null;
	private Date fechaMinima = null;
	private Integer numeroFavoritos = null;
	private Integer valoracion = null;
	private Integer idVendedor = null;
	private Integer tipoVendedor = null;
	private Date anhoPublicacionMaximo = null;
	private Date anhoPublicacionMinimo = null;
	private Integer formato = null;
	private Integer tipoTapa = null;
	
	public Criteria() {
		
	}

	public Integer getIdCategoria() {
		return idCategoria;
	}

	public void setIdCategoria(Integer idCategoria) {
		this.idCategoria = idCategoria;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Double getPrecioDesde() {
		return precioDesde;
	}

	public void setPrecioDesde(Double precioDesde) {
		this.precioDesde = precioDesde;
	}

	public Double getPrecioHasta() {
		return precioHasta;
	}

	public void setPrecioHasta(Double precioHasta) {
		this.precioHasta = precioHasta;
	}

	public Date getFechaMaxima() {
		return fechaMaxima;
	}

	public void setFechaMaxima(Date fechaMaxima) {
		this.fechaMaxima = fechaMaxima;
	}

	public Date getFechaMinima() {
		return fechaMinima;
	}

	public void setFechaMinima(Date fechaMinima) {
		this.fechaMinima = fechaMinima;
	}

	public Integer getNumeroFavoritos() {
		return numeroFavoritos;
	}

	public void setNumeroFavoritos(Integer numeroFavoritos) {
		this.numeroFavoritos = numeroFavoritos;
	}

	public Integer getValoracion() {
		return valoracion;
	}

	public void setValoracion(Integer valoracion) {
		this.valoracion = valoracion;
	}

	public Integer getIdVendedor() {
		return idVendedor;
	}

	public void setIdVendedor(Integer idVendedor) {
		this.idVendedor = idVendedor;
	}

	public Integer getTipoVendedor() {
		return tipoVendedor;
	}

	public void setTipoVendedor(Integer tipoVendedor) {
		this.tipoVendedor = tipoVendedor;
	}

	public Date getAnhoPublicacionMaximo() {
		return anhoPublicacionMaximo;
	}

	public void setAnhoPublicacionMaximo(Date anhoPublicacionMaximo) {
		this.anhoPublicacionMaximo = anhoPublicacionMaximo;
	}

	public Date getAnhoPublicacionMinimo() {
		return anhoPublicacionMinimo;
	}

	public void setAnhoPublicacionMinimo(Date anhoPublicacionMinimo) {
		this.anhoPublicacionMinimo = anhoPublicacionMinimo;
	}

	public Integer getFormato() {
		return formato;
	}

	public void setFormato(Integer formato) {
		this.formato = formato;
	}

	public Integer  getTipoTapa() {
		return tipoTapa;
	}

	public void setTipoTapa(Integer tipoTapa) {
		this.tipoTapa = tipoTapa;
	}
	
	

}
