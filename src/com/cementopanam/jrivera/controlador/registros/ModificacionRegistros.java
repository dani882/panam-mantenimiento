package com.cementopanam.jrivera.controlador.registros;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

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
	

	public boolean borrarRegistros(List<Causa> causas, List<Disciplina> disciplinas) throws SQLException {
		
		try (Connection con = cbd.conectarABaseDatos();
				CallableStatement cs = con.prepareCall("{call sp_eliminar_registros(?,?)}");) {
						
			//Borra registro de Causa
			for (Causa causa : causas) {
				cs.setString(1, causa.getTipoCausa());
				cs.setString(2, causa.getIdDisciplina());
				cs.addBatch();
				log.info("Nombre de Causa: " + causa.getTipoCausa());
			}
			
			//Borra registro de Disciplina
			for (Disciplina disciplina : disciplinas) {
				cs.setString(1, disciplina.getNombreDisciplina());
				cs.setString(2, "disciplina");
				cs.addBatch();
				log.info("Nombre de Disciplina: " + disciplina.getNombreDisciplina());
			}
			
			cs.executeBatch();
			con.commit();
		}
		
		catch (SQLException sqle) {
			log.log(Level.SEVERE, sqle.toString(), sqle);
			JOptionPane.showMessageDialog(null, sqle.getMessage(), sqle.getClass().toString(),
					JOptionPane.ERROR_MESSAGE);

			con.rollback();
			return false;
			
		} 
		catch (Exception e) {
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

			// Area
			cs.setString(1, area.getNombreArea());
			cs.setString(2, "area");
			cs.setString(3, "");
			cs.setInt(4, area.getIdArea());
			cs.addBatch();

			// SubArea
			cs.setString(1, subArea.getNombreSubArea());
			cs.setString(2, "subArea");
			cs.setString(3, "");
			cs.setString(4, subArea.getIdArea());
			cs.addBatch();

			// Disciplina
			cs.setString(1, disciplina.getNombreDisciplina());
			cs.setString(2, "disciplina");
			cs.setString(3, "");
			cs.setInt(4, disciplina.getIdDisciplina());
			cs.addBatch();

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
			}

			cs.executeBatch();
			con.commit();

		} 
		catch (SQLException sqle) {
			log.log(Level.SEVERE, sqle.toString(), sqle);
			JOptionPane.showMessageDialog(null, sqle.getMessage(), sqle.getClass().toString(),
					JOptionPane.ERROR_MESSAGE);

			con.rollback();
			return false;
		}
		catch (Exception e) {
			log.log(Level.SEVERE, e.toString(), e);
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