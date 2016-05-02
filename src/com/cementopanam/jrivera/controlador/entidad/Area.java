package com.cementopanam.jrivera.controlador.entidad;

public class Area {

	private int idArea;
	private String nombreArea;
	/**
	 * @return the idArea
	 */
	public int getIdArea() {
		return idArea;
	}
	/**
	 * @param idArea the idArea to set
	 */
	public void setIdArea(int idArea) {
		this.idArea = idArea;
	}
	/**
	 * @return the nombreArea
	 */
	public String getNombreArea() {
		return nombreArea;
	}
	/**
	 * @param nombreArea the nombreArea to set
	 */
	public void setNombreArea(String nombreArea) {
		this.nombreArea = nombreArea;
	}
	/**
	 * @param idArea
	 * @param nombreArea
	 */
	public Area(int idArea, String nombreArea) {
		this.idArea = idArea;
		this.nombreArea = nombreArea;
	}
	
	/**
	 * @param nombreArea
	 */
	public Area(String nombreArea) {
		this.nombreArea = nombreArea;
	}
	
	public Area() {}
	
}
