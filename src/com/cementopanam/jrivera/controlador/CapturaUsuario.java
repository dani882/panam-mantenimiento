package com.cementopanam.jrivera.controlador;

import java.io.Serializable;
import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.swing.JOptionPane;

public class CapturaUsuario implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6296070502701471258L;
	public String nombreUsuario;
	
	/**
	 * Crea el archivo serializado con el nombre de la PC
	 * @return hostaname de la PC
	 */
	public String obtenerNombrePC() {
		
		String hostname = "Unknown";

		try
		{
		    InetAddress addr;
		    addr = InetAddress.getLocalHost();
		    hostname = addr.getHostName();
		}
		catch (UnknownHostException ex)
		{
		    JOptionPane.showMessageDialog(null, "El Hostname no pudo ser resuelto", ex.getClass().toString(),
					JOptionPane.ERROR_MESSAGE);
		}
		return hostname;
	}
}