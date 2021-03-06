
package com.cementopanam.jrivera.vista.internalFrames;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import javax.swing.SwingWorker;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

import org.apache.log4j.Logger;

import com.cementopanam.jrivera.controlador.ManipulacionDatos;
import com.cementopanam.jrivera.controlador.paros.AdministracionParos;
import com.cementopanam.jrivera.controlador.paros.Paro;
import com.cementopanam.jrivera.controlador.usuario.CapturaUsuario;
import com.cementopanam.jrivera.vista.ModificacionParo;
import com.cementopanam.jrivera.vista.Principal;
import com.cementopanam.jrivera.vista.helper.tablaModelo.TablaModeloEquipoSolucion;
import com.cementopanam.jrivera.vista.helper.tablaModelo.TablaModeloParo;
import com.toedter.calendar.JDateChooser;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

public class Reportes extends JInternalFrame implements ItemListener {

	/**
	 * 
	 */
	private static final Logger logger = Logger.getLogger(Reportes.class);
	private static final long serialVersionUID = 3425343178839382975L;

	private String formatoFecha = "yyyy-MM-dd";

	private JDateChooser busquedaDCFechaDesde;
	private JDateChooser busquedaDCFechaHasta;
	private JDateChooser informeDCFechaDesde;
	private JDateChooser informeDCFechaHasta;

	private JComboBox<String> cbTipoReporte;

	private SimpleDateFormat df;
	private JTable tablaResultado;

	private TablaModeloParo modeloParo = new TablaModeloParo();
	private TablaModeloEquipoSolucion equipoSolucion = new TablaModeloEquipoSolucion();

	private JButton btnBuscar;
	private JButton btnInformeGenerarReporte;
	public JTabbedPane tabbedPane;
	private JCheckBox chckbxReporteFiltrarPor;
	private JList<String> listReporteSubArea;
	private JComboBox<String> cbReporteArea;
	private JLabel lblArea;
	private JLabel lblSubarea;
	private JTextField txtSoluciones;
	private JCheckBox chckbxBuscarSoluciones;
	private JLabel lblCodigoDeEquipo;

	private AdministracionParos admParo;
	// Establece la cantidad maxima de subAreas que pueden ser seleccionadas
	private static final int MAX_SUBAREA = 10;
	private JScrollPane scrollPaneSubArea;
	
	private CapturaUsuario captura = new CapturaUsuario();
	private String usuarioLog = Principal.usuarioActual.getText();

	/**
	 * Crea el frame.
	 */
	public Reportes() {
		initComponents();

		admParo = new AdministracionParos();
		try (ResultSet rs = admParo.rellenarCombo("area", null);) {
			// Rellena combo Causa
			while (rs.next()) {

				cbReporteArea.addItem(rs.getString("nombre_area"));
			}
		} catch (SQLException e) {
			logger.error(e.toString(), e);
		}

		cbReporteArea.setSelectedIndex(-1);
		// Muestra las subAreas del Area seleccionada
		cbReporteArea.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				poblarJListSubArea();
			}
		});

	}

	private void initComponents() {

		setFrameIcon(null);
		setTitle("Busqueda y Reportes de Paro");
		setDefaultCloseOperation(HIDE_ON_CLOSE);
		setIconifiable(true);
		setClosable(true);
		getContentPane().setFont(new Font("Verdana", Font.PLAIN, 12));
		getContentPane().setLayout(new BorderLayout(0, 0));

		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setFont(new Font("Verdana", Font.PLAIN, 12));
		getContentPane().add(tabbedPane, BorderLayout.CENTER);

		JPanel panelBusqueda = new JPanel();
		tabbedPane.addTab("Busqueda", null, panelBusqueda, null);

		JPanel panelInformes = new JPanel();
		tabbedPane.addTab("Informes", null, panelInformes, null);

		btnInformeGenerarReporte = new JButton("Generar Reporte");
		btnInformeGenerarReporte.setIcon(new ImageIcon(Reportes.class.getResource("/iconos32x32/report32x32.png")));
		btnInformeGenerarReporte.setFont(new Font("Verdana", Font.PLAIN, 12));
		btnInformeGenerarReporte.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				generarReporte();

			}
		});

		JPanel panelInformeRangoFecha = new JPanel();
		panelInformeRangoFecha.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229), 2, true),
				"Opciones de Reporte", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(51, 51, 51)));
		panelInformeRangoFecha.setLayout(null);
		GroupLayout gl_panelInformes = new GroupLayout(panelInformes);
		gl_panelInformes
				.setHorizontalGroup(gl_panelInformes.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panelInformes.createSequentialGroup().addContainerGap()
								.addGroup(gl_panelInformes.createParallelGroup(Alignment.LEADING)
										.addComponent(panelInformeRangoFecha, GroupLayout.DEFAULT_SIZE, 809,
												Short.MAX_VALUE)
										.addComponent(btnInformeGenerarReporte))
								.addContainerGap()));
		gl_panelInformes.setVerticalGroup(gl_panelInformes.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panelInformes.createSequentialGroup().addContainerGap()
						.addComponent(panelInformeRangoFecha, GroupLayout.PREFERRED_SIZE, 332,
								GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED, 208, Short.MAX_VALUE)
						.addComponent(btnInformeGenerarReporte).addContainerGap()));

		JLabel lblTipoDeReporte = new JLabel("Tipo de Reporte");
		lblTipoDeReporte.setBounds(20, 34, 104, 16);
		panelInformeRangoFecha.add(lblTipoDeReporte);
		lblTipoDeReporte.setFont(new Font("Verdana", Font.BOLD, 12));

		cbTipoReporte = new JComboBox<String>();
		cbTipoReporte.setBounds(187, 28, 154, 29);
		panelInformeRangoFecha.add(cbTipoReporte);
		cbTipoReporte.setModel(new DefaultComboBoxModel<String>(
				new String[] { "Rango de Fecha", "Rango de Fecha(excel)", "Frecuentes", "Duracion" }));
		cbTipoReporte.setSelectedIndex(-1);
		cbTipoReporte.setFont(new Font("Verdana", Font.PLAIN, 12));

		JPanel panelReportesRangoFecha = new JPanel();
		panelReportesRangoFecha.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0), 1, true),
				"Rango de Fecha", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panelReportesRangoFecha.setBounds(20, 84, 347, 111);
		panelInformeRangoFecha.add(panelReportesRangoFecha);
		panelReportesRangoFecha.setLayout(null);

		JLabel lblFechaDeInicio = new JLabel("Fecha de Inicio");
		lblFechaDeInicio.setBounds(10, 30, 105, 15);
		panelReportesRangoFecha.add(lblFechaDeInicio);
		lblFechaDeInicio.setFont(new Font("Verdana", Font.BOLD, 12));

		JLabel lblFechaFin = new JLabel("Fecha Fin");
		lblFechaFin.setBounds(10, 61, 105, 15);
		panelReportesRangoFecha.add(lblFechaFin);
		lblFechaFin.setFont(new Font("Verdana", Font.BOLD, 12));

		informeDCFechaDesde = new JDateChooser();
		informeDCFechaDesde.setBounds(165, 21, 155, 28);
		panelReportesRangoFecha.add(informeDCFechaDesde);
		informeDCFechaDesde.getCalendarButton().setFont(new Font("Verdana", Font.PLAIN, 12));

		informeDCFechaHasta = new JDateChooser();
		informeDCFechaHasta.setBounds(165, 61, 155, 28);
		panelReportesRangoFecha.add(informeDCFechaHasta);
		informeDCFechaHasta.getCalendarButton().setFont(new Font("Verdana", Font.PLAIN, 12));

		JSeparator separator = new JSeparator();
		separator.setOrientation(SwingConstants.VERTICAL);
		separator.setBounds(450, 28, 12, 282);
		panelInformeRangoFecha.add(separator);

		chckbxReporteFiltrarPor = new JCheckBox("Filtrar:");
		chckbxReporteFiltrarPor.setMnemonic('F');
		chckbxReporteFiltrarPor.setFont(new Font("Verdana", Font.PLAIN, 12));
		chckbxReporteFiltrarPor.setBounds(508, 28, 154, 23);
		chckbxReporteFiltrarPor.setSelected(false);
		chckbxReporteFiltrarPor.addItemListener(this);
		panelInformeRangoFecha.add(chckbxReporteFiltrarPor);

		cbReporteArea = new JComboBox<String>();
		cbReporteArea.setEnabled(false);
		cbReporteArea.setSelectedIndex(-1);
		cbReporteArea.setFont(new Font("Verdana", Font.PLAIN, 12));
		cbReporteArea.setBounds(508, 99, 408, 29);
		panelInformeRangoFecha.add(cbReporteArea);

		lblArea = new JLabel("Area");
		lblArea.setEnabled(false);
		lblArea.setFont(new Font("Verdana", Font.PLAIN, 12));
		lblArea.setBounds(508, 74, 408, 14);
		panelInformeRangoFecha.add(lblArea);

		scrollPaneSubArea = new JScrollPane();
		scrollPaneSubArea.setEnabled(false);
		scrollPaneSubArea.setBounds(508, 182, 406, 128);
		panelInformeRangoFecha.add(scrollPaneSubArea);

		listReporteSubArea = new JList<String>();
		scrollPaneSubArea.setViewportView(listReporteSubArea);
		listReporteSubArea.setEnabled(false);
		listReporteSubArea.setFont(new Font("Verdana", Font.PLAIN, 12));

		lblSubarea = new JLabel("SubArea");
		lblSubarea.setEnabled(false);
		lblSubarea.setFont(new Font("Verdana", Font.PLAIN, 12));
		lblSubarea.setBounds(508, 155, 136, 14);
		panelInformeRangoFecha.add(lblSubarea);
		panelInformes.setLayout(gl_panelInformes);
		panelBusqueda.setLayout(null);

		JPanel panel_Resultado = new JPanel();
		panel_Resultado.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Resultado de Busqueda",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_Resultado.setBounds(10, 155, 948, 306);
		panelBusqueda.add(panel_Resultado);
		panel_Resultado.setLayout(new BorderLayout(0, 0));

		JScrollPane scrollPaneResultado = new JScrollPane();
		panel_Resultado.add(scrollPaneResultado, BorderLayout.CENTER);

		tablaResultado = new JTable() {

			/**
			 * 
			 */
			private static final long serialVersionUID = -9206177619230112589L;

			@Override
			public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {

				// Ajusta la columna de la tabla a un tamano adecuado
				Component component = super.prepareRenderer(renderer, row, column);
				int rendererWidth = component.getPreferredSize().width;
				TableColumn tableColumn = getColumnModel().getColumn(column);
				tableColumn.setPreferredWidth(
						Math.max(rendererWidth + getIntercellSpacing().width, tableColumn.getPreferredWidth()));
				return component;
			}
		};
		tablaResultado.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {

				if (e.getKeyCode() == KeyEvent.VK_ENTER && (!(chckbxBuscarSoluciones.isSelected()))) {

					modificarParo();
				}
			}

		});
		tablaResultado.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				if (e.getClickCount() == 2 && !e.isConsumed() && (!(chckbxBuscarSoluciones.isSelected()))) {

					modificarParo();

				}
			}
		});
		tablaResultado.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		// Sirve para no permitir que el usuario reordene las columnas
		tablaResultado.getTableHeader().setReorderingAllowed(false);
		tablaResultado.setFont(new Font("Verdana", Font.PLAIN, 12));

		scrollPaneResultado.setViewportView(tablaResultado);
		tablaResultado.setModel(modeloParo);

		btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (chckbxBuscarSoluciones.isSelected()) {
					mostrarSolucion();
				}

				else
					buscarParo();

			}
		});
		btnBuscar.setFont(new Font("Verdana", Font.BOLD, 12));
		btnBuscar.setBounds(10, 117, 89, 23);
		panelBusqueda.add(btnBuscar);

		JPanel panelRangoFecha = new JPanel();
		panelRangoFecha.setBorder(
				new TitledBorder(null, "Rango de Fecha", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelRangoFecha.setBounds(10, 11, 382, 95);
		panelBusqueda.add(panelRangoFecha);
		panelRangoFecha.setLayout(null);

		JLabel labelDesde = new JLabel("Desde");
		labelDesde.setFont(new Font("Verdana", Font.PLAIN, 12));
		labelDesde.setBounds(10, 23, 65, 25);
		panelRangoFecha.add(labelDesde);

		JLabel labelHasta = new JLabel("Hasta");
		labelHasta.setFont(new Font("Verdana", Font.PLAIN, 12));
		labelHasta.setBounds(10, 59, 65, 25);
		panelRangoFecha.add(labelHasta);

		busquedaDCFechaHasta = new JDateChooser();
		busquedaDCFechaHasta.getCalendarButton().setFont(new Font("Verdana", Font.PLAIN, 12));
		busquedaDCFechaHasta.setBounds(85, 59, 159, 25);
		panelRangoFecha.add(busquedaDCFechaHasta);

		busquedaDCFechaDesde = new JDateChooser();
		busquedaDCFechaDesde.getCalendarButton().setFont(new Font("Verdana", Font.PLAIN, 12));
		busquedaDCFechaDesde.setBounds(85, 23, 159, 25);
		panelRangoFecha.add(busquedaDCFechaDesde);

		JSeparator separador = new JSeparator();
		separador.setOrientation(SwingConstants.VERTICAL);
		separador.setBounds(476, 11, 9, 133);
		panelBusqueda.add(separador);

		chckbxBuscarSoluciones = new JCheckBox("Buscar Soluciones");
		chckbxBuscarSoluciones.setFont(new Font("Verdana", Font.PLAIN, 12));
		chckbxBuscarSoluciones.setBounds(546, 20, 153, 23);
		chckbxBuscarSoluciones.addItemListener(this);
		panelBusqueda.add(chckbxBuscarSoluciones);

		lblCodigoDeEquipo = new JLabel("Codigo de Equipo");
		lblCodigoDeEquipo.setEnabled(false);
		lblCodigoDeEquipo.setFont(new Font("Verdana", Font.PLAIN, 12));
		lblCodigoDeEquipo.setBounds(550, 67, 121, 23);
		panelBusqueda.add(lblCodigoDeEquipo);

		txtSoluciones = new JTextField();
		txtSoluciones.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {

				if (e.getKeyCode() == KeyEvent.VK_ENTER) {

					mostrarSolucion();
				}
			}
		});
		txtSoluciones.setToolTipText("Escriba el codigo de equipo para ver sus Soluciones");
		txtSoluciones.setEnabled(false);
		txtSoluciones.setFont(new Font("Verdana", Font.PLAIN, 12));
		txtSoluciones.setBounds(678, 65, 270, 29);
		panelBusqueda.add(txtSoluciones);
		txtSoluciones.setColumns(10);
		setBounds(60, 2, 979, 532);

		// Actualiza el JTable en el panel de Busqueda
		modeloParo.fireTableDataChanged();
	}

	private void mostrarSolucion() {

		try {

			equipoSolucion.buscarSolucion(txtSoluciones.getText());
			tablaResultado.setModel(equipoSolucion);
		}

		catch (NullPointerException npe) {
			tablaResultado.setModel(equipoSolucion);
		} catch (Exception e) {
			logger.error(e.toString(), e);
			JOptionPane.showMessageDialog(null, e.getMessage(), e.getClass().toString(), JOptionPane.ERROR_MESSAGE);
		}
	}

	private void buscarParo() {

		try {
			df = new SimpleDateFormat(formatoFecha);

			String fInicio = df.format(busquedaDCFechaDesde.getDate().getTime());
			String fFin = df.format(busquedaDCFechaHasta.getDate().getTime());

			Paro paro = new Paro();
			paro.setTiempoInicio(fInicio);
			paro.setTiempoFin(fFin);

			if (paro.getTiempoInicio().equals(null) || paro.getTiempoFin().equals(null)) {
				modeloParo.buscarParo(new Paro(), "");
				tablaResultado.setModel(modeloParo);
			} else {
				modeloParo.buscarParo(paro, "fecha");
				tablaResultado.setModel(modeloParo);
			}
		}

		catch (NullPointerException npe) {
			modeloParo.buscarParo(new Paro(), "");
		} catch (Exception e) {
			logger.error(e.toString(), e);
			JOptionPane.showMessageDialog(null, e.getMessage(), e.getClass().toString(), JOptionPane.ERROR_MESSAGE);
		}
	}

	private void generarReporte() {

		if (cbTipoReporte.getSelectedIndex() < 0) {
			JOptionPane.showMessageDialog(null, "Debe elegir un tipo de Reporte", "Tipo de Reporte",
					JOptionPane.INFORMATION_MESSAGE);
			return;
		}

		SwingWorker<Void, String> worker = new SwingWorker<Void, String>() {

			@Override
			protected Void doInBackground() throws Exception {

				publish();
				df = new SimpleDateFormat(formatoFecha);

				Map<String, Object> parametro = new HashMap<String, Object>();
				String archivo = "";
				String titulo = "";

				try (InputStream in = getClass().getResourceAsStream("/properties/paros.properties");) {
					// Carga las propiedades del archivo
					Properties pros = new Properties();
					pros.load(in);

					switch (cbTipoReporte.getSelectedIndex()) {
					case 0:
						// Paros por Rango de fecha
						archivo = pros.getProperty("fecha");
						titulo = "Fecha";
						break;

					case 1:
						// Paros por Rango de fecha optimizado para excel
						archivo = pros.getProperty("excel");
						titulo = "Fecha";
						// Sirve para que el reporte sea de una pagina
						parametro.put(JRParameter.IS_IGNORE_PAGINATION, true);
						break;
					case 2:
						// Paros por Frecuencia
						archivo = pros.getProperty("frecuencia");
						titulo = "Frecuencia";
						break;
					case 3:
						// Paros por duracion
						archivo = pros.getProperty("duracion");
						titulo = "Duracion";
						break;
					}

					try {

						String fechaInicio = df.format(informeDCFechaDesde.getDate().getTime());
						String fechaFin = df.format(informeDCFechaHasta.getDate().getTime());

						// Conecta a la Base de Datos para la generacion del
						// reporte
						ManipulacionDatos md = new ManipulacionDatos();
						Connection con = md.obtenerConexion();

						parametro.put("fechaInicio", fechaInicio);
						parametro.put("fechaFin", fechaFin);

						// Valida si el filtro esta activo
						if (chckbxReporteFiltrarPor.isSelected()) {
							parametro.put("f_area", String.valueOf(cbReporteArea.getSelectedItem()));

							// Obtiene el indice de todos los elementos
							// seleccionados
							int[] indicesSeleccionados = listReporteSubArea.getSelectedIndices();

							if (indicesSeleccionados.length > MAX_SUBAREA) {
								JOptionPane.showMessageDialog(null, "Solo puede elegir maximo 10 subAreas",
										"Limite SubArea", JOptionPane.INFORMATION_MESSAGE);
								return null;
							}

							int i = 1;
							for (; i <= indicesSeleccionados.length; i++) {
								String seleccionSubArea = String.valueOf(
										listReporteSubArea.getModel().getElementAt(indicesSeleccionados[i - 1]));

								parametro.put("f_subArea" + i, seleccionSubArea);
							}

							// asigna null para los restantes
							for (; i <= MAX_SUBAREA; i++) {
								parametro.put("f_subArea" + i, null);

							}
						}

						JasperReport jr = (JasperReport) JRLoader.loadObjectFromFile(archivo);
						JasperPrint jp = JasperFillManager.fillReport(jr, parametro, con);

						JasperViewer jv = new JasperViewer(jp, false);
						jv.setVisible(true);
						jv.setTitle("Reporte de Paros por " + titulo);
						
					} catch (JRException ex) {
						logger.error(ex.toString(), ex);
						JOptionPane.showMessageDialog(null, ex.getMessage(), ex.getClass().toString(),
								JOptionPane.ERROR_MESSAGE);
					}
					
				} catch (NullPointerException npe) {
					JOptionPane.showMessageDialog(null, "Debe elegir un rango de Fecha", "Rango de Fecha",
							JOptionPane.INFORMATION_MESSAGE);
					
				} catch (Exception e) {
					logger.error(e.toString(), e);
					JOptionPane.showMessageDialog(null, e.getMessage(), e.getClass().toString(),
							JOptionPane.ERROR_MESSAGE);
				}
				return null;

			}

			@Override
			protected void process(List<String> chunks) {
				Principal.lblStatusBar.setIcon(new ImageIcon(getClass().getResource("/iconos16x16/loading.gif")));
				Principal.lblStatusBar.setText("Generando Reporte. Espere...");
				btnInformeGenerarReporte.setVisible(false);
				btnInformeGenerarReporte.setEnabled(false);
			}

			// Can safely update the GUI from this method.
			@Override
			protected void done() {

				Principal.lblStatusBar.setIcon(new ImageIcon(getClass().getResource("/iconos16x16/ok.png")));
				Principal.lblStatusBar.setText("Listo");
				btnInformeGenerarReporte.setVisible(true);
				btnInformeGenerarReporte.setEnabled(true);
				
				// Guarda la accion en un archivo log
				usuarioLog = Principal.usuarioActual.getText();
				
				logger.info("Usuario: " + usuarioLog + " conectado en PC: " + captura.obtenerNombrePC()
						+ " genero un reporte");
			}
		};

		worker.execute();
	}

	/**
	 * Abre la interfaz para modificar los valores de un paro
	 */
	private void modificarParo() {

		int fila = tablaResultado.getSelectedRow();
		int columna = tablaResultado.getSelectedColumn();

		int codigoParo = 0;
		String tiempoInicio = "";
		String solucion = "";
		String descripcionAdicional = "";
		String tiempoFin = "";
		String causa = "";
		String disciplina = "";

		for (int i = 0; i < columna; i++) {

			codigoParo = (int) tablaResultado.getValueAt(fila, 0);
			disciplina = String.valueOf(tablaResultado.getValueAt(fila, 5));
			causa = String.valueOf(tablaResultado.getValueAt(fila, 6));
			descripcionAdicional = String.valueOf(tablaResultado.getValueAt(fila, 7));

			if (descripcionAdicional.equals(null) || descripcionAdicional.equals("")) {
				descripcionAdicional = "";
			}
			tiempoInicio = String.valueOf(tablaResultado.getValueAt(fila, 8));
			tiempoFin = String.valueOf(tablaResultado.getValueAt(fila, 9));
			solucion = String.valueOf(tablaResultado.getValueAt(fila, 10));
		}

		ModificacionParo modificacion = new ModificacionParo(
				new Paro(codigoParo, tiempoInicio, tiempoFin, solucion, causa, descripcionAdicional, disciplina),
				new AdministracionParos(), getDesktopPane());

		setVisible(false);
		modificacion.setVisible(true);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * java.awt.event.ItemListener#itemStateChanged(java.awt.event.ItemEvent)
	 */
	public void itemStateChanged(ItemEvent e) {

		Object source = e.getItemSelectable();

		if (source == chckbxReporteFiltrarPor) {

			lblArea.setEnabled(true);
			lblSubarea.setEnabled(true);
			listReporteSubArea.setEnabled(true);
			cbReporteArea.setEnabled(true);
			scrollPaneSubArea.setEnabled(true);

		} else if (source == chckbxBuscarSoluciones) {

			lblCodigoDeEquipo.setEnabled(true);
			txtSoluciones.setEnabled(true);

			busquedaDCFechaDesde.setEnabled(false);
			busquedaDCFechaHasta.setEnabled(false);
		}

		if (e.getStateChange() == ItemEvent.DESELECTED) {

			lblArea.setEnabled(false);
			lblSubarea.setEnabled(false);
			listReporteSubArea.setEnabled(false);
			cbReporteArea.setEnabled(false);
			scrollPaneSubArea.setEnabled(false);

			lblCodigoDeEquipo.setEnabled(false);
			txtSoluciones.setEnabled(false);

			busquedaDCFechaDesde.setEnabled(true);
			busquedaDCFechaHasta.setEnabled(true);
		}
	}

	/**
	 * 
	 */
	private void poblarJListSubArea() {

		DefaultListModel<String> modelo = new DefaultListModel<String>();
		String area = String.valueOf(cbReporteArea.getSelectedItem());

		try {
			// rs.close();
			ResultSet rs = admParo.rellenarCombo("subArea", area);

			while (rs.next()) {

				modelo.addElement(rs.getString("nombre_sub_area"));
			}
		} catch (SQLException sqle) {
			logger.error(sqle.toString(), sqle);
		}

		listReporteSubArea.setModel(modelo);
	}
}