package com.cementopanam.jrivera.controlador.paros;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JOptionPane;

import com.cementopanam.jrivera.controlador.ManipulacionDatos;
import com.cementopanam.jrivera.modelo.ConeccionBD;

public class AdministracionParos extends ManipulacionDatos {

	private static final Logger log= Logger.getLogger( AdministracionParos.class.getName() );
	private ConeccionBD cbd;
	private Connection con = null;
	private CallableStatement cs = null;
	private ResultSet rs = null;

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
	 * Muestra los paros
	 * @return el listado de Paros
	 */
	public ArrayList<Paro> mostrarParo(Paro p, String filtro) {

		ArrayList<Paro> lista = new ArrayList<Paro>();
		try {
			con = cbd.conectarABaseDatos();
			cs = con.prepareCall("{call sp_buscar_paro(?,?,?)}");
			
			cs.setString(1, p.getTiempoInicio());
			cs.setString(2, p.getTiempoFin());
			cs.setString(3, filtro);
			
			rs = cs.executeQuery();

			while (rs.next()) {

				int codigo = rs.getInt(1);
				String usuario = rs.getString(2);
				String area = rs.getString(3);
				String subArea = rs.getString(4);
				String equipo = rs.getString(5);
				String disciplina = rs.getString(6);
				String causa = rs.getString(7);
				String descripcionAdicional = rs.getString(8);
				String tiempoInicio = rs.getString(9);
				String tiempoFin = rs.getString(10);
				String solucion = rs.getString(11);

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
	 * Modifica el paro seleccionado
	 * @param p - atributos de la entidad
	 * @param idCausa - indice de la causa
	 * @return - true si fue exitoso, false si no se completa la operacion
	 * @throws SQLException
	 */
	public boolean modificarParo(Paro p, int idCausa) throws SQLException {

		if (compararFecha(p.getTiempoInicio(), p.getTiempoFin(), formatoFecha) == false) {
			return false;
		}

		// Busca ID de Disciplina
		int idDisciplina = Integer.parseInt(buscarIndice(p.getDisciplina(), "disciplina"));

		try {

			con = cbd.conectarABaseDatos();
			cs = con.prepareCall("{call sp_modificar_paro(?,?,?,?,?,?,?,?)}");

			cs.setInt(1, p.getCodigo());
			cs.setString(2, p.getSolucion());
			cs.setString(3, p.getTiempoInicio());
			cs.setString(4, p.getTiempoFin());
			cs.setInt(5, idDisciplina);
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
	 * Elimina el paro seleccionado
	 * @param idParo - indice del paro a ser eliminar
	 * @return - true si fue exitoso, false si no se completa la operacion
	 * @throws SQLException
	 */
	public boolean eliminarParo(int idParo) throws SQLException {

		try {

			con = cbd.conectarABaseDatos();
			cs = con.prepareCall("{call sp_eliminar_paro(?)}");

			cs.setInt(1, idParo);

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
	public String buscarIndiceCausa(String causa, String descripcion) {

		String resultado = "";

		// Valida si la descripcion adicional esta vacia o nula
		int longitud = causa.length();
		if (longitud == 0) {

			return resultado = buscarIndice(causa, "causa");
		}

		try {
			con = cbd.conectarABaseDatos();

			cs = con.prepareCall("{call sp_buscar_causa(?,?,?)}");

			cs.setString(1, causa);
			cs.setString(2, descripcion);
			cs.registerOutParameter(3, java.sql.Types.VARCHAR);

			cs.executeQuery();
			resultado = cs.getString(3);

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
}