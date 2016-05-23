package com.cementopanam.jrivera.controlador.entidad;

public class Causa {

	private int idCausa;
	private String tipoCausa;
	private String idUsuario;
	private String idDisciplina;
	
	/**
	 * @return the idDisciplina
	 */
	public String getIdDisciplina() {
		return idDisciplina;
	}
	/**
	 * @param idDisciplina the idDisciplina to set
	 */
	public void setIdDisciplina(String idDisciplina) {
		this.idDisciplina = idDisciplina;
	}
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
	 * @param idDisciplina
	 */
	public Causa(int idCausa, String tipoCausa, String idUsuario, String idDisciplina) {
		this.idCausa = idCausa;
		this.tipoCausa = tipoCausa;
		this.idUsuario = idUsuario;
		this.idDisciplina = idDisciplina;
	}
	
	/**
	 * @param tipoCausa
	 * @param idDisciplina
	 */
	public Causa(String tipoCausa, String idDisciplina) {
		this.tipoCausa = tipoCausa;
		this.idDisciplina = idDisciplina;
	}
	
	public Causa() {}
}
