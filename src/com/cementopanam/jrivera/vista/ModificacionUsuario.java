package com.cementopanam.jrivera.vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import javax.swing.border.TitledBorder;

import com.cementopanam.jrivera.controlador.usuario.AdministracionUsuario;
import com.cementopanam.jrivera.controlador.usuario.Usuario;

public class ModificacionUsuario extends JDialog {

	/**
	 * 
	 */
	private static final Logger log = Logger.getLogger(ModificacionUsuario.class.getName());
	private static final long serialVersionUID = 8299667867069516816L;
	private final JPanel contentPanel = new JPanel();
	private JComboBox<String> cbUsuario;
	private JPasswordField pwdClave;
	private JPasswordField pwdRepetirClave;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JComboBox<Object> cbTipoUsuario;

	private String usuarioActual = Principal.usuarioActual.getText();
	private JRadioButton rdbtnActivo;
	private JRadioButton rdbtnInactivo;

	private ResultSet rs = null;
	private AdministracionUsuario admUsuario = new AdministracionUsuario();

	/**
	 * Create the dialog.
	 */
	public ModificacionUsuario() {

		setModal(true);
		setLocationRelativeTo(null);
		setFont(new Font("Verdana", Font.PLAIN, 12));
		setTitle("Modificacion de Usuario");
		setBounds(100, 100, 401, 343);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);

		JLabel lblUsuario = new JLabel("Usuario");
		lblUsuario.setFont(new Font("Verdana", Font.PLAIN, 12));

		cbUsuario = new JComboBox<String>();
		cbUsuario.setFont(new Font("Verdana", Font.PLAIN, 12));
		cbUsuario.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {

				if (e.getStateChange() == ItemEvent.SELECTED) {

					mostrarUsuario(String.valueOf(e.getItem()));
					mostrarUsuario();

				}
			}
		});

		JLabel lblClaveNueva = new JLabel("Clave Nueva");
		lblClaveNueva.setFont(new Font("Verdana", Font.PLAIN, 12));

		JLabel lblRepetirClave = new JLabel("Repetir Clave");
		lblRepetirClave.setFont(new Font("Verdana", Font.PLAIN, 12));

		pwdClave = new JPasswordField();
		pwdClave.setFont(new Font("Verdana", Font.PLAIN, 12));

		pwdRepetirClave = new JPasswordField();
		pwdRepetirClave.setFont(new Font("Verdana", Font.PLAIN, 12));

		JLayeredPane layeredPane = new JLayeredPane();
		layeredPane.setBorder(
				new TitledBorder(null, "Estado de Usuario", TitledBorder.LEADING, TitledBorder.TOP, null, null));

		JLabel lblTipoDeUsuario = new JLabel("Tipo de Usuario");
		lblTipoDeUsuario.setFont(new Font("Verdana", Font.PLAIN, 12));

		String[] tipoUsuarios = { "Administrador", "Operador", "Consultor" };
		cbTipoUsuario = new JComboBox<Object>(tipoUsuarios);
		cbTipoUsuario.setFont(new Font("Verdana", Font.PLAIN, 12));
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPanel.createSequentialGroup().addContainerGap().addGroup(gl_contentPanel
						.createParallelGroup(Alignment.TRAILING).addGroup(gl_contentPanel
								.createSequentialGroup().addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_contentPanel.createSequentialGroup()
												.addComponent(lblTipoDeUsuario, GroupLayout.DEFAULT_SIZE,
														GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
												.addPreferredGap(ComponentPlacement.UNRELATED))
										.addGroup(gl_contentPanel.createSequentialGroup()
												.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
														.addGroup(gl_contentPanel.createSequentialGroup()
																.addComponent(lblClaveNueva, GroupLayout.DEFAULT_SIZE,
																		88, Short.MAX_VALUE)
																.addGap(20))
														.addGroup(gl_contentPanel.createSequentialGroup()
																.addComponent(lblUsuario, GroupLayout.DEFAULT_SIZE, 59,
																		Short.MAX_VALUE)
																.addGap(49))
														.addGroup(gl_contentPanel.createSequentialGroup()
																.addComponent(lblRepetirClave, GroupLayout.DEFAULT_SIZE,
																		105, Short.MAX_VALUE)
																.addGap(3)))
												.addGap(0)))
								.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
										.addComponent(pwdClave, Alignment.TRAILING, 175, 175, Short.MAX_VALUE)
										.addComponent(cbUsuario, Alignment.TRAILING, 0, 175, Short.MAX_VALUE)
										.addComponent(pwdRepetirClave, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE,
												175, Short.MAX_VALUE)
										.addComponent(cbTipoUsuario, 0, 175, Short.MAX_VALUE)))
						.addComponent(layeredPane, GroupLayout.PREFERRED_SIZE, 183, GroupLayout.PREFERRED_SIZE))
						.addGap(83)));
		gl_contentPanel.setVerticalGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING).addGroup(gl_contentPanel
				.createSequentialGroup().addGap(29)
				.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE).addComponent(lblUsuario)
						.addComponent(cbUsuario, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE))
				.addPreferredGap(ComponentPlacement.UNRELATED)
				.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE).addComponent(lblClaveNueva)
						.addComponent(pwdClave, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE))
				.addPreferredGap(ComponentPlacement.UNRELATED)
				.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblRepetirClave, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE)
						.addComponent(pwdRepetirClave, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE))
				.addPreferredGap(ComponentPlacement.UNRELATED)
				.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE).addComponent(lblTipoDeUsuario)
						.addComponent(cbTipoUsuario, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE))
				.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
				.addComponent(layeredPane, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
						GroupLayout.PREFERRED_SIZE)
				.addContainerGap()));
		layeredPane.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		rdbtnActivo = new JRadioButton("Activo");
		rdbtnActivo.setFont(new Font("Verdana", Font.PLAIN, 12));
		layeredPane.add(rdbtnActivo);
		buttonGroup.add(rdbtnActivo);

		rdbtnInactivo = new JRadioButton("Inactivo");
		rdbtnInactivo.setFont(new Font("Verdana", Font.PLAIN, 12));
		layeredPane.add(rdbtnInactivo);
		buttonGroup.add(rdbtnInactivo);
		contentPanel.setLayout(gl_contentPanel);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBorder(new MatteBorder(1, 0, 0, 0, (Color) new Color(0, 0, 0)));
			buttonPane.setLayout(new FlowLayout(FlowLayout.CENTER));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Modificar");
				okButton.setIcon(new ImageIcon(
						ModificacionUsuario.class.getResource("/iconos32x32/edit-male-user-icon32x32.png")));
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						modificarUsuario();
					}
				});
				okButton.setFont(new Font("Verdana", Font.PLAIN, 12));
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancelar");
				cancelButton
						.setIcon(new ImageIcon(ModificacionUsuario.class.getResource("/iconos32x32/fail32x32.png")));
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				cancelButton.setFont(new Font("Verdana", Font.PLAIN, 12));
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

		mostrarUsuario();
		cbUsuario.setSelectedItem(usuarioActual);
	}

	private boolean validarPassword() {

		if (Arrays.equals(pwdClave.getPassword(), pwdRepetirClave.getPassword())) {
			return true;
		} else
			return false;
	}

	private int obtenerTipoUsuario() {

		// Elige un Administrador
		if (cbTipoUsuario.getSelectedIndex() == 0) {
			return 1;
			// Elige un Operador
		} else if (cbTipoUsuario.getSelectedIndex() == 1) {
			return 2;
			// Elige un Consultor
		} else if (cbTipoUsuario.getSelectedIndex() == 2) {
			return 3;
		} else {
			return 0;
		}
	}

	private String obtenerEstadoUsuario() {

		if (rdbtnActivo.isSelected()) {
			return rdbtnActivo.getText().toLowerCase();
		} else if (rdbtnInactivo.isSelected()) {
			return rdbtnInactivo.getText().toLowerCase();
		}
		return null;
	}

	private void modificarUsuario() {

		String usuario = String.valueOf(cbUsuario.getSelectedItem());
		int tipoUsuario = obtenerTipoUsuario();
		String estadoUsuario = obtenerEstadoUsuario();
		char[] password = pwdClave.getPassword();

		String clave = "";
		for (char c : password) {
			clave += c;
		}

		// Valida si las claves coinciden
		if (validarPassword() == false) {
			JOptionPane.showMessageDialog(null, "La clave de usuario no coincide", "Clave de Usuario",
					JOptionPane.WARNING_MESSAGE);
			return;
		}

		// Valida si se eligio un tipo de Usuario
		if (tipoUsuario == 0) {
			JOptionPane.showMessageDialog(null, "Debe elegir un tipo de Usuario");
			return;
		}

		// Valida si se eligio un Estado de Usuario
		if (estadoUsuario.equals(null)) {
			JOptionPane.showMessageDialog(null, "Debe elegir un Estado de Usuario");
			return;
		}

		// Busca ID de Usuario
		int idUsuario = Integer
				.parseInt(admUsuario.buscarIndice(String.valueOf(cbUsuario.getSelectedItem()), "usuario"));

		try {
			boolean resultado = false;
			if (clave.length() == 0) {

				resultado = admUsuario
						.modificarUsuario(new Usuario(idUsuario, tipoUsuario, usuario, null, estadoUsuario));
			} else {
				resultado = admUsuario
						.modificarUsuario(new Usuario(idUsuario, tipoUsuario, usuario, clave, estadoUsuario));
			}

			if (resultado == true) {
				Principal.lblStatusBar.setIcon(new ImageIcon(getClass().getResource("/iconos16x16/ok.png")));
				Principal.lblStatusBar.setText("Usuario " + usuario + " actualizado correctamente");
				dispose();
			} else {
				Principal.lblStatusBar.setIcon(new ImageIcon(getClass().getResource("/iconos16x16/warning-icon.png")));
				Principal.lblStatusBar.setText("No se pudo completar la operacion");
			}

		} catch (SQLException sqle) {
			log.log(Level.SEVERE, sqle.toString(), sqle);
			JOptionPane.showMessageDialog(null, sqle.getMessage(), sqle.getClass().toString(),
					JOptionPane.ERROR_MESSAGE);
		} catch (Exception e) {
			log.log(Level.SEVERE, e.toString(), e);
			JOptionPane.showMessageDialog(null, e.getMessage(), e.getClass().toString(), JOptionPane.ERROR_MESSAGE);
		}
	}

	private void mostrarUsuario() {

		try {
			rs = admUsuario.mostrarUsuario(null);

			while (rs.next()) {

				cbUsuario.addItem(rs.getString("nombre_usuario"));
			}
			// Elimina el item duplicado
			if (cbUsuario.getItemAt(1).equals("admin")) {
				cbUsuario.removeItemAt(1);
				;
			}
		} catch (Exception e) {
			log.log(Level.SEVERE, e.toString(), e);
			JOptionPane.showMessageDialog(null, e.getMessage(), e.getClass().toString(), JOptionPane.ERROR_MESSAGE);
		}
	}

	private void mostrarUsuario(String usuarioSeleccionado) {

		try {
			rs = admUsuario.mostrarUsuario(usuarioSeleccionado);

			if (rs.next()) {

				if (rs.getString("tipo_usuario").equalsIgnoreCase("administrador")) {
					cbTipoUsuario.setSelectedIndex(0);
				} else if (rs.getString("tipo_usuario").equalsIgnoreCase("operador")) {
					cbTipoUsuario.setSelectedIndex(1);
				} else if (rs.getString("tipo_usuario").equalsIgnoreCase("consultor")) {
					cbTipoUsuario.setSelectedIndex(2);
				}

				// Verifica el estado del usuario
				if (rs.getString("estatus").equalsIgnoreCase("activo")) {
					rdbtnActivo.setSelected(true);
				} else {
					rdbtnInactivo.setSelected(true);
				}
			}

		} catch (SQLException sqle) {
			log.log(Level.SEVERE, sqle.toString(), sqle);
			JOptionPane.showMessageDialog(null, sqle.getMessage(), sqle.getClass().toString(),
					JOptionPane.ERROR_MESSAGE);
		} catch (Exception e) {
			log.log(Level.SEVERE, e.toString(), e);
			JOptionPane.showMessageDialog(null, e.getMessage(), e.getClass().toString(), JOptionPane.ERROR_MESSAGE);
		}
	}
}