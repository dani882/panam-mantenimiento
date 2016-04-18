package com.cementopanam.jrivera.controlador.entidad;

public class Equipo {

	private int idEquipo;
	private String codEquipo;
	private String nombreEquipo;
	private int idSubArea;
	/**
	 * @return the idEquipo
	 */
	public int getIdEquipo() {
		return idEquipo;
	}
	/**
	 * @param idEquipo the idEquipo to set
	 */
	public void setIdEquipo(int idEquipo) {
		this.idEquipo = idEquipo;
	}
	/**
	 * @return the codEquipo
	 */
	public String getCodEquipo() {
		return codEquipo;
	}
	/**
	 * @param codEquipo the codEquipo to set
	 */
	public void setCodEquipo(String codEquipo) {
		this.codEquipo = codEquipo;
	}
	/**
	 * @return the nombreEquipo
	 */
	public String getNombreEquipo() {
		return nombreEquipo;
	}
	/**
	 * @param nombreEquipo the nombreEquipo to set
	 */
	public void setNombreEquipo(String nombreEquipo) {
		this.nombreEquipo = nombreEquipo;
	}
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
	 * @param idEquipo
	 * @param codEquipo
	 * @param nombreEquipo
	 * @param idSubArea
	 */
	public Equipo(int idEquipo, String codEquipo, String nombreEquipo, int idSubArea) {
		this.idEquipo = idEquipo;
		this.codEquipo = codEquipo;
		this.nombreEquipo = nombreEquipo;
		this.idSubArea = idSubArea;
	}
	
	public Equipo() {}
}
