package com.rollanddice.comunidice.model;

import java.util.Date;

public class Mensaje extends AbstractValueObject{
	
	private Integer idMensaje = null;
	private Integer usuarioEmisor = null;
	private Integer usuarioReceptor = null;
	private String contenido = null;
	private Date fechaHora = null;
	private String nombre = null;
	
	public Mensaje() {
		
	}

	public Integer getIdMensaje() {
		return idMensaje;
	}

	public void setIdMensaje(Integer idMensaje) {
		this.idMensaje = idMensaje;
	}

	public Integer getUsuarioEmisor() {
		return usuarioEmisor;
	}

	public void setUsuarioEmisor(Integer usuarioEmisor) {
		this.usuarioEmisor = usuarioEmisor;
	}

	public void setUsuarioReceptor(Integer usuarioReceptor) {
		this.usuarioReceptor = usuarioReceptor;
	}

	public Integer getUsuarioReceptor() {
		return usuarioReceptor;
	}
	public String getContenido() {
		return contenido;
	}

	public void setContenido(String contenido) {
		this.contenido = contenido;
	}

	public Date getFechaHora() {
		return fechaHora;
	}

	public void setFechaHora(Date fechaHora) {
		this.fechaHora = fechaHora;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
}
