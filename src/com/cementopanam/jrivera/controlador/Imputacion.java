package com.cementopanam.jrivera.controlador;

public class Imputacion {
	
	private String equipo;
	private String usuario;
	private String disciplina;
	
	private String causa;
	private String causaExtendida;
	
	private String tiempoInicio;
	private String tiempoFin;
	private String estadoParo;
	private String estadoEquipo;
	private String solucion;
	
	/**
	 * @return the equipo
	 */
	public String getEquipo() {
		return equipo;
	}
	/**
	 * @param equipo the equipo to set
	 */
	public void setEquipo(String equipo) {
		this.equipo = equipo;
	}
	/**
	 * @return the usuario
	 */
	public String getUsuario() {
		return usuario;
	}
	/**
	 * @param usuario the usuario to set
	 */
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	/**
	 * @return the disciplina
	 */
	public String getDisciplina() {
		return disciplina;
	}
	/**
	 * @param disciplina the disciplina to set
	 */
	public void setDisciplina(String disciplina) {
		this.disciplina = disciplina;
	}
	/**
	 * @return the causa
	 */
	public String getCausa() {
		return causa;
	}
	/**
	 * @param causa the causa to set
	 */
	public void setCausa(String causa) {
		this.causa = causa;
	}
	/**
	 * @return the causaExtendida
	 */
	public String getCausaExtendida() {
		return causaExtendida;
	}
	/**
	 * @param causaExtendida the causaExtendida to set
	 */
	public void setCausaExtendida(String causaExtendida) {
		this.causaExtendida = causaExtendida;
	}
	/**
	 * @return the tiempoInicio
	 */
	public String getTiempoInicio() {
		return tiempoInicio;
	}
	/**
	 * @param tiempoInicio the tiempoInicio to set
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
	 * @param tiempoFin the tiempoFin to set
	 */
	public void setTiempoFin(String tiempoFin) {
		this.tiempoFin = tiempoFin;
	}
	/**
	 * @return the estadoParo
	 */
	public String getEstadoParo() {
		return estadoParo;
	}
	/**
	 * @param estadoParo the estadoParo to set
	 */
	public void setEstadoParo(String estadoParo) {
		this.estadoParo = estadoParo;
	}
	
	/**
	 * @return the estadoEquipo
	 */
	public String getEstadoEquipo() {
		return estadoEquipo;
	}
	/**
	 * @param estadoEquipo the estadoEquipo to set
	 */
	public void setEstadoEquipo(String estadoEquipo) {
		this.estadoEquipo = estadoEquipo;
	}
	/**
	 * @return the solucion
	 */
	public String getSolucion() {
		return solucion;
	}
	/**
	 * @param solucion the solucion to set
	 */
	public void setSolucion(String solucion) {
		this.solucion = solucion;
	}
	/**
	 * @param equipo
	 * @param usuario
	 * @param disciplina
	 * @param causa
	 * @param causaExtendida
	 * @param tiempoInicio
	 * @param tiempoFin
	 * @param estadoParo
	 * @param estadoEquipo
	 * @param solucion
	 */
	public Imputacion(String equipo, String usuario, String disciplina, String causa, String causaExtendida,
			String tiempoInicio, String tiempoFin, String estadoParo, String estadoEquipo, String solucion) {
		
		this.equipo = equipo;
		this.usuario = usuario;
		this.disciplina = disciplina;
		this.causa = causa;
		this.causaExtendida = causaExtendida;
		this.tiempoInicio = tiempoInicio;
		this.tiempoFin = tiempoFin;
		this.estadoParo = estadoParo;
		this.estadoEquipo = estadoEquipo;
		this.solucion = solucion;
	}
	
	public Imputacion() {}
	
	
}
