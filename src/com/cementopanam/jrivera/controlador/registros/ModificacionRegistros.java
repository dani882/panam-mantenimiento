package com.cementopanam.jrivera.controlador.registros;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import org.apache.log4j.Logger;

import com.cementopanam.jrivera.controlador.ManipulacionDatos;
import com.cementopanam.jrivera.controlador.entidad.Area;
import com.cementopanam.jrivera.controlador.entidad.Causa;
import com.cementopanam.jrivera.controlador.entidad.Disciplina;
import com.cementopanam.jrivera.controlador.entidad.Equipo;
import com.cementopanam.jrivera.controlador.entidad.SubArea;
import com.cementopanam.jrivera.controlador.usuario.CapturaUsuario;
import com.cementopanam.jrivera.modelo.ConeccionBD;

/**
 * @author jrivera
 *
 */
public class ModificacionRegistros extends ManipulacionDatos {

	private static final Logger logger = Logger.getLogger(ModificacionRegistros.class);
	private ConeccionBD cbd;
	private Connection con = null;
	
	private CapturaUsuario captura = new CapturaUsuario();
	
	public ModificacionRegistros() {
		
		cbd = ConeccionBD.getInstance();

		if (!cbd.verificarConexion()) {
			try {
				cbd.conectarABaseDatos();
			} catch (Exception e) {
				logger.error(e.toString(), e);
				JOptionPane.showMessageDialog(null, e.getMessage(), e.getClass().toString(), JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	

	/**
	 * Borrar los registros seleccionados
	 * @param causas - las causas que se borraran
	 * @param disciplinas - las disciplinas que se borraran
	 * @return - true si el borrado fue exitoso, false si no fue satisfactorio
	 * @throws SQLException
	 */
	public boolean borrarRegistros(List<Causa> causas, List<Disciplina> disciplinas) throws SQLException {
		
		//TODO Agregar los demas registros de eliminacion en los parametros
		try (Connection con = cbd.conectarABaseDatos();
				CallableStatement cs = con.prepareCall("{call sp_eliminar_registros(?,?)}");) {
						
			//Borra registro de Causa
			for (Causa causa : causas) {
				cs.setString(1, causa.getTipoCausa());
				cs.setString(2, causa.getIdDisciplina());
				cs.addBatch();
				
				logger.info("Borrado Causa: " + causa.getTipoCausa() + " por Usuario en PC: " + captura.obtenerNombrePC());
			}
			
			//Borra registro de Disciplina
			for (Disciplina disciplina : disciplinas) {
				cs.setString(1, disciplina.getNombreDisciplina());
				cs.setString(2, "disciplina");
				cs.addBatch();
				
				logger.info("Borrado Displina: " + disciplina.getNombreDisciplina() + " por Usuario en PC: " + captura.obtenerNombrePC());
			}
			
			cs.executeBatch();
			con.commit();
		}
		
		catch (SQLException sqle) {
			logger.error(sqle.toString(), sqle);
			JOptionPane.showMessageDialog(null, sqle.getMessage(), sqle.getClass().toString(),
					JOptionPane.ERROR_MESSAGE);

			con.rollback();
			return false;
			
		} 
		catch (Exception e) {
			logger.error(e.toString(), e);
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
	public boolean agregarRegistros(Area area, List<Causa> causas, Disciplina disciplina, 
			Equipo equipo, SubArea subArea) throws SQLException {

		try (Connection con = cbd.conectarABaseDatos();
				CallableStatement cs = con.prepareCall("{call sp_agregar_registros(?,?,?,?)}");) {

			// Equipo
			cs.setString(1, equipo.getNombreEquipo());
			cs.setString(2, "equipo");
			cs.setString(3, equipo.getCodEquipo());
			cs.setString(4, equipo.getIdSubArea());
			cs.addBatch();

			logger.info("Agregado Equipo. Codigo: " + equipo.getCodEquipo() +" Nombre: "+ equipo.getNombreEquipo() +" por Usuario en PC: " + captura.obtenerNombrePC());
			
			// Area
			cs.setString(1, area.getNombreArea());
			cs.setString(2, "area");
			cs.setString(3, "");
			cs.setInt(4, area.getIdArea());
			cs.addBatch();

			logger.info("Agregado Area: " + area.getNombreArea() + " por Usuario en PC: " + captura.obtenerNombrePC());

			// SubArea
			cs.setString(1, subArea.getNombreSubArea());
			cs.setString(2, "subArea");
			cs.setString(3, "");
			cs.setString(4, subArea.getIdArea());
			cs.addBatch();
			
			logger.info("Agregado SubArea: " + subArea.getNombreSubArea() + " por Usuario en PC: " + captura.obtenerNombrePC());

			// Disciplina
			cs.setString(1, disciplina.getNombreDisciplina());
			cs.setString(2, "disciplina");
			cs.setString(3, "");
			cs.setInt(4, disciplina.getIdDisciplina());
			cs.addBatch();

			logger.info("Agregado Disciplina: " + disciplina.getNombreDisciplina() + " por Usuario en PC: " + captura.obtenerNombrePC());

			// Causa
			for (Causa causa : causas) {
				if(causa.getTipoCausa() == null) {
					// Sale del bucle y no guarda nada en la base de datos
					break;
				}
				cs.setString(1, causa.getTipoCausa());
				cs.setString(2, "causa");
				cs.setString(3, "");
				cs.setString(4, causa.getIdDisciplina());
				cs.addBatch();
				
				logger.info("Agregado Causa: " + causa.getTipoCausa() + " por Usuario en PC: " + captura.obtenerNombrePC());

			}

			cs.executeBatch();
			con.commit();

		} 
		catch (SQLException sqle) {
			logger.error(sqle.toString(), sqle);
			JOptionPane.showMessageDialog(null, sqle.getMessage(), sqle.getClass().toString(),
					JOptionPane.ERROR_MESSAGE);

			con.rollback();
			return false;
		}
		catch (Exception e) {
			logger.error(e.toString(), e);
			JOptionPane.showMessageDialog(null, e.getMessage(), e.getClass().toString(), JOptionPane.ERROR_MESSAGE);

			con.rollback();
			return false;
		}
		return true;
	}
	
	
	public DefaultTableModel mostrarDatos(String registro) {
		
		DefaultTableModel modelo = new DefaultTableModel();
		ResultSet rs = null;
		
	    try {  
	        while (modelo.getRowCount()>0){
	        	modelo.removeRow(0);
	        }
	        
	    try (Connection con = cbd.conectarABaseDatos();
	    		CallableStatement cs = con.prepareCall("{call sp_mostrar_registros(?)}");) {
	    	
	    	//Rellena el JTable de Modificacion Area
	    	switch (registro) {
				case "area":
					
					cs.setString(1, registro);
		        	rs = cs.executeQuery();
		        		
		    	    modelo.addColumn("Area");
		    	        
		    	    while(rs.next()){
		    	    	modelo.addRow(new Object[]{
		    	        rs.getString("nombre_area")});
		    	    }
					
					break;
				
			//Rellena el JTable de Modificacion SubArea
				case "subArea":
					
					cs.setString(1, registro);
		        	rs = cs.executeQuery();
		        		
		    	    modelo.addColumn("SubArea");
		    	        
		    	    while(rs.next()){
		    	    	modelo.addRow(new Object[]{
		    	        rs.getString("nombre_sub_area")});
		    	    }
					
					break;
					
			//Rellena el JTable de Modificacion Equipo
				case "equipo":
					
					cs.setString(1, registro);
		        	rs = cs.executeQuery();
		        		
		    	    modelo.addColumn("Codigo Equipo");
		    	    modelo.addColumn("Nombre Equipo");
		    	        
		    	    while(rs.next()){
		    	    	modelo.addRow(new Object[]{
		    	        rs.getString("cod_equipo"), rs.getString("nombre_equipo")});
		    	    }
					
					break;
					
			//Rellena el JTable de Modificacion Causa
				case "causa":
					
					cs.setString(1, registro);
		        	rs = cs.executeQuery();
		        		
		    	    modelo.addColumn("Causa");
		    	    modelo.addColumn("Disciplina");
		    	        
		    	    while(rs.next()){
		    	    	modelo.addRow(new Object[]{
		    	        rs.getString("tipo_causa"), rs.getString("nombre_disciplina")});
		    	    }
					
					break;
			//Rellena el JTable de Modificacion Disciplina
				case "disciplina":
					
					cs.setString(1, registro);
		        	rs = cs.executeQuery();
		        		
		    	    modelo.addColumn("Disciplina");
		    	        
		    	    while(rs.next()){
		    	    	modelo.addRow(new Object[]{
		    	        rs.getString("nombre_disciplina")});
		    	    }
					
					break;

				}
	        }
	        
	      }catch (Exception ex) {
	        System.err.println(ex);
	      }
		return modelo;
	}
}