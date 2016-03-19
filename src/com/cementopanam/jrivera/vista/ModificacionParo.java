package com.cementopanam.jrivera.vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import com.cementopanam.jrivera.controlador.paros.AdministracionParos;
import com.cementopanam.jrivera.controlador.paros.Paro;
import com.cementopanam.jrivera.vista.helper.CustomJComboBox;

import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class ModificacionParo extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JButton okButton;
	private JButton cancelButton;
	private JLabel lblTiempoDeInicio;
	private JLabel lblTiempoDeFin;
	private JTextField textFieldTiempoInicio;
	private JTextField textFieldTiempoFin;
	private JComboBox<String> comboBoxCausa;
	private JComboBox<String> comboBoxDisciplina;
	private JTextArea txtAreaSolucion;
	
	private Paro paro;
	private AdministracionParos admParo;
	private JTextArea txtDescripcionAdicional;

	/**
	 * Create the dialog.
	 */
	
	public ModificacionParo(Paro modificacion, AdministracionParos paroDB) {
		
		this();
		
		paro = modificacion;
		admParo = paroDB;
		
		System.out.println("Desde Modificacion "  + modificacion.getDisciplina());
		
		try {
			
			//Rellena combo Causa
			ResultSet rs = admParo.rellenarCombo("causa", 0);
			while(rs.next()) {
				comboBoxCausa.addItem(rs.getString("tipo_causa"));
			}
			//Rellena combo Disciplina
			ResultSet rs2 = admParo.rellenarCombo("disciplina", 0);
			while(rs2.next()) {
				comboBoxDisciplina.addItem(rs2.getString("nombre_disciplina"));
			}
			
		} catch (SQLException e) {}
		
		txtAreaSolucion.setText(modificacion.getSolucion());
		txtDescripcionAdicional.setText(modificacion.getDescripcionAdicional());
		textFieldTiempoInicio.setText(modificacion.getTiempoInicio());
		textFieldTiempoFin.setText(modificacion.getTiempoFin());
		comboBoxCausa.setSelectedItem(String.valueOf(modificacion.getCausa()));
		comboBoxDisciplina.setSelectedItem(String.valueOf(modificacion.getDisciplina()));
	}
	
	public ModificacionParo() {
		
		getContentPane().setFont(new Font("Verdana", Font.PLAIN, 12));
		setTitle("Modificar Paro");
		setModal(true);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setFont(new Font("Verdana", Font.PLAIN, 12));
		setBounds(100, 100, 588, 407);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.NORTH);
		{
			lblTiempoDeInicio = new JLabel("Tiempo de Inicio");
			lblTiempoDeInicio.setFont(new Font("Verdana", Font.PLAIN, 12));
		}
		{
			lblTiempoDeFin = new JLabel("Tiempo de Fin");
			lblTiempoDeFin.setHorizontalAlignment(SwingConstants.RIGHT);
			lblTiempoDeFin.setFont(new Font("Verdana", Font.PLAIN, 12));
		}
		
		textFieldTiempoInicio = new JTextField();
		textFieldTiempoInicio.setFont(new Font("Verdana", Font.PLAIN, 12));
		textFieldTiempoInicio.setColumns(10);
		
		textFieldTiempoFin = new JTextField();
		textFieldTiempoFin.setFont(new Font("Verdana", Font.PLAIN, 12));
		textFieldTiempoFin.setColumns(10);
		
		JLabel lblCausa = new JLabel("Causa");
		lblCausa.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCausa.setFont(new Font("Verdana", Font.PLAIN, 12));
		
		comboBoxCausa = new CustomJComboBox();
		comboBoxCausa.setFont(new Font("Verdana", Font.PLAIN, 12));
		
		JLabel lblDisciplina = new JLabel("Disciplina");
		lblDisciplina.setFont(new Font("Verdana", Font.PLAIN, 12));
		
		comboBoxDisciplina = new CustomJComboBox();
		comboBoxDisciplina.setFont(new Font("Verdana", Font.PLAIN, 12));
		
		JScrollPane scrollPaneSolucion = new JScrollPane();
		scrollPaneSolucion.setViewportBorder(new TitledBorder(null, "Solucion", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		JScrollPane scrollPaneDescripcionAdicional = new JScrollPane();
		scrollPaneDescripcionAdicional.setViewportBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Descripcion Adicional", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addContainerGap()
							.addComponent(scrollPaneSolucion, GroupLayout.DEFAULT_SIZE, 265, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(scrollPaneDescripcionAdicional, GroupLayout.PREFERRED_SIZE, 267, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addGap(8)
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
								.addComponent(lblTiempoDeInicio)
								.addComponent(lblDisciplina))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING, false)
								.addComponent(comboBoxDisciplina, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(textFieldTiempoInicio, GroupLayout.DEFAULT_SIZE, 155, Short.MAX_VALUE))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
								.addComponent(lblCausa)
								.addGroup(gl_contentPanel.createSequentialGroup()
									.addComponent(lblTiempoDeFin)
									.addGap(18)
									.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
										.addComponent(comboBoxCausa, Alignment.TRAILING, 0, 162, Short.MAX_VALUE)
										.addComponent(textFieldTiempoFin, GroupLayout.DEFAULT_SIZE, 162, Short.MAX_VALUE))))))
					.addContainerGap())
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGap(38)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblTiempoDeInicio)
						.addComponent(textFieldTiempoInicio, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
						.addComponent(textFieldTiempoFin, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblTiempoDeFin))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(comboBoxDisciplina, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblDisciplina)
						.addComponent(lblCausa)
						.addComponent(comboBoxCausa, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE))
					.addGap(54)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(scrollPaneSolucion, GroupLayout.PREFERRED_SIZE, 137, GroupLayout.PREFERRED_SIZE)
						.addComponent(scrollPaneDescripcionAdicional, GroupLayout.PREFERRED_SIZE, 137, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(25, Short.MAX_VALUE))
		);
		
		txtDescripcionAdicional = new JTextArea();
		scrollPaneDescripcionAdicional.setViewportView(txtDescripcionAdicional);
		
		txtAreaSolucion = new JTextArea();
		scrollPaneSolucion.setViewportView(txtAreaSolucion);
		contentPanel.setLayout(gl_contentPanel);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				okButton = new JButton("Guardar");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
						actualizarParo();
			
					}

				});
				okButton.setFont(new Font("Verdana", Font.PLAIN, 12));
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				cancelButton = new JButton("Cancelar");
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
	}

	private void actualizarParo() {
		
		textFieldTiempoInicio.getText();
		comboBoxDisciplina.getSelectedItem();
//		textFieldTiempoFin
//		comboBoxCausa
//		txtAreaSolucion
//		txtDescripcionAdicional
		
		
	}
}