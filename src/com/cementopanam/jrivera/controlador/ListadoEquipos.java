package com.cementopanam.jrivera.controlador;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JOptionPane;

import com.cementopanam.jrivera.modelo.ConeccionBD;

public class ListadoEquipos {
	
	ConeccionBD cbd;
	Connection con = null;
	CallableStatement cs = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	public ListadoEquipos() {
		
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
	
	public ResultSet mostrarEquipos() {
		
		String sql = "SELECT * FROM mantenimientodb.equipo;";
		
		try {
			con = cbd.conectarABaseDatos();
			
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
				
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), e.getClass().toString(),
					JOptionPane.ERROR_MESSAGE);
		}
		
		System.out.println("Mostrando equipos");
		return rs;
	}
	
	
	
	
	
	
	

}
