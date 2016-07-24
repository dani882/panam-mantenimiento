package com.cementopanam.jrivera.vista.internalFrames;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import com.cementopanam.jrivera.controlador.ManipulacionDatos;
import com.cementopanam.jrivera.controlador.entidad.Area;
import com.cementopanam.jrivera.controlador.entidad.Causa;
import com.cementopanam.jrivera.controlador.entidad.Disciplina;
import com.cementopanam.jrivera.controlador.entidad.Equipo;
import com.cementopanam.jrivera.controlador.entidad.SubArea;
import com.cementopanam.jrivera.controlador.registros.ModificacionRegistros;
import com.cementopanam.jrivera.vista.Principal;

public class AdministracionRegistros extends JInternalFrame {
	/**
	 * 
	 */
	private static final Logger log = Logger.getLogger(AdministracionRegistros.class.getName());
	private static final long serialVersionUID = 7802198920491306605L;

	private ManipulacionDatos manipulacionDatos = new ManipulacionDatos();
	private ModificacionRegistros modificacionRegistros = new ModificacionRegistros();
	private JTextField txtNombreEquipo;
	private JTextField txtCodEquipo;
	private JTextField txtArea;
	private JTextField txtSubArea;
	private JComboBox<String> cbArea;
	private JComboBox<String> cbSubArea;
	private JTextField txtCausa;
	private JTextField txtDisciplina;
	private JList<String> listDisciplina;
	private JTable tblModificarArea;
	private JTable tblModificarEquipo;
	private JTable tblModificarCausa;
	private JTable tblModificarSubArea;
	private JTable tblModificarDisciplina;
	private JTable tblBorrarCausa;
	private JTable tblBorrarDisciplina;

	public AdministracionRegistros() {

		setFrameIcon(null);
		setIconifiable(true);
		setClosable(true);
		getContentPane().setFont(new Font("Verdana", Font.PLAIN, 12));
		getContentPane().setLayout(new BorderLayout(0, 0));

		JTabbedPane tpMenu = new JTabbedPane(JTabbedPane.TOP);
		tpMenu.setFont(new Font("Verdana", Font.PLAIN, 12));
		getContentPane().add(tpMenu, BorderLayout.CENTER);

		JPanel panelAgregar = new JPanel();
		tpMenu.addTab("Agregar", null, panelAgregar, null);
		panelAgregar.setLayout(null);

		JPanel panelEquipo = new JPanel();
		panelEquipo.setLayout(null);
		panelEquipo.setBorder(new TitledBorder(null, "Equipo", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelEquipo.setBounds(20, 231, 495, 181);
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
		cbSubArea.setToolTipText("SubArea a la que pertenecera el equipo nuevo");
		cbSubArea.setFont(new Font("Verdana", Font.PLAIN, 12));
		cbSubArea.setBounds(20, 116, 448, 30);
		panelEquipo.add(cbSubArea);

		JLabel lblNombreEquipo = new JLabel("Nombre de Equipo");
		lblNombreEquipo.setFont(new Font("Verdana", Font.PLAIN, 12));
		lblNombreEquipo.setBounds(277, 23, 191, 23);
		panelEquipo.add(lblNombreEquipo);

		txtNombreEquipo = new JTextField();
		txtNombreEquipo.setToolTipText("Nombre del nuevo equipo");
		txtNombreEquipo.setFont(new Font("Verdana", Font.PLAIN, 12));
		txtNombreEquipo.setColumns(10);
		txtNombreEquipo.setBounds(277, 49, 191, 30);
		panelEquipo.add(txtNombreEquipo);

		txtCodEquipo = new JTextField();
		txtCodEquipo.setToolTipText("Codigo del nuevo equipo");
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
		txtArea.setToolTipText("Agrega una nueva Area");
		txtArea.setFont(new Font("Verdana", Font.PLAIN, 12));
		txtArea.setColumns(10);
		txtArea.setBounds(20, 53, 191, 30);
		panelArea.add(txtArea);

		JPanel panelSubArea = new JPanel();
		panelSubArea.setLayout(null);
		panelSubArea.setBorder(new TitledBorder(null, "SubArea", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelSubArea.setBounds(279, 58, 238, 164);
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
		cbArea.setToolTipText("Area a la que pertenecera");
		cbArea.setFont(new Font("Verdana", Font.PLAIN, 12));
		cbArea.setBounds(20, 120, 191, 30);
		panelSubArea.add(cbArea);

		txtSubArea = new JTextField();
		txtSubArea.setToolTipText("Agrega nueva SubArea");
		txtSubArea.setFont(new Font("Verdana", Font.PLAIN, 12));
		txtSubArea.setColumns(10);
		txtSubArea.setBounds(20, 52, 191, 30);
		panelSubArea.add(txtSubArea);

		JPanel panelBotones = new JPanel();
		FlowLayout fl_panelBotones = (FlowLayout) panelBotones.getLayout();
		fl_panelBotones.setAlignment(FlowLayout.RIGHT);
		panelBotones.setBounds(20, 423, 784, 52);
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

		JPanel panelTituloAgregar = new JPanel();
		panelTituloAgregar.setBackground(Color.BLACK);
		panelTituloAgregar.setBounds(0, 0, 830, 36);
		panelAgregar.add(panelTituloAgregar);

		JLabel lblTitulo = new JLabel("Agregar Nuevos Registros");
		lblTitulo.setForeground(Color.WHITE);
		lblTitulo.setFont(new Font("Verdana", Font.BOLD, 16));
		panelTituloAgregar.add(lblTitulo);
		setTitle("Administracion de Registros");
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 10, 835, 540);

		rellenarCombo();
		cbSubArea.setSelectedIndex(-1);
		cbArea.setSelectedIndex(-1);

		JPanel panelCausa = new JPanel();
		panelCausa.setLayout(null);
		panelCausa.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Causa", TitledBorder.LEADING,
				TitledBorder.TOP, null, new Color(0, 0, 0)));
		panelCausa.setBounds(538, 58, 265, 232);
		panelAgregar.add(panelCausa);

		JLabel label = new JLabel("Causa");
		label.setFont(new Font("Verdana", Font.PLAIN, 12));
		label.setBounds(29, 21, 46, 24);
		panelCausa.add(label);

		txtCausa = new JTextField();
		txtCausa.setToolTipText("Agrega una nueva causa");
		txtCausa.setFont(new Font("Verdana", Font.PLAIN, 12));
		txtCausa.setColumns(10);
		txtCausa.setBounds(29, 46, 214, 30);
		panelCausa.add(txtCausa);

		JLabel lblDisciplinaAQue = new JLabel("Disciplina(s) a que pertenece");
		lblDisciplinaAQue.setFont(new Font("Verdana", Font.PLAIN, 12));
		lblDisciplinaAQue.setBounds(29, 93, 200, 14);
		panelCausa.add(lblDisciplinaAQue);

		JScrollPane scrollPaneDisciplina = new JScrollPane();
		scrollPaneDisciplina.setBounds(29, 118, 214, 103);
		panelCausa.add(scrollPaneDisciplina);

		listDisciplina = new JList<String>();
		scrollPaneDisciplina.setViewportView(listDisciplina);
		listDisciplina.setBorder(new LineBorder(new Color(0, 0, 0)));
		listDisciplina.setToolTipText("Disciplinas a que pertenece la nueva causa");
		// Pobla la lista con los resultados de la Base de Datos
		try {
			manipulacionDatos.poblarJList(listDisciplina);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		listDisciplina.setFont(new Font("Verdana", Font.PLAIN, 12));

		JPanel panelDisciplina = new JPanel();
		panelDisciplina
				.setBorder(new TitledBorder(null, "Disciplina", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelDisciplina.setBounds(538, 301, 265, 111);
		panelAgregar.add(panelDisciplina);
		panelDisciplina.setLayout(null);

		JLabel lblDisciplina = new JLabel("Disciplina");
		lblDisciplina.setBounds(29, 24, 58, 16);
		lblDisciplina.setFont(new Font("Verdana", Font.PLAIN, 12));
		panelDisciplina.add(lblDisciplina);

		txtDisciplina = new JTextField();
		txtDisciplina.setToolTipText("Agrega nueva Disciplina");
		txtDisciplina.setBounds(29, 51, 214, 30);
		txtDisciplina.setFont(new Font("Verdana", Font.PLAIN, 12));
		txtDisciplina.setColumns(10);
		panelDisciplina.add(txtDisciplina);

		JPanel panelModificar = new JPanel();
		tpMenu.addTab("Modificar", null, panelModificar, null);
		panelModificar.setLayout(new BorderLayout(0, 0));

		JTabbedPane tabbedPaneModificar = new JTabbedPane(JTabbedPane.TOP);
		panelModificar.add(tabbedPaneModificar, BorderLayout.CENTER);

		JPanel panelModificarArea = new JPanel();
		tabbedPaneModificar.addTab("Area y SubArea", null, panelModificarArea, null);
		panelModificarArea.setLayout(null);

		JScrollPane scrollPaneModificarArea = new JScrollPane();
		scrollPaneModificarArea.setBounds(10, 11, 323, 359);
		panelModificarArea.add(scrollPaneModificarArea);

		tblModificarArea = new JTable();
		tblModificarArea.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tblModificarArea.setFillsViewportHeight(true);
		tblModificarArea.setFont(new Font("Verdana", Font.PLAIN, 12));
		scrollPaneModificarArea.setViewportView(tblModificarArea);
		tblModificarArea.setModel(modificacionRegistros.mostrarDatos("area"));
		// Sirve para no permitir que el usuario reordene las columnas
		tblModificarArea.getTableHeader().setReorderingAllowed(false);

		JScrollPane scrollPaneModificarSubArea = new JScrollPane();
		scrollPaneModificarSubArea.setBounds(358, 11, 441, 359);
		panelModificarArea.add(scrollPaneModificarSubArea);

		tblModificarSubArea = new JTable();
		tblModificarSubArea.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tblModificarSubArea.setFillsViewportHeight(true);
		scrollPaneModificarSubArea.setViewportView(tblModificarSubArea);
		tblModificarSubArea.setFont(new Font("Verdana", Font.PLAIN, 12));
		tblModificarSubArea.setModel(modificacionRegistros.mostrarDatos("subArea"));
		// Sirve para no permitir que el usuario reordene las columnas
		tblModificarSubArea.getTableHeader().setReorderingAllowed(false);

		JPanel panelModificarEquipo = new JPanel();
		tabbedPaneModificar.addTab("Equipo", null, panelModificarEquipo, null);
		panelModificarEquipo.setLayout(null);

		JScrollPane scrollPaneModificarEquipo = new JScrollPane();
		scrollPaneModificarEquipo.setBounds(10, 11, 789, 359);
		panelModificarEquipo.add(scrollPaneModificarEquipo);

		tblModificarEquipo = new JTable();
		tblModificarEquipo.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tblModificarEquipo.setFillsViewportHeight(true);
		tblModificarEquipo.setFont(new Font("Verdana", Font.PLAIN, 12));
		scrollPaneModificarEquipo.setViewportView(tblModificarEquipo);
		tblModificarEquipo.setModel(modificacionRegistros.mostrarDatos("equipo"));
		// Sirve para no permitir que el usuario reordene las columnas
		tblModificarEquipo.getTableHeader().setReorderingAllowed(false);

		JPanel panelModificarCausa = new JPanel();
		tabbedPaneModificar.addTab("Causa y Disciplina", null, panelModificarCausa, null);
		panelModificarCausa.setLayout(null);

		JScrollPane scrollPaneModificarCausa = new JScrollPane();
		scrollPaneModificarCausa.setBounds(10, 11, 536, 359);
		panelModificarCausa.add(scrollPaneModificarCausa);

		tblModificarCausa = new JTable() {
			/**
			 * 
			 */
			private static final long serialVersionUID = -3466158353530192531L;

			public boolean isCellEditable(int row, int column) {
				switch (column) {
				case 1: // selecciona la columna que se desea que no sea
						// editable
					return false;
				default:
					return true;
				}
			}
		};
		tblModificarCausa.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tblModificarCausa.setFillsViewportHeight(true);
		tblModificarCausa.setFont(new Font("Verdana", Font.PLAIN, 12));
		scrollPaneModificarCausa.setViewportView(tblModificarCausa);
		tblModificarCausa.setModel(modificacionRegistros.mostrarDatos("causa"));
		// Sirve para no permitir que el usuario reordene las columnas
		tblModificarCausa.getTableHeader().setReorderingAllowed(false);

		JScrollPane scrollPaneModificarDisciplina = new JScrollPane();
		scrollPaneModificarDisciplina.setBounds(556, 11, 243, 359);
		panelModificarCausa.add(scrollPaneModificarDisciplina);

		tblModificarDisciplina = new JTable();
		tblModificarDisciplina.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tblModificarDisciplina.setFillsViewportHeight(true);
		scrollPaneModificarDisciplina.setViewportView(tblModificarDisciplina);
		tblModificarDisciplina.setFont(new Font("Verdana", Font.PLAIN, 12));
		tblModificarDisciplina.setModel(modificacionRegistros.mostrarDatos("disciplina"));
		// Sirve para no permitir que el usuario reordene las columnas
		tblModificarDisciplina.getTableHeader().setReorderingAllowed(false);

		JPanel panelBorrar = new JPanel();
		tpMenu.addTab("Borrar", null, panelBorrar, null);
		panelBorrar.setLayout(new BorderLayout(0, 0));

		JPanel panelTituloBorrar = new JPanel();
		panelTituloBorrar.setBackground(Color.BLACK);
		panelBorrar.add(panelTituloBorrar, BorderLayout.NORTH);

		JLabel lblTituloBorrar = new JLabel("Borrar Registros");
		lblTituloBorrar.setForeground(Color.WHITE);
		lblTituloBorrar.setFont(new Font("Verdana", Font.BOLD, 16));
		panelTituloBorrar.add(lblTituloBorrar);

		JPanel panelCentroBorrar = new JPanel();
		panelBorrar.add(panelCentroBorrar, BorderLayout.CENTER);
		panelCentroBorrar.setLayout(null);

		JScrollPane scrollPaneBorrarCausa = new JScrollPane();
		scrollPaneBorrarCausa.setBounds(10, 11, 536, 359);
		panelCentroBorrar.add(scrollPaneBorrarCausa);

		tblBorrarCausa = new JTable() {

			/**
			 * 
			 */
			private static final long serialVersionUID = 1917807722999935768L;

			public boolean isCellEditable(int fila, int columna) {
				switch (columna) {
				case 0: // selecciona la columna que se desea que no sea
						// editable
					return false;
				case 1: // selecciona la columna que se desea que no sea
						// editable
					return false;
				default:
					return false;
				}
			}
		};
		tblBorrarCausa.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {

				if (e.getKeyChar() == KeyEvent.VK_DELETE) {
					log.info("Se presiono delete");
					borrarRegistros();
				}
			}
		});
		scrollPaneBorrarCausa.setViewportView(tblBorrarCausa);
		tblBorrarCausa.setModel(modificacionRegistros.mostrarDatos("causa"));
		tblBorrarCausa.setFont(new Font("Verdana", Font.PLAIN, 12));
		// Sirve para no permitir que el usuario reordene las columnas
		tblBorrarCausa.getTableHeader().setReorderingAllowed(false);

		JScrollPane scrollPaneBorrarDisciplina = new JScrollPane();
		scrollPaneBorrarDisciplina.setBounds(556, 11, 243, 359);
		panelCentroBorrar.add(scrollPaneBorrarDisciplina);

		tblBorrarDisciplina = new JTable() {

			/**
			 * 
			 */
			private static final long serialVersionUID = -5036637592998233488L;

			public boolean isCellEditable(int row, int column) {
				switch (column) {
				case 0: // selecciona la columna que se desea que no sea
						// editable
					return false;
				default:
					return false;
				}
			}
		};
		tblBorrarDisciplina.setFillsViewportHeight(true);
		scrollPaneBorrarDisciplina.setViewportView(tblBorrarDisciplina);
		tblBorrarDisciplina.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tblBorrarDisciplina.setModel(modificacionRegistros.mostrarDatos("disciplina"));
		tblBorrarDisciplina.setFont(new Font("Verdana", Font.PLAIN, 12));
		// Sirve para no permitir que el usuario reordene las columnas
		tblBorrarDisciplina.getTableHeader().setReorderingAllowed(false);

		JButton btnBorrarRegistros = new JButton("Borrar Registros");
		btnBorrarRegistros
				.setIcon(new ImageIcon(AdministracionRegistros.class.getResource("/iconos32x32/delete32x32.png")));
		btnBorrarRegistros.setFont(new Font("Verdana", Font.PLAIN, 12));
		btnBorrarRegistros.setBounds(628, 381, 171, 41);
		panelCentroBorrar.add(btnBorrarRegistros);

	}

	/**
	 * Borrar las causas seleccionadas
	 */
	private void borrarRegistros() {
		// TODO Escribir el codigo para borrar los registros seleccionados

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

		// Guardar informacion de una nueva Area
		Area area = new Area();
		if (nombreArea.length() == 0) {
			area.setNombreArea(null);
		} else {
			area.setNombreArea(nombreArea);
		}

		// Guarda informacion de una nueva SubArea
		SubArea subArea = new SubArea();
		if (nombreSubArea.length() == 0) {
			subArea.setNombreSubArea(null);
		} else {
			subArea.setNombreSubArea(nombreSubArea);

			if (cbArea.getSelectedIndex() == -1) {
				JOptionPane.showMessageDialog(null, "Debe seleccionar el area para esta subArea", "Seleccione Area",
						JOptionPane.INFORMATION_MESSAGE);
				return;
			}

			subArea.setIdArea(areaAQuePertenece);
		}

		// Guarda informacion de un nuevo Equipo
		Equipo equipo;
		// si no se escribe el nombre del equipo.
		if (codEquipo.length() == 0) {
			equipo = new Equipo(0, null, null, null);
		} else {

			if (cbSubArea.getSelectedIndex() == -1) {
				JOptionPane.showMessageDialog(null, "Debe seleccionar la SubArea para este Equipo",
						"Seleccione SubArea", JOptionPane.INFORMATION_MESSAGE);
				return;
			}

			equipo = new Equipo(0, codEquipo, nombreEquipo, subAreaAQuePertenece);
		}

		// Guarda informacion de una nueva causa

		List<Causa> listaCausa = null;
		if (nombreCausa.length() == 0) {

			listaCausa = new ArrayList<Causa>();
			Causa causa = new Causa();
			causa.setTipoCausa(null);
			listaCausa.add(causa);
		} else {

			// Obtiene el indice de todos los elementos seleccionados
			int[] indicesSeleccionados = listDisciplina.getSelectedIndices();

			// Lista de las Areas seleccionadas
			listaCausa = new ArrayList<Causa>();
			// Obtiene todos los elementos seleccionados usando los indices
			for (int i = 0; i < indicesSeleccionados.length; i++) {
				String seleccionDisciplina = String
						.valueOf(listDisciplina.getModel().getElementAt(indicesSeleccionados[i]));

				listaCausa.add(new Causa(nombreCausa, seleccionDisciplina));
			}
		}

		// Guarda informacion de una nueva Disciplina
		Disciplina disciplina = new Disciplina();
		if (nombreDisciplina.length() == 0) {
			disciplina.setNombreDisciplina(null);
		} else {
			disciplina.setNombreDisciplina(nombreDisciplina);
		}

		try {
			// Si el resultado es satisfactorio, notifica que se agregaron
			// nuevos registros a la Base de Datos
			if (modificacionRegistros.agregarRegistros(area, listaCausa, disciplina, equipo, subArea) == true) {

				Principal.lblStatusBar.setIcon(new ImageIcon(getClass().getResource("/iconos16x16/ok.png")));
				Principal.lblStatusBar.setText("Registro(s) agregado(s) correctamente");
				rellenarCombo();
				limpiarCampos();
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

	/**
	 * Limpia los componentes luego de agregar nuevos registros y actualiza
	 * tablas de modificar y borrar causas
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

		// Actualiza las tablas de Modificacion de Registros
		tblModificarArea.setModel(modificacionRegistros.mostrarDatos("area"));
		tblModificarSubArea.setModel(modificacionRegistros.mostrarDatos("subArea"));
		tblModificarEquipo.setModel(modificacionRegistros.mostrarDatos("equipo"));
		tblModificarCausa.setModel(modificacionRegistros.mostrarDatos("causa"));
		tblModificarDisciplina.setModel(modificacionRegistros.mostrarDatos("disciplina"));

		// Actualiza las tablas de Borrado de Registros
		tblBorrarCausa.setModel(modificacionRegistros.mostrarDatos("causa"));
		tblBorrarDisciplina.setModel(modificacionRegistros.mostrarDatos("disciplina"));
	}

	/**
	 * Rellena los comboBox de la interfaz
	 */
	private void rellenarCombo() {

		ResultSet rs = null;
		cbArea.removeAllItems();
		cbSubArea.removeAllItems();
		try {

			// Rellena ComboBox Area
			rs = manipulacionDatos.rellenarCombo("area", null);
			while (rs.next()) {
				cbArea.addItem(rs.getString("nombre_area"));
			}

			// Rellena ComboBox SubArea
			rs = manipulacionDatos.rellenarCombo("subArea", null);
			while (rs.next()) {
				cbSubArea.addItem(rs.getString("nombre_sub_area"));
			}

		} catch (SQLException sqle) {
			log.log(Level.SEVERE, sqle.toString(), sqle);
			JOptionPane.showMessageDialog(null, sqle.getMessage(), sqle.getClass().toString(),
					JOptionPane.ERROR_MESSAGE);
		} catch (Exception e) {
			log.log(Level.SEVERE, e.toString(), e);
			JOptionPane.showMessageDialog(null, e.getMessage(), e.getClass().toString(), JOptionPane.ERROR_MESSAGE);
		}
		cbSubArea.setSelectedItem(-1);
		cbArea.setSelectedItem(-1);
	}
}