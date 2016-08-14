package com.cementopanam.jrivera.controlador.usuario;

import java.io.Serializable;
import java.net.InetAddress;
import java.net.UnknownHostException;

import org.apache.log4j.Logger;

public class CapturaUsuario implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6296070502701471258L;
	private static final Logger logger = Logger.getLogger(CapturaUsuario.class);
	public String nombreUsuario;

	/**
	 * Crea el archivo serializado con el nombre de la PC
	 * @return hostaname de la PC
	 */
	public String obtenerNombrePC() {

		String hostname = "Desconocido";

		try {
			InetAddress addr;
			addr = InetAddress.getLocalHost();
			hostname = addr.getHostName();
		} catch (UnknownHostException ex) {
			logger.error("El Nombre de Host no pudo ser resuelto: " + ex.getMessage());
		}
		return hostname;
	}
}