package com.cementopanam.jrivera.controlador;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JOptionPane;

import org.apache.log4j.Logger;

import com.cementopanam.jrivera.controlador.usuario.CapturaUsuario;
import com.cementopanam.jrivera.modelo.ConeccionBD;

public class ManipulacionDatos {

	private static final Logger logger = Logger.getLogger(ManipulacionDatos.class);
	private ConeccionBD cbd;
	private Connection con = null;
	private CallableStatement cs = null;
	private CapturaUsuario captura = new CapturaUsuario();

	public ManipulacionDatos() {

		cbd = ConeccionBD.getInstance();

		if (!cbd.verificarConexion()) {
			try {
				cbd.conectarABaseDatos();
			} catch (Exception e) {
				logger.error("Usuario en PC: " + captura.obtenerNombrePC() + ". Exception: " + e.toString());
				JOptionPane.showMessageDialog(null, e.getMessage(), e.getClass().toString(), JOptionPane.ERROR_MESSAGE);
				return;
			}
		}
	}

	/**
	 * Conecta a la Base de Datos
	 * 
	 * @return la conexion a la base de datos
	 */
	public Connection obtenerConexion() {

		try {

			cbd = ConeccionBD.getInstance();
			con = cbd.conectarABaseDatos();
		} catch (Exception e) {
			logger.error("Usuario en PC: " + captura.obtenerNombrePC() + ". Exception: " + e.toString());
			JOptionPane.showMessageDialog(null, e.getMessage(), e.getClass().toString(), JOptionPane.ERROR_MESSAGE);
			JOptionPane.showMessageDialog(null, "Se va a cerrar la aplicacion");
			System.exit(0);
		}
		return con;
	}

	/**
	 * Agrega elementos a los comboBox
	 * 
	 * @param sentencia
	 *            - condicion para saber que elemento para que comboBox rellenar
	 * @param indice
	 *            - indice solicitado por un elemento
	 * @return ResultSet de elementos para el comboBox
	 */
	public ResultSet rellenarCombo(String sentencia, String indice) throws SQLException {

		try {

			con = cbd.conectarABaseDatos();
			cs = con.prepareCall("{call sp_rellenar_comboBox(?,?)}");

			cs.setString(1, sentencia);
			cs.setString(2, indice);

		} catch (SQLException sqle) {
			logger.error("Usuario en PC: " + captura.obtenerNombrePC() + ". Exception: " + sqle.toString());
			JOptionPane.showMessageDialog(null, sqle.getMessage());
		} catch (Exception e) {
			logger.error("Usuario en PC: " + captura.obtenerNombrePC() + ". Exception: " + e.toString());
			JOptionPane.showMessageDialog(null, e.getMessage(), e.getClass().toString(), JOptionPane.ERROR_MESSAGE);
			JOptionPane.showMessageDialog(null, "Se va a cerrar la aplicacion");
			System.exit(0);
		}

		return cs.executeQuery();
	}

	/**
	 * Visualiza el estado de los paros
	 * 
	 * @param estado
	 *            estado el paro - Parametro para visualizar los estados de los
	 *            Paros
	 * @return Resultset - Retorna el listado de paros
	 */
	public ResultSet actualizarTabla(String estado) throws SQLException {

		try {
			con = cbd.conectarABaseDatos();
		} catch (Exception e) {
			logger.error("Usuario en PC: " + captura.obtenerNombrePC() + ". Exception: " + e.toString());
			JOptionPane.showMessageDialog(null, e.getMessage(), e.getClass().toString(), JOptionPane.ERROR_MESSAGE);
		}

		cs = con.prepareCall("{call Paros(?)}");

		cs.setString(1, estado);

		return cs.executeQuery();
	}

	/**
	 * Busca el indice de un campo
	 * 
	 * @param indice
	 *            - atributo que se desea buscar indice
	 * @param sentencia
	 *            - ejecuta la consulta de la condicion proporcionada
	 * @return Retorna el indice de un atributo
	 */
	public int buscarIndice(String indice, String sentencia) {

		int resultado = 0;

		try {
			con = cbd.conectarABaseDatos();
			cs = con.prepareCall("{call buscar_indice(?,?,?)}");

			cs.setString(1, indice);
			cs.setString(2, sentencia);

			cs.registerOutParameter(3, java.sql.Types.INTEGER);
			cs.executeUpdate();

			resultado = cs.getInt(3);

		} catch (SQLException sqle) {
			logger.error("Usuario en PC: " + captura.obtenerNombrePC() + ". Exception: " + sqle.toString());
			JOptionPane.showMessageDialog(null, sqle.getMessage(), sqle.getClass().toString(),
					JOptionPane.ERROR_MESSAGE);

		} catch (Exception e) {
			logger.error("Usuario en PC: " + captura.obtenerNombrePC() + ". Exception: " + e.toString());
			JOptionPane.showMessageDialog(null, e.getMessage(), e.getClass().toString(), JOptionPane.ERROR_MESSAGE);
			JOptionPane.showMessageDialog(null, "Se va a cerrar la aplicacion");
			System.exit(0);
		}
		return resultado;
	}

	/**
	 * Compara las fechas indicando cual es el mayor
	 * 
	 * @param fechaInicio
	 *            - Fecha de Inicio que se va a comparar
	 * @param fechaFin
	 *            - Fecha de Fin que se va a comparar
	 * @param formatoFecha
	 *            - Formato de comparacion
	 * @return true, si la fecha de fin es mayor a la Fecha de Inicio. false, si
	 *         la Fecha de Inicio es mayor a la Fecha de Fin
	 */
	public boolean compararFecha(String fechaInicio, String fechaFin, String formatoFecha) {

		if (!(fechaFin == (null))) {
			// Verifica si la fecha de Fin es Mayor a Fecha Inicio de Paro
			ComparacionFechas cf = new ComparacionFechas();
			boolean comparacionFechas = cf.compararFechas(fechaInicio, fechaFin, formatoFecha);

			if (comparacionFechas == true) {

				try {
					JOptionPane.showMessageDialog(null, "La fecha Fin no puede ser Mayor a la Fecha de Inicio de Paro",
							"Comparacion Fechas", JOptionPane.ERROR_MESSAGE);

					return false;
				} catch (Exception e) {
					logger.warn(e.toString(), e);
				}
			}
		}
		return true;
	}

	/**
	 * Genera el listado de las disciplinas para mostrarlas en un JList
	 * 
	 * @param lista
	 *            - JList que se va a poblar
	 * @throws SQLException
	 */
	public void poblarJList(JList<String> lista) throws SQLException {

		DefaultListModel<String> modelo = new DefaultListModel<String>();

		try (Connection con = cbd.conectarABaseDatos();
				CallableStatement cs = con.prepareCall("{call sp_listar_disciplina()}");) {

			ResultSet rs = cs.executeQuery();

			while (rs.next()) {

				modelo.addElement(rs.getString("nombre_disciplina"));
			}
			lista.setModel(modelo);

		} catch (SQLException sqle) {
			logger.error("Usuario en PC: " + captura.obtenerNombrePC() + ". Exception: "
					+ sqle.toString());
			JOptionPane.showMessageDialog(null, sqle.getMessage(), sqle.getClass().toString(),
					JOptionPane.ERROR_MESSAGE);

		} catch (Exception e) {
			logger.error("Usuario en PC: " + captura.obtenerNombrePC() + ". Exception: "
					+ e.toString());
			JOptionPane.showMessageDialog(null, e.getMessage(), e.getClass().toString(), JOptionPane.ERROR_MESSAGE);
			JOptionPane.showMessageDialog(null, "Se va a cerrar la aplicacion");
			System.exit(0);
		}
	}

	/**
	 * Autentica el usuario al sistema
	 * 
	 * @param usuario
	 *            - nombre de usuario
	 * @param clave
	 *            - clave del usuario
	 * @return Resultado de la busqueda de usuario
	 * @throws SQLException
	 */
	public ResultSet autenticarUsuario(String usuario, String clave) throws SQLException {

		try {

			con = cbd.conectarABaseDatos();
		} catch (Exception e) {
			logger.error("Usuario en PC: " + captura.obtenerNombrePC() + ". Exception: "
					+ e.toString());
			JOptionPane.showMessageDialog(null, e.getMessage(), e.getClass().toString(), JOptionPane.ERROR_MESSAGE);
			JOptionPane.showMessageDialog(null, "Se va a cerrar la aplicacion");
			System.exit(0);
		}

		cs = con.prepareCall("{call logearUsuario(?,?)}");

		cs.setString(1, usuario);
		cs.setString(2, clave);

		return cs.executeQuery();
	}

	/**
	 * Cierra todas las conexiones de Base de Datos
	 * 
	 */
	public void cerrarConexiones() {

		try {

			if (cs != null) {
				cs.close();
			}

			cbd.cerrarConexion();
		} catch (SQLException sqle) {
			logger.error("Usuario en PC: " + captura.obtenerNombrePC() + ". Exception: "
					+ sqle.toString());
			JOptionPane.showMessageDialog(null, sqle.getMessage(), sqle.getClass().toString(),
					JOptionPane.ERROR_MESSAGE);

		} catch (Exception e) {
			logger.error("Usuario en PC: " + captura.obtenerNombrePC() + ". Exception: "
					+ e.toString());
			JOptionPane.showMessageDialog(null, e.getMessage(), e.getClass().toString(), JOptionPane.ERROR_MESSAGE);
			JOptionPane.showMessageDialog(null, "Se va a cerrar la aplicacion");
			System.exit(0);
		}
	}
}