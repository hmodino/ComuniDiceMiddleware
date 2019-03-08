package com.rollanddice.comunidice.model;

import java.util.Date;
import java.util.List;

public class Producto extends AbstractValueObject{
	
	private Integer idProducto = null;
	private Integer idCategoria = null;
	private String nombre = null;
	private Double precio = null;
	private String descripcion = null;
	private Date fechaEntrada = null;
	private Integer stock = null;
	private List<Comentario> comentarios = null;
	private String urlImagen = null;
	private Double numeroFavoritos = null;
	private List<Double> valoraciones = null;
	private Double valoracion = null;
	
	public Producto() {
		
	}

	public Integer getIdProducto() {
		return idProducto;
	}

	public void setIdProducto(Integer id) {
		this.idProducto = id;
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

	public Double getPrecio() {
		return precio;
	}

	public void setPrecio(Double precio) {
		this.precio = precio;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Date getFechaEntrada() {
		return fechaEntrada;
	}

	public void setFechaEntrada(Date fechaEntrada) {
		this.fechaEntrada = fechaEntrada;
	}

	public Integer getStock() {
		return stock;
	}

	public void setStock(Integer stock) {
		this.stock = stock;
	}

	public List<Comentario> getComentarios() {
		return comentarios;
	}

	public void setComentarios(List<Comentario> comentarios) {
		this.comentarios = comentarios;
	}

	public String getUrlImagen() {
		return urlImagen;
	}

	public void setUrlImagen(String urlImagen) {
		this.urlImagen = urlImagen;
	}

	public Double getNumeroFavoritos() {
		return numeroFavoritos;
	}

	public void setNumeroFavoritos(Double favoritos) {
		this.numeroFavoritos = favoritos;
	}

	public List<Double> getValoraciones() {
		return valoraciones;
	}

	public void setValoraciones(List<Double> valoraciones) {
		this.valoraciones = valoraciones;
	}

	public Double getValoracion() {
		return valoracion;
	}

	public void setValoracion(Double valoracion) {
		this.valoracion = valoracion;
	}

}
