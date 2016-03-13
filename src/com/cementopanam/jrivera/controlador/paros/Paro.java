package com.cementopanam.jrivera.controlador.paros;

public class Paro {

	private int codigo;
	private String usuario;
	private String area;
	private String subArea;
	private String equipo;
	private String tiempoInicio;
	private String tiempoFin;
	private String solucion;
	
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public String getSubArea() {
		return subArea;
	}
	public void setSubArea(String subArea) {
		this.subArea = subArea;
	}
	public String getEquipo() {
		return equipo;
	}
	public void setEquipo(String equipo) {
		this.equipo = equipo;
	}
	public String getTiempoInicio() {
		return tiempoInicio;
	}
	public void setTiempoInicio(String tiempoInicio) {
		this.tiempoInicio = tiempoInicio;
	}
	public String getTiempoFin() {
		return tiempoFin;
	}
	public void setTiempoFin(String tiempoFin) {
		this.tiempoFin = tiempoFin;
	}
	public String getSolucion() {
		return solucion;
	}
	public void setSolucion(String solucion) {
		this.solucion = solucion;
	}
	/**
	 * @param codigo
	 * @param usuario
	 * @param area
	 * @param subArea
	 * @param equipo
	 * @param tiempoInicio
	 * @param tiempoFin
	 * @param solucion
	 */
	public Paro(int codigo, String usuario, String area, String subArea, String equipo, String tiempoInicio,
			String tiempoFin, String solucion) {
		super();
		this.codigo = codigo;
		this.usuario = usuario;
		this.area = area;
		this.subArea = subArea;
		this.equipo = equipo;
		this.tiempoInicio = tiempoInicio;
		this.tiempoFin = tiempoFin;
		this.solucion = solucion;
	}
	
	public Paro() {}
}
