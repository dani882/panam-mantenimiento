package com.cementopanam.jrivera.controlador.usuario;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JOptionPane;

import com.cementopanam.jrivera.controlador.ManipulacionDatos;
import com.cementopanam.jrivera.modelo.ConeccionBD;

/**
 * @author jrivera
 *
 */
public class AdministracionUsuario extends ManipulacionDatos {

	private static final Logger log = Logger.getLogger(AdministracionUsuario.class.getName());
	private ConeccionBD cbd;
	private Connection con;
	private CallableStatement cs = null;
	private ResultSet rs = null;

	public AdministracionUsuario() {

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

	public boolean registrarUsuario(Usuario usuario) throws SQLException {

		try (Connection con = cbd.conectarABaseDatos();
				CallableStatement cs = con.prepareCall("{call sp_agregar_usuario(?,?,?,?,?,?)}");) {

			cs.setString(1, usuario.getNombreUsuario());
			cs.setString(2, usuario.getPassword());
			cs.setInt(3, usuario.getTipoUsuario());
			cs.setInt(4, Integer.parseInt(usuario.getCodEmpleado()));
			cs.setString(5, usuario.getNombreEmpleado());
			cs.setString(6, usuario.getApellidoEmpleado());

			cs.execute();
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

	public boolean modificarUsuario(Usuario usuario) throws SQLException {

		try (Connection con = cbd.conectarABaseDatos();
				CallableStatement cs = con.prepareCall("{call sp_cambiar_credencial_usuario(?,?,?,?,?)}");) {

			cs.setString(1, usuario.getNombreUsuario());
			cs.setString(2, usuario.getPassword());
			cs.setInt(3, usuario.getTipoUsuario());
			cs.setString(4, usuario.getEstadoUsuario());
			cs.setString(5, usuario.getCodEmpleado());

			cs.execute();
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

	public ResultSet mostrarUsuario(String usuario) throws SQLException {

		try {

			con = cbd.conectarABaseDatos();
			cs = con.prepareCall("{call sp_mostrar_usuario(?)}");

			cs.setString(1, usuario);
			rs = cs.executeQuery();
		}

		catch (SQLException sqle) {
			log.log(Level.SEVERE, sqle.toString(), sqle);
			JOptionPane.showMessageDialog(null, sqle.getMessage(), sqle.getClass().toString(),
					JOptionPane.ERROR_MESSAGE);

		} catch (Exception e) {
			log.log(Level.SEVERE, e.toString(), e);
			JOptionPane.showMessageDialog(null, e.getMessage(), e.getClass().toString(), JOptionPane.ERROR_MESSAGE);

		}

		// finally {
		// cerrarConexiones();
		// }

		return rs;
	}
}