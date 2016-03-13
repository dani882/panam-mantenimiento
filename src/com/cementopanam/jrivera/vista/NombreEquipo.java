package com.cementopanam.jrivera.vista;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import com.cementopanam.jrivera.controlador.ManipulacionDatos;
import com.cementopanam.jrivera.controlador.listadoEquipos.ListadoEquipos;
import com.cementopanam.jrivera.modelo.ConeccionBD;
import com.cementopanam.jrivera.vista.helper.tablaModelo.TablaModeloEquipos;

import javax.swing.JTextField;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.FlowLayout;
import javax.swing.BoxLayout;

public class NombreEquipo extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4631645774768719309L;
	ManipulacionDatos md;
	ConeccionBD cbd;
	Connection con = null;

	CallableStatement cs = null;
	
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	private JTable tblEquipo;
	private TablaModeloEquipos tablaModeloEquipos = new TablaModeloEquipos();
	private JTextField textField;
	/**
	 * Create the panel.
	 */
	public NombreEquipo() {
		
		this.setSize(new Dimension(600, 370));
		
		setModal(true);
		setLocationRelativeTo(null);
		getContentPane().setFont(new Font("Verdana", Font.PLAIN, 12));
		setFont(new Font("Verdana", Font.PLAIN, 12));
		setTitle("Codigo de Equipos");
		getContentPane().setLayout(new BorderLayout(0, 0));
		
		JPanel panelSuperior = new JPanel();
		getContentPane().add(panelSuperior, BorderLayout.NORTH);
		panelSuperior.setLayout(new BorderLayout(0, 0));
		
		JLabel lblNewLabel = new JLabel("Buscar Codigo:");
		lblNewLabel.setFont(new Font("Verdana", Font.PLAIN, 12));
		panelSuperior.add(lblNewLabel, BorderLayout.WEST);
		
		textField = new JTextField();
		textField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				tablaModeloEquipos.buscar(textField.getText());
			}
		});
		textField.setFont(new Font("Verdana", Font.PLAIN, 12));
		panelSuperior.add(textField, BorderLayout.CENTER);
		textField.setColumns(10);
		
		JPanel panelCentral = new JPanel();
		getContentPane().add(panelCentral, BorderLayout.CENTER);
		panelCentral.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		panelCentral.add(scrollPane);
		
		tblEquipo = new JTable();
		tblEquipo.setFont(new Font("Verdana", Font.PLAIN, 12));
		scrollPane.setViewportView(tblEquipo);
		tblEquipo.setModel(tablaModeloEquipos);
		
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setVisible(true);
	}
}