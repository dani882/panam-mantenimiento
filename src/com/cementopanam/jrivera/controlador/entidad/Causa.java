package com.cementopanam.jrivera.controlador.entidad;

public class Causa {

	private int idCausa;
	private String tipoCausa;
	private String idUsuario;
	//TODO Agregar ID Disciplina cuando se haya modificado la BD y relacionado con tabla causa
	/**
	 * @return the idCausa
	 */
	public int getIdCausa() {
		return idCausa;
	}
	/**
	 * @param idCausa the idCausa to set
	 */
	public void setIdCausa(int idCausa) {
		this.idCausa = idCausa;
	}
	/**
	 * @return the tipoCausa
	 */
	public String getTipoCausa() {
		return tipoCausa;
	}
	/**
	 * @param tipoCausa the tipoCausa to set
	 */
	public void setTipoCausa(String tipoCausa) {
		this.tipoCausa = tipoCausa;
	}
	/**
	 * @return the idUsuario
	 */
	public String getIdUsuario() {
		return idUsuario;
	}
	/**
	 * @param idUsuario the idUsuario to set
	 */
	public void setIdUsuario(String idUsuario) {
		this.idUsuario = idUsuario;
	}
	/**
	 * @param idCausa
	 * @param tipoCausa
	 * @param idUsuario
	 */
	public Causa(int idCausa, String tipoCausa, String idUsuario) {
		this.idCausa = idCausa;
		this.tipoCausa = tipoCausa;
		this.idUsuario = idUsuario;
	}
	
	public Causa() {}
}
