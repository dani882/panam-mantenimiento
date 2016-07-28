package com.cementopanam.jrivera.vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

/**
 * @author Jesus Rivera
 * @version 1.7.0
 */
public class Autor extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4552920338518782653L;
	private final JPanel contentPanel = new JPanel();

	/**
	 * Crea el dialog.
	 */
	public Autor() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Autor.class.getResource("/imagenes/logo-panam.png")));

		StringBuilder anio = new StringBuilder(String.valueOf(Calendar.getInstance().get(Calendar.YEAR)));

		setSize(454, 181);

		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setResizable(false);
		setModal(true);
		setTitle("Sobre la Aplicacion");
		setBounds(100, 100, 454, 305);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		JLabel lblRegistradorDePacientes = new JLabel("Imputaciones de Paros");
		lblRegistradorDePacientes.setFont(new Font("Verdana", Font.BOLD, 16));
		lblRegistradorDePacientes.setBounds(6, 18, 270, 22);
		contentPanel.add(lblRegistradorDePacientes);

		JLabel lblVersion = new JLabel("Version 1.7.0 ");
		lblVersion.setFont(new Font("Verdana", Font.PLAIN, 14));
		lblVersion.setBounds(6, 68, 146, 16);
		contentPanel.add(lblVersion);

		JLabel lblCopyright = new JLabel(" - Copyright © 2015-" + anio);
		lblCopyright.setFont(new Font("Verdana", Font.PLAIN, 14));
		lblCopyright.setBounds(104, 65, 270, 22);
		contentPanel.add(lblCopyright);

		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
			}
		});
		btnAceptar.setBounds(342, 233, 90, 28);
		contentPanel.add(btnAceptar);

		JLabel lblEmail = new JLabel("Desarrollador: Ing. Jesus Rivera");
		lblEmail.setFont(new Font("Verdana", Font.BOLD, 14));
		lblEmail.setBounds(6, 110, 270, 22);
		contentPanel.add(lblEmail);

		JPanel panelInformacion = new JPanel();
		panelInformacion.setBackground(Color.WHITE);
		panelInformacion.setBounds(6, 153, 426, 78);
		contentPanel.add(panelInformacion);
		panelInformacion.setLayout(new BorderLayout(0, 0));

		JScrollPane scrollPane = new JScrollPane();
		panelInformacion.add(scrollPane);

		JTextArea txtrAplicacionUtilizadaPara = new JTextArea();
		scrollPane.setViewportView(txtrAplicacionUtilizadaPara);
		txtrAplicacionUtilizadaPara.setText(
				"Aplicacion utilizada para el registro e informe de paros.\r\n\r\nEn colaboración con:\r\nIng. Alfredo Amstrong\r\nIng. Juan Carlos Fernandez\r\nIng. Jose Pagan");
	}
}
