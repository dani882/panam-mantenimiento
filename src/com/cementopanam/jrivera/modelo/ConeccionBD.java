package com.cementopanam.jrivera.modelo;

/**
 * Entidad para manipular la Base de Datos
 * @author Jesus Rivera
 * @version 1.0
 */
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import javax.swing.JOptionPane;

public class ConeccionBD {

	private Connection con = null;

	private static ConeccionBD cbd = new ConeccionBD();

	// Hace que el constructor no pueda ser instanciado
	private ConeccionBD() {
	}

	// Obtiene el unico Objeto Disponible
	public static ConeccionBD getInstance() {
		return cbd;
	}

	/**
	 * Conecta a la base de datos
	 * 
	 * @return Connection la conexion de la base de datos
	 * @throws SQLException
	 */
	public Connection conectarABaseDatos() throws SQLException {

		try (InputStream in = getClass().getResourceAsStream("/properties/db.properties");) {
			// Carga las propiedades del archivo
			Properties pros = new Properties();
			pros.load(in);

			// Asigna los parametros para la Base de Datos
			String url = pros.getProperty("url");
			String usuario = pros.getProperty("usuario");
			String password = pros.getProperty("password");
			String driverBD = pros.getProperty("driverBD");
			;

			// Crea la conexion a la Base de Datos
			Class.forName(driverBD).newInstance();
			con = DriverManager.getConnection(url, usuario, password);
			con.setAutoCommit(false);
		}

		catch (IOException | ClassNotFoundException | InstantiationException | IllegalAccessException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), e.getClass().toString(), JOptionPane.ERROR_MESSAGE);
		}

		return con;
	}

	/**
	 * Cierra la conexion de la base de datos
	 * 
	 * @throws SQLException
	 */
	public void cerrarConexion() throws SQLException {

		if (verificarConexion() == true) {
			con.close();
			System.out.println("Desconectado");
		} else {
			System.out.println("Conexion abierta");
		}
	}

	/**
	 * Verifica si la conexion sigue abierta
	 */
	public boolean verificarConexion() {

		if (con != null) {
			return true;
		} else {
			return false;
		}
	}
}