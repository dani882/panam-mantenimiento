package com.cementopanam.jrivera.controlador;

/**
 * @author jrivera
 *
 */
public class Usuario {

	private int codEmpleado;
	private int tipoUsuario;
	private String nombreEmpleado;
	private String apellidoEmpleado;
	private String nombreUsuario;
	private String password;
	
	/**
	 * @return the codEmpleado
	 */
	public int getCodEmpleado() {
		return codEmpleado;
	}
	/**
	 * @param codEmpleado the codEmpleado to set
	 */
	public void setCodEmpleado(int codEmpleado) {
		this.codEmpleado = codEmpleado;
	}
	/**
	 * @return the nombreEmpleado
	 */
	public String getNombreEmpleado() {
		return nombreEmpleado;
	}
	/**
	 * @param nombreEmpleado the nombreEmpleado to set
	 */
	public void setNombreEmpleado(String nombreEmpleado) {
		this.nombreEmpleado = nombreEmpleado;
	}
	/**
	 * @return the apellidoEmpleado
	 */
	public String getApellidoEmpleado() {
		return apellidoEmpleado;
	}
	/**
	 * @param apellidoEmpleado the apellidoEmpleado to set
	 */
	public void setApellidoEmpleado(String apellidoEmpleado) {
		this.apellidoEmpleado = apellidoEmpleado;
	}
	/**
	 * @return the nombreUsuario
	 */
	public String getNombreUsuario() {
		return nombreUsuario;
	}
	/**
	 * @param nombreUsuario the nombreUsuario to set
	 */
	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}
	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * @return the tipoUsuario
	 */
	public int getTipoUsuario() {
		return tipoUsuario;
	}
	/**
	 * @param tipoUsuario the tipoUsuario to set
	 */
	public void setTipoUsuario(int tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}

	
	/**
	 * @param codEmpleado
	 * @param nombreEmpleado
	 * @param apellidoEmpleado
	 * @param nombreUsuario
	 * @param password
	 * @param tipoUsuario
	 */
	public Usuario(int codEmpleado, String nombreEmpleado, String apellidoEmpleado, String nombreUsuario,
			String password, int tipoUsuario) {

		this.codEmpleado = codEmpleado;
		this.nombreEmpleado = nombreEmpleado;
		this.apellidoEmpleado = apellidoEmpleado;
		this.nombreUsuario = nombreUsuario;
		this.password = password;
		this.tipoUsuario = tipoUsuario;
	}
}
