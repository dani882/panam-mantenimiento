package com.cementopanam.jrivera.controlador.paros;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import com.cementopanam.jrivera.controlador.ManipulacionDatos;
import com.cementopanam.jrivera.modelo.ConeccionBD;

public class AdministracionParos extends ManipulacionDatos{

	private ConeccionBD cbd;
	private Connection con = null;
	private CallableStatement cs = null;
	private ResultSet rs = null;
	
	public AdministracionParos() {
		
		cbd = ConeccionBD.getInstance();
		
		if (!cbd.verificarConexion()) {
			try {
				cbd.conectarABaseDatos();
			}
			catch(Exception e) {
				JOptionPane.showMessageDialog(null, e.getMessage(), e.getClass().toString(),
						JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	
	public ArrayList<Paro> mostrarParo() {
		
		ArrayList<Paro> lista = new ArrayList<Paro>();
		try {
			con = cbd.conectarABaseDatos();
			cs = con.prepareCall("{call sp_buscar_paro()}");
			rs = cs.executeQuery();
			
			while(rs.next()) {
				
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
				
				lista.add(new Paro(codigo, usuario, area, subArea, equipo, tiempoInicio, tiempoFin, solucion, causa, descripcionAdicional, disciplina));	
			}
		}
		catch (SQLException sqle) {
			JOptionPane.showMessageDialog(null, sqle.getMessage(), sqle.getClass().toString(),
					JOptionPane.ERROR_MESSAGE);
		}
		catch(Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), e.getClass().toString(),
					JOptionPane.ERROR_MESSAGE);
		}
		
		return lista;
	}
	
	
/*	public void poblarTabla(JTable tabla) {
	   
		try {
	        rs = mostrarParo();
	        System.out.println("Poblando tabla");

	        //To remove previously added rows
	  //      while(tabla.getRowCount() > 0) 
	  //      {
	  //          ((DefaultTableModel) tabla.getModel()).removeRow(0);
	   //     }
	        int columns = rs.getMetaData().getColumnCount();
	        while(rs.next())
	        {
	            Object[] row = new Object[columns];
	            for (int i = 1; i <= columns; i++)
	            {  
	                row[i - 1] = rs.getObject(i);
	            }
	            ((DefaultTableModel) tabla.getModel()).insertRow(rs.getRow()-1,row);
	        }

	        System.out.println("Termine");
	    }
	    catch(SQLException sqle) {
	    	JOptionPane.showMessageDialog(null, sqle.getMessage(), sqle.getClass().toString(),
					JOptionPane.ERROR_MESSAGE);
	    }
		catch(Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), e.getClass().toString(),
					JOptionPane.ERROR_MESSAGE);
		}
		finally{
			md.cerrarConexiones();
		}
	}*/
}