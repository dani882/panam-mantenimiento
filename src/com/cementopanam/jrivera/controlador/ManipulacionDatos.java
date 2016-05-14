package com.cementopanam.jrivera.controlador;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JOptionPane;

import com.cementopanam.jrivera.modelo.ConeccionBD;

public class ManipulacionDatos {

	private static final Logger log = Logger.getLogger(ManipulacionDatos.class.getName());
	private ConeccionBD cbd;
	private Connection con = null;
	private CallableStatement cs = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;

	private String formatoFecha = "yyyy-MM-dd HH:mm:ss";

	public ManipulacionDatos() {

		cbd = ConeccionBD.getInstance();

		if (!cbd.verificarConexion()) {
			try {
				cbd.conectarABaseDatos();
			} catch (Exception e) {
				log.log(Level.SEVERE, e.toString(), e);
				JOptionPane.showMessageDialog(null, e.getMessage(), e.getClass().toString(), JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	public Connection obtenerConexion() {

		try {

			cbd = ConeccionBD.getInstance();
			con = cbd.conectarABaseDatos();
		} catch (Exception e) {
			log.log(Level.SEVERE, e.toString(), e);
			JOptionPane.showMessageDialog(null, e.getMessage(), e.getClass().toString(), JOptionPane.ERROR_MESSAGE);
		}
		return con;
	}

	/**
	 * Agrega elementos a los comboBox
	 * 
	 * @param sentencia
	 * @param indice
	 * @return Lista de elementos para el comboBox
	 */
	public ResultSet rellenarCombo(String sentencia, int indice) throws SQLException {

		String sql = "";
		try {
			con = cbd.conectarABaseDatos();

			if (sentencia.equalsIgnoreCase("area") && indice == 0) {

				sql = "SELECT * FROM mantenimientodb_nuevo.area;";
				pstmt = con.prepareStatement(sql);
				rs = pstmt.executeQuery();
			}  
			else if (sentencia.equalsIgnoreCase("disciplina") && indice == 0) {

				sql = "SELECT * FROM mantenimientodb_nuevo.disciplina;";
				pstmt = con.prepareStatement(sql);
				rs = pstmt.executeQuery();
			}
			
			else if (sentencia.equalsIgnoreCase("causa") && indice == 0) {

				sql = "SELECT * FROM mantenimientodb_nuevo.causa WHERE id_usuario=1;";
				pstmt = con.prepareStatement(sql);
				rs = pstmt.executeQuery();
			}

			else {

				switch (sentencia) {

				case "subArea":
					sql = "SELECT * FROM mantenimientodb_nuevo.sub_area WHERE id_area = ?;";
					pstmt = con.prepareStatement(sql);
					pstmt.setInt(1, indice);
					rs = pstmt.executeQuery();
					break;

				case "equipo":
					sql = "SELECT * FROM mantenimientodb_nuevo.equipo WHERE idSubArea = ?;";
					pstmt = con.prepareStatement(sql);
					pstmt.setInt(1, indice);
					rs = pstmt.executeQuery();
					break;
				
				case "causa":
					sql = "SELECT * FROM mantenimientodb_nuevo.causa WHERE id_usuario=1 AND id_disciplina = ?;";
					pstmt = con.prepareStatement(sql);
					pstmt.setInt(1, indice);
					rs = pstmt.executeQuery();
					break;

				default:
					JOptionPane.showMessageDialog(null, "Debe seleccionar todos los campos", "Seleccione campos",
							JOptionPane.WARNING_MESSAGE);
					break;
				}
			}
		} catch (Exception e) {
			log.log(Level.SEVERE, e.toString(), e);
			JOptionPane.showMessageDialog(null, e.getMessage(), e.getClass().toString(), JOptionPane.ERROR_MESSAGE);
		}

		return rs;

		/*
		 * String resultado = "";
		 * 
		 * try { con = cbd.conectarABaseDatos();
		 * 
		 * cs = con.prepareCall("{call rellenarcomboBox(?,?,?)}");
		 * 
		 * cs.setString(1, sentencia); cs.setString(2, indice);
		 * 
		 * cs.registerOutParameter(3, java.sql.Types.VARCHAR);
		 * cs.executeUpdate();
		 * 
		 * resultado = cs.getString(3);
		 * 
		 * System.out.println("Se uso el metodo rellenarCombo");
		 * 
		 * } catch (SQLException sqle) { JOptionPane.showMessageDialog(null,
		 * sqle.getMessage()); } catch (Exception e) {
		 * JOptionPane.showMessageDialog(null, e.getMessage()); }
		 * System.out.println(
		 * "Conectado a la Base de Datos desde metodo rellenarCombo");
		 * 
		 * return resultado;
		 */
	}

	/**
	 * Actualiza el paro de Pendiente a Completado
	 * 
	 * @param idParo
	 * @param tiempoFin
	 * @param tiempoInicio
	 * @return true - si se realizado con exito la operacion, false - si no se
	 *         completo la operacion
	 * @throws SQLException
	 */
	public boolean actualizarParo(int idParo, String tiempoFin, String tiempoInicio) throws SQLException {

		// Verifica si la fecha de Fin es Mayor a Fecha Inicio de Paro
		ComparacionFechas cf = new ComparacionFechas();
		boolean comparacionFechas = cf.compararFechas(tiempoInicio, tiempoFin, formatoFecha);

		if (comparacionFechas == true) {

			try {
				JOptionPane.showMessageDialog(null, "La fecha Fin no puede ser Mayor a la Fecha de Inicio de Paro",
						"Comparacion Fechas", JOptionPane.ERROR_MESSAGE);

				return false;
			} catch (Exception e) {
				log.log(Level.WARNING, e.toString(), e);
				return false;
			}
		}

		String detalleSolucion = JOptionPane.showInputDialog(null, "¿Que se tuvo que hacer para solucionar este paro?",
				"Solucion de paro", JOptionPane.INFORMATION_MESSAGE);

		if (detalleSolucion == null || detalleSolucion.equals(null) || detalleSolucion == ""
				|| detalleSolucion.equals("")) {
			JOptionPane.showMessageDialog(null, "Debe escribir la solucion del paro", "Solucion de Paro",
					JOptionPane.WARNING_MESSAGE);

			return false;
		}
		con = cbd.conectarABaseDatos();
		cs = con.prepareCall("{call sp_actualizar_paro(?,?,?)}");

		cs.setInt(1, idParo);
		cs.setString(2, tiempoFin);
		cs.setString(3, detalleSolucion);

		cs.execute();
		con.commit();

		return true;
	}

	/**
	 * Visualiza el estado de los paros
	 * 
	 * @param estatus
	 *            estado el paro - Parametro para visualizar los estados de los
	 *            Paros
	 * @return Resultset - Retorna el listado de paros
	 */
	public ResultSet actualizarTabla(String estatus) throws SQLException {

		try {
			con = cbd.conectarABaseDatos();
		} catch (Exception e) {
			log.log(Level.SEVERE, e.toString(), e);
			JOptionPane.showMessageDialog(null, e.getMessage(), e.getClass().toString(), JOptionPane.ERROR_MESSAGE);
		}

		cs = con.prepareCall("{call Paros(?)}");

		cs.setString(1, estatus);

		rs = cs.executeQuery();

		return rs;
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
	public String buscarIndice(String indice, String sentencia) {

		String resultado = "";

		try {
			con = cbd.conectarABaseDatos();
			cs = con.prepareCall("{call buscar_indice(?,?,?)}");

			cs.setString(1, indice);
			cs.setString(2, sentencia);

			cs.registerOutParameter(3, java.sql.Types.VARCHAR);
			cs.executeUpdate();

			resultado = cs.getString(3);

		} catch (SQLException sqle) {
			log.log(Level.SEVERE, sqle.toString(), sqle);
			JOptionPane.showMessageDialog(null, sqle.getMessage(), sqle.getClass().toString(),
					JOptionPane.ERROR_MESSAGE);
		} catch (Exception e) {
			log.log(Level.SEVERE, e.toString(), e);
			JOptionPane.showMessageDialog(null, e.getMessage(), e.getClass().toString(), JOptionPane.ERROR_MESSAGE);
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
					JOptionPane.showMessageDialog(null,
							"La fecha Fin no puede ser Mayor a la Fecha de " + "Inicio de Paro", "Comparacion Fechas",
							JOptionPane.ERROR_MESSAGE);

					return false;
				} catch (Exception e) {
					log.log(Level.SEVERE, e.toString(), e);
				}
			}

		}
		return true;

	}

	/**
	 * Autentica el usuario
	 * 
	 * @param user
	 *            - usuario
	 * @param password
	 *            - clave
	 * @return Resultado de la busqueda de usuario
	 * @throws SQLException
	 */
	public ResultSet autenticarUsuario(String user, String password) throws SQLException {

		try {

			con = cbd.conectarABaseDatos();
		} catch (Exception e) {
			log.log(Level.SEVERE, e.toString(), e);
			JOptionPane.showMessageDialog(null, e.getMessage(), e.getClass().toString(), JOptionPane.ERROR_MESSAGE);
		}

		// TODO Modificar este procedimiento para que tenga un OUT de estatus
		cs = con.prepareCall("{call logearUsuario(?,?)}");

		// String estatus = cs.getString("estatus");

		cs.setString(1, user);
		cs.setString(2, password);

		return rs = cs.executeQuery();
	}

	/**
	 * Cierra todas las conexiones de Base de Datos
	 * 
	 */
	public void cerrarConexiones() {

		try {

			if (rs != null) {
				rs.close();
			}

			if (pstmt != null) {
				pstmt.close();
			}

			if (cs != null) {
				cs.close();
			}

			cbd.cerrarConexion();
		} catch (SQLException sqle) {
			log.log(Level.SEVERE, sqle.toString(), sqle);
			JOptionPane.showMessageDialog(null, sqle.getMessage(), sqle.getClass().toString(),
					JOptionPane.ERROR_MESSAGE);

		} catch (Exception e) {
			log.log(Level.SEVERE, e.toString(), e);
			JOptionPane.showMessageDialog(null, e.getMessage(), e.getClass().toString(), JOptionPane.ERROR_MESSAGE);
		}
	}
}