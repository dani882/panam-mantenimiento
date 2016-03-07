package com.cementopanam.jrivera.controlador;

public class NombreEquipo {

	private String codigo;
	private String nombre;
	/**
	 * @return the codigo
	 */
	public String getCodigo() {
		return codigo;
	}
	/**
	 * @param codigo the codigo to set
	 */
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}
	/**
	 * @param nombre the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public NombreEquipo(String codigo, String nombre) {
		this.codigo = codigo;
		this.nombre = nombre;
	}
}
