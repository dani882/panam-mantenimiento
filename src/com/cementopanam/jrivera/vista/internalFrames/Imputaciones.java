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
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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

import org.apache.log4j.Logger;

import com.cementopanam.jrivera.controlador.Imputacion;
import com.cementopanam.jrivera.controlador.ManipulacionDatos;
import com.cementopanam.jrivera.controlador.paros.AdministracionParos;
import com.cementopanam.jrivera.controlador.paros.Paro;
import com.cementopanam.jrivera.controlador.usuario.AdministracionUsuario;
import com.cementopanam.jrivera.controlador.usuario.CapturaUsuario;
import com.cementopanam.jrivera.vista.ModificacionPendiente;
import com.cementopanam.jrivera.vista.NombreEquipo;
import com.cementopanam.jrivera.vista.Principal;
import com.cementopanam.jrivera.vista.helper.BarraEstado;
import com.cementopanam.jrivera.vista.helper.JComboBoxPersonalizado;
import com.cementopanam.jrivera.vista.helper.TimerThread;

import net.proteanit.sql.DbUtils;

/**
 * Interfaz de Usuario para almacenar los paros en la base de datos
 * @author jrivera
 *
 */
public class Imputaciones extends JInternalFrame {

	/**
	 * 
	 */
	private static final Logger logger = Logger.getLogger(Imputaciones.class);
	private static final long serialVersionUID = -5302400411757216295L;

	private ManipulacionDatos md;
	private AdministracionParos admParos;
	private Principal pri;

	// private CapturaUsuario captura = null;
	// private CapturaUsuario nombreHost = new CapturaUsuario();
	private AdministracionUsuario admUsuario = new AdministracionUsuario();

	private JButton btnIniciarParo;
	private JButton btnDetenerParo;
	private JButton btnParosPendientes;
	private JButton btnParosCompletados;
	private JButton btnFechaInicio;
	private JButton btnFechaFin;
	private JButton btnEquipo;
	private JButton btnSubArea;
	private JButton btnArea;
	private JButton btnDisciplina;
	private JButton btnCausa;

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

	private JComboBox<String> cbEquipo;
	private JComboBox<String> cbSubArea;
	private JComboBox<String> cbArea;
	private JComboBox<String> cbDisciplina;
	private JComboBox<String> cbCausa;

	private JScrollPane scrollPaneTablaParos;
	private JScrollPane scrollPaneMotivoCausa;

	private JTextArea textAreaMotivoCausa;
	private JTable tblParos;

	private JLayeredPane layeredPaneEstadoEquipo;

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

	private JLayeredPane layeredPaneEstadoParo;

	private JFormattedTextField formattedTextFieldFechaInicio;
	private JFormattedTextField formattedTextFieldFechaFin;

	int min = 0;
	int max = 100;
	private JPanel panelParos;

	private CapturaUsuario captura = new CapturaUsuario();
	private String usuarioLog = Principal.usuarioActual.getText();

	/**
	 * Crea el frame
	 */
	public Imputaciones() {

		initComponents(); // Inicia los componentes de la interfaz
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

		layeredPaneEstadoEquipo = new JLayeredPane();
		layeredPaneEstadoEquipo.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)), "Estado de Equipo",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(51, 51, 51)));
		layeredPaneEstadoEquipo.setBounds(80, 229, 209, 53);
		getContentPane().add(layeredPaneEstadoEquipo);
		layeredPaneEstadoEquipo.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		rdbtnActivo = new JRadioButton("Activo");
		rdbtnActivo.setSelected(true);
		rdbtnActivo.setFont(new Font("Verdana", Font.PLAIN, 12));
		layeredPaneEstadoEquipo.add(rdbtnActivo);
		buttonGroup.add(rdbtnActivo);

		rdbtnInactivo = new JRadioButton("Inactivo");
		rdbtnInactivo.setFont(new Font("Verdana", Font.PLAIN, 12));
		layeredPaneEstadoEquipo.add(rdbtnInactivo);
		buttonGroup.add(rdbtnInactivo);

		lblEquipo = new JLabel("Equipo");
		lblEquipo.setFont(new Font("Verdana", Font.PLAIN, 12));
		lblEquipo.setBounds(12, 203, 74, 15);
		getContentPane().add(lblEquipo);

		cbEquipo = new JComboBox<String>();
		cbEquipo.setEnabled(false);
		cbEquipo.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {

				if (e.getStateChange() == ItemEvent.SELECTED) {

					if (!(cbDisciplina.isEnabled())) {
						cbDisciplina.setEnabled(true);
					}
					cbDisciplina.removeAllItems();
					rellenarcomboBox("disciplina");
				}
			}
		});

		cbEquipo.setFont(new Font("Verdana", Font.PLAIN, 12));
		cbEquipo.setBounds(104, 198, 158, 24);
		getContentPane().add(cbEquipo);

		lblSubArea = new JLabel("Sub Area");
		lblSubArea.setFont(new Font("Verdana", Font.PLAIN, 12));
		lblSubArea.setBounds(12, 172, 70, 15);
		getContentPane().add(lblSubArea);

		cbSubArea = new JComboBoxPersonalizado();
		cbSubArea.setEnabled(false);
		cbSubArea.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {

				if (e.getStateChange() == ItemEvent.SELECTED) {

					if (!(cbEquipo.isEnabled())) {
						cbEquipo.setEnabled(true);
					}
					cbEquipo.removeAllItems();
					rellenarcomboBox("equipo");
				}
			}
		});
		cbSubArea.setFont(new Font("Verdana", Font.PLAIN, 12));
		cbSubArea.setBounds(103, 167, 159, 24);
		getContentPane().add(cbSubArea);

		lblArea = new JLabel("Area");
		lblArea.setFont(new Font("Verdana", Font.PLAIN, 12));
		lblArea.setBounds(12, 141, 70, 15);
		getContentPane().add(lblArea);

		cbArea = new JComboBoxPersonalizado();
		cbArea.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {

				if (e.getStateChange() == ItemEvent.SELECTED) {

					// Verifica si el combo de SubArea no esta activo
					if (!(cbSubArea.isEnabled())) {
						cbSubArea.setEnabled(true);
					}
					// Elimina todos los elementos del ComboBox SubArea
					cbSubArea.removeAllItems();
					rellenarcomboBox("subArea");
					cbEquipo.removeAllItems();
				}
			}
		});
		cbArea.setFont(new Font("Verdana", Font.PLAIN, 12));
		cbArea.setBounds(103, 136, 159, 24);
		getContentPane().add(cbArea);

		lblDisciplina = new JLabel("Disciplina");
		lblDisciplina.setFont(new Font("Verdana", Font.PLAIN, 12));
		lblDisciplina.setBounds(12, 302, 70, 15);
		getContentPane().add(lblDisciplina);

		cbDisciplina = new JComboBoxPersonalizado();
		cbDisciplina.setEnabled(false);
		cbDisciplina.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {

				if (e.getStateChange() == ItemEvent.SELECTED) {

					// Verifica si el combo de SubArea no esta activo
					if (!(cbCausa.isEnabled())) {
						cbCausa.setEnabled(true);
					}
					// Elimina todos los elementos del ComboBox Causa
					cbCausa.removeAllItems();
					rellenarcomboBox("causa");
				}

			}
		});

		cbDisciplina.setFont(new Font("Verdana", Font.PLAIN, 12));
		cbDisciplina.setBounds(104, 297, 158, 24);
		getContentPane().add(cbDisciplina);

		lblCausa = new JLabel("Causa");
		lblCausa.setFont(new Font("Verdana", Font.PLAIN, 12));
		lblCausa.setBounds(12, 336, 70, 15);
		getContentPane().add(lblCausa);

		cbCausa = new JComboBoxPersonalizado();
		cbCausa.setEnabled(false);
		cbCausa.setFont(new Font("Verdana", Font.PLAIN, 12));

		cbCausa.setBounds(103, 331, 159, 24);
		getContentPane().add(cbCausa);

		panelParos = new JPanel();
		panelParos.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Paros Completados",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panelParos.setBounds(354, 49, 762, 397);
		getContentPane().add(panelParos);
		panelParos.setLayout(null);

		scrollPaneTablaParos = new JScrollPane();
		scrollPaneTablaParos.setBounds(6, 16, 750, 375);
		panelParos.add(scrollPaneTablaParos);

		tblParos = new JTable() {

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
		
		
		tblParos.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				
				// Valida si se presiono la tecla enter en el Jtable de Paros Pendientes
				if (e.getKeyCode() == KeyEvent.VK_ENTER && paroSeleccionado.equals("Pendiente")) {

					visualizarPendiente();
				}
			}
		});
		tblParos.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				// Valida si se dio click dos veces en el Jtable de Paros Pendientes
				if (e.getClickCount() == 2 && paroSeleccionado.equals("Pendiente")) {

					visualizarPendiente();
				}
			}
		});
		tblParos.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		// Sirve para limitar para que sea una sola seleccion
		tblParos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		// Sirve para no permitir la edicion por columnas
		tblParos.setDefaultEditor(Object.class, null);
		// Sirve para no permitir que el usuario reordene las columnas
		tblParos.getTableHeader().setReorderingAllowed(false);
		tblParos.setFont(new Font("Verdana", Font.PLAIN, 12));

		scrollPaneTablaParos.setViewportView(tblParos);
		actualizarTabla(estado[0]); // Muestra el listado de Paros Completados

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

		btnEquipo = new JButton("");
		btnEquipo.setToolTipText("Informacion de Codigo y Nombre de Equipos");
		btnEquipo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				mostrarAyudaEquipo();
			}
		});
		btnEquipo.setIcon(new ImageIcon(Imputaciones.class.getResource("/iconos24x24/Support_help.png")));
		btnEquipo.setFont(new Font("Verdana", Font.PLAIN, 12));
		btnEquipo.setBounds(274, 198, 62, 25);
		getContentPane().add(btnEquipo);

		btnSubArea = new JButton("+");
		btnSubArea.setEnabled(false);
		btnSubArea.setFont(new Font("Verdana", Font.PLAIN, 12));
		btnSubArea.setBounds(274, 167, 62, 25);
		getContentPane().add(btnSubArea);

		btnArea = new JButton("+");
		btnArea.setEnabled(false);
		btnArea.setFont(new Font("Verdana", Font.PLAIN, 12));
		btnArea.setBounds(274, 136, 62, 25);
		getContentPane().add(btnArea);

		btnDisciplina = new JButton("+");
		btnDisciplina.setEnabled(false);
		btnDisciplina.setFont(new Font("Verdana", Font.PLAIN, 12));
		btnDisciplina.setBounds(274, 299, 62, 25);
		getContentPane().add(btnDisciplina);

		btnCausa = new JButton("+");
		btnCausa.setEnabled(false);
		btnCausa.setFont(new Font("Verdana", Font.PLAIN, 12));
		btnCausa.setBounds(274, 331, 62, 25);
		getContentPane().add(btnCausa);

		lblHoraDeInicio = new JLabel("Fecha de Inicio");
		lblHoraDeInicio.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblHoraDeInicio.setBounds(12, 12, 85, 16);
		getContentPane().add(lblHoraDeInicio);

		df = new SimpleDateFormat(formatoFecha);
		df.setLenient(false);

		try {
			mascaraFecha = new MaskFormatter("####-##-## ##:##:##");
			mascaraFecha.setAllowsInvalid(false);

			formattedTextFieldFechaInicio = new JFormattedTextField();
			mascaraFecha.install(formattedTextFieldFechaInicio);
		} catch (ParseException e1) {
			logger.error("Usuario: " + usuarioLog + " en PC: " + captura.obtenerNombrePC() + ". Exception: "
					+ e1.toString());
			JOptionPane.showMessageDialog(null, e1.getMessage(), e1.getClass().toString(), JOptionPane.ERROR_MESSAGE);
		}
		formattedTextFieldFechaInicio.addFocusListener(new FocusAdapter() {

			@Override
			public void focusLost(FocusEvent e) {

				pri = new Principal();
				if (esFechaValida(formattedTextFieldFechaInicio.getText()) == false) {
					pri.mostrarMensaje("La fecha introducida no es valida", alerta[0]);
				} else {
					pri.mostrarMensaje("Listo", alerta[1]);
				}
			}
		});

		try {
			mascaraFecha = new MaskFormatter("####-##-## ##:##:##");
			mascaraFecha.setAllowsInvalid(false);

			formattedTextFieldFechaFin = new JFormattedTextField();
			mascaraFecha.install(formattedTextFieldFechaFin);
		} catch (ParseException e1) {
			logger.error("Usuario: " + usuarioLog + " en PC: " + captura.obtenerNombrePC() + ". Exception: "
					+ e1.toString());
			JOptionPane.showMessageDialog(null, e1.getMessage(), e1.getClass().toString(), JOptionPane.ERROR_MESSAGE);
		}

		formattedTextFieldFechaInicio.setToolTipText("Ano-Mes-Dia Hora:Minuto:Segundo");
		formattedTextFieldFechaInicio.setFont(new Font("Verdana", Font.PLAIN, 12));
		formattedTextFieldFechaInicio.setBounds(104, 8, 157, 25);
		getContentPane().add(formattedTextFieldFechaInicio);

		lblFechaDeFin = new JLabel("Fecha de Fin");
		lblFechaDeFin.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblFechaDeFin.setBounds(12, 44, 85, 16);
		getContentPane().add(lblFechaDeFin);

		formattedTextFieldFechaFin.setToolTipText("Ano-Mes-Dia Hora:Minuto:Segundo");
		formattedTextFieldFechaFin.setFont(new Font("Verdana", Font.PLAIN, 12));
		formattedTextFieldFechaFin.setBounds(104, 40, 157, 25);
		getContentPane().add(formattedTextFieldFechaFin);

		scrollPaneMotivoCausa = new JScrollPane();
		scrollPaneMotivoCausa.setViewportBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"),
				"Descripcion Extendida", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		scrollPaneMotivoCausa.setBounds(102, 367, 234, 79);

		getContentPane().add(scrollPaneMotivoCausa);

		textAreaMotivoCausa = new JTextArea();
		textAreaMotivoCausa.setToolTipText("Informacion Adicional sobre Causa Seleccionada");
		textAreaMotivoCausa.setFont(new Font("Verdana", Font.PLAIN, 12));

		scrollPaneMotivoCausa.setViewportView(textAreaMotivoCausa);

		layeredPaneEstadoParo = new JLayeredPane();
		layeredPaneEstadoParo.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)), "Estado de Paro",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(51, 51, 51)));
		layeredPaneEstadoParo.setBounds(55, 72, 234, 53);
		getContentPane().add(layeredPaneEstadoParo);
		layeredPaneEstadoParo.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

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
		layeredPaneEstadoParo.add(rdbtnCompletado);

		rdbtnPendiente = new JRadioButton("Pendiente");
		rdbtnPendiente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				formattedTextFieldFechaFin.setText(null);
				formattedTextFieldFechaFin.setEnabled(false);
				btnFechaFin.setEnabled(false);
			}
		});
		buttonGroup_1.add(rdbtnPendiente);
		rdbtnPendiente.setFont(new Font("Verdana", Font.PLAIN, 12));
		layeredPaneEstadoParo.add(rdbtnPendiente);

		btnFechaInicio = new JButton("+");
		btnFechaInicio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				Date dateobj = new Date();
				formattedTextFieldFechaInicio.setText(df.format(dateobj));
			}
		});
		btnFechaInicio.setFont(new Font("Verdana", Font.PLAIN, 12));
		btnFechaInicio.setBounds(274, 8, 62, 25);
		getContentPane().add(btnFechaInicio);

		btnFechaFin = new JButton("+");
		btnFechaFin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				Date dateobj = new Date();
				formattedTextFieldFechaFin.setText(df.format(dateobj));
			}
		});
		btnFechaFin.setFont(new Font("Verdana", Font.PLAIN, 12));
		btnFechaFin.setBounds(274, 40, 62, 25);
		getContentPane().add(btnFechaFin);

		btnParosPendientes = new JButton("Paros Pendientes");
		btnParosPendientes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				mostrarPendientes();
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
		setBounds(65, 5, 1150, 590);
	}

	/**
	 * 
	 */
	protected void visualizarPendiente() {

		int fila = tblParos.getSelectedRow();
		int codigoParo = (int) tblParos.getValueAt(fila, 0);
		String subArea = String.valueOf(tblParos.getValueAt(fila, 3));

		String usuarioActual = Principal.usuarioActual.getText();
		String usuarioTabla = String.valueOf(tblParos.getValueAt(fila, 1));
		String tipoUsuario = verificarTipoUsuario(usuarioActual);

		// Verifica si el usuario autenticado es el mismo o tiene privilegios
		// para modificar
		// el paro pendiente seleccionado
		if (!(usuarioActual.equals(usuarioTabla) || tipoUsuario.equals("administrador"))) {
			JOptionPane.showMessageDialog(null, "No tiene privilegios para modificar este paro",
					"Privilegios de Usuario", JOptionPane.INFORMATION_MESSAGE);

			return;
		}

		try {
			ModificacionPendiente modificacion = new ModificacionPendiente(codigoParo, subArea, getDesktopPane());
			setVisible(false);
			modificacion.setVisible(true);
		} catch (SQLException sqle) {

			logger.error("Usuario: " + usuarioLog + " en PC: " + captura.obtenerNombrePC() + ". Exception: "
					+ sqle.toString());
			JOptionPane.showMessageDialog(null, sqle.getMessage(), sqle.getClass().toString(),
					JOptionPane.ERROR_MESSAGE);

		} catch (Exception e) {

			logger.error(
					"Usuario: " + usuarioLog + " en PC: " + captura.obtenerNombrePC() + ". Exception: " + e.toString());
			JOptionPane.showMessageDialog(null, e.getMessage(), e.getClass().toString(), JOptionPane.ERROR_MESSAGE);

		}
	}

	/**
	 * Actualiza el paro de Pendiente a Completado
	 */
	private void actualizarEstadoParo() {

		boolean resultado;
		String usuarioActual = Principal.usuarioActual.getText();
		Date pruebaFecha = null;

		try {

			int fila = tblParos.getSelectedRow();
			int idParo = Integer.parseInt(String.valueOf(tblParos.getValueAt(fila, 0)));

			String usuarioTabla = String.valueOf(tblParos.getValueAt(fila, 1));
			String fechaInicio = String.valueOf(tblParos.getValueAt(fila, 5));
			String fechaFin = formattedTextFieldFechaFin.getText();

			String tipoUsuario = verificarTipoUsuario(usuarioActual);

			// Verifica si el usuario autenticado tiene privilegios para
			// modificar el paro seleccionado
			if (!(usuarioActual.equals(usuarioTabla) || tipoUsuario.equals("administrador"))) {
				JOptionPane.showMessageDialog(null, "No tiene privilegios para modificar este paro",
						"Privilegios de Usuario", JOptionPane.INFORMATION_MESSAGE);

				return;
			}

			pruebaFecha = df.parse(fechaFin);
			if (!df.format(pruebaFecha).equals(fechaFin)) {
				JOptionPane.showMessageDialog(null, "La Fecha introducida es invalida", "Fecha de Fin",
						JOptionPane.INFORMATION_MESSAGE);

				return;
			}

			String solucion = escribirSolucion();

			if (solucion == null) {

				JOptionPane.showMessageDialog(null, "Debe escribir la solucion del paro", "Solucion de Paro",
						JOptionPane.WARNING_MESSAGE);

				return;
			}
			admParos = new AdministracionParos();
			resultado = admParos.actualizarParo(new Paro(idParo, fechaInicio, fechaFin, solucion));

			if (resultado == true) {
				Principal.lblStatusBar.setIcon(new ImageIcon(getClass().getResource("/iconos16x16/ok.png")));
				Principal.lblStatusBar.setText("Paro Imputado correctamente");
				limpiarCampos();
				actualizarTabla(estado[1]); // Muestra el listado de Paros
											// Pendientes

				// Guarda la accion en un archivo log
				usuarioLog = Principal.usuarioActual.getText();
				logger.info("Usuario: " + usuarioLog + " conectado en PC: " + captura.obtenerNombrePC()
						+ " imputo paro correctamente");

			} else {
				Principal.lblStatusBar.setIcon(new ImageIcon(getClass().getResource("/iconos16x16/warning-icon.png")));
				Principal.lblStatusBar.setText("No se pudo completar la operacion");
				return;
			}

			// Cuenta la cantidad de paros pendientes por completar
			pri.mostrarPendientes(admParos.contarPendientes());

		} catch (SQLException sqle) {
			logger.error("Usuario: " + usuarioLog + " en PC: " + captura.obtenerNombrePC() + ". Exception: "
					+ sqle.toString());
			JOptionPane.showMessageDialog(null, sqle.getMessage(), sqle.getClass().toString(),
					JOptionPane.ERROR_MESSAGE);
			return;

		} catch (ParseException pe) {
			logger.warn("Usuario: " + usuarioLog + " en PC: " + captura.obtenerNombrePC() + ""
					+ ". La Fecha introducida es invalida. Exception: " + pe.toString());
			JOptionPane.showMessageDialog(null, "La Fecha introducida es invalida", "Fecha invalida",
					JOptionPane.WARNING_MESSAGE);

		} catch (ArrayIndexOutOfBoundsException aiobe) {
			logger.warn("Usuario: " + usuarioLog + " en PC: " + captura.obtenerNombrePC() + ""
					+ ". Debe elegir un paro. Exception: " + aiobe.toString());
			JOptionPane.showMessageDialog(null, "Debe elegir un paro", "Seleccionar paro", JOptionPane.WARNING_MESSAGE);
			return;

		} catch (NumberFormatException nfe) {
			logger.error("Usuario: " + usuarioLog + " en PC: " + captura.obtenerNombrePC() + ". Exception: "
					+ nfe.toString());
			JOptionPane.showMessageDialog(null, nfe.getMessage(), nfe.getClass().toString(), JOptionPane.ERROR_MESSAGE);

		} catch (Exception e1) {
			logger.error(
					"Usuario: " + usuarioLog + " en PC: " + captura.obtenerNombrePC() + ". Exception: " + e1.toString());
			JOptionPane.showMessageDialog(null, e1.getMessage(), e1.getClass().toString(), JOptionPane.ERROR_MESSAGE);
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

	/**
	 * Muestra los paros Completados y Pendientes en un JTable
	 * 
	 * @param estado
	 *            - Tipo de paro que se desee visualizar
	 */
	private void actualizarTabla(String estado) {

		ResultSet rs = null;
		try {
			md = new ManipulacionDatos();

			rs = md.actualizarTabla(estado);
			tblParos.setModel(DbUtils.resultSetToTableModel(rs));
		} catch (Exception e) {

			logger.error(
					"Usuario: " + usuarioLog + " en PC: " + captura.obtenerNombrePC() + ". Exception: " + e.toString());
			JOptionPane.showMessageDialog(null, e.getMessage(), e.getClass().toString(), JOptionPane.ERROR_MESSAGE);
		} finally {
			md.cerrarConexiones();
		}
	}

	/**
	 * Metodo para poblar los comboBox desde los campos de la base de datos
	 */
	private void rellenarcomboBox(String campo) {

		ResultSet rs = null;
		md = new ManipulacionDatos();

		try {
			// Rellena comboBox Area
			if (campo.equals("area")) {
				rs = md.rellenarCombo("area", null);

				while (rs.next()) {
					cbArea.addItem(rs.getString("nombre_area"));
				}
				cbArea.setSelectedIndex(-1);
			}

			// Rellena comboBox SubArea
			else if (campo.equals("subArea")) {
				// Busca el indice de area seleccionada
				String area = String.valueOf(cbArea.getSelectedItem());

				rs = md.rellenarCombo("subArea", area);
				while (rs.next()) {
					String subArea = rs.getString("nombre_sub_area");
					cbSubArea.addItem(subArea);
				}
				cbSubArea.setSelectedIndex(-1);

			} else if (campo.equals("equipo")) {
				// Busca el indice de subArea seleccionada
				String subArea = String.valueOf(cbSubArea.getSelectedItem());

				rs = md.rellenarCombo("equipo", subArea);
				while (rs.next()) {
					String cod_equipo = rs.getString("cod_equipo");
					cbEquipo.addItem(cod_equipo);
				}
				cbEquipo.setSelectedIndex(-1);
			}

			// Rellena comboBox Disciplina
			if (campo.equals("disciplina")) {
				rs = md.rellenarCombo("disciplina", null);

				while (rs.next()) {
					cbDisciplina.addItem(rs.getString("nombre_disciplina"));
				}

				cbDisciplina.setSelectedIndex(-1);
			}

			// Rellena comboBox Causa
			if (campo.equals("causa")) {
				String disciplina = String.valueOf(cbDisciplina.getSelectedItem());
				rs = md.rellenarCombo("causa", disciplina);

				while (rs.next()) {
					cbCausa.addItem(rs.getString("tipo_causa"));
				}

				cbCausa.setSelectedIndex(-1);
			}
		}

		catch (SQLException sqle) {
			logger.error("Usuario: " + usuarioLog + " en PC: " + captura.obtenerNombrePC() + ". Exception: "
					+ sqle.toString());
			JOptionPane.showMessageDialog(null, sqle.getMessage(), sqle.getClass().toString(),
					JOptionPane.ERROR_MESSAGE);
		}

		catch (Exception e) {
			logger.error(
					"Usuario: " + usuarioLog + " en PC: " + captura.obtenerNombrePC() + ". Exception: " + e.toString());
			JOptionPane.showMessageDialog(null, e.getMessage(), e.getClass().toString(), JOptionPane.ERROR_MESSAGE);
		} finally {
			// Cierra la conexion de la Base de Datos
			md.cerrarConexiones();
		}
	}

	/**
	 * Limpia los campos una vez se complete una imputacion exitosa
	 */
	private void limpiarCampos() {

		cbEquipo.setSelectedIndex(-1);
		cbSubArea.setSelectedIndex(-1);
		cbArea.setSelectedIndex(-1);
		cbDisciplina.setSelectedIndex(-1);
		cbCausa.setSelectedIndex(-1);

		rdbtnInactivo.setEnabled(true);
		rdbtnPendiente.setEnabled(true);

		formattedTextFieldFechaInicio.setText("");
		formattedTextFieldFechaFin.setText("");
		textAreaMotivoCausa.setText("");

		cbEquipo.setEnabled(false);
		cbSubArea.setEnabled(false);
		cbCausa.setEnabled(false);
	}

	/**
	 * Guarda la informacion suministrada de un paro
	 */
	private void imputarParo() {

		admParos = new AdministracionParos();
		boolean resultado = false;
		Date pruebaFecha = null;

		try {

			String fechaInicio = formattedTextFieldFechaInicio.getText();
			String fechaFin = formattedTextFieldFechaFin.getText();
			String equipo = cbEquipo.getSelectedItem().toString();
			String estadoParo = obtenerEstadoParo();
			String estadoEquipo = obtenerEstadoEquipo();
			String disciplina = cbDisciplina.getSelectedItem().toString();
			String tipoCausa = cbCausa.getSelectedItem().toString();
			String usuario = Principal.usuarioActual.getText();
			String otraCausa = textAreaMotivoCausa.getText();

			// Valida si las fechas introducidas tienen un formato correcto
			if (rdbtnCompletado.isSelected()) {

				pruebaFecha = df.parse(fechaFin);
				if (!df.format(pruebaFecha).equals(fechaFin)) {
					JOptionPane.showMessageDialog(null, "La Fecha introducida es invalida", "Fecha de Fin",
							JOptionPane.INFORMATION_MESSAGE);

					return;
				}
			}

			// Valida si se selecciono el estado de paro
			if (estadoParo.equals(null)) {

				JOptionPane.showMessageDialog(null, "Debe seleccionar el estado de Paro", "Seleccionar Paro",
						JOptionPane.ERROR_MESSAGE);

				return;
			}

			// Valida si se escribio la Descripcion Adicional de una Causa
			else if (otraCausa.equals(null) || otraCausa.trim().length() == 0) {

				JOptionPane.showMessageDialog(null, "Debe escribir Descripcional Adicional de Paro",
						"Descripcion Adicional", JOptionPane.ERROR_MESSAGE);
				return;

			}

			else {

				String solucion = "";

				// Verifica si el Paro es Compleado
				if (estadoParo.equals("Completado")) {

					solucion = escribirSolucion();

					// Verifica si escribio una solucion de Paro Completado
					if (solucion == null) {

						JOptionPane.showMessageDialog(null, "Debe escribir la solucion del paro", "Solucion de Paro",
								JOptionPane.WARNING_MESSAGE);

						return;
					}
					// Valida si las fechas introducidas tienen un formato
					// correcto
					pruebaFecha = df.parse(fechaInicio);
					pruebaFecha = df.parse(fechaFin);

					// En caso de que el paro este Completado
					resultado = admParos.imputarParo(new Imputacion(equipo, usuario, disciplina, tipoCausa, otraCausa,
							fechaInicio, fechaFin, estadoParo, estadoEquipo, solucion));
				} else {
					// Valida si las fechas introducidas tienen un formato
					// correcto
					pruebaFecha = df.parse(fechaInicio);

					// En caso de que el paro este Pendiente
					resultado = admParos.imputarParo(new Imputacion(equipo, usuario, disciplina, tipoCausa, otraCausa,
							fechaInicio, null, estadoParo, estadoEquipo, null));
				}

				// Si el resultado es satisfactorio, notifica la imputacion
				// exitosa o no por el contrario
				if (resultado == true) {
					Principal.lblStatusBar.setIcon(new ImageIcon(getClass().getResource("/iconos16x16/ok.png")));
					Principal.lblStatusBar.setText("Paro Imputado correctamente");
					limpiarCampos();
					actualizarTabla(estado[0]);

					// Guarda la accion en un archivo log
					usuarioLog = Principal.usuarioActual.getText();
					logger.info("Usuario: " + usuarioLog + " conectado en PC: " + captura.obtenerNombrePC()
							+ " imputo paro correctamente");

				} else {
					Principal.lblStatusBar
							.setIcon(new ImageIcon(getClass().getResource("/iconos16x16/warning-icon.png")));
					Principal.lblStatusBar.setText("No se pudo completar la operacion");
				}
				pri.mostrarPendientes(admParos.contarPendientes());
			}
		} catch (NumberFormatException nfe) {
			logger.error("Usuario: " + usuarioLog + " en PC: " + captura.obtenerNombrePC() + ". Exception: "
					+ nfe.toString());
			JOptionPane.showMessageDialog(null, nfe.getMessage(), nfe.getClass().toString(), JOptionPane.ERROR_MESSAGE);
		} catch (ParseException pe) {
			logger.error("Usuario: " + usuarioLog + " en PC: " + captura.obtenerNombrePC() + ""
					+ ". Formato de fecha incorrecto. Exception: " + pe.toString());
			JOptionPane.showMessageDialog(null, "Formato de fecha incorrecto", "Fecha invalida",
					JOptionPane.ERROR_MESSAGE);
		} catch (NullPointerException npe) {
			logger.warn("Usuario: " + usuarioLog + " en PC: " + captura.obtenerNombrePC() + ""
					+ ". Debe rellenar todos los campos. Exception: " + npe.toString());
			JOptionPane.showMessageDialog(null, "Debe rellenar todos los campos", npe.getClass().toString(),
					JOptionPane.ERROR_MESSAGE);
		} catch (Exception ex) {
			logger.error(
					"Usuario: " + usuarioLog + " en PC: " + captura.obtenerNombrePC() + ". Exception: " + ex.toString());
			JOptionPane.showMessageDialog(null, ex.getMessage(), ex.getClass().toString(), JOptionPane.ERROR_MESSAGE);
		}
	}

	/**
	 * Muestra el panel para introducir la solucion de paros completados
	 * 
	 * @return la solucion introducida por el usuario
	 */
	private String escribirSolucion() {

		// Coloca los botones del JOptionPane en español
		UIManager.put("OptionPane.okButtonText", "Aceptar");
		UIManager.put("OptionPane.cancelButtonText", "Cancelar");

		String solucion = JOptionPane.showInputDialog(null, "¿Que se tuvo que hacer para solucionar este paro?",
				"Solucion de paro", JOptionPane.INFORMATION_MESSAGE);

		if (solucion.trim() == null || solucion.trim().equals(null) || solucion.trim() == ""
				|| solucion.trim().equals("")) {

			return null;
		}
		return solucion;
	}

	/**
	 * Retorna el Estado de los equipos
	 * 
	 * @return el estado del equipo
	 */
	private String obtenerEstadoEquipo() {
		if (rdbtnActivo.isSelected()) {
			return rdbtnActivo.getText();
		}
		return rdbtnInactivo.getText();
	}

	/**
	 * Retorna el Estado de Paro elegido
	 * 
	 * @return el estado del Paro
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
			formattedTextFieldFechaInicio.setEnabled(true);
			formattedTextFieldFechaFin.setEnabled(true);
			btnFechaFin.setEnabled(true);
		}
	}

	// Metodo para mostrar los Paros Completados en el JTable
	private void btnParosCompletadosActionPerformed(ActionEvent e) {

		paroSeleccionado = "Completado";
		formattedTextFieldFechaInicio.setEnabled(true);
		btnFechaInicio.setEnabled(true);
		rdbtnPendiente.setEnabled(true);
		cbArea.setEnabled(true);
		btnIniciarParo.setEnabled(true);
		cbDisciplina.setEnabled(true);

		cbCausa.setEnabled(true);
		scrollPaneMotivoCausa.setEnabled(true);
		textAreaMotivoCausa.setEnabled(true);
		rdbtnInactivo.setEnabled(true);
		formattedTextFieldFechaFin.setEnabled(true);
		btnFechaFin.setEnabled(true);
		btnDetenerParo.setEnabled(false);

		if (SwingUtilities.isEventDispatchThread()) {

			SwingUtilities.invokeLater(new Runnable() {

				@Override
				public void run() {
					// Muestra el listado de Paros Completados
					actualizarTabla(estado[0]);
				}
			});
		}
	}

	/**
	 * Muestra en un JTable el listado de paros pendientes
	 */
	public void mostrarPendientes() {

		panelParos.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Paros Pendientes",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));

		paroSeleccionado = "Pendiente";
		formattedTextFieldFechaInicio.setEnabled(false);
		btnFechaInicio.setEnabled(false);
		rdbtnPendiente.setEnabled(false);
		cbArea.setEnabled(false);
		cbSubArea.setEnabled(false);
		cbEquipo.setEnabled(false);
		rdbtnInactivo.setEnabled(false);
		cbDisciplina.setEnabled(false);

		cbCausa.setEnabled(false);
		scrollPaneMotivoCausa.setEnabled(false);
		textAreaMotivoCausa.setEnabled(false);
		btnIniciarParo.setEnabled(false);
		rdbtnCompletado.setSelected(true);
		btnDetenerParo.setEnabled(true);
		formattedTextFieldFechaFin.setEnabled(true);
		btnFechaFin.setEnabled(true);
		rdbtnActivo.setSelected(true);

		if (SwingUtilities.isEventDispatchThread()) {

			SwingUtilities.invokeLater(new Runnable() {

				@Override
				public void run() {
					// Muestra el listado de Paros Pendientes
					actualizarTabla(estado[1]);
				}
			});
		}
	}

	/**
	 * Valida el tipo de Usuario(Administrador, Consultor o Operador) del
	 * Usuario Actual Conectado al Sistema
	 * 
	 * @param usuarioSeleccionado
	 *            - Usuario actual
	 * @return - Tipo de Usuario conectado al sistema
	 */
	private String verificarTipoUsuario(String usuarioSeleccionado) {

		String tipoUsuario = "";
		ResultSet rs = null;

		try {
			rs = admUsuario.mostrarUsuario(usuarioSeleccionado);

			if (rs.next()) {

				tipoUsuario = rs.getString("tipo_usuario");
			}

		} catch (SQLException sqle) {
			logger.error("Usuario: " + usuarioLog + " en PC: " + captura.obtenerNombrePC() + ". Exception: "
					+ sqle.toString());
			JOptionPane.showMessageDialog(null, sqle.getMessage(), sqle.getClass().toString(),
					JOptionPane.ERROR_MESSAGE);
		} catch (Exception e) {
			logger.error(
					"Usuario: " + usuarioLog + " en PC: " + captura.obtenerNombrePC() + ". Exception: " + e.toString());
			JOptionPane.showMessageDialog(null, e.getMessage(), e.getClass().toString(), JOptionPane.ERROR_MESSAGE);
		}
		return tipoUsuario;
	}

	/**
	 * @param nuevoValor
	 *            porcentaje para actua
	 */
	public void actualizarBarraEstado(int nuevoValor) {
		Principal.pbar.setValue(nuevoValor);
	}

	/**
	 * Muestra el JTable de la busqueda de equipos por codigo
	 */
	public void mostrarAyudaEquipo() {
		new NombreEquipo();
	}
}