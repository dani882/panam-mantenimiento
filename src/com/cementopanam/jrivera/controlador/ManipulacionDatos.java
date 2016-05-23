package com.cementopanam.jrivera.controlador;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JOptionPane;

import com.cementopanam.jrivera.modelo.ConeccionBD;

public class ManipulacionDatos {

	private static final Logger log = Logger.getLogger(ManipulacionDatos.class.getName());
	private ConeccionBD cbd;
	private Connection con = null;
	private CallableStatement cs = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;

	public ManipulacionDatos() {

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

	/**
	 * Conecta a la Base de Datos
	 * @return la conexion a la base de datos
	 */
	public Connection obtenerConexion() {

		try {

			cbd = ConeccionBD.getInstance();
			con = cbd.conectarABaseDatos();
		} catch (Exception e) {
			log.log(Level.SEVERE, e.toString(), e);
			JOptionPane.showMessageDialog(null, e.getMessage(), e.getClass().toString(), JOptionPane.ERROR_MESSAGE);
		}
		return con;
	}

	/**
	 * Agrega elementos a los comboBox
	 * 
	 * @param sentencia - condicion para saber que elemento para que comboBox rellenar
	 * @param indice - indice solicitado por un elemento
	 * @return ResultSet de elementos para el comboBox
	 */
	public ResultSet rellenarCombo(String sentencia, String indice) throws SQLException {
		
		try {
			
			con = cbd.conectarABaseDatos();
			cs = con.prepareCall("{call sp_rellenar_comboBox(?,?)}");
			
			cs.setString(1, sentencia);
			cs.setString(2, indice);
			
		}
		catch (SQLException sqle) {
			log.log(Level.SEVERE, sqle.toString(), sqle);
			JOptionPane.showMessageDialog(null, sqle.getMessage()); 
		}
		catch (Exception e) {
			log.log(Level.SEVERE, e.toString(), e); 
			JOptionPane.showMessageDialog(null, e.getMessage()); }
	
		return rs = cs.executeQuery();
	}
	 


	/**
	 * Visualiza el estado de los paros
	 * 
	 * @param estatus
	 *            estado el paro - Parametro para visualizar los estados de los
	 *            Paros
	 * @return Resultset - Retorna el listado de paros
	 */
	public ResultSet actualizarTabla(String estatus) throws SQLException {

		try {
			con = cbd.conectarABaseDatos();
		} catch (Exception e) {
			log.log(Level.SEVERE, e.toString(), e);
			JOptionPane.showMessageDialog(null, e.getMessage(), e.getClass().toString(), JOptionPane.ERROR_MESSAGE);
		}

		cs = con.prepareCall("{call Paros(?)}");

		cs.setString(1, estatus);

		return rs = cs.executeQuery();
	}

	/**
	 * Busca el indice de un campo
	 * 
	 * @param indice
	 *            - atributo que se desea buscar indice
	 * @param sentencia
	 *            - ejecuta la consulta de la condicion proporcionada
	 * @return Retorna el indice de un atributo
	 */
	public int buscarIndice(String indice, String sentencia) {

		int resultado = 0;

		try {
			con = cbd.conectarABaseDatos();
			cs = con.prepareCall("{call buscar_indice(?,?,?)}");

			cs.setString(1, indice);
			cs.setString(2, sentencia);

			cs.registerOutParameter(3, java.sql.Types.INTEGER);
			cs.executeUpdate();

			resultado = cs.getInt(3);

		} catch (SQLException sqle) {
			log.log(Level.SEVERE, sqle.toString(), sqle);
			JOptionPane.showMessageDialog(null, sqle.getMessage(), sqle.getClass().toString(),
					JOptionPane.ERROR_MESSAGE);
			
		} catch (Exception e) {
			log.log(Level.SEVERE, e.toString(), e);
			JOptionPane.showMessageDialog(null, e.getMessage(), e.getClass().toString(), JOptionPane.ERROR_MESSAGE);
		}
		return resultado;
	}

	/**
	 * Compara las fechas indicando cual es el mayor
	 * 
	 * @param fechaInicio
	 *            - Fecha de Inicio que se va a comparar
	 * @param fechaFin
	 *            - Fecha de Fin que se va a comparar
	 * @param formatoFecha
	 *            - Formato de comparacion
	 * @return true, si la fecha de fin es mayor a la Fecha de Inicio. false, si
	 *         la Fecha de Inicio es mayor a la Fecha de Fin
	 */
	public boolean compararFecha(String fechaInicio, String fechaFin, String formatoFecha) {

		if (!(fechaFin == (null))) {
			// Verifica si la fecha de Fin es Mayor a Fecha Inicio de Paro
			ComparacionFechas cf = new ComparacionFechas();
			boolean comparacionFechas = cf.compararFechas(fechaInicio, fechaFin, formatoFecha);

			if (comparacionFechas == true) {

				try {
					JOptionPane.showMessageDialog(null,
							"La fecha Fin no puede ser Mayor a la Fecha de Inicio de Paro", "Comparacion Fechas",
							JOptionPane.ERROR_MESSAGE);

					return false;
				} catch (Exception e) {
					log.log(Level.WARNING, e.toString(), e);
				}
			}
		}
		return true;
	}
	
	/**
	 * Genera el listado de las disciplinas para mostrarlas en un JList
	 * @param lista - JList que se va a poblar
	 * @throws SQLException
	 */
	public void poblarJList(JList<String> lista) throws SQLException {
		
		DefaultListModel<String> modelo = new DefaultListModel<String>();
		
		try(Connection  con = cbd.conectarABaseDatos();
			    CallableStatement cs = con.prepareCall("{call sp_listar_disciplina()}");) {

		    ResultSet resultSet = cs.executeQuery();

		    while (resultSet.next()) {
		    	
		        String nombreDisciplina = resultSet.getString("nombre_disciplina"); 
		        modelo.addElement(nombreDisciplina);
		    }
		    lista.setModel(modelo);

		}
		catch (SQLException sqle) {
			log.log(Level.SEVERE, sqle.toString(), sqle);
			JOptionPane.showMessageDialog(null, sqle.getMessage(), sqle.getClass().toString(),
					JOptionPane.ERROR_MESSAGE);

		} catch (Exception e) {
			log.log(Level.SEVERE, e.toString(), e);
			JOptionPane.showMessageDialog(null, e.getMessage(), e.getClass().toString(), JOptionPane.ERROR_MESSAGE);
		}
	}

	/**
	 * Autentica el usuario
	 * 
	 * @param user
	 *            - usuario
	 * @param password
	 *            - clave
	 * @return Resultado de la busqueda de usuario
	 * @throws SQLException
	 */
	public ResultSet autenticarUsuario(String user, String password) throws SQLException {

		try {

			con = cbd.conectarABaseDatos();
		} catch (Exception e) {
			log.log(Level.SEVERE, e.toString(), e);
			JOptionPane.showMessageDialog(null, e.getMessage(), e.getClass().toString(), JOptionPane.ERROR_MESSAGE);
		}

		// TODO Modificar este procedimiento para que tenga un OUT de estatus
		cs = con.prepareCall("{call logearUsuario(?,?)}");

		// String estatus = cs.getString("estatus");

		cs.setString(1, user);
		cs.setString(2, password);

		return rs = cs.executeQuery();
	}

	/**
	 * Cierra todas las conexiones de Base de Datos
	 * 
	 */
	public void cerrarConexiones() {

		try {

			if (rs != null) {
				rs.close();
			}

			if (pstmt != null) {
				pstmt.close();
			}

			if (cs != null) {
				cs.close();
			}

			cbd.cerrarConexion();
		} catch (SQLException sqle) {
			log.log(Level.SEVERE, sqle.toString(), sqle);
			JOptionPane.showMessageDialog(null, sqle.getMessage(), sqle.getClass().toString(),
					JOptionPane.ERROR_MESSAGE);

		} catch (Exception e) {
			log.log(Level.SEVERE, e.toString(), e);
			JOptionPane.showMessageDialog(null, e.getMessage(), e.getClass().toString(), 
					JOptionPane.ERROR_MESSAGE);
		}
	}
}