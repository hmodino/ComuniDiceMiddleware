package com.rollanddice.comunidice.model;

import java.util.Date;
import java.util.List;

public class Usuario extends AbstractValueObject{
	
	private Integer idUsuario = null;
	private String nombreUsuario = null;
	private String nombre = null;
	private String apellido1 = null;
	private String apellido2 = null;
	private String email = null;
	private String contrasenha = null;
	private String descripcion = null;
	private Direccion idDireccion = null;
	private List<Amigo> amigos = null;
	private List<Favorito> favoritos = null;
	private List<Favorito> valoraciones = null;
	private List<Mensaje> mensajes = null;
	private List<Compra> compras = null;
	private List<Venta> ventas = null;
	private List<Comentario> comentarios = null;
	private Date fechaAlta = null;
	private String telefono = null;
	
	public Usuario() {
		
	}

	public Integer getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getNombreUsuario() {
		return nombreUsuario;
	}

	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido1() {
		return apellido1;
	}

	public void setApellido1(String apellido1) {
		this.apellido1 = apellido1;
	}

	public String getApellido2() {
		return apellido2;
	}

	public void setApellido2(String apellido2) {
		this.apellido2 = apellido2;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getContrasenha() {
		return contrasenha;
	}

	public void setContrasenha(String contrasenha) {
		this.contrasenha = contrasenha;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Direccion getIdDireccion() {
		return idDireccion;
	}

	public void setIdDireccion(Direccion idDireccion) {
		this.idDireccion = idDireccion;
	}

	public List<Amigo> getAmigos() {
		return amigos;
	}

	public void setAmigos(List<Amigo> amigos) {
		this.amigos = amigos;
	}

	public List<Favorito> getFavoritos() {
		return favoritos;
	}

	public void setFavoritos(List<Favorito> favoritos) {
		this.favoritos = favoritos;
	}

	public List<Favorito> getValoraciones() {
		return valoraciones;
	}

	public void setValoraciones(List<Favorito> valoraciones) {
		this.valoraciones = valoraciones;
	}

	public List<Mensaje> getMensajes() {
		return mensajes;
	}

	public void setMensajes(List<Mensaje> mensajes) {
		this.mensajes = mensajes;
	}

	public List<Compra> getCompras() {
		return compras;
	}

	public void setCompras(List<Compra> compras) {
		this.compras = compras;
	}

	public List<Venta> getVentas() {
		return ventas;
	}

	public void setVentas(List<Venta> ventas) {
		this.ventas = ventas;
	}

	public List<Comentario> getComentarios() {
		return comentarios;
	}

	public void setComentarios(List<Comentario> comentarios) {
		this.comentarios = comentarios;
	}

	public Date getFechaAlta() {
		return fechaAlta;
	}

	public void setFechaAlta(Date fechaAlta) {
		this.fechaAlta = fechaAlta;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	
	@Override
	public boolean equals(Object o) {
		
		if(o==null || !(o instanceof Usuario)) {
			return false;
		}
		if(this.getIdUsuario()== null || this.getNombreUsuario()==null || this.getEmail()==null) {
			return false;
		}
		Usuario u = (Usuario) o;
		if(!this.getIdUsuario().equals(u.getIdUsuario()) || !this.getNombreUsuario().equals(u.getNombreUsuario()) || !this.getEmail().equals(u.getEmail())) {
			return false;
		}
		return true;
	}

	@Override
	public int hashCode() {
		StringBuilder s = new StringBuilder();
		if(idUsuario!=null && nombreUsuario!=null && email!=null) {
			s.append(idUsuario);
			s.append(nombreUsuario);
			s.append(email);
			return s.hashCode();
		}
		else {
			s.append("null");
			return s.hashCode();
		}
	}
}
