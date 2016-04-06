package com.cementopanam.jrivera.controlador;

public class Imputacion {

	private String usuario;
	private String equipo;
	private String subArea;
	private String area;
	private String tiempoInicio;
	private String tiempoFinal;

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getEquipo() {
		return equipo;
	}

	public void setEquipo(String equipo) {
		this.equipo = equipo;
	}

	public String getSubArea() {
		return subArea;
	}

	public void setSubArea(String subArea) {
		this.subArea = subArea;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getTiempoInicio() {
		return tiempoInicio;
	}

	public void setTiempoInicio(String tiempoInicio) {
		this.tiempoInicio = tiempoInicio;
	}

	public String getTiempoFinal() {
		return tiempoFinal;
	}

	public void setTiempoFinal(String tiempoFinal) {
		this.tiempoFinal = tiempoFinal;
	}

	public Imputacion() {
	}

	public Imputacion(String usuario, String equipo, String subArea, String area, String tiempoInicio,
			String tiempoFinal) {

		this.usuario = usuario;
		this.equipo = equipo;
		this.subArea = subArea;
		this.area = area;
		this.tiempoInicio = tiempoInicio;
		this.tiempoFinal = tiempoFinal;
	}

}
