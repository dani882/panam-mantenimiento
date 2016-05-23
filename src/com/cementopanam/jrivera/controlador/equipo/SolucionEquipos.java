package com.cementopanam.jrivera.controlador.equipo;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import com.cementopanam.jrivera.modelo.ConeccionBD;

public class SolucionEquipos {

	ConeccionBD cbd;
	Connection con = null;
	CallableStatement cs = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;

	public SolucionEquipos() {

		cbd = ConeccionBD.getInstance();

		if (!cbd.verificarConexion()) {
			try {
				cbd.conectarABaseDatos();
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, e.getMessage(), e.getClass().toString(), JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	public ArrayList<NombreEquipo> mostrarSoluciones(String codigo) {

		ArrayList<NombreEquipo> solucionEquipo = new ArrayList<NombreEquipo>();

		try(Connection con = cbd.conectarABaseDatos();
				CallableStatement cs = con.prepareCall("{call sp_buscar_solucion_equipo(?)}");) {

			cs.setString(1, codigo);
			rs = cs.executeQuery();

			while (rs.next()) {

				solucionEquipo.add(new NombreEquipo(rs.getString(1), rs.getString(2), 
						rs.getString(3), rs.getString(4), rs.getString(5)));
			}
		}

		catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), e.getClass().toString(), JOptionPane.ERROR_MESSAGE);
		}
		return solucionEquipo;
	}
}