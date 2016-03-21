package com.cementopanam.jrivera.vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
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
import com.cementopanam.jrivera.vista.internalFrames.Reportes;

public class ModificacionParo extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7239938319668117403L;
	private final JPanel contentPanel = new JPanel();
	private JButton okButton;
	private JButton cancelButton;
	private JLabel lblTiempoDeInicio;
	private JLabel lblTiempoDeFin;
	private JTextField textFieldTiempoInicio;
	private JTextField textFieldTiempoFin;
	private JTextArea txtDescripcionAdicional;
	private JComboBox<String> comboBoxCausa;
	private JComboBox<String> comboBoxDisciplina;
	private JTextArea txtAreaSolucion;
	
	private int codigo;
	private int codigoCausa;
	
	private AdministracionParos admParo;
	
	public ModificacionParo(Paro modificacion, AdministracionParos paroDB) {
		
		this();
		admParo = paroDB;
		
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
		
		codigo = modificacion.getCodigo();
		
		//Obtiene el codigo de Causa
		if(modificacion.getDescripcionAdicional().length() == 0) {
			codigoCausa = Integer.parseInt(admParo.buscarIndiceCausa(modificacion.getCausa(), 
					""));
		}
		else {
			codigoCausa = Integer.parseInt(admParo.buscarIndiceCausa(modificacion.getCausa(), 
					modificacion.getDescripcionAdicional()));
		}
		
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
		txtDescripcionAdicional.setFont(new Font("Verdana", Font.PLAIN, 12));
		scrollPaneDescripcionAdicional.setViewportView(txtDescripcionAdicional);
		
		txtAreaSolucion = new JTextArea();
		txtAreaSolucion.setFont(new Font("Verdana", Font.PLAIN, 12));
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

	/**
	 * Metodo utilizado para actualizar Datos de los Paros
	 */
	private void actualizarParo() {
		
		String tiempoInicio = textFieldTiempoInicio.getText();
		String disciplina = String.valueOf(comboBoxDisciplina.getSelectedItem());
		String tiempoFin = textFieldTiempoFin.getText();
		String causa = String.valueOf(comboBoxCausa.getSelectedItem());
		String solucion = txtAreaSolucion.getText();
		String descripcionAdicional = txtDescripcionAdicional.getText();
		
		//Si el paro fue exitoso
		try {
			if(admParo.modificarParo(new Paro(codigo, tiempoInicio, tiempoFin, solucion, 
					causa, descripcionAdicional, disciplina), codigoCausa) == true) {
				
				Principal.lblStatusBar.setIcon(new ImageIcon(getClass().getResource("/iconos16x16/ok.png")));
				Principal.lblStatusBar.setText("Paro Actualizado correctamente");
				
				Reportes repo = new Reportes();
				repo.setVisible(true);
				this.dispose();
			}
			else {
				Principal.lblStatusBar.setIcon(new ImageIcon(getClass().getResource("/iconos16x16/warning-icon.png")));
				 Principal.lblStatusBar.setText("No se pudo completar la operacion");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}