package com.cementopanam.jrivera.vista;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import com.cementopanam.jrivera.controlador.ListadoEquipos;
import com.cementopanam.jrivera.controlador.ManipulacionDatos;
import com.cementopanam.jrivera.modelo.ConeccionBD;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

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
	private JTextArea textAreaEquipos;
	/**
	 * Create the panel.
	 */
	public NombreEquipo() {
		
		this.setSize(new Dimension(401, 370)); 
		this.setResizable(false);
		
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
		btnMostrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				ListadoEquipos le = new ListadoEquipos();
				rs = le.mostrarEquipos();
				
				StringBuilder equipos = new StringBuilder();
				try {
					while(rs.next()) {
						String codEquipo = rs.getString("cod_equipo");
						String nombreEquipo = rs.getString("nombre_equipo");
						equipos.append(codEquipo + " = " + nombreEquipo + "\n");
					}
					
					textAreaEquipos.setText(equipos.toString());
				} catch (SQLException sqle) {
					JOptionPane.showMessageDialog(null, sqle.getMessage(), sqle.getClass().toString(),
							JOptionPane.ERROR_MESSAGE);
				}
				catch (Exception e) {
					JOptionPane.showMessageDialog(null, e.getMessage(), e.getClass().toString(),
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		panelSuperior.add(btnMostrar);
		
		textAreaEquipos = new JTextArea();
		
		JScrollPane scroll = new JScrollPane (textAreaEquipos);
	    scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
	          scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		
		
		getContentPane().add(scroll, BorderLayout.CENTER);
		
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setVisible(true);
	}
}