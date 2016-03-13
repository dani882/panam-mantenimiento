package com.cementopanam.jrivera.controlador.listadoEquipos;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

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
	
	public ArrayList<NombreEquipo> mostrarEquipos(String codigo) {
		
		ArrayList<NombreEquipo> listaEquipo = new ArrayList<NombreEquipo>();

		try {
			con = cbd.conectarABaseDatos();
			cs = con.prepareCall("{call sp_buscar_equipo(?)}");
			
			cs.setString(1, codigo);
			rs = cs.executeQuery();
			
			
			while(rs.next()) {
				listaEquipo.add(new NombreEquipo(rs.getString(2), rs.getString(3)));
				}
			}
			
		 catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), e.getClass().toString(),
					JOptionPane.ERROR_MESSAGE);
		}
		return listaEquipo;
	}
}
