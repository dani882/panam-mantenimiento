package com.cementopanam.jrivera.controlador.paros;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JOptionPane;

import com.cementopanam.jrivera.controlador.ComparacionFechas;
import com.cementopanam.jrivera.controlador.Imputacion;
import com.cementopanam.jrivera.controlador.ManipulacionDatos;
import com.cementopanam.jrivera.modelo.ConeccionBD;

public class AdministracionParos extends ManipulacionDatos {

	private static final Logger log = Logger.getLogger(AdministracionParos.class.getName());
	private ConeccionBD cbd;
	private Connection con = null;
	private CallableStatement cs = null;

	private String formatoFecha = "yyyy-MM-dd HH:mm:ss";

	/**
	 * 
	 */
	public AdministracionParos() {

		cbd = ConeccionBD.getInstance();

		if (!cbd.verificarConexion()) {
			try {
				cbd.conectarABaseDatos();
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, e.getMessage(), e.getClass().toString(), JOptionPane.ERROR_MESSAGE);
				log.log(Level.SEVERE, e.toString(), e);
			}
		}
	}

	/**
	 * Muestra los paros en la busqueda de paros
	 * 
	 * @return el listado de Paros
	 */
	public ArrayList<Paro> mostrarParo(Paro p, String filtro) {

		ArrayList<Paro> lista = new ArrayList<Paro>();
		ResultSet rs = null;

		try (Connection con = cbd.conectarABaseDatos();
				CallableStatement cs = con.prepareCall("{call sp_buscar_paro(?,?,?)}");) {

			cs.setString(1, p.getTiempoInicio());
			cs.setString(2, p.getTiempoFin());
			cs.setString(3, filtro);

			rs = cs.executeQuery();

			while (rs.next()) {

				int codigo = rs.getInt("Codigo");
				String usuario = rs.getString("Usuario");
				String area = rs.getString("Area");
				String subArea = rs.getString("SubArea");
				String equipo = rs.getString("Equipo");
				String disciplina = rs.getString("Disciplina");
				String causa = rs.getString("Causa");
				String descripcionAdicional = rs.getString("Descripcion Adicional");
				String tiempoInicio = rs.getString("Tiempo de Inicio");
				String tiempoFin = rs.getString("Tiempo Final");
				String solucion = rs.getString("Solucion");

				lista.add(new Paro(codigo, usuario, area, subArea, equipo, tiempoInicio, tiempoFin, solucion, causa,
						descripcionAdicional, disciplina));
			}
		} catch (SQLException sqle) {
			JOptionPane.showMessageDialog(null, sqle.getMessage(), sqle.getClass().toString(),
					JOptionPane.ERROR_MESSAGE);
			log.log(Level.SEVERE, sqle.toString(), sqle);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), e.getClass().toString(), JOptionPane.ERROR_MESSAGE);
			log.log(Level.SEVERE, e.toString(), e);
		}
		return lista;
	}

	/**
	 * Guarda los paros a la base de datos
	 * 
	 * @param imp
	 *            - informaciones del paro para guardar al sistema
	 * @return - true si la operacion fue exitosa, false - si no se compleeto la
	 *         operacion
	 * @throws SQLException
	 */
	public boolean imputarParo(Imputacion imp) throws SQLException {

		// Verifica si el tiempo de inicio es superior al tiempo de fin
		if (compararFecha(imp.getTiempoInicio(), imp.getTiempoFin(), formatoFecha) == false) {
			return false;
		}

		try (Connection con = cbd.conectarABaseDatos();
				CallableStatement cs = con.prepareCall("{call sp_insertar_paro(?,?,?,?,?,?,?,?,?,?)}");) {

			cs.setString(1, imp.getCausa()); // Causa
			cs.setString(2, imp.getCausaExtendida()); // Causa Extendida
			cs.setString(3, imp.getEquipo()); // Codigo de Equipo
			cs.setString(4, imp.getUsuario()); // Nombre de Usuario
			cs.setString(5, imp.getDisciplina()); // Disciplina
			cs.setString(6, imp.getTiempoInicio()); // Tiempo de Inicio
			cs.setString(7, imp.getTiempoFin()); // Tiempo de Fin
			cs.setString(8, imp.getEstadoEquipo()); // Estado del Equipo (Activo
													// o Inactivo)
			cs.setString(9, imp.getEstadoParo()); // Estado del Paro (Completado
													// o Pendiente)
			cs.setString(10, imp.getSolucion()); // Solucion del paro, null si
													// paro esta Pendiente

			cs.execute();
			con.commit();

		} catch (SQLException sqle) {
			JOptionPane.showMessageDialog(null, sqle.getMessage(), sqle.getClass().toString(),
					JOptionPane.ERROR_MESSAGE);
			con.rollback();
			log.log(Level.SEVERE, sqle.toString(), sqle);
			return false;
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), e.getClass().toString(), JOptionPane.ERROR_MESSAGE);
			con.rollback();
			log.log(Level.SEVERE, e.toString(), e);
			return false;
		}
		return true;

	}

	/**
	 * Modifica el paro seleccionado
	 * 
	 * @param p
	 *            - atributos de la entidad
	 * @param idCausa
	 *            - indice de la causa
	 * @return - true si fue exitoso, false si no se completa la operacion
	 * @throws SQLException
	 */
	public boolean modificarParo(Paro p, int idCausa) throws SQLException {

		// Comprueba si la fecha de fin es menor a la Fecha de Inicio
		if (compararFecha(p.getTiempoInicio(), p.getTiempoFin(), formatoFecha) == false) {
			return false;
		}

		try (Connection con = cbd.conectarABaseDatos();
				CallableStatement cs = con.prepareCall("{call sp_modificar_paro(?,?,?,?,?,?,?,?)}");) {

			cs.setInt(1, p.getCodigo());
			cs.setString(2, p.getSolucion());
			cs.setString(3, p.getTiempoInicio());
			cs.setString(4, p.getTiempoFin());
			cs.setString(5, p.getDisciplina());
			cs.setString(6, p.getCausa());
			cs.setString(7, p.getDescripcionAdicional());
			cs.setInt(8, idCausa);

			cs.execute();
			con.commit();
		} catch (SQLException sqle) {
			JOptionPane.showMessageDialog(null, sqle.getMessage(), sqle.getClass().toString(),
					JOptionPane.ERROR_MESSAGE);
			con.rollback();
			log.log(Level.SEVERE, sqle.toString(), sqle);
			return false;

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), e.getClass().toString(), JOptionPane.ERROR_MESSAGE);
			con.rollback();
			log.log(Level.SEVERE, e.toString(), e);
			return false;
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
	public boolean actualizarParo(Paro paro) throws SQLException {

		// Verifica si la fecha de Fin es Mayor a Fecha Inicio de Paro
		ComparacionFechas cf = new ComparacionFechas();
		boolean comparacionFechas = cf.compararFechas(paro.getTiempoInicio(), paro.getTiempoFin(), formatoFecha);

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

		else {

			try (Connection con = cbd.conectarABaseDatos();
					CallableStatement cs = con.prepareCall("{call sp_actualizar_paro(?,?,?)}");) {

				cs.setInt(1, paro.getCodigo());
				cs.setString(2, paro.getTiempoFin());
				cs.setString(3, paro.getSolucion());

				cs.execute();
				con.commit();
			}
		}
		return true;
	}

	/**
	 * Modifica el paro pendiente seleccionado
	 * 
	 * @param p
	 *            - atributos de la entidad
	 * @param idCausa
	 *            - indice de la causa
	 * @return - true si fue exitoso, false si no se completa la operacion
	 * @throws SQLException
	 */
	public boolean modificarParoPendiente(Paro p, int idCausa) throws SQLException {

		try (Connection con = cbd.conectarABaseDatos();
				CallableStatement cs = con.prepareCall("{call sp_modificar_paro_pendiente(?,?,?,?,?,?,?)}");) {

			cs.setInt(1, p.getCodigo());
			cs.setString(2, p.getTiempoInicio());
			cs.setString(3, p.getDisciplina());
			cs.setString(4, p.getCausa());
			cs.setString(5, p.getDescripcionAdicional());
			cs.setString(6, p.getEquipo());
			cs.setInt(7, idCausa);

			cs.execute();
			con.commit();
		} catch (SQLException sqle) {
			JOptionPane.showMessageDialog(null, sqle.getMessage(), sqle.getClass().toString(),
					JOptionPane.ERROR_MESSAGE);
			con.rollback();
			log.log(Level.SEVERE, sqle.toString(), sqle);
			return false;

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), e.getClass().toString(), JOptionPane.ERROR_MESSAGE);
			con.rollback();
			log.log(Level.SEVERE, e.toString(), e);
			return false;
		}
		return true;
	}

	/**
	 * Elimina el paro seleccionado
	 * 
	 * @param idParo
	 *            - indice del paro a ser eliminar
	 * @return - true si fue exitoso, false si no se completa la operacion
	 * @throws SQLException
	 */
	public boolean eliminarParo(int idCausa) throws SQLException {

		try (Connection con = cbd.conectarABaseDatos();
				CallableStatement cs = con.prepareCall("{call sp_eliminar_paro(?)}");) {

			cs.setInt(1, idCausa);

			cs.execute();
			con.commit();
		} catch (SQLException sqle) {
			JOptionPane.showMessageDialog(null, sqle.getMessage(), sqle.getClass().toString(),
					JOptionPane.ERROR_MESSAGE);
			con.rollback();
			log.log(Level.SEVERE, sqle.toString(), sqle);
			return false;

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), e.getClass().toString(), JOptionPane.ERROR_MESSAGE);
			con.rollback();
			log.log(Level.SEVERE, e.toString(), e);
			return false;
		}
		return true;
	}

	/**
	 * @param causa
	 *            - Causa del paro
	 * @param descripcion
	 *            - Descripcion Adicional del Paro
	 * @return el indice de la Causa Seleccionada
	 */
	public int buscarIndiceCausa(String causa, String descripcion) {

		int resultado = 0;

		// Valida si la descripcion adicional esta vacia o nula
		if (causa.length() == 0) {

			return resultado = buscarIndice(causa, "causa");
		}

		try (Connection con = cbd.conectarABaseDatos();
				CallableStatement cs = con.prepareCall("{call sp_buscar_causa(?,?,?)}");) {

			cs.setString(1, causa);
			cs.setString(2, descripcion);
			cs.registerOutParameter(3, java.sql.Types.INTEGER);

			cs.executeQuery();
			// Muestra el ID de causa obtenida de la Base de Datos
			resultado = cs.getInt(3);

		} catch (SQLException sqle) {
			JOptionPane.showMessageDialog(null, sqle.getMessage(), sqle.getClass().toString(),
					JOptionPane.ERROR_MESSAGE);
			log.log(Level.SEVERE, sqle.toString(), sqle);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), e.getClass().toString(), JOptionPane.ERROR_MESSAGE);
			log.log(Level.SEVERE, e.toString(), e);
		}
		return resultado;
	}

	/**
	 * Cuenta la cantidad de Paros Pendientes por Completar
	 * 
	 * @return el numero de paros pendientes
	 */
	public int contarPendientes() {

		int resultado = 0;

		try (Connection con = cbd.conectarABaseDatos();
				CallableStatement cs = con.prepareCall("{call sp_contar_paros_pendientes(?)}");) {

			log.info("Conectado de Contador Paros Pendientes");
			cs.registerOutParameter(1, java.sql.Types.INTEGER);

			cs.executeQuery();
			// Muestra la cantidad de Paros Pendientes
			resultado = cs.getInt(1);

		} catch (SQLException sqle) {
			JOptionPane.showMessageDialog(null, sqle.getMessage(), sqle.getClass().toString(),
					JOptionPane.ERROR_MESSAGE);
			log.log(Level.SEVERE, sqle.toString(), sqle);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), e.getClass().toString(), JOptionPane.ERROR_MESSAGE);
			log.log(Level.SEVERE, e.toString(), e);
		}
		log.info("Desconectado de Contador Paros Pendientes");
		return resultado;
	}

	public ResultSet mostrarDetallePendiente(int codigoParo) throws SQLException {

		try {

			con = cbd.conectarABaseDatos();
			cs = con.prepareCall("{call sp_mostrar_detalle_pendiente(?)}");

			cs.setInt(1, codigoParo);

		} catch (SQLException sqle) {
			log.log(Level.SEVERE, sqle.toString(), sqle);
			JOptionPane.showMessageDialog(null, sqle.getMessage());
		} catch (Exception e) {
			log.log(Level.SEVERE, e.toString(), e);
			JOptionPane.showMessageDialog(null, e.getMessage());
		}

		return cs.executeQuery();
	}
}