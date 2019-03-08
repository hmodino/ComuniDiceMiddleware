package com.rollanddice.comunidice.model;

public class Juego extends Producto{

	private Integer idVendedor = null;
	private Integer tipoVendedor = null;
	private Integer paginas = null;
	private Integer anhoPublicacion = null;
	private Integer formato = null;
	private Integer tipoTapa = null;
	
	public Juego() {
		
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

	public Integer getPaginas() {
		return paginas;
	}

	public void setPaginas(Integer paginas) {
		this.paginas = paginas;
	}

	public Integer getAnhoPublicacion() {
		return anhoPublicacion;
	}

	public void setAnhoPublicacion(Integer anhoPublicacion) {
		this.anhoPublicacion = anhoPublicacion;
	}

	public Integer getFormato() {
		return formato;
	}

	public void setFormato(Integer formato) {
		this.formato = formato;
	}

	public Integer getTipoTapa() {
		return tipoTapa;
	}

	public void setTipoTapa(Integer tipoTapa) {
		this.tipoTapa = tipoTapa;
	}
	
}
