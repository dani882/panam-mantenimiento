package com.cementopanam.jrivera.vista.internalFrames;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Arrays;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import com.cementopanam.jrivera.controlador.usuario.AdministracionUsuario;
import com.cementopanam.jrivera.controlador.usuario.Usuario;
import com.cementopanam.jrivera.vista.ModificacionUsuario;
import com.cementopanam.jrivera.vista.Principal;

public class AdministracionUsuarios extends JInternalFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6053479021274723939L;
	private JTextField txtNombre;
	private JTextField txtApellido;
	private JTextField txtCodEmpleado;
	private JTextField txtNombreUsuario;
	private JPasswordField pwdClave;
	private JPasswordField pwdConfirmarClave;
	private JButton btnLimpiar;
	private JButton btnEditar;
	private JComboBox<Object> cbTipoUsuario;

	private AdministracionUsuario admUsuario = new AdministracionUsuario();

	/**
	 * Create the frame.
	 */
	public AdministracionUsuarios() {
		getContentPane().setFont(new Font("Verdana", Font.PLAIN, 12));
		setFrameIcon(null);
		setClosable(true);
		setIconifiable(true);

		setTitle("Administracion de Usuarios");
		getContentPane().setEnabled(false);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(50, 50, 439, 526);

		JLayeredPane layeredPaneDatosEmpleado = new JLayeredPane();
		layeredPaneDatosEmpleado.setBounds(10, 58, 392, 170);
		layeredPaneDatosEmpleado.setLayout(null);
		layeredPaneDatosEmpleado.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229), 2, true),
				"Datos de Empleado", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(51, 51, 51)));

		JLabel lblCodEmpleado = new JLabel("Codigo Empleado");
		lblCodEmpleado.setFont(new Font("Verdana", Font.PLAIN, 12));
		lblCodEmpleado.setBounds(35, 29, 124, 15);
		layeredPaneDatosEmpleado.add(lblCodEmpleado);

		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setFont(new Font("Verdana", Font.PLAIN, 12));
		lblNombre.setBounds(35, 96, 49, 16);
		layeredPaneDatosEmpleado.add(lblNombre);

		txtNombre = new JTextField();
		txtNombre.setFont(new Font("Verdana", Font.PLAIN, 12));
		txtNombre.setColumns(10);
		txtNombre.setBounds(35, 118, 147, 30);
		layeredPaneDatosEmpleado.add(txtNombre);

		JLabel lblApellido = new JLabel("Apellido");
		lblApellido.setFont(new Font("Verdana", Font.PLAIN, 12));
		lblApellido.setBounds(192, 96, 49, 16);
		layeredPaneDatosEmpleado.add(lblApellido);

		txtApellido = new JTextField();
		txtApellido.setFont(new Font("Verdana", Font.PLAIN, 12));
		txtApellido.setColumns(10);
		txtApellido.setBounds(192, 118, 147, 30);
		layeredPaneDatosEmpleado.add(txtApellido);

		txtCodEmpleado = new JTextField();
		txtCodEmpleado.setFont(new Font("Verdana", Font.PLAIN, 12));
		txtCodEmpleado.setColumns(10);
		txtCodEmpleado.setBounds(35, 55, 147, 30);
		layeredPaneDatosEmpleado.add(txtCodEmpleado);

		JLayeredPane layeredPaneAcceso = new JLayeredPane();
		layeredPaneAcceso.setBounds(10, 239, 392, 172);
		layeredPaneAcceso.setLayout(null);
		layeredPaneAcceso.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229), 2, true),
				"Informacion de Acceso", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(51, 51, 51)));

		JLabel lblNombreUsuario = new JLabel("Nombre de Usuario");
		lblNombreUsuario.setFont(new Font("Verdana", Font.PLAIN, 12));
		lblNombreUsuario.setBounds(35, 31, 124, 15);
		layeredPaneAcceso.add(lblNombreUsuario);

		JLabel lblConfirmarClave = new JLabel("Confirmar contrase\u00F1a");
		lblConfirmarClave.setFont(new Font("Verdana", Font.PLAIN, 12));
		lblConfirmarClave.setBounds(192, 90, 147, 16);
		layeredPaneAcceso.add(lblConfirmarClave);

		txtNombreUsuario = new JTextField();
		txtNombreUsuario.setFont(new Font("Verdana", Font.PLAIN, 12));
		txtNombreUsuario.setColumns(10);
		txtNombreUsuario.setBounds(35, 53, 147, 30);
		layeredPaneAcceso.add(txtNombreUsuario);

		String[] tipoUsuarios = { "Administrador", "Operador", "Consultor" };
		cbTipoUsuario = new JComboBox<Object>(tipoUsuarios);
		cbTipoUsuario.setSelectedIndex(-1);
		cbTipoUsuario.setFont(new Font("Verdana", Font.PLAIN, 12));
		cbTipoUsuario.setBounds(35, 110, 147, 30);
		layeredPaneAcceso.add(cbTipoUsuario);

		JLabel lblTipoUsuario = new JLabel("Tipo de Usuario");
		lblTipoUsuario.setFont(new Font("Verdana", Font.PLAIN, 12));
		lblTipoUsuario.setBounds(35, 90, 124, 16);
		layeredPaneAcceso.add(lblTipoUsuario);

		pwdClave = new JPasswordField();
		pwdClave.setFont(new Font("Verdana", Font.PLAIN, 12));
		pwdClave.setBounds(192, 54, 147, 30);
		layeredPaneAcceso.add(pwdClave);

		pwdConfirmarClave = new JPasswordField();
		pwdConfirmarClave.setFont(new Font("Verdana", Font.PLAIN, 12));
		pwdConfirmarClave.setBounds(192, 110, 147, 30);
		layeredPaneAcceso.add(pwdConfirmarClave);

		JLabel lblClave = new JLabel("Contrase\u00F1a");
		lblClave.setFont(new Font("Verdana", Font.PLAIN, 12));
		lblClave.setBounds(192, 32, 124, 15);
		layeredPaneAcceso.add(lblClave);

		JLayeredPane layeredPaneBotones = new JLayeredPane();
		layeredPaneBotones.setBorder(new LineBorder(new Color(184, 207, 229), 2, true));
		layeredPaneBotones.setBounds(10, 422, 392, 55);
		layeredPaneBotones.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JButton btnGuardar = new JButton("Guardar");
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				agregarUsuario();
			}
		});
		btnGuardar.setIcon(
				new ImageIcon(AdministracionUsuarios.class.getResource("/iconos32x32/registroUsuario32x32.png")));
		btnGuardar.setFont(new Font("Verdana", Font.PLAIN, 12));
		layeredPaneBotones.add(btnGuardar);

		btnEditar = new JButton("Editar");
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				ModificacionUsuario modUsuario = new ModificacionUsuario();
				modUsuario.setVisible(true);
			}
		});
		btnEditar.setIcon(
				new ImageIcon(AdministracionUsuarios.class.getResource("/iconos32x32/edit-male-user-icon32x32.png")));
		btnEditar.setFont(new Font("Verdana", Font.PLAIN, 12));
		layeredPaneBotones.add(btnEditar);

		btnLimpiar = new JButton("Limpiar");
		btnLimpiar.setIcon(new ImageIcon(AdministracionUsuarios.class.getResource("/iconos32x32/clean32x32.png")));
		btnLimpiar.setFont(new Font("Verdana", Font.PLAIN, 12));
		layeredPaneBotones.add(btnLimpiar);
		getContentPane().setLayout(null);
		getContentPane().add(layeredPaneDatosEmpleado);
		getContentPane().add(layeredPaneAcceso);
		getContentPane().add(layeredPaneBotones);
		
		JPanel panelTitulo = new JPanel();
		panelTitulo.setBackground(Color.BLACK);
		panelTitulo.setBounds(0, 11, 433, 36);
		getContentPane().add(panelTitulo);
		
		JLabel lblNuevoUsuario = new JLabel("Agregar Nuevo Usuario");
		lblNuevoUsuario.setForeground(Color.WHITE);
		lblNuevoUsuario.setFont(new Font("Verdana", Font.BOLD, 16));
		panelTitulo.add(lblNuevoUsuario);
	}

	private int obtenerTipoUsuario() {

		if (cbTipoUsuario.getSelectedIndex() == 0) {
			return 1;
		} else if (cbTipoUsuario.getSelectedIndex() == 1) {
			return 2;
		} else if (cbTipoUsuario.getSelectedIndex() == 2) {
			return 3;
		} else {
			JOptionPane.showMessageDialog(null, "Debe elegir un tipo de Usuario");
			return 0;
		}
	}

	private boolean validarPassword() {

		if (Arrays.equals(pwdClave.getPassword(), pwdConfirmarClave.getPassword())) {
			return true;
		} else
			return false;
	}

	private void limpiarCamposUsuario() {

		txtCodEmpleado.setText("");
		txtNombre.setText("");
		txtApellido.setText("");
		txtNombreUsuario.setText("");
		pwdClave.setText("");
		cbTipoUsuario.setSelectedIndex(-1);
		pwdConfirmarClave.setText("");
		// radioButtonActivo.setSelected(false);
		// radioButtonInactivo.setSelected(false);
	}

	private boolean agregarUsuario() {

		// TODO Realizar la comprobacion de si estan todos los campos rellenados
		try {

			String codEmpleado = txtCodEmpleado.getText();
			String nombreEmpleado = txtNombre.getText();
			String apellidoEmpleado = txtApellido.getText();
			String nombreUsuario = txtNombreUsuario.getText().toLowerCase().trim();
			char[] password = pwdClave.getPassword();

			String clave = "";
			for (char c : password) {
				clave += c;
			}

			int tipoUsuario = obtenerTipoUsuario();

			if (validarPassword() == false) {
				JOptionPane.showMessageDialog(null, "La clave de usuario no coincide", "Clave de Usuario",
						JOptionPane.WARNING_MESSAGE);
				return false;
			}

			Usuario usuario = new Usuario(codEmpleado, nombreEmpleado, apellidoEmpleado, nombreUsuario, clave,
					tipoUsuario);

			if (admUsuario.registrarUsuario(usuario) == true) {
				Principal.lblStatusBar.setIcon(new ImageIcon(getClass().getResource("/iconos16x16/ok.png")));
				Principal.lblStatusBar.setText("Usuario " + nombreUsuario + " creado correctamente");
				limpiarCamposUsuario();
				return true;
			} else {
				Principal.lblStatusBar.setIcon(new ImageIcon(getClass().getResource("/iconos16x16/warning-icon.png")));
				Principal.lblStatusBar.setText("No se pudo completar la operacion");
			}
		} catch (SQLException sqle) {
			JOptionPane.showMessageDialog(null, sqle.getMessage(), sqle.getClass().toString(),
					JOptionPane.ERROR_MESSAGE);
			return false;
		}

		catch (NumberFormatException nfe) {
			JOptionPane.showMessageDialog(null, "Formato de entrada de datos no valido", "Entrada datos invalida",
					JOptionPane.ERROR_MESSAGE);
			return false;
		}

		catch (NullPointerException npe) {
			JOptionPane.showMessageDialog(null, "Debe rellenar todos los campos", "Rellenar campos",
					JOptionPane.ERROR_MESSAGE);
			return false;
		} catch (Exception e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage(), e1.getClass().toString(), JOptionPane.ERROR_MESSAGE);
			return false;
		}
		return true;
	}
}