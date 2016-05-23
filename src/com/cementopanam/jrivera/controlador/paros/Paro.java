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
	private String causa;
	private String descripcionAdicional;
	private String disciplina;

	/**
	 * @return the codigo
	 */
	public int getCodigo() {
		return codigo;
	}

	/**
	 * @param codigo
	 *            the codigo to set
	 */
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	/**
	 * @return the usuario
	 */
	public String getUsuario() {
		return usuario;
	}

	/**
	 * @param usuario
	 *            the usuario to set
	 */
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	/**
	 * @return the area
	 */
	public String getArea() {
		return area;
	}

	/**
	 * @param area
	 *            the area to set
	 */
	public void setArea(String area) {
		this.area = area;
	}

	/**
	 * @return the subArea
	 */
	public String getSubArea() {
		return subArea;
	}

	/**
	 * @param subArea
	 *            the subArea to set
	 */
	public void setSubArea(String subArea) {
		this.subArea = subArea;
	}

	/**
	 * @return the equipo
	 */
	public String getEquipo() {
		return equipo;
	}

	/**
	 * @param equipo
	 *            the equipo to set
	 */
	public void setEquipo(String equipo) {
		this.equipo = equipo;
	}

	/**
	 * @return the tiempoInicio
	 */
	public String getTiempoInicio() {
		return tiempoInicio;
	}

	/**
	 * @param tiempoInicio
	 *            the tiempoInicio to set
	 */
	public void setTiempoInicio(String tiempoInicio) {
		this.tiempoInicio = tiempoInicio;
	}

	/**
	 * @return the tiempoFin
	 */
	public String getTiempoFin() {
		return tiempoFin;
	}

	/**
	 * @param tiempoFin
	 *            the tiempoFin to set
	 */
	public void setTiempoFin(String tiempoFin) {
		this.tiempoFin = tiempoFin;
	}

	/**
	 * @return the solucion
	 */
	public String getSolucion() {
		return solucion;
	}

	/**
	 * @param solucion
	 *            the solucion to set
	 */
	public void setSolucion(String solucion) {
		this.solucion = solucion;
	}

	/**
	 * @return the causa
	 */
	public String getCausa() {
		return causa;
	}

	/**
	 * @param causa
	 *            the causa to set
	 */
	public void setCausa(String causa) {
		this.causa = causa;
	}

	/**
	 * @return the descripcionAdicional
	 */
	public String getDescripcionAdicional() {
		return descripcionAdicional;
	}

	/**
	 * @param descripcionAdicional
	 *            the descripcionAdicional to set
	 */
	public void setDescripcionAdicional(String descripcionAdicional) {
		this.descripcionAdicional = descripcionAdicional;
	}

	/**
	 * @return the disciplina
	 */
	public String getDisciplina() {
		return disciplina;
	}

	/**
	 * @param disciplina
	 *            the disciplina to set
	 */
	public void setDisciplina(String disciplina) {
		this.disciplina = disciplina;
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
	 * @param causa
	 * @param descripcionAdicional
	 * @param disciplina
	 */
	public Paro(int codigo, String usuario, String area, String subArea, String equipo, String tiempoInicio,
			String tiempoFin, String solucion, String causa, String descripcionAdicional, String disciplina) {

		this.codigo = codigo;
		this.usuario = usuario;
		this.area = area;
		this.subArea = subArea;
		this.equipo = equipo;
		this.tiempoInicio = tiempoInicio;
		this.tiempoFin = tiempoFin;
		this.solucion = solucion;
		this.causa = causa;
		this.descripcionAdicional = descripcionAdicional;
		this.disciplina = disciplina;
	}

	/**
	 * @param tiempoInicio
	 * @param tiempoFin
	 * @param solucion
	 * @param causa
	 * @param descripcionAdicional
	 * @param disciplina
	 */
	public Paro(int codigo, String tiempoInicio, String tiempoFin, String solucion, String causa,
			String descripcionAdicional, String disciplina) {

		this.codigo = codigo;
		this.tiempoInicio = tiempoInicio;
		this.tiempoFin = tiempoFin;
		this.solucion = solucion;
		this.causa = causa;
		this.descripcionAdicional = descripcionAdicional;
		this.disciplina = disciplina;
	}

	/**
	 * Constructor de paros Pendientes a Completados
	 * @param codigo - codigo de la imputacion que se completara
	 * @param tiempoInicio - Tiempo en que inicio el paro
	 * @param tiempoFin - Tiempo en que finalizo el paro
	 * @param solucion - Solucion del paro a completarse
	 */
	public Paro(int codigo, String tiempoInicio, String tiempoFin, String solucion) {
	
		this.codigo = codigo;
		this.tiempoInicio = tiempoInicio;
		this.tiempoFin = tiempoFin;
		this.solucion = solucion;
	}

	public Paro() {}
}
