package com.cementopanam.jrivera.controlador.equipo;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import org.apache.log4j.Logger;

import com.cementopanam.jrivera.modelo.ConeccionBD;

public class SolucionEquipos {

	private static final Logger logger = Logger.getLogger(SolucionEquipos.class);
	ConeccionBD cbd;
	Connection con = null;
	CallableStatement cs = null;
	ResultSet rs = null;

	public SolucionEquipos() {

		cbd = ConeccionBD.getInstance();

		if (!cbd.verificarConexion()) {
			try {
				cbd.conectarABaseDatos();
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
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
			logger.error(e.getMessage(), e);
			JOptionPane.showMessageDialog(null, e.getMessage(), e.getClass().toString(), JOptionPane.ERROR_MESSAGE);
		}
		return solucionEquipo;
	}
}