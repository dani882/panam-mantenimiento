package com.cementopanam.jrivera.controlador;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import com.cementopanam.jrivera.modelo.ConeccionBD;

/**
 * @author jrivera
 *
 */
public class AdministracionUsuario {

	private ConeccionBD cbd;
	private Connection con = null;
	private CallableStatement cs = null;
	private ResultSet rs = null;
	private ManipulacionDatos md = new ManipulacionDatos();
	
	public AdministracionUsuario() {
		
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
	
	public boolean registrarUsuario(Usuario usuario) throws SQLException {
		
		boolean resultado = false;
		
		try{
		con = cbd.conectarABaseDatos();
		cs = con.prepareCall("{call sp_agregar_usuario(?,?,?,?,?,?)}");
		
		cs.setString(1, usuario.getNombreUsuario());
		cs.setString(2, usuario.getPassword());
		cs.setInt(3, usuario.getTipoUsuario());
		cs.setInt(4, usuario.getCodEmpleado());
		cs.setString(5, usuario.getNombreEmpleado());
		cs.setString(6, usuario.getApellidoEmpleado());
		
		cs.executeQuery();
		resultado = true;
		con.commit();
		
		}
		catch(SQLException sqle) {
			JOptionPane.showMessageDialog(null, sqle.getMessage(), sqle.getClass().toString(),
					JOptionPane.ERROR_MESSAGE);
			
			con.rollback();
			return resultado = false;
			
		}catch(Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), e.getClass().toString(),
					JOptionPane.ERROR_MESSAGE);
			
			con.rollback();
			return resultado = false;
		}
		
		finally{
			md.cerrarConexiones();
		}
		return resultado;
	}
	
	
	public boolean cambiarClave(Usuario usuario) throws SQLException{
		
		boolean resultado = false;
		
		try{
		con = cbd.conectarABaseDatos();
		cs = con.prepareCall("{call sp_cambiar_clave(?,?,?,?,?,?)}");
		
		cs.setString(1, usuario.getNombreUsuario());
		cs.setString(2, usuario.getPassword());
		cs.setInt(3, usuario.getTipoUsuario());
		cs.setInt(4, usuario.getCodEmpleado());
		cs.setString(5, usuario.getNombreEmpleado());
		cs.setString(6, usuario.getApellidoEmpleado());
		
		cs.executeQuery();
		resultado = true;
		con.commit();
		
		}
		catch(SQLException sqle) {
			JOptionPane.showMessageDialog(null, sqle.getMessage(), sqle.getClass().toString(),
					JOptionPane.ERROR_MESSAGE);
			
			con.rollback();
			return resultado = false;
			
		}catch(Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), e.getClass().toString(),
					JOptionPane.ERROR_MESSAGE);
			
			con.rollback();
			return resultado = false;
		}
		return resultado;	
	}	
}