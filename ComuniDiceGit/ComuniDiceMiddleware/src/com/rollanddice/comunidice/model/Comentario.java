package com.rollanddice.comunidice.model;

import java.util.Date;

public class Comentario extends AbstractValueObject{
	
	private Integer idComentario = null;
	private Integer usuario = null;
	private Integer foro = null;
	private Integer producto = null;
	private String contenido = null;
	private Date fecha = null;
	private String nombreUsuario = null;
	
	public Comentario() {
		
	}

	public Integer getIdComentario() {
		return idComentario;
	}

	public void setIdComentario(Integer idComentario) {
		this.idComentario = idComentario;
	}

	public Integer getUsuario() {
		return usuario;
	}

	public void setUsuario(Integer usuario) {
		this.usuario = usuario;
	}

	public Integer getForo() {
		return foro;
	}

	public void setForo(Integer foro) {
		this.foro = foro;
	}

	public Integer getProducto() {
		return producto;
	}

	public void setProducto(Integer producto) {
		this.producto = producto;
	}

	public String getContenido() {
		return contenido;
	}

	public void setContenido(String contenido) {
		this.contenido = contenido;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getNombreUsuario() {
		return nombreUsuario;
	}

	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}

}
