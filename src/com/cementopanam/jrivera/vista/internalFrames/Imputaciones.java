package com.cementopanam.jrivera.vista.internalFrames;

import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.text.MaskFormatter;

import com.cementopanam.jrivera.controlador.ComparacionFechas;
import com.cementopanam.jrivera.controlador.Imputacion;
import com.cementopanam.jrivera.controlador.ManipulacionDatos;
import com.cementopanam.jrivera.controlador.paros.AdministracionParos;
import com.cementopanam.jrivera.controlador.paros.Paro;
import com.cementopanam.jrivera.controlador.usuario.AdministracionUsuario;
import com.cementopanam.jrivera.vista.ModificacionImputacion;
import com.cementopanam.jrivera.vista.NombreEquipo;
import com.cementopanam.jrivera.vista.Principal;
import com.cementopanam.jrivera.vista.helper.BarraEstado;
import com.cementopanam.jrivera.vista.helper.JComboBoxPersonalizado;
import com.cementopanam.jrivera.vista.helper.TimerThread;

import net.proteanit.sql.DbUtils;

public class Imputaciones extends JInternalFrame {

	/**
	 * 
	 */
	private static final Logger log = Logger.getLogger(Imputaciones.class.getName());
	private static final long serialVersionUID = -5302400411757216295L;
	ManipulacionDatos md;
	private AdministracionParos admParos;
	ComparacionFechas comparacionFechas;
	Principal pri;

	// private CapturaUsuario captura = null;
	// private CapturaUsuario nombreHost = new CapturaUsuario();
	private AdministracionUsuario admUsuario = new AdministracionUsuario();

	private JButton btnIniciarParo;
	private JButton btnDetenerParo;
	private JButton btnParosPendientes;
	private JButton btnParosCompletados;
	private JButton button_fechaInicio;
	private JButton button_fechaFin;

	public JButton button_equipo;
	public JButton button_subArea;
	public JButton button_area;
	public JButton button_disciplina;
	public JButton button_causa;

	protected TimerThread timerThread;

	private JLabel lblEquipo;
	private JLabel lblSubArea;
	private JLabel lblArea;
	private JLabel lblDisciplina;
	private JLabel lblCausa;
	private JLabel lblHoraDeInicio;
	private JLabel lblFechaDeFin;

	public BarraEstado statusBar;

	private DateFormat df;
	private MaskFormatter mascaraFecha;

	private JComboBox<String> comboBoxEquipo;
	private JComboBox<String> comboBoxSubArea;
	private JComboBox<String> comboBoxArea;
	private JComboBox<String> comboBoxDisciplina;
	private JComboBox<String> comboBoxCausa;

	private JScrollPane scrollPane_tabla;
	private JScrollPane scrollPane_MotivoCausa;

	private JTextArea textArea_motivoCausa;
	private JTable tablaParos;

	private JLayeredPane layeredPane_estadoEquipo;

	private String formatoFecha = "yyyy-MM-dd HH:mm:ss";
	private String[] estado = { "Completado", "Pendiente" };
	private String paroSeleccionado = "Completado";
	private String[] alerta = { "advertencia", "ok" };

	private final ButtonGroup buttonGroup = new ButtonGroup();
	private final ButtonGroup buttonGroup_1 = new ButtonGroup();
	
	private JRadioButton rdbtnCompletado;
	private JRadioButton rdbtnPendiente;
	private JRadioButton rdbtnActivo;
	private JRadioButton rdbtnInactivo;

	private JLayeredPane layeredPane_estadoParo;

	private JFormattedTextField formattedTextField_fechaInicio;
	private JFormattedTextField formattedTextField_fechaFin;

	int min = 0;
	int max = 100;
	private JPanel panelParos;

	/**
	 * Crea el frame
	 */
	public Imputaciones() {
		initComponents();
		rellenarcomboBox("area");
	}

	private void initComponents() {

		setFrameIcon(null);
		setTitle("Formulario de Imputaciones de Paro");
		setDefaultCloseOperation(HIDE_ON_CLOSE);
		setIconifiable(true);
		setClosable(true);
		getContentPane().setFont(new Font("Verdana", Font.PLAIN, 12));
		getContentPane().setLayout(null);
		setBounds(27, 26, 775, 533);

		layeredPane_estadoEquipo = new JLayeredPane();
		layeredPane_estadoEquipo.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)), "Estado de Equipo",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(51, 51, 51)));
		layeredPane_estadoEquipo.setBounds(80, 229, 209, 53);
		getContentPane().add(layeredPane_estadoEquipo);
		layeredPane_estadoEquipo.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		rdbtnActivo = new JRadioButton("Activo");
		rdbtnActivo.setSelected(true);
		rdbtnActivo.setFont(new Font("Verdana", Font.PLAIN, 12));
		layeredPane_estadoEquipo.add(rdbtnActivo);
		buttonGroup.add(rdbtnActivo);

		rdbtnInactivo = new JRadioButton("Inactivo");
		rdbtnInactivo.setFont(new Font("Verdana", Font.PLAIN, 12));
		layeredPane_estadoEquipo.add(rdbtnInactivo);
		buttonGroup.add(rdbtnInactivo);

		lblEquipo = new JLabel("Equipo");
		lblEquipo.setFont(new Font("Verdana", Font.PLAIN, 12));
		lblEquipo.setBounds(12, 203, 74, 15);
		getContentPane().add(lblEquipo);

		comboBoxEquipo = new JComboBox<String>();
		comboBoxEquipo.setEnabled(false);
		comboBoxEquipo.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {

				if (e.getStateChange() == ItemEvent.SELECTED) {

					if (!(comboBoxDisciplina.isEnabled())) {
						comboBoxDisciplina.setEnabled(true);
					}
					comboBoxDisciplina.removeAllItems();
					rellenarcomboBox("disciplina");
				}
			}
		});
		
		comboBoxEquipo.setFont(new Font("Verdana", Font.PLAIN, 12));
		comboBoxEquipo.setBounds(104, 198, 158, 24);
		getContentPane().add(comboBoxEquipo);

		lblSubArea = new JLabel("Sub Area");
		lblSubArea.setFont(new Font("Verdana", Font.PLAIN, 12));
		lblSubArea.setBounds(12, 172, 70, 15);
		getContentPane().add(lblSubArea);

		comboBoxSubArea = new JComboBoxPersonalizado();
		comboBoxSubArea.setEnabled(false);
		comboBoxSubArea.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {

				if (e.getStateChange() == ItemEvent.SELECTED) {

					if (!(comboBoxEquipo.isEnabled())) {
						comboBoxEquipo.setEnabled(true);
					}
					comboBoxEquipo.removeAllItems();
					rellenarcomboBox("equipo");
				}
			}
		});
		comboBoxSubArea.setFont(new Font("Verdana", Font.PLAIN, 12));
		comboBoxSubArea.setBounds(103, 167, 159, 24);
		getContentPane().add(comboBoxSubArea);

		lblArea = new JLabel("Area");
		lblArea.setFont(new Font("Verdana", Font.PLAIN, 12));
		lblArea.setBounds(12, 141, 70, 15);
		getContentPane().add(lblArea);

		comboBoxArea = new JComboBoxPersonalizado();
		comboBoxArea.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {

				if (e.getStateChange() == ItemEvent.SELECTED) {

					// Verifica si el combo de SubArea no esta activo
					if (!(comboBoxSubArea.isEnabled())) {
						comboBoxSubArea.setEnabled(true);
					}
					// Elimina todos los elementos del Combo
					comboBoxSubArea.removeAllItems();
					rellenarcomboBox("subArea");
					comboBoxEquipo.removeAllItems();
				}
			}
		});
		comboBoxArea.setFont(new Font("Verdana", Font.PLAIN, 12));
		comboBoxArea.setBounds(103, 136, 159, 24);
		getContentPane().add(comboBoxArea);

		lblDisciplina = new JLabel("Disciplina");
		lblDisciplina.setFont(new Font("Verdana", Font.PLAIN, 12));
		lblDisciplina.setBounds(12, 302, 70, 15);
		getContentPane().add(lblDisciplina);

		comboBoxDisciplina = new JComboBoxPersonalizado();
		comboBoxDisciplina.setEnabled(false);
		comboBoxDisciplina.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				
				if (e.getStateChange() == ItemEvent.SELECTED) {

					// Verifica si el combo de SubArea no esta activo
					if (!(comboBoxCausa.isEnabled())) {
						comboBoxCausa.setEnabled(true);
					}
					// Elimina todos los elementos del Combo
					comboBoxCausa.removeAllItems();
					rellenarcomboBox("causa");
				}
				
			}
		});

		comboBoxDisciplina.setFont(new Font("Verdana", Font.PLAIN, 12));
		comboBoxDisciplina.setBounds(104, 297, 158, 24);
		getContentPane().add(comboBoxDisciplina);

		lblCausa = new JLabel("Causa");
		lblCausa.setFont(new Font("Verdana", Font.PLAIN, 12));
		lblCausa.setBounds(12, 336, 70, 15);
		getContentPane().add(lblCausa);

		comboBoxCausa = new JComboBoxPersonalizado();
		comboBoxCausa.setEnabled(false);
		comboBoxCausa.setFont(new Font("Verdana", Font.PLAIN, 12));

		comboBoxCausa.setBounds(103, 331, 159, 24);
		getContentPane().add(comboBoxCausa);

		panelParos = new JPanel();
		panelParos.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Paros Completados",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panelParos.setBounds(354, 49, 762, 397);
		getContentPane().add(panelParos);
		panelParos.setLayout(null);

		scrollPane_tabla = new JScrollPane();
		scrollPane_tabla.setBounds(6, 16, 750, 375);
		panelParos.add(scrollPane_tabla);

		tablaParos = new JTable() {

			/**
			 * 
			 */
			private static final long serialVersionUID = -6964605111921202585L;

			@Override
			public Component prepareRenderer(TableCellRenderer renderizador, int fila, int columna) {

				// Ajusta la columna de la tabla a un tamano adecuado
				Component componente = super.prepareRenderer(renderizador, fila, columna);
				int archoRenderizador = componente.getPreferredSize().width;
				TableColumn columnaTabla = getColumnModel().getColumn(columna);
				columnaTabla.setPreferredWidth(
						Math.max(archoRenderizador + getIntercellSpacing().width, columnaTabla.getPreferredWidth()));
				return componente;
			}
		};
		tablaParos.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				int codigoParo = 0;
				
				if (e.getClickCount() == 2 && paroSeleccionado.equals("Pendiente")) {
	                System.out.println("Se hizo click dos veces");
	                System.out.println("El estado es: " + paroSeleccionado);
	                
	                int fila = tablaParos.getSelectedRow();
	                codigoParo = (int) tablaParos.getValueAt(fila, 0);
	                
	                
	                System.out.println("Codigo de Paro " + codigoParo);
	                
	                
	                Paro paro = new Paro();
	                paro.setCodigo(codigoParo);
	        		ModificacionImputacion modificacion = new ModificacionImputacion(paro, 
	        				new AdministracionParos(),getDesktopPane());

	        		setVisible(false);
	        		modificacion.setVisible(true);
	                
	                
	                
	            } 
				
			}
		});
		tablaParos.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		// Sirve para limitar para que sea una sola seleccion
		tablaParos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		//Sirve para no permitir la edicion por columnas
		tablaParos.setDefaultEditor(Object.class, null);
		// Sirve para no permitir que el usuario reordene las columnas
		tablaParos.getTableHeader().setReorderingAllowed(false);
		tablaParos.setFont(new Font("Verdana", Font.PLAIN, 12));

		scrollPane_tabla.setViewportView(tablaParos);
		actualizarTabla(estado[0]);

		btnIniciarParo = new JButton("Imputar Paro");
		btnIniciarParo.setIcon(new ImageIcon(Imputaciones.class.getResource("/iconos32x32/ok32x32.png")));
		btnIniciarParo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				imputarParo();
			}
		});
		btnIniciarParo.setFont(new Font("Verdana", Font.PLAIN, 12));
		btnIniciarParo.setBounds(431, 457, 158, 35);
		getContentPane().add(btnIniciarParo);

		btnDetenerParo = new JButton("Detener Paro");
		btnDetenerParo.setIcon(new ImageIcon(Imputaciones.class.getResource("/iconos32x32/stop.32x32.png")));
		btnDetenerParo.setEnabled(false);
		btnDetenerParo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				actualizarEstadoParo();


			}
		});
		btnDetenerParo.setFont(new Font("Verdana", Font.PLAIN, 12));
		btnDetenerParo.setBounds(609, 457, 156, 35);
		getContentPane().add(btnDetenerParo);

		button_equipo = new JButton("");
		button_equipo.setToolTipText("Informacion de Codigo y Nombre de Equipos");
		button_equipo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				new NombreEquipo();
			}
		});
		button_equipo.setIcon(new ImageIcon(Imputaciones.class.getResource("/iconos24x24/Support_help.png")));
		button_equipo.setFont(new Font("Verdana", Font.PLAIN, 12));
		button_equipo.setBounds(274, 198, 62, 25);
		getContentPane().add(button_equipo);

		button_subArea = new JButton("+");
		button_subArea.setEnabled(false);
		button_subArea.setFont(new Font("Verdana", Font.PLAIN, 12));
		button_subArea.setBounds(274, 167, 62, 25);
		getContentPane().add(button_subArea);

		button_area = new JButton("+");
		button_area.setEnabled(false);
		button_area.setFont(new Font("Verdana", Font.PLAIN, 12));
		button_area.setBounds(274, 136, 62, 25);
		getContentPane().add(button_area);

		button_disciplina = new JButton("+");
		button_disciplina.setEnabled(false);
		button_disciplina.setFont(new Font("Verdana", Font.PLAIN, 12));
		button_disciplina.setBounds(274, 299, 62, 25);
		getContentPane().add(button_disciplina);

		button_causa = new JButton("+");
		button_causa.setEnabled(false);
		button_causa.setFont(new Font("Verdana", Font.PLAIN, 12));
		button_causa.setBounds(274, 331, 62, 25);
		getContentPane().add(button_causa);

		lblHoraDeInicio = new JLabel("Fecha de Inicio");
		lblHoraDeInicio.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblHoraDeInicio.setBounds(12, 12, 85, 16);
		getContentPane().add(lblHoraDeInicio);

		df = new SimpleDateFormat(formatoFecha);
		df.setLenient(false);

		try {
			mascaraFecha = new MaskFormatter("####-##-## ##:##:##");
			mascaraFecha.setAllowsInvalid(false);

			formattedTextField_fechaInicio = new JFormattedTextField();
			mascaraFecha.install(formattedTextField_fechaInicio);
		} catch (ParseException e1) {
			log.log(Level.WARNING, e1.toString(), e1);
			JOptionPane.showMessageDialog(null, e1.getMessage(), e1.getClass().toString(), JOptionPane.ERROR_MESSAGE);
		}
		formattedTextField_fechaInicio.addFocusListener(new FocusAdapter() {

			@Override
			public void focusLost(FocusEvent e) {

				pri = new Principal();
				if (esFechaValida(formattedTextField_fechaInicio.getText()) == false) {
					pri.mostrarMensaje("La fecha introducida no es valida", alerta[0]);
				} else {
					pri.mostrarMensaje("Listo", alerta[1]);
				}
			}
		});

		try {
			mascaraFecha = new MaskFormatter("####-##-## ##:##:##");
			mascaraFecha.setAllowsInvalid(false);

			formattedTextField_fechaFin = new JFormattedTextField();
			mascaraFecha.install(formattedTextField_fechaFin);
		} catch (ParseException e1) {
			log.log(Level.WARNING, e1.toString(), e1);
			JOptionPane.showMessageDialog(null, e1.getMessage(), e1.getClass().toString(), JOptionPane.ERROR_MESSAGE);
		}

		formattedTextField_fechaInicio.setToolTipText("Ano-Mes-Dia Hora:Minuto:Segundo");
		formattedTextField_fechaInicio.setFont(new Font("Verdana", Font.PLAIN, 12));
		formattedTextField_fechaInicio.setBounds(104, 8, 157, 25);
		getContentPane().add(formattedTextField_fechaInicio);

		lblFechaDeFin = new JLabel("Fecha de Fin");
		lblFechaDeFin.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblFechaDeFin.setBounds(12, 44, 85, 16);
		getContentPane().add(lblFechaDeFin);

		formattedTextField_fechaFin.setToolTipText("Ano-Mes-Dia Hora:Minuto:Segundo");
		formattedTextField_fechaFin.setFont(new Font("Verdana", Font.PLAIN, 12));
		formattedTextField_fechaFin.setBounds(104, 40, 157, 25);
		getContentPane().add(formattedTextField_fechaFin);

		scrollPane_MotivoCausa = new JScrollPane();
		scrollPane_MotivoCausa.setViewportBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"),
				"Descripcion Extendida", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		scrollPane_MotivoCausa.setBounds(102, 367, 234, 79);

		getContentPane().add(scrollPane_MotivoCausa);

		textArea_motivoCausa = new JTextArea();
		textArea_motivoCausa.setToolTipText("Informacion Adicional sobre Causa Seleccionada");
		textArea_motivoCausa.setFont(new Font("Verdana", Font.PLAIN, 12));

		scrollPane_MotivoCausa.setViewportView(textArea_motivoCausa);

		layeredPane_estadoParo = new JLayeredPane();
		layeredPane_estadoParo.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)), "Estado de Paro",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(51, 51, 51)));
		layeredPane_estadoParo.setBounds(55, 72, 234, 53);
		getContentPane().add(layeredPane_estadoParo);
		layeredPane_estadoParo.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		rdbtnCompletado = new JRadioButton("Completado");
		rdbtnCompletado.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {

				rdbtnCompletadoMouseClicked(e);

			}
		});
		rdbtnCompletado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (rdbtnCompletado.isSelected()) {
					// formattedTextField_fechaFin.setEnabled(false);
					rdbtnPendiente.setEnabled(false);
					rdbtnInactivo.setEnabled(false);
					rdbtnActivo.setSelected(true);
				}
			}
		});
		buttonGroup_1.add(rdbtnCompletado);
		rdbtnCompletado.setSelected(false);
		rdbtnCompletado.setFont(new Font("Verdana", Font.PLAIN, 12));
		layeredPane_estadoParo.add(rdbtnCompletado);

		rdbtnPendiente = new JRadioButton("Pendiente");
		rdbtnPendiente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				formattedTextField_fechaFin.setText(null);
				formattedTextField_fechaFin.setEnabled(false);
				button_fechaFin.setEnabled(false);
			}
		});
		buttonGroup_1.add(rdbtnPendiente);
		rdbtnPendiente.setFont(new Font("Verdana", Font.PLAIN, 12));
		layeredPane_estadoParo.add(rdbtnPendiente);

		button_fechaInicio = new JButton("+");
		button_fechaInicio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				Date dateobj = new Date();
				formattedTextField_fechaInicio.setText(df.format(dateobj));
			}
		});
		button_fechaInicio.setFont(new Font("Verdana", Font.PLAIN, 12));
		button_fechaInicio.setBounds(274, 8, 62, 25);
		getContentPane().add(button_fechaInicio);

		button_fechaFin = new JButton("+");
		button_fechaFin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				Date dateobj = new Date();
				formattedTextField_fechaFin.setText(df.format(dateobj));
			}
		});
		button_fechaFin.setFont(new Font("Verdana", Font.PLAIN, 12));
		button_fechaFin.setBounds(274, 40, 62, 25);
		getContentPane().add(button_fechaFin);

		btnParosPendientes = new JButton("Paros Pendientes");
		btnParosPendientes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelParos.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Paros Pendientes",
						TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
				btnParosPendientesActionPerformed(e);
			}
		});
		btnParosPendientes.setFont(new Font("Verdana", Font.PLAIN, 12));
		btnParosPendientes.setBounds(619, 12, 146, 25);
		getContentPane().add(btnParosPendientes);

		btnParosCompletados = new JButton("Paros Completados");
		btnParosCompletados.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelParos.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Paros Completados",
						TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
				btnParosCompletadosActionPerformed(e);
			}
		});
		btnParosCompletados.setFont(new Font("Verdana", Font.PLAIN, 12));
		btnParosCompletados.setBounds(449, 12, 158, 25);
		getContentPane().add(btnParosCompletados);
		setBounds(27, 26, 775, 570);
	}

	/**
	 * Actualiza el paro de Pendiente a Completado
	 */
	private void actualizarEstadoParo() {
		
		boolean resultado;
		String usuarioActual = Principal.usuarioActual.getText();
		Date pruebaFecha = null;

		try {

			int fila = tablaParos.getSelectedRow();
			int idParo = Integer.parseInt(String.valueOf(tablaParos.getValueAt(fila, 0)));

			String usuarioTabla = String.valueOf(tablaParos.getValueAt(fila, 1));
			String fechaInicio = String.valueOf(tablaParos.getValueAt(fila, 5));
			String fechaFin = formattedTextField_fechaFin.getText();

			String tipoUsuario = mostrarUsuario(usuarioActual);

			// Verifica si el usuario autenticado tiene privilegios para modificar el paro seleccionado
			if (!(usuarioActual.equals(usuarioTabla) || tipoUsuario.equals("administrador"))) {
				JOptionPane.showMessageDialog(null, "No tiene privilegios para modificar este paro", 
						"Privilegios de Usuario", JOptionPane.INFORMATION_MESSAGE);
				
				return;
			}
			
			 pruebaFecha = df.parse(fechaFin);
			 if (!df.format(pruebaFecha).equals(fechaFin)) {
				 JOptionPane.showMessageDialog(null, "La Fecha introducida es invalida",
						 "Fecha de Fin", JOptionPane.INFORMATION_MESSAGE);
				 
				 return;
			}
			
			String solucion = escribirSolucion();
			
			if(solucion == null) {
				
				JOptionPane.showMessageDialog(null, "Debe escribir la solucion del paro",
						"Solucion de Paro", JOptionPane.WARNING_MESSAGE);
				
				return;
			}
			admParos = new AdministracionParos();
			resultado = admParos.actualizarParo(new Paro(idParo, fechaInicio, fechaFin, solucion));

			if (resultado == true) {
				Principal.lblStatusBar.setIcon(new ImageIcon(getClass().getResource("/iconos16x16/ok.png")));
				Principal.lblStatusBar.setText("Paro Imputado correctamente");
				limpiarCampos();
				actualizarTabla(estado[1]);
			} else {
				Principal.lblStatusBar
						.setIcon(new ImageIcon(getClass().getResource("/iconos16x16/warning-icon.png")));
				Principal.lblStatusBar.setText("No se pudo completar la operacion");
				return;
			}
			pri.mostrarPendientes(admParos.contarPendientes());
		}
		catch (SQLException sqle) {
			log.log(Level.SEVERE, sqle.toString(), sqle);
			JOptionPane.showMessageDialog(null, sqle.getMessage(), sqle.getClass().toString(),
					JOptionPane.ERROR_MESSAGE);
			return;
		}
		catch (ParseException pe) {
			JOptionPane.showMessageDialog(null, "La Fecha introducida es invalida", 
					"Fecha invalida", JOptionPane.ERROR_MESSAGE);
		}
		catch (ArrayIndexOutOfBoundsException aiobe) {
			JOptionPane.showMessageDialog(null, "Debe elegir un paro", "Seleccionar paro",
					JOptionPane.ERROR_MESSAGE);
			return;
		}
		catch (NumberFormatException nfe) {
			log.log(Level.SEVERE, nfe.toString(), nfe);
			JOptionPane.showMessageDialog(null, nfe.getMessage(), nfe.getClass().toString(), JOptionPane.ERROR_MESSAGE);
		}
		catch (Exception e1) {
			log.log(Level.SEVERE, e1.toString(), e1);
			JOptionPane.showMessageDialog(null, e1.getMessage(), e1.getClass().toString(),
					JOptionPane.ERROR_MESSAGE);
			return;
		}
	}

	private boolean esFechaValida(String fecha) {

		try {
			df.parse(fecha);
		} catch (ParseException e) {
			return false;
		}
		return true;
	}

	// Muestra los paros en un JTable
	private void actualizarTabla(String estado) {

		ResultSet rs = null;
		try {
			md = new ManipulacionDatos();

			rs = md.actualizarTabla(estado);
			tablaParos.setModel(DbUtils.resultSetToTableModel(rs));
		} catch (Exception e) {
			log.log(Level.SEVERE, e.toString(), e);
			JOptionPane.showMessageDialog(null, e.getMessage(), e.getClass().toString(), JOptionPane.ERROR_MESSAGE);
		} finally {
			md.cerrarConexiones();
		}
	}

	/**
	 * Metodo para poblar los comboBox
	 */
	private void rellenarcomboBox(String campo) {

		ManipulacionDatos md = new ManipulacionDatos();

		ResultSet rs = null;
		md = new ManipulacionDatos();

		try {
			// Rellena comboBox Area
			if (campo.equals("area")) {
				rs = md.rellenarCombo("area", null);

				while (rs.next()) {
					comboBoxArea.addItem(rs.getString("nombre_area"));
				}

				comboBoxArea.setSelectedIndex(-1);
			}
			// Rellena comboBox SubArea
			else if (campo.equals("subArea")) {
				// Busca el indice de area seleccionado
				String area = String.valueOf(comboBoxArea.getSelectedItem());

				rs = md.rellenarCombo("subArea", area);
				while (rs.next()) {
					String subArea = rs.getString("nombre_sub_area");
					comboBoxSubArea.addItem(subArea);
				}
				comboBoxSubArea.setSelectedIndex(-1);
			} else if (campo.equals("equipo")) {
				// Busca el indice de subArea seleccionado
				String subArea = String.valueOf(comboBoxSubArea.getSelectedItem());

				rs = md.rellenarCombo("equipo", subArea);
				while (rs.next()) {
					String cod_equipo = rs.getString("cod_equipo");
					comboBoxEquipo.addItem(cod_equipo);
				}
				comboBoxEquipo.setSelectedIndex(-1);
			}

			// Rellena comboBox Area
			if (campo.equals("disciplina")) {
				rs = md.rellenarCombo("disciplina", null);

				while (rs.next()) {
					comboBoxDisciplina.addItem(rs.getString("nombre_disciplina"));
				}

				comboBoxDisciplina.setSelectedIndex(-1);
			}

			if (campo.equals("causa")) {
				// Rellena comboBox Area
				String disciplina = String.valueOf(comboBoxDisciplina.getSelectedItem());
				rs = md.rellenarCombo("causa", disciplina);

				while (rs.next()) {
					comboBoxCausa.addItem(rs.getString("tipo_causa"));
				}

				comboBoxCausa.setSelectedIndex(-1);
			}
		}

		catch (SQLException sqle) {
			log.log(Level.SEVERE, sqle.toString(), sqle);
			JOptionPane.showMessageDialog(null, sqle.getMessage(), sqle.getClass().toString(),
					JOptionPane.ERROR_MESSAGE);
		}

		catch (Exception e) {
			log.log(Level.SEVERE, e.toString(), e);
			JOptionPane.showMessageDialog(null, e.getMessage(), e.getClass().toString(), JOptionPane.ERROR_MESSAGE);
		} finally {
			md.cerrarConexiones();
		}
	}

	/**
	 * Limpia los campos una vez se complete una imputacion exitosa
	 */
	private void limpiarCampos() {

		comboBoxEquipo.setSelectedIndex(-1);
		comboBoxSubArea.setSelectedIndex(-1);
		comboBoxArea.setSelectedIndex(-1);
		comboBoxDisciplina.setSelectedIndex(-1);
		comboBoxCausa.setSelectedIndex(-1);

		rdbtnInactivo.setEnabled(true);
		rdbtnPendiente.setEnabled(true);

		formattedTextField_fechaInicio.setText("");
		formattedTextField_fechaFin.setText("");
		textArea_motivoCausa.setText("");
		
		comboBoxEquipo.setEnabled(false);
		comboBoxSubArea.setEnabled(false);
		comboBoxCausa.setEnabled(false);
	}

	/**
	 * Guarda la informacion suministrado de un paro
	 */
	private void imputarParo() {

			admParos = new AdministracionParos();
			boolean resultado = false;
			Date pruebaFecha = null;
			
			try {

				String fechaInicio = formattedTextField_fechaInicio.getText();
				String fechaFin = formattedTextField_fechaFin.getText();
				String equipo = comboBoxEquipo.getSelectedItem().toString();
				String estadoParo = obtenerEstadoParo();
				String estadoEquipo = obtenerEstadoEquipo();
				String disciplina = comboBoxDisciplina.getSelectedItem().toString();
				String tipoCausa = comboBoxCausa.getSelectedItem().toString();
				String usuario = Principal.usuarioActual.getText();
				String otraCausa = textArea_motivoCausa.getText();

				
				 if(rdbtnCompletado.isSelected()) {
					//Valida si las fechas introducidas tienen un formato correcto
					 pruebaFecha = df.parse(fechaFin);
					 if (!df.format(pruebaFecha).equals(fechaFin)) {
						 JOptionPane.showMessageDialog(null, "La Fecha introducida es invalida",
								 "Fecha de Fin", JOptionPane.INFORMATION_MESSAGE);
						 
						 return;
					}
				 }
	
				 
				if (estadoParo.equals(null)) {
					JOptionPane.showMessageDialog(null, "Debe seleccionar el estado de Paro", "Seleccionar Paro",
							JOptionPane.ERROR_MESSAGE);

					return;
				}

				else if (otraCausa.equals(null) || otraCausa.trim().length() == 0) {

					JOptionPane.showMessageDialog(null, "Debe escribir Descripcional Adicional de Paro",
							"Descripcion Adicional", JOptionPane.ERROR_MESSAGE);
					return;

				} else {
					String solucion = "";

					// Verifica si el Paro es Compleado o Pendiente
					if (estadoParo.equals("Completado")) {
						
						solucion = escribirSolucion();
						
						if(solucion == null) {
							
							JOptionPane.showMessageDialog(null, "Debe escribir la solucion del paro",
									"Solucion de Paro", JOptionPane.WARNING_MESSAGE);
							
							return;
						}
						//Valida si las fechas introducidas tienen un formato correcto
						 pruebaFecha = df.parse(fechaInicio);
						 pruebaFecha = df.parse(fechaFin);
						 
						// En caso de que el paro este Completado		
						resultado = admParos.imputarParo(new Imputacion(equipo, usuario, disciplina, 
								tipoCausa, otraCausa, fechaInicio, fechaFin, estadoParo, estadoEquipo, solucion));
					} else {
						//Valida si las fechas introducidas tienen un formato correcto
						 pruebaFecha = df.parse(fechaInicio);
						 
						// En caso de que el paro este Pendiente
						resultado = admParos.imputarParo(new Imputacion(equipo, usuario, disciplina, 
								tipoCausa, otraCausa, fechaInicio, null, estadoParo, estadoEquipo, null));
					}

					// Si el resultado es satisfactorio, notifica la imputacion
					// exitosa o no por el contrario
					if (resultado == true) {
						Principal.lblStatusBar.setIcon(new ImageIcon(getClass().getResource("/iconos16x16/ok.png")));
						Principal.lblStatusBar.setText("Paro Imputado correctamente");
						limpiarCampos();
						actualizarTabla(estado[0]);
						
					} else {
						Principal.lblStatusBar
								.setIcon(new ImageIcon(getClass().getResource("/iconos16x16/warning-icon.png")));
						Principal.lblStatusBar.setText("No se pudo completar la operacion");
					}
					pri.mostrarPendientes(admParos.contarPendientes());
				}
			} catch (NumberFormatException nfe) {
				log.log(Level.SEVERE, nfe.toString(), nfe);
				JOptionPane.showMessageDialog(null, nfe.getMessage(), nfe.getClass().toString(), JOptionPane.ERROR_MESSAGE);
			}
			catch (ParseException pe) {
				log.log(Level.SEVERE, pe.toString(), pe);
				JOptionPane.showMessageDialog(null, "Formato de fecha incorrecto", 
						"Fecha invalida", JOptionPane.ERROR_MESSAGE);
			}
			catch (NullPointerException npe) {
				log.log(Level.WARNING, npe.toString(), npe);
				JOptionPane.showMessageDialog(null, "Debe rellenar todos los campos", npe.getClass().toString(),
						JOptionPane.ERROR_MESSAGE);
			} catch (Exception ex) {
				log.log(Level.SEVERE, ex.toString(), ex);
				JOptionPane.showMessageDialog(null, ex.getMessage(), ex.getClass().toString(), JOptionPane.ERROR_MESSAGE);
			}
	}

	/**
	 * Muestra el panel para introducir la solucion de paros completados
	 * @return la solucion introducida por el usuario
	 */
	private String escribirSolucion() {
		
		//Coloca los botones del JOptionPane en español
		UIManager.put("OptionPane.okButtonText", "Aceptar");
		UIManager.put("OptionPane.cancelButtonText", "Cancelar");
		String solucion = JOptionPane.showInputDialog(null,
				"¿Que se tuvo que hacer para solucionar este paro?", "Solucion de paro",
				JOptionPane.INFORMATION_MESSAGE);

		if (solucion == null || solucion.equals(null) || solucion == ""
				|| solucion.equals("")) {
			return null;
		}
		return solucion;
	}

	/**
	 * Retorna el Estado de los equipos
	 * @return Retorna el estado del equipo
	 */
	private String obtenerEstadoEquipo() {
		if (rdbtnActivo.isSelected()) {
			return rdbtnActivo.getText();
		}
		return rdbtnInactivo.getText();
	}

	/**
	 * Retorna el Estado de Paro elegido
	 * @return Retorna el estado del Paro
	 */
	private String obtenerEstadoParo() {
		if (rdbtnCompletado.isSelected()) {
			return rdbtnCompletado.getText();
		} else if (rdbtnPendiente.isSelected()) {
			return rdbtnPendiente.getText();
		} else
			return null;
	}

	// Metodo para activar radioButtions desactivados
	private void rdbtnCompletadoMouseClicked(MouseEvent e) {

		if ((e.getClickCount() == 2) && !(rdbtnPendiente.isEnabled())) {
			rdbtnPendiente.setEnabled(true);
			rdbtnInactivo.setEnabled(true);
			formattedTextField_fechaInicio.setEnabled(true);
			formattedTextField_fechaFin.setEnabled(true);
			button_fechaFin.setEnabled(true);
		}
	}

	// Metodo para mostrar los Paros Completados en la Tabla
	private void btnParosCompletadosActionPerformed(ActionEvent e) {

		paroSeleccionado = "Completado";
		formattedTextField_fechaInicio.setEnabled(true);
		button_fechaInicio.setEnabled(true);
		rdbtnPendiente.setEnabled(true);
		comboBoxArea.setEnabled(true);
		btnIniciarParo.setEnabled(true);
		comboBoxDisciplina.setEnabled(true);
		
		comboBoxCausa.setEnabled(true);
		scrollPane_MotivoCausa.setEnabled(true);
		textArea_motivoCausa.setEnabled(true);
		rdbtnInactivo.setEnabled(true);
		formattedTextField_fechaFin.setEnabled(true);
		button_fechaFin.setEnabled(true);
		btnDetenerParo.setEnabled(false);

		if (SwingUtilities.isEventDispatchThread()) {

			SwingUtilities.invokeLater(new Runnable() {

				@Override
				public void run() {
					actualizarTabla(estado[0]);
				}
			});
		}
	}

	/**
	 * Muestra los Paros Pendientes en la Tabla
	 * 
	 * @param e
	 */
	private void btnParosPendientesActionPerformed(ActionEvent e) {

		paroSeleccionado = "Pendiente";
		formattedTextField_fechaInicio.setEnabled(false);
		button_fechaInicio.setEnabled(false);
		rdbtnPendiente.setEnabled(false);
		comboBoxArea.setEnabled(false);
		comboBoxSubArea.setEnabled(false);
		comboBoxEquipo.setEnabled(false);
		rdbtnInactivo.setEnabled(false);
		comboBoxDisciplina.setEnabled(false);

		comboBoxCausa.setEnabled(false);
		scrollPane_MotivoCausa.setEnabled(false);
		textArea_motivoCausa.setEnabled(false);
		btnIniciarParo.setEnabled(false);
		rdbtnCompletado.setSelected(true);
		btnDetenerParo.setEnabled(true);
		formattedTextField_fechaFin.setEnabled(true);
		button_fechaFin.setEnabled(true);
		rdbtnActivo.setSelected(true);

		if (SwingUtilities.isEventDispatchThread()) {

			SwingUtilities.invokeLater(new Runnable() {

				@Override
				public void run() {
					actualizarTabla(estado[1]);
				}
			});
		}
	}

	private String mostrarUsuario(String usuarioSeleccionado) {

		String tipoUsuario = "";
		ResultSet rs = null;

		try {
			rs = admUsuario.mostrarUsuario(usuarioSeleccionado);

			if (rs.next()) {

				tipoUsuario = rs.getString("tipo_usuario");
			}

		} catch (SQLException sqle) {
			log.log(Level.SEVERE, sqle.toString(), sqle);
			JOptionPane.showMessageDialog(null, sqle.getMessage(), sqle.getClass().toString(),
					JOptionPane.ERROR_MESSAGE);
		} catch (Exception e) {
			log.log(Level.SEVERE, e.toString(), e);
			JOptionPane.showMessageDialog(null, e.getMessage(), e.getClass().toString(), JOptionPane.ERROR_MESSAGE);
		}
		return tipoUsuario;
	}

	public void actualizarBarraEstado(int nuevoValor) {
		Principal.pbar.setValue(nuevoValor);
	}
}