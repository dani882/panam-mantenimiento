package com.cementopanam.jrivera.controlador.listadoEquipos;

public class NombreEquipo {

	private String codigoEquipo;
	private String nombreEquipo;
	private String nombreArea;
	private String nombreSubArea;

	/**
	 * @return the codigoEquipo
	 */
	public String getCodigoEquipo() {
		return codigoEquipo;
	}

	/**
	 * @param codigoEquipo
	 *            the codigoEquipo to set
	 */
	public void setCodigoEquipo(String codigoEquipo) {
		this.codigoEquipo = codigoEquipo;
	}

	/**
	 * @return the nombreEquipo
	 */
	public String getNombreEquipo() {
		return nombreEquipo;
	}

	/**
	 * @param nombreEquipo
	 *            the nombreEquipo to set
	 */
	public void setNombreEquipo(String nombreEquipo) {
		this.nombreEquipo = nombreEquipo;
	}

	/**
	 * @return the nombreArea
	 */
	public String getNombreArea() {
		return nombreArea;
	}

	/**
	 * @param nombreArea
	 *            the nombreArea to set
	 */
	public void setNombreArea(String nombreArea) {
		this.nombreArea = nombreArea;
	}

	/**
	 * @return the nombreSubArea
	 */
	public String getNombreSubArea() {
		return nombreSubArea;
	}

	/**
	 * @param nombreSubArea
	 *            the nombreSubArea to set
	 */
	public void setNombreSubArea(String nombreSubArea) {
		this.nombreSubArea = nombreSubArea;
	}

	/**
	 * @param codigoEquipo
	 * @param nombreEquipo
	 * @param nombreArea
	 * @param nombreSubArea
	 */
	public NombreEquipo(String codigoEquipo, String nombreEquipo, String nombreArea, String nombreSubArea) {
		super();
		this.codigoEquipo = codigoEquipo;
		this.nombreEquipo = nombreEquipo;
		this.nombreArea = nombreArea;
		this.nombreSubArea = nombreSubArea;
	}
}