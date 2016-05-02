package com.cementopanam.jrivera.controlador;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JOptionPane;

import com.cementopanam.jrivera.controlador.entidad.Area;
import com.cementopanam.jrivera.controlador.entidad.Causa;
import com.cementopanam.jrivera.controlador.entidad.Disciplina;
import com.cementopanam.jrivera.controlador.entidad.Equipo;
import com.cementopanam.jrivera.controlador.entidad.SubArea;
import com.cementopanam.jrivera.modelo.ConeccionBD;
import com.cementopanam.jrivera.vista.Principal;

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

				sql = "SELECT * FROM mantenimientodb.area;";
				pstmt = con.prepareStatement(sql);
				rs = pstmt.executeQuery();
			} else if (sentencia.equalsIgnoreCase("causa") && indice == 0) {

				sql = "SELECT * FROM mantenimientodb.causa WHERE id_usuario=1;";
				pstmt = con.prepareStatement(sql);
				rs = pstmt.executeQuery();
			} else if (sentencia.equalsIgnoreCase("disciplina") && indice == 0) {

				sql = "SELECT * FROM mantenimientodb.disciplina;";
				pstmt = con.prepareStatement(sql);
				rs = pstmt.executeQuery();
			} else if (sentencia.equalsIgnoreCase("subArea") && indice == 0) {

				sql = "SELECT * FROM mantenimientodb.sub_area;";
				pstmt = con.prepareStatement(sql);
				rs = pstmt.executeQuery();
			}

			else {

				switch (sentencia) {

				case "subArea":
					sql = "SELECT * FROM mantenimientodb.sub_area WHERE id_area = ?;";
					pstmt = con.prepareStatement(sql);
					pstmt.setInt(1, indice);
					rs = pstmt.executeQuery();
					break;

				case "equipo":
					sql = "SELECT * FROM mantenimientodb.equipo WHERE idSubArea = ?;";
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
	 * Agrega nuevos paros a la base de datos
	 * 
	 * @param fechaInicio
	 * @param fechaFin
	 * @param equipo
	 * @param estadoParo
	 * @param estadoEquipo
	 * @param subArea
	 * @param area
	 * @param disciplina
	 * @param tipoCausa
	 * @param usuario
	 * @param otraCausa
	 * @return boolean
	 */
	public boolean imputarParo(String fechaInicio, String fechaFin, String equipo, String estadoParo,
			String estadoEquipo, String subArea, String area, String disciplina, String tipoCausa, String usuario,
			String otraCausa) throws ParseException {

		try {

			if (compararFecha(fechaInicio, fechaFin, formatoFecha) == false) {
				return false;
			}

			// Conecta a la base de datos
			con = cbd.conectarABaseDatos();
			con.setAutoCommit(false);

			try {

				// Busca indice de Equipo
				int idEquipo = Integer.parseInt(buscarIndice(equipo, "equipo"));

				/*
				 * //Busca indice de SubArea int idSubArea =
				 * Integer.parseInt(buscarIndice(subArea, "sub_area"));
				 * System.out.println("Valor de SubArea " + idSubArea);
				 */

				// Busca indice de Usuario
				int idUsuario = Integer.parseInt(buscarIndice(usuario, "usuario"));

				// Busca indice de Disciplina
				int idDisciplina = Integer.parseInt(buscarIndice(disciplina, "disciplina"));

				/*
				 * //Busca indice de Area int idArea =
				 * Integer.parseInt(buscarIndice(area, "area"));
				 * System.out.println("Valor de Area " + idArea);
				 */

				// Busca indice de Causa
				// int idCausa = Integer.parseInt(buscarIndice(tipoCausa,
				// "causa"));

				/*
				 * Causa
				 */

				String sqlInsertCausa = "INSERT INTO `mantenimientodb`.`causa` "
						+ "(`tipo_causa`, `descripcion_adicional`, `id_usuario`) VALUES (?, ?, ?);";

				pstmt = con.prepareStatement(sqlInsertCausa, PreparedStatement.RETURN_GENERATED_KEYS);

				pstmt.setString(1, tipoCausa);
				pstmt.setString(2, otraCausa);
				pstmt.setInt(3, idUsuario);

				pstmt.executeUpdate();

				// Variable para obtener la clave del ultimo registro generado
				int claveCausaGenerada = 0;
				rs = pstmt.getGeneratedKeys();
				while (rs.next()) {
					claveCausaGenerada = rs.getInt(1);
				}

				/*
				 * Equipo_Causa
				 */

				String sqlInsertEquipoCausa = "INSERT INTO `mantenimientodb`.`equipo_causa` "
						+ "(`id_equipo`, `id_causa`, `estatus_equipo`) VALUES (?, ?, ?);";

				pstmt = con.prepareStatement(sqlInsertEquipoCausa, PreparedStatement.RETURN_GENERATED_KEYS);

				pstmt.setInt(1, idEquipo);
				// TODO Crear un verificador para cuando exista un registro
				// igual, no insertar
				// TODO Hacerlo con Stored Procedure, busca condicion if...
				if (estadoEquipo.equals("Activo")) {
					pstmt.setInt(2, claveCausaGenerada);
					pstmt.setString(3, "Activo");

					pstmt.executeUpdate();
				} else {
					pstmt.setInt(2, claveCausaGenerada);
					pstmt.setString(3, "Inactivo");

					pstmt.executeUpdate();
				}

				// Variable para obtener la clave del ultimo registro generado
				int claveEquipoCausaGenerada = 0;
				rs = pstmt.getGeneratedKeys();
				while (rs.next()) {
					claveEquipoCausaGenerada = rs.getInt(1);
				}

				/*
				 * Operacion Imputacion
				 */
				String sqlInsertOperacionImputacion = "INSERT INTO `mantenimientodb`.`operacion_imputacion` "
						+ "(`tiempo_inicio_paro`, `tiempo_fin_paro`, `estatus_paro`, `id_disciplina`, "
						+ "`id_equipo_causa`) " + "VALUES (?, ?, ?, ?, ?);";

				pstmt = con.prepareStatement(sqlInsertOperacionImputacion, PreparedStatement.RETURN_GENERATED_KEYS);

				pstmt.setString(1, fechaInicio);

				// Si es seleccionado paro completado entonces se guarda la
				// fecha de fin, de lo contrario se guarda un null en la base de datos.

				if (estadoParo.equals("Completado") && (!(fechaFin.equals(null) || fechaFin == ""))) {

					pstmt.setString(2, fechaFin);
					pstmt.setString(3, estadoParo);
					pstmt.setInt(4, idDisciplina);
					pstmt.setInt(5, claveEquipoCausaGenerada);

					pstmt.executeUpdate();

				}
				// En caso del Paro estar Pendiente de Completar
				else {
					pstmt.setString(2, null);
					pstmt.setString(3, estadoParo);
					pstmt.setInt(4, idDisciplina);
					pstmt.setInt(5, claveEquipoCausaGenerada);

					pstmt.executeUpdate();
				}

				// Variable para obtener la clave del ultimo registro generado
				int claveParoGenerada = 0;
				rs = pstmt.getGeneratedKeys();
				while (rs.next()) {
					claveParoGenerada = rs.getInt(1);
				}

				/*
				 * Solucion
				 */

				if (estadoParo.equals("Completado")) {
					try {

						String detalleSolucion = JOptionPane.showInputDialog(null,
								"¿Que se tuvo que hacer para solucionar este paro?", "Solucion de paro",
								JOptionPane.INFORMATION_MESSAGE);

						if (detalleSolucion == null || detalleSolucion.equals(null) || detalleSolucion == ""
								|| detalleSolucion.equals("")) {
							JOptionPane.showMessageDialog(null, "Debe escribir la solucion del paro",
									"Solucion de Paro", JOptionPane.WARNING_MESSAGE);

							con.rollback();
							return false;
						}

						String sqlInsertSolucion = "INSERT INTO `mantenimientodb`.`solucion` (`solucion_paro`, `id_operacion_imputacion`)"
								+ " VALUES (?, ?);";

						pstmt = con.prepareStatement(sqlInsertSolucion, PreparedStatement.RETURN_GENERATED_KEYS);
						pstmt.setString(1, detalleSolucion);
						pstmt.setInt(2, claveParoGenerada);

						pstmt.executeUpdate();

					}
					catch (Exception e) {
						log.log(Level.SEVERE, e.toString(), e);
						JOptionPane.showMessageDialog(null, e.getMessage(), e.getClass().toString(),
								JOptionPane.ERROR_MESSAGE);
						con.rollback();
						return false;
					}
				}
		
				con.commit();

				// Muestra la Barra de Progreso
				// if (SwingUtilities.isEventDispatchThread()) {
				Principal.mostrarProgreso(5);

				// }

			} catch (SQLException sqle) {

				log.log(Level.SEVERE, sqle.toString(), sqle);
				JOptionPane.showMessageDialog(null, sqle.getMessage(), sqle.getClass().toString(),
						JOptionPane.ERROR_MESSAGE);
				con.rollback();
				return false;
			}
		} catch (SQLException e) {
			log.log(Level.SEVERE, e.toString(), e);
			try {
				con.rollback();
				return false;

			} catch (Exception ex) {
				log.log(Level.SEVERE, ex.toString(), ex);
				JOptionPane.showMessageDialog(null, ex.getMessage(), ex.getClass().toString(),
						JOptionPane.ERROR_MESSAGE);
				return false;
			}
		} finally {
			if (cbd.verificarConexion() == true) {
				cerrarConexiones();
			}
		}
		return true;
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
				log.log(Level.SEVERE, e.toString(), e);
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