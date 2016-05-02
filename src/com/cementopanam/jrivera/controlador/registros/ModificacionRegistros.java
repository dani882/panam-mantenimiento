/**
 * 
 */
package com.cementopanam.jrivera.controlador.registros;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JOptionPane;

import com.cementopanam.jrivera.controlador.ManipulacionDatos;
import com.cementopanam.jrivera.controlador.entidad.Area;
import com.cementopanam.jrivera.controlador.entidad.Causa;
import com.cementopanam.jrivera.controlador.entidad.Disciplina;
import com.cementopanam.jrivera.controlador.entidad.Equipo;
import com.cementopanam.jrivera.controlador.entidad.SubArea;
import com.cementopanam.jrivera.modelo.ConeccionBD;

/**
 * @author jrivera
 *
 */
public class ModificacionRegistros extends ManipulacionDatos {

	private static final Logger log = Logger.getLogger(ManipulacionDatos.class.getName());
	private ConeccionBD cbd;
	private Connection con = null;
	private CallableStatement cs = null;
	
	public ModificacionRegistros() {
		
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
	

	public boolean borrarRegistros(List<Area> areas) throws SQLException {
		
		try (Connection con = cbd.conectarABaseDatos();
				CallableStatement cs = con.prepareCall("{call sp_eliminar_registros(?,?)}");) {
						
			//Borra registro de Area
			for (Area area : areas) {
				cs.setString(1, area.getNombreArea());
				cs.setString(2, "area");
				cs.addBatch();
				log.info("Nombre de Area: " + area.getNombreArea());
			}
			
			cs.executeBatch();
			con.commit();
			
			log.info("Se fue a la base de datos");
		}
		
		catch (SQLException sqle) {
			log.log(Level.SEVERE, sqle.toString(), sqle);
			JOptionPane.showMessageDialog(null, sqle.getMessage(), sqle.getClass().toString(),
					JOptionPane.ERROR_MESSAGE);

			con.rollback();
			return false;
		} catch (Exception e) {
			log.log(Level.SEVERE, e.toString(), e);
			JOptionPane.showMessageDialog(null, e.getMessage(), e.getClass().toString(), JOptionPane.ERROR_MESSAGE);

			con.rollback();
			return false;
		}
		return true;
		
	}
	
	
	/**
	 * Agrega nuevos registros a la base de datos
	 * 
	 * @param area
	 *            - registro de nueva area
	 * @param causa
	 *            - registro de una nueva causa
	 * @param disciplina
	 *            - registro de una nueva disciplina
	 * @param equipo
	 *            - registro de un nuevo equipo
	 * @param subArea
	 *            - registro de una nueva subArea
	 * @return true, si la operacion se realizo correctamente. false, si fue lo
	 *         contrario
	 * @throws SQLException
	 */
	public boolean agregarRegistros(Area area, Causa causa, Disciplina disciplina, Equipo equipo, SubArea subArea)
			throws SQLException {

		try (Connection con = cbd.conectarABaseDatos();
				CallableStatement cs = con.prepareCall("{call sp_agregar_registros(?,?,?,?)}");) {

			// Equipo
			cs.setString(1, equipo.getNombreEquipo());
			cs.setString(2, "equipo");
			cs.setString(3, equipo.getCodEquipo());
			cs.setInt(4, equipo.getIdSubArea());
			cs.addBatch();

			// Area
			cs.setString(1, area.getNombreArea());
			cs.setString(2, "area");
			cs.setString(3, area.getNombreArea());
			cs.setInt(4, area.getIdArea());
			cs.addBatch();

			// SubArea
			cs.setString(1, subArea.getNombreSubArea());
			cs.setString(2, "subArea");
			cs.setString(3, subArea.getNombreSubArea());
			cs.setInt(4, subArea.getIdArea());
			cs.addBatch();

			// Disciplina
			cs.setString(1, disciplina.getNombreDisciplina());
			cs.setString(2, "disciplina");
			cs.setString(3, disciplina.getNombreDisciplina());
			cs.setInt(4, disciplina.getIdDisciplina());
			cs.addBatch();

			// Causa
			cs.setString(1, causa.getTipoCausa());
			cs.setString(2, "causa");
			cs.setString(3, causa.getTipoCausa());
			cs.setInt(4, 1);
			cs.addBatch();

			cs.executeBatch();
			con.commit();

		} catch (SQLException sqle) {
			log.log(Level.SEVERE, sqle.toString(), sqle);
			JOptionPane.showMessageDialog(null, sqle.getMessage(), sqle.getClass().toString(),
					JOptionPane.ERROR_MESSAGE);

			con.rollback();
			return false;
		} catch (Exception e) {
			log.log(Level.SEVERE, e.toString(), e);
			JOptionPane.showMessageDialog(null, e.getMessage(), e.getClass().toString(), JOptionPane.ERROR_MESSAGE);

			con.rollback();
			return false;
		}

		return true;
	}
	
	
	
	
	
	
	
	
	
}
