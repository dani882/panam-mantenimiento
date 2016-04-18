package com.cementopanam.jrivera.controlador.entidad;

public class SubArea {

	private int idSubArea;
	private String nombreSubArea;
	private int idArea;
	/**
	 * @return the idSubArea
	 */
	public int getIdSubArea() {
		return idSubArea;
	}
	/**
	 * @param idSubArea the idSubArea to set
	 */
	public void setIdSubArea(int idSubArea) {
		this.idSubArea = idSubArea;
	}
	/**
	 * @return the nombreSubArea
	 */
	public String getNombreSubArea() {
		return nombreSubArea;
	}
	/**
	 * @param nombreSubArea the nombreSubArea to set
	 */
	public void setNombreSubArea(String nombreSubArea) {
		this.nombreSubArea = nombreSubArea;
	}
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
	 * @param idSubArea
	 * @param nombreSubArea
	 * @param idArea
	 */
	public SubArea(int idSubArea, String nombreSubArea, int idArea) {
		this.idSubArea = idSubArea;
		this.nombreSubArea = nombreSubArea;
		this.idArea = idArea;
	}
	
	public SubArea() {}
}
