package com.cementopanam.jrivera.vista.internalFrames;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Arrays;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import com.cementopanam.jrivera.controlador.AdministracionUsuario;
import com.cementopanam.jrivera.controlador.Usuario;
import com.cementopanam.jrivera.vista.Principal;

public class AdministracionRegistros2 extends JInternalFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 934194678976157002L;
	private JTextField textFieldNombreUsuario;
	private JPasswordField passwordFieldContrasena;
	private JPasswordField passwordFieldConfirmarContrasena;
	private JComboBox comboBoxTipoUsuario;
	private JTextField textFieldNombreEmpleado;
	private JTextField textFieldApellidoEmpleado;
	private JTextField textFieldCodigoEmpleado;

	private AdministracionUsuario admUsuario = new AdministracionUsuario();
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JRadioButton radioButtonActivo;
	private JRadioButton radioButtonInactivo;
	/**
	 * Crea el frame.
	 */
	public AdministracionRegistros2() {
		initComponents();
	}
	
	private void initComponents() {
		
		setFrameIcon(new ImageIcon(AdministracionRegistros2.class.getResource("/imagenes/logo-panam.png")));
		setTitle("Administracion de Registros");
		setIconifiable(true);
		setClosable(true);
		getContentPane().setFont(new Font("Verdana", Font.PLAIN, 12));
		getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.X_AXIS));
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBorder(UIManager.getBorder("Button.border"));
		tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
		tabbedPane.setFont(new Font("Verdana", Font.PLAIN, 12));
		getContentPane().add(tabbedPane);
		
		JPanel panelRegistros = new JPanel();
		tabbedPane.addTab("Registros", null, panelRegistros, null);
		
		JLabel lblProbando = new JLabel("");
		GroupLayout gl_panelRegistros = new GroupLayout(panelRegistros);
		gl_panelRegistros.setHorizontalGroup(
			gl_panelRegistros.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelRegistros.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblProbando)
					.addContainerGap(413, Short.MAX_VALUE))
		);
		gl_panelRegistros.setVerticalGroup(
			gl_panelRegistros.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelRegistros.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblProbando)
					.addContainerGap(416, Short.MAX_VALUE))
		);
		panelRegistros.setLayout(gl_panelRegistros);
		
		JPanel panelUsuarios = new JPanel();
		tabbedPane.addTab("Usuarios", null, panelUsuarios, null);
		
		JLayeredPane layeredPaneInformacionAcceso = new JLayeredPane();
		layeredPaneInformacionAcceso.setLayout(null);
		layeredPaneInformacionAcceso.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229), 2, true), "Informacion de Acceso", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(51, 51, 51)));
		
		JLabel labelNombreUsuario = new JLabel("Nombre de Usuario");
		labelNombreUsuario.setFont(new Font("Verdana", Font.PLAIN, 12));
		labelNombreUsuario.setBounds(10, 31, 124, 15);
		layeredPaneInformacionAcceso.add(labelNombreUsuario);
		
		JLabel labelConfirmarContrasena = new JLabel("Confirmar contrase\u00F1a");
		labelConfirmarContrasena.setFont(new Font("Verdana", Font.PLAIN, 12));
		labelConfirmarContrasena.setBounds(167, 90, 147, 16);
		layeredPaneInformacionAcceso.add(labelConfirmarContrasena);
		
		textFieldNombreUsuario = new JTextField();
		textFieldNombreUsuario.setFont(new Font("Verdana", Font.PLAIN, 12));
		textFieldNombreUsuario.setColumns(10);
		textFieldNombreUsuario.setBounds(10, 53, 147, 30);
		layeredPaneInformacionAcceso.add(textFieldNombreUsuario);
		
		String[] tipoUsuarios = {"Administrador", "Operador", "Consultor"};
		comboBoxTipoUsuario = new JComboBox(tipoUsuarios);
		comboBoxTipoUsuario.setSelectedIndex(-1);
		comboBoxTipoUsuario.setFont(new Font("Verdana", Font.PLAIN, 12));
		comboBoxTipoUsuario.setBounds(10, 110, 147, 30);
		layeredPaneInformacionAcceso.add(comboBoxTipoUsuario);
		
		JLabel labelTipoUsuario = new JLabel("Tipo de Usuario");
		labelTipoUsuario.setFont(new Font("Verdana", Font.PLAIN, 12));
		labelTipoUsuario.setBounds(10, 90, 124, 16);
		layeredPaneInformacionAcceso.add(labelTipoUsuario);
		
		passwordFieldContrasena = new JPasswordField();
		passwordFieldContrasena.setBounds(167, 54, 147, 30);
		layeredPaneInformacionAcceso.add(passwordFieldContrasena);
		
		passwordFieldConfirmarContrasena = new JPasswordField();
		passwordFieldConfirmarContrasena.setBounds(167, 110, 147, 30);
		layeredPaneInformacionAcceso.add(passwordFieldConfirmarContrasena);
		
		JLayeredPane layeredPaneEstatusUsuario = new JLayeredPane();
		layeredPaneEstatusUsuario.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)), "Estatus Usuario", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(51, 51, 51)));
		layeredPaneEstatusUsuario.setBounds(324, 75, 172, 53);
		layeredPaneInformacionAcceso.add(layeredPaneEstatusUsuario);
		layeredPaneEstatusUsuario.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		radioButtonActivo = new JRadioButton("Activo");
		buttonGroup.add(radioButtonActivo);
		radioButtonActivo.setSelected(true);
		radioButtonActivo.setFont(new Font("Verdana", Font.PLAIN, 12));
		layeredPaneEstatusUsuario.add(radioButtonActivo);
		
		radioButtonInactivo = new JRadioButton("Inactivo");
		buttonGroup.add(radioButtonInactivo);
		radioButtonInactivo.setFont(new Font("Verdana", Font.PLAIN, 12));
		layeredPaneEstatusUsuario.add(radioButtonInactivo);
		
		JLayeredPane layeredPaneDatosEmpleado = new JLayeredPane();
		layeredPaneDatosEmpleado.setLayout(null);
		layeredPaneDatosEmpleado.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229), 2, true), "Datos de Empleado", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(51, 51, 51)));
		
		JLabel labelCodigoEmpleado = new JLabel("Codigo Empleado");
		labelCodigoEmpleado.setFont(new Font("Verdana", Font.PLAIN, 12));
		labelCodigoEmpleado.setBounds(10, 31, 124, 15);
		layeredPaneDatosEmpleado.add(labelCodigoEmpleado);
		
		JLabel labelNombreEmpleado = new JLabel("Nombre");
		labelNombreEmpleado.setFont(new Font("Verdana", Font.PLAIN, 12));
		labelNombreEmpleado.setBounds(10, 98, 49, 16);
		layeredPaneDatosEmpleado.add(labelNombreEmpleado);
		
		textFieldNombreEmpleado = new JTextField();
		textFieldNombreEmpleado.setColumns(10);
		textFieldNombreEmpleado.setBounds(10, 120, 147, 30);
		layeredPaneDatosEmpleado.add(textFieldNombreEmpleado);
		
		JLabel labelApellidoEmpleado = new JLabel("Apellido");
		labelApellidoEmpleado.setFont(new Font("Verdana", Font.PLAIN, 12));
		labelApellidoEmpleado.setBounds(167, 98, 49, 16);
		layeredPaneDatosEmpleado.add(labelApellidoEmpleado);
		
		textFieldApellidoEmpleado = new JTextField();
		textFieldApellidoEmpleado.setColumns(10);
		textFieldApellidoEmpleado.setBounds(167, 120, 147, 30);
		layeredPaneDatosEmpleado.add(textFieldApellidoEmpleado);
		
		textFieldCodigoEmpleado = new JTextField();
		textFieldCodigoEmpleado.setColumns(10);
		textFieldCodigoEmpleado.setBounds(10, 57, 147, 30);
		layeredPaneDatosEmpleado.add(textFieldCodigoEmpleado);
		
		JLayeredPane layeredPaneBotones = new JLayeredPane();
		GroupLayout gl_panelUsuarios = new GroupLayout(panelUsuarios);
		gl_panelUsuarios.setHorizontalGroup(
			gl_panelUsuarios.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelUsuarios.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panelUsuarios.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panelUsuarios.createSequentialGroup()
							.addComponent(layeredPaneDatosEmpleado, GroupLayout.DEFAULT_SIZE, 523, Short.MAX_VALUE)
							.addContainerGap())
						.addGroup(gl_panelUsuarios.createSequentialGroup()
							.addComponent(layeredPaneBotones, GroupLayout.DEFAULT_SIZE, 513, Short.MAX_VALUE)
							.addGap(20))
						.addGroup(gl_panelUsuarios.createSequentialGroup()
							.addComponent(layeredPaneInformacionAcceso, GroupLayout.DEFAULT_SIZE, 523, Short.MAX_VALUE)
							.addContainerGap())))
		);
		gl_panelUsuarios.setVerticalGroup(
			gl_panelUsuarios.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_panelUsuarios.createSequentialGroup()
					.addContainerGap()
					.addComponent(layeredPaneDatosEmpleado, GroupLayout.DEFAULT_SIZE, 165, Short.MAX_VALUE)
					.addGap(18)
					.addComponent(layeredPaneInformacionAcceso, GroupLayout.PREFERRED_SIZE, 172, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(layeredPaneBotones, GroupLayout.PREFERRED_SIZE, 63, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		
		JLabel lblContrasea = new JLabel("Contrase\u00F1a");
		lblContrasea.setFont(new Font("Verdana", Font.PLAIN, 12));
		lblContrasea.setBounds(167, 32, 124, 15);
		layeredPaneInformacionAcceso.add(lblContrasea);
		
		JButton btnLimpiar = new JButton("Limpiar");
		btnLimpiar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limpiarCamposUsuario();
			}
		});
		btnLimpiar.setIcon(new ImageIcon(AdministracionRegistros2.class.getResource("/iconos32x32/clean32x32.png")));
		btnLimpiar.setBounds(351, 11, 152, 41);
		layeredPaneBotones.add(btnLimpiar);
		btnLimpiar.setFont(new Font("Verdana", Font.PLAIN, 12));
		
		JButton buttonGuardarUsuario = new JButton("Guardar");
		buttonGuardarUsuario.setBounds(31, 11, 152, 41);
		layeredPaneBotones.add(buttonGuardarUsuario);
		buttonGuardarUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				agregarUsuario();
			}
		});
		buttonGuardarUsuario.setIcon(new ImageIcon(AdministracionRegistros2.class.getResource("/iconos32x32/registroUsuario32x32.png")));
		buttonGuardarUsuario.setFont(new Font("Verdana", Font.PLAIN, 12));
		
		JButton btnEditar = new JButton("Editar");
		btnEditar.setBounds(191, 11, 152, 41);
		layeredPaneBotones.add(btnEditar);
		btnEditar.setIcon(new ImageIcon(AdministracionRegistros2.class.getResource("/iconos32x32/edit-male-user-icon32x32.png")));
		btnEditar.setFont(new Font("Verdana", Font.PLAIN, 12));
		panelUsuarios.setLayout(gl_panelUsuarios);
	}
	
	private int obtenerTipoUsuario() {
		
		if(comboBoxTipoUsuario.getSelectedIndex() == 0) {
			return 1;
		}
		else if(comboBoxTipoUsuario.getSelectedIndex() == 1) {
			return 2;
		}
		else if(comboBoxTipoUsuario.getSelectedIndex() == 2) {
			return 3;
		}
		else {JOptionPane.showMessageDialog(null, "Debe elegir un tipo de Usuario");
		return 0;
		}
	}
	
	private boolean validarPassword() {
		
		if (Arrays.equals(passwordFieldContrasena.getPassword(), passwordFieldConfirmarContrasena.getPassword())) {
			  return true;
			}
		else	return false;
	}
	
	private void limpiarCamposUsuario() {
		
		textFieldCodigoEmpleado.setText("");
		textFieldNombreEmpleado.setText("");
		textFieldApellidoEmpleado.setText("");
		textFieldNombreUsuario.setText("");
		passwordFieldContrasena.setText("");
		comboBoxTipoUsuario.setSelectedIndex(-1);
		passwordFieldConfirmarContrasena.setText("");
		radioButtonActivo.setSelected(false);
		radioButtonInactivo.setSelected(false);
	}
	
	private boolean agregarUsuario() {
		
		boolean resultado = false;
		//TODO Realizar la comprobacion de si estan todos los campos rellenados
		try{
			
			int codEmpleado = Integer.parseInt(textFieldCodigoEmpleado.getText());
			String nombreEmpleado = textFieldNombreEmpleado.getText();
			String apellidoEmpleado = textFieldApellidoEmpleado.getText();
			String nombreUsuario = textFieldNombreUsuario.getText().toLowerCase().trim();
			char[] password = passwordFieldContrasena.getPassword();

			String clave = "";
			for (char c : password) {
				clave += c;
			}
			
			int tipoUsuario = obtenerTipoUsuario(); 
			
			if(validarPassword() == false) {
				JOptionPane.showMessageDialog(null, "La clave de usuario no coincide", "Clave de Usuario", 
						JOptionPane.WARNING_MESSAGE);
				return resultado;
			}
			
			Usuario usuario = new Usuario(codEmpleado, nombreEmpleado, apellidoEmpleado, nombreUsuario, clave, tipoUsuario);
			
			if(admUsuario.registrarUsuario(usuario) == true) {
				Principal.lblStatusbar.setIcon(new ImageIcon(getClass().getResource("/iconos16x16/ok.png")));
				Principal.lblStatusbar.setText("Usuario " +nombreUsuario+ " creado correctamente");
				limpiarCamposUsuario();
				resultado = true;
			}
			else{
				Principal.lblStatusbar.setIcon(new ImageIcon(getClass().getResource("/iconos16x16/warning-icon.png")));
				Principal.lblStatusbar.setText("No se pudo completar la operacion");
			}
		}
		catch(SQLException sqle) {
			JOptionPane.showMessageDialog(null, sqle.getMessage(), sqle.getClass().toString(),
					JOptionPane.ERROR_MESSAGE);
			resultado = false;
		}
		
		catch(NumberFormatException nfe) {
			JOptionPane.showMessageDialog(null, "Formato de entrada de datos no valido", "Entrada datos invalida",
				JOptionPane.ERROR_MESSAGE);
			resultado = false;
		}
		
		catch(NullPointerException npe) {
			JOptionPane.showMessageDialog(null, "Debe rellenar todos los campos", "Rellenar campos",
					JOptionPane.ERROR_MESSAGE);
			}
		catch(Exception e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage(), e1.getClass().toString(),
					JOptionPane.ERROR_MESSAGE);
			resultado = false;
		}
		return resultado;
	}
}