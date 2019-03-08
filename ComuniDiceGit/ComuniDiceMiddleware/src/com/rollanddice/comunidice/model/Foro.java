package com.rollanddice.comunidice.model;

import java.util.Date;
import java.util.List;

public class Foro extends AbstractValueObject{
	
	private Integer idForo = null;
	private String nombre = null;
	private Integer idUsuario = null;
	private Integer categoria = null;
	private List<Comentario> comentarios = null;
	private Date fechaCreacion = null;
	
	public Foro() {
		
	}

	public Integer getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
	}

	public Integer getIdForo() {
		return idForo;
	}

	public void setIdForo(Integer idForo) {
		this.idForo = idForo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Integer getCategoria() {
		return categoria;
	}

	public void setCategoria(Integer categoria) {
		this.categoria = categoria;
	}

	public List<Comentario> getComentarios() {
		return comentarios;
	}

	public void setComentarios(List<Comentario> comentarios) {
		this.comentarios = comentarios;
	}

	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}
	

}
