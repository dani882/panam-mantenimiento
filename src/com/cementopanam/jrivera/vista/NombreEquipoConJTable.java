package com.cementopanam.jrivera.vista;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
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
import com.cementopanam.jrivera.modelo.ConeccionBD;
import com.cementopanam.jrivera.vista.helper.TablaModeloEquipos;

public class NombreEquipoConJTable extends JDialog {

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
	/**
	 * Create the panel.
	 */
	public NombreEquipoConJTable() {
		
		
		this.setSize(new Dimension(401, 370)); 
		this.setResizable(false);
		
		setModal(true);
		
		setLocationRelativeTo(null);
		getContentPane().setFont(new Font("Verdana", Font.PLAIN, 12));
		setFont(new Font("Verdana", Font.PLAIN, 12));
		
		setTitle("Codigo de Equipos");
		setModal(false);
		getContentPane().setLayout(new BorderLayout(0, 0));
		
		JPanel panelSuperior = new JPanel();
		getContentPane().add(panelSuperior, BorderLayout.NORTH);
		
		JLabel lblCodigo = new JLabel("Codigo de Equipos");
		lblCodigo.setFont(new Font("Verdana", Font.PLAIN, 12));
		panelSuperior.add(lblCodigo);
		
		JButton btnMostrar = new JButton("Mostrar");
		panelSuperior.add(btnMostrar);
		
		JPanel panelCentral = new JPanel();
		getContentPane().add(panelCentral, BorderLayout.CENTER);
		panelCentral.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 375, 286);
		panelCentral.add(scrollPane);
		
		tblEquipo = new JTable();
		scrollPane.setViewportView(tblEquipo);
		tblEquipo.setModel(new TablaModeloEquipos());
		
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setVisible(true);
	}
}