package com.cementopanam.jrivera.controlador.usuario;

/**
 * @author jrivera
 *
 */
public class Usuario {

	private String codEmpleado;
	private int tipoUsuario;
	private String nombreEmpleado;
	private String apellidoEmpleado;
	private String nombreUsuario;
	private String password;
	private String estadoUsuario;

	/**
	 * @return the codEmpleado
	 */
	public String getCodEmpleado() {
		return codEmpleado;
	}

	/**
	 * @param codEmpleado
	 *            the codEmpleado to set
	 */
	public void setCodEmpleado(String codEmpleado) {
		this.codEmpleado = codEmpleado;
	}

	/**
	 * @return the nombreEmpleado
	 */
	public String getNombreEmpleado() {
		return nombreEmpleado;
	}

	/**
	 * @param nombreEmpleado
	 *            the nombreEmpleado to set
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
	 * @param apellidoEmpleado
	 *            the apellidoEmpleado to set
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
	 * @param nombreUsuario
	 *            the nombreUsuario to set
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
	 * @param password
	 *            the password to set
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
	 * @param tipoUsuario
	 *            the tipoUsuario to set
	 */
	public void setTipoUsuario(int tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}

	/**
	 * @return the estadoUsuario
	 */
	public String getEstadoUsuario() {
		return estadoUsuario;
	}

	/**
	 * @param estadoUsuario
	 *            the estadoUsuario to set
	 */
	public void setEstadoUsuario(String estadoUsuario) {
		this.estadoUsuario = estadoUsuario;
	}

	/**
	 * Constructor para agregar nuevo usuario
	 * 
	 * @param codEmpleado
	 * @param nombreEmpleado
	 * @param apellidoEmpleado
	 * @param nombreUsuario
	 * @param password
	 * @param tipoUsuario
	 */
	public Usuario(String codEmpleado, String nombreEmpleado, String apellidoEmpleado, String nombreUsuario,
			String password, int tipoUsuario) {

		this.codEmpleado = codEmpleado;
		this.nombreEmpleado = nombreEmpleado;
		this.apellidoEmpleado = apellidoEmpleado;
		this.nombreUsuario = nombreUsuario;
		this.password = password;
		this.tipoUsuario = tipoUsuario;
	}

	/**
	 * Contructor para modificar informacion del Usuario
	 * 
	 * @param codEmpleado
	 * @param tipoUsuario
	 * @param nombreUsuario
	 * @param password
	 * @param estadoUsuario
	 */
	public Usuario(String codEmpleado, int tipoUsuario, String nombreUsuario, String password, String estadoUsuario) {

		this.codEmpleado = codEmpleado;
		this.tipoUsuario = tipoUsuario;
		this.nombreUsuario = nombreUsuario;
		this.password = password;
		this.estadoUsuario = estadoUsuario;
	}

	/**
	 * 
	 */
	public Usuario() {
	}

}