package com.cementopanam.jrivera.controlador.entidad;

public class Disciplina {

	private int idDisciplina;
	private String nombreDisciplina;

	/**
	 * @return the idDisciplina
	 */
	public int getIdDisciplina() {
		return idDisciplina;
	}

	/**
	 * @param idDisciplina
	 *            the idDisciplina to set
	 */
	public void setIdDisciplina(int idDisciplina) {
		this.idDisciplina = idDisciplina;
	}

	/**
	 * @return the nombreDisciplina
	 */
	public String getNombreDisciplina() {
		return nombreDisciplina;
	}

	/**
	 * @param nombreDisciplina
	 *            the nombreDisciplina to set
	 */
	public void setNombreDisciplina(String nombreDisciplina) {
		this.nombreDisciplina = nombreDisciplina;
	}

	/**
	 * @param idDisciplina
	 * @param nombreDisciplina
	 */
	public Disciplina(int idDisciplina, String nombreDisciplina) {
		this.idDisciplina = idDisciplina;
		this.nombreDisciplina = nombreDisciplina;
	}

	public Disciplina() {
	}
}
