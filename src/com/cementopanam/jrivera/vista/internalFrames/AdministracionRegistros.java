package com.cementopanam.jrivera.vista.internalFrames;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import com.cementopanam.jrivera.controlador.ManipulacionDatos;
import com.cementopanam.jrivera.controlador.entidad.Area;
import com.cementopanam.jrivera.controlador.entidad.Causa;
import com.cementopanam.jrivera.controlador.entidad.Disciplina;
import com.cementopanam.jrivera.controlador.entidad.Equipo;
import com.cementopanam.jrivera.controlador.entidad.SubArea;
import com.cementopanam.jrivera.vista.Principal;

public class AdministracionRegistros extends JInternalFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7802198920491306605L;
	
	private ManipulacionDatos md = new ManipulacionDatos();
	private JTextField txtNombreEquipo;
	private JTextField txtCodEquipo;
	private JTextField txtArea;
	private JTextField txtCausa;
	private JTextField txtDisciplina;
	private JTextField txtSubArea;
	private JComboBox<String> cbArea;
	private JComboBox<String> cbSubArea;

	
	public AdministracionRegistros() {
		setFrameIcon(null);
		setIconifiable(true);
		setClosable(true);
		getContentPane().setFont(new Font("Verdana", Font.PLAIN, 12));
		getContentPane().setLayout(new BorderLayout(0, 0));
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setFont(new Font("Verdana", Font.PLAIN, 12));
		getContentPane().add(tabbedPane, BorderLayout.CENTER);
		
		JPanel panelAgregar = new JPanel();
		tabbedPane.addTab("Agregar", null, panelAgregar, null);
		panelAgregar.setLayout(null);
		
		JPanel panelEquipo = new JPanel();
		panelEquipo.setLayout(null);
		panelEquipo.setBorder(new TitledBorder(null, "Equipo", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelEquipo.setBounds(20, 231, 518, 181);
		panelAgregar.add(panelEquipo);
		
		JLabel lblCodEquipo = new JLabel("Codigo de Equipo");
		lblCodEquipo.setFont(new Font("Verdana", Font.PLAIN, 12));
		lblCodEquipo.setBounds(20, 23, 191, 23);
		panelEquipo.add(lblCodEquipo);
		
		JLabel lblSubAreaAQuepertenece = new JLabel("SubArea a que pertenece");
		lblSubAreaAQuepertenece.setFont(new Font("Verdana", Font.PLAIN, 12));
		lblSubAreaAQuepertenece.setBounds(20, 89, 191, 23);
		panelEquipo.add(lblSubAreaAQuepertenece);
		
		cbSubArea = new JComboBox<String>();
		cbSubArea.setFont(new Font("Verdana", Font.PLAIN, 12));
		cbSubArea.setBounds(20, 116, 459, 33);
		panelEquipo.add(cbSubArea);
		
		JLabel lblNombreEquipo = new JLabel("Nombre de Equipo");
		lblNombreEquipo.setFont(new Font("Verdana", Font.PLAIN, 12));
		lblNombreEquipo.setBounds(280, 23, 191, 23);
		panelEquipo.add(lblNombreEquipo);
		
		txtNombreEquipo = new JTextField();
		txtNombreEquipo.setFont(new Font("Verdana", Font.PLAIN, 12));
		txtNombreEquipo.setColumns(10);
		txtNombreEquipo.setBounds(280, 49, 199, 30);
		panelEquipo.add(txtNombreEquipo);
		
		txtCodEquipo = new JTextField();
		txtCodEquipo.setFont(new Font("Verdana", Font.PLAIN, 12));
		txtCodEquipo.setColumns(10);
		txtCodEquipo.setBounds(20, 49, 191, 30);
		panelEquipo.add(txtCodEquipo);
		
		JPanel panelArea = new JPanel();
		panelArea.setLayout(null);
		panelArea.setBorder(new TitledBorder(null, "Area", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelArea.setBounds(20, 58, 238, 164);
		panelAgregar.add(panelArea);
		
		JLabel lblNombreArea = new JLabel("Nombre del Area");
		lblNombreArea.setFont(new Font("Verdana", Font.PLAIN, 12));
		lblNombreArea.setBounds(20, 26, 105, 16);
		panelArea.add(lblNombreArea);
		
		txtArea = new JTextField();
		txtArea.setFont(new Font("Verdana", Font.PLAIN, 12));
		txtArea.setColumns(10);
		txtArea.setBounds(20, 53, 191, 30);
		panelArea.add(txtArea);
		
		JPanel panelSubArea = new JPanel();
		panelSubArea.setLayout(null);
		panelSubArea.setBorder(new TitledBorder(null, "SubArea", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelSubArea.setBounds(290, 58, 248, 164);
		panelAgregar.add(panelSubArea);
		
		JLabel lblAreaAQuePertenece = new JLabel("Area a que pertenece");
		lblAreaAQuePertenece.setFont(new Font("Verdana", Font.PLAIN, 12));
		lblAreaAQuePertenece.setBounds(20, 93, 137, 16);
		panelSubArea.add(lblAreaAQuePertenece);
		
		JLabel lblNombreSubArea = new JLabel("Nombre de SubArea");
		lblNombreSubArea.setFont(new Font("Verdana", Font.PLAIN, 12));
		lblNombreSubArea.setBounds(20, 26, 191, 23);
		panelSubArea.add(lblNombreSubArea);
		
		cbArea = new JComboBox<String>();
		cbArea.setFont(new Font("Verdana", Font.PLAIN, 12));
		cbArea.setBounds(20, 120, 191, 33);
		panelSubArea.add(cbArea);
		
		txtSubArea = new JTextField();
		txtSubArea.setFont(new Font("Verdana", Font.PLAIN, 12));
		txtSubArea.setColumns(10);
		txtSubArea.setBounds(20, 52, 191, 30);
		panelSubArea.add(txtSubArea);
		
		JPanel panelCausa = new JPanel();
		panelCausa.setLayout(null);
		panelCausa.setBorder(new TitledBorder(null, "Causa y Disciplina", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelCausa.setBounds(20, 423, 518, 111);
		panelAgregar.add(panelCausa);
		
		JLabel lblCausa = new JLabel("Causa");
		lblCausa.setFont(new Font("Verdana", Font.PLAIN, 12));
		lblCausa.setBounds(234, 31, 46, 24);
		panelCausa.add(lblCausa);
		
		txtCausa = new JTextField();
		txtCausa.setFont(new Font("Verdana", Font.PLAIN, 12));
		txtCausa.setColumns(10);
		txtCausa.setBounds(234, 56, 274, 30);
		panelCausa.add(txtCausa);
		
		JLabel lblDisciplina = new JLabel("Disciplina");
		lblDisciplina.setFont(new Font("Verdana", Font.PLAIN, 12));
		lblDisciplina.setBounds(20, 31, 69, 24);
		panelCausa.add(lblDisciplina);
		
		txtDisciplina = new JTextField();
		txtDisciplina.setFont(new Font("Verdana", Font.PLAIN, 12));
		txtDisciplina.setColumns(10);
		txtDisciplina.setBounds(20, 56, 191, 30);
		panelCausa.add(txtDisciplina);
		
		JPanel panelBotones = new JPanel();
		FlowLayout fl_panelBotones = (FlowLayout) panelBotones.getLayout();
		fl_panelBotones.setAlignment(FlowLayout.RIGHT);
		panelBotones.setBounds(20, 545, 518, 52);
		panelAgregar.add(panelBotones);
		
		JButton btnGuardar = new JButton("Guardar Registros");
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				guardarRegistros();
			}
		});
		btnGuardar.setIcon(new ImageIcon(AdministracionRegistros.class.getResource("/iconos32x32/ok32x32.png")));
		btnGuardar.setFont(new Font("Verdana", Font.PLAIN, 12));
		panelBotones.add(btnGuardar);
		
		JPanel panelTitulo = new JPanel();
		panelTitulo.setBackground(Color.BLACK);
		panelTitulo.setBounds(0, 11, 562, 36);
		panelAgregar.add(panelTitulo);
		
		JLabel lblTitulo = new JLabel("Agregar Nuevos Registros");
		lblTitulo.setForeground(Color.WHITE);
		lblTitulo.setFont(new Font("Verdana", Font.BOLD, 16));
		panelTitulo.add(lblTitulo);
		
		JPanel panelBorrar = new JPanel();
		tabbedPane.addTab("Borrar", null, panelBorrar, null);
		setTitle("Administracion de Registros");
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 10, 569, 663);
		
		rellenarCombo();
		cbSubArea.setSelectedIndex(-1);
		cbArea.setSelectedIndex(-1);
	}
	
private void guardarRegistros() {
		
		String nombreArea = txtArea.getText();
		String nombreSubArea = txtSubArea.getText();
		String codEquipo = txtCodEquipo.getText();
		String nombreEquipo = txtNombreEquipo.getText();
		String subAreaAQuePertenece = String.valueOf(cbSubArea.getSelectedItem());
		String areaAQuePertenece = String.valueOf(cbArea.getSelectedItem());
		String nombreDisciplina = txtDisciplina.getText();
		String nombreCausa = txtCausa.getText();
		
	//	for (Component c : this.getRootPane().getComponents()) {
			
	//		System.out.println("Los componentes son: " +c.getName());
			
	//	}
		//Guardar informacion de una nueva Area
		Area area = new Area();
		if(nombreArea.trim().length() == 0) {
			area.setNombreArea(null);
		}
		else {
			area.setNombreArea(nombreArea);
		}
		
		//Guarda informacion de una nueva SubArea
		SubArea subArea = new SubArea();
		if(nombreSubArea.trim().length() == 0) {
			subArea.setNombreSubArea(null);
		}
		else {
			subArea.setNombreSubArea(nombreSubArea);
			
			if(cbArea.getSelectedIndex() == -1) {
				JOptionPane.showMessageDialog(null, "Debe seleccionar el area para esta subArea",
						"Seleccione Area", JOptionPane.INFORMATION_MESSAGE);
				return;
			}
			int idArea = Integer.parseInt(md.buscarIndice(areaAQuePertenece, "area"));
			subArea.setIdArea(idArea);
		}

		//Guarda informacion de un nuevo Equipo
		Equipo equipo = null;
		//si no se escribe el nombre del equipo.
		if(codEquipo.trim().length() == 0 || nombreEquipo.trim().length() == 0) {
			equipo = new Equipo(0, null, null, 0);
		}
		else {
			
			if(cbSubArea.getSelectedIndex() == -1) {
				JOptionPane.showMessageDialog(null, "Debe seleccionar la SubArea para este Equipo",
						"Seleccione SubArea", JOptionPane.INFORMATION_MESSAGE);
				return;
			}
			//Busca el indice de la SubArea seleccciona
			int idSubArea = Integer.parseInt(md.buscarIndice(subAreaAQuePertenece, "sub_area"));
			equipo = new Equipo(0, codEquipo, nombreEquipo, idSubArea);
		}

		
		//Guarda informacion de una nueva causa
		Causa causa = new Causa();
		if(nombreCausa.trim().length() == 0) {
			causa.setTipoCausa(null);
		}
		else {
			causa.setTipoCausa(nombreCausa);
		}
		
		//Guarda informacion de una nueva Disciplina
		Disciplina disciplina = new Disciplina();
		if(nombreDisciplina.trim().length() == 0) {
			disciplina.setNombreDisciplina(null);
		}
		else {
			disciplina.setNombreDisciplina(nombreDisciplina);
		}

		try {
			// Si el resultado es satisfactorio, notifica que se agregaron nuevos registros a la Base de Datos
			if (md.agregarRegistros(area, causa, disciplina, equipo, subArea) == true) {
				
				Principal.lblStatusBar.setIcon(new ImageIcon(getClass().getResource("/iconos16x16/ok.png")));
				Principal.lblStatusBar.setText("Registro(s) agregado(s) correctamente");
				rellenarCombo();
				limpiarCampos();
			} else {
				Principal.lblStatusBar
						.setIcon(new ImageIcon(getClass().getResource("/iconos16x16/warning-icon.png")));
				Principal.lblStatusBar.setText("No se pudo completar la operacion");
			}
			
			
		} catch (SQLException sqle) {
			JOptionPane.showMessageDialog(null, sqle.getMessage(), sqle.getClass().toString(), 
					JOptionPane.ERROR_MESSAGE);
		}
		catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), e.getClass().toString(), 
					JOptionPane.ERROR_MESSAGE);
		}
	}
	
	
	/**
	 * Limpia los componentes luego de agregar nuevos registros
	 */
	private void limpiarCampos() {
		
		txtArea.setText("");
		txtSubArea.setText("");
		txtCodEquipo.setText("");
		txtNombreEquipo.setText("");
		cbArea.setSelectedIndex(-1);
		cbSubArea.setSelectedIndex(-1);
		txtDisciplina.setText("");
		txtCausa.setText("");
	}

	/**
	 * Rellena los comboBox de la interfaz
	 */
	private void rellenarCombo() {

		ResultSet rs = null;
		cbArea.removeAllItems();
		cbSubArea.removeAllItems();
		try {
			
			//Rellena ComboBox Area
			rs = md.rellenarCombo("area", 0);
			while (rs.next()) {
				cbArea.addItem(rs.getString("nombre_area"));
			}
			
			//Rellena ComboBox SubArea
			rs = md.rellenarCombo("subArea", 0);
			while (rs.next()) {
				cbSubArea.addItem(rs.getString(2));
			}
			
		} catch (SQLException sqle) {
			JOptionPane.showMessageDialog(null, sqle.getMessage(), sqle.getClass().toString(), 
					JOptionPane.ERROR_MESSAGE);
		}
		catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), e.getClass().toString(), 
					JOptionPane.ERROR_MESSAGE);
		}
		cbSubArea.setSelectedItem(-1);
		cbArea.setSelectedItem(-1);
	}
}