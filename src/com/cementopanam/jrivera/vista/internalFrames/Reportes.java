
package com.cementopanam.jrivera.vista.internalFrames;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.InputStream;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.DefaultComboBoxModel;
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
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

import com.cementopanam.jrivera.controlador.ManipulacionDatos;
import com.cementopanam.jrivera.controlador.paros.AdministracionParos;
import com.cementopanam.jrivera.controlador.paros.Paro;
import com.cementopanam.jrivera.vista.ModificacionParo;
import com.cementopanam.jrivera.vista.helper.tablaModelo.TablaModeloEquiposSolucion;
import com.cementopanam.jrivera.vista.helper.tablaModelo.TablaModeloParo;
import com.toedter.calendar.JDateChooser;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;
import javax.swing.JTextField;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Reportes extends JInternalFrame implements ItemListener {

	/**
	 * 
	 */
	private static final Logger log = Logger.getLogger(Reportes.class.getName());
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
	private TablaModeloEquiposSolucion equipoSolucion = new TablaModeloEquiposSolucion();
	
	private JButton btnBuscar;
	public JTabbedPane tabbedPane;
	private JCheckBox chckbxFiltrarPor;
	private JList<String> listSubArea;
	private JComboBox<String> cBReporteArea;
	private JLabel lblArea;
	private JLabel lblSubarea;
	private JScrollPane scrollPaneSubArea;
	private JTextField txtSoluciones;
	private JCheckBox chckbxBuscarSoluciones;
	private JLabel lblCodigoDeEquipo;

	/**
	 * Crea el frame.
	 */
	public Reportes() {
		initComponents();
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

		JButton btnInformeGenerarReporte = new JButton("Generar Reporte");
		btnInformeGenerarReporte.setIcon(new ImageIcon(Reportes.class.getResource("/iconos32x32/report32x32.png")));
		btnInformeGenerarReporte.setFont(new Font("Verdana", Font.PLAIN, 12));
		btnInformeGenerarReporte.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				generarReporte();

			}
		});

		JPanel panelInformeRangoFecha = new JPanel();
		panelInformeRangoFecha.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229), 2, true), "Opciones de Reporte", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(51, 51, 51)));
		panelInformeRangoFecha.setLayout(null);
		GroupLayout gl_panelInformes = new GroupLayout(panelInformes);
		gl_panelInformes.setHorizontalGroup(
			gl_panelInformes.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelInformes.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panelInformes.createParallelGroup(Alignment.LEADING)
						.addComponent(panelInformeRangoFecha, GroupLayout.DEFAULT_SIZE, 809, Short.MAX_VALUE)
						.addComponent(btnInformeGenerarReporte))
					.addContainerGap())
		);
		gl_panelInformes.setVerticalGroup(
			gl_panelInformes.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panelInformes.createSequentialGroup()
					.addContainerGap()
					.addComponent(panelInformeRangoFecha, GroupLayout.PREFERRED_SIZE, 332, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 208, Short.MAX_VALUE)
					.addComponent(btnInformeGenerarReporte)
					.addContainerGap())
		);
		
				JLabel lblTipoDeReporte = new JLabel("Tipo de Reporte");
				lblTipoDeReporte.setBounds(20, 34, 104, 16);
				panelInformeRangoFecha.add(lblTipoDeReporte);
				lblTipoDeReporte.setFont(new Font("Verdana", Font.BOLD, 12));
				
						cbTipoReporte = new JComboBox<String>();
						cbTipoReporte.setBounds(187, 28, 154, 29);
						panelInformeRangoFecha.add(cbTipoReporte);
						cbTipoReporte.setModel(
								new DefaultComboBoxModel<String>(new String[] {"Rango de Fecha", "Rango de Fecha(excel)", "Frecuentes", "Duracion"}));
						cbTipoReporte.setSelectedIndex(-1);
						cbTipoReporte.setFont(new Font("Verdana", Font.PLAIN, 12));
						
						JPanel panelReportesRangoFecha = new JPanel();
						panelReportesRangoFecha.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0), 1, true), "Rango de Fecha", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
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
														separator.setBounds(403, 11, 12, 299);
														panelInformeRangoFecha.add(separator);
														
														chckbxFiltrarPor = new JCheckBox("Filtrar:");
														chckbxFiltrarPor.setMnemonic('F');
														chckbxFiltrarPor.setFont(new Font("Verdana", Font.PLAIN, 12));
														chckbxFiltrarPor.setBounds(421, 28, 97, 23);
														chckbxFiltrarPor.setSelected(false);
														chckbxFiltrarPor.addItemListener(this);
														panelInformeRangoFecha.add(chckbxFiltrarPor);
														
														cBReporteArea = new JComboBox<String>();
														cBReporteArea.setEnabled(false);
														cBReporteArea.setSelectedIndex(-1);
														cBReporteArea.setFont(new Font("Verdana", Font.PLAIN, 12));
														cBReporteArea.setBounds(425, 99, 347, 29);
														panelInformeRangoFecha.add(cBReporteArea);
														
														lblArea = new JLabel("Area");
														lblArea.setEnabled(false);
														lblArea.setFont(new Font("Verdana", Font.PLAIN, 12));
														lblArea.setBounds(425, 74, 46, 14);
														panelInformeRangoFecha.add(lblArea);
														
														scrollPaneSubArea = new JScrollPane();
														scrollPaneSubArea.setEnabled(false);
														scrollPaneSubArea.setBounds(425, 180, 347, 130);
														panelInformeRangoFecha.add(scrollPaneSubArea);
														
														listSubArea = new JList<String>();
														listSubArea.setEnabled(false);
														listSubArea.setFont(new Font("Verdana", Font.PLAIN, 12));
														scrollPaneSubArea.setColumnHeaderView(listSubArea);
														
														lblSubarea = new JLabel("SubArea");
														lblSubarea.setEnabled(false);
														lblSubarea.setFont(new Font("Verdana", Font.PLAIN, 12));
														lblSubarea.setBounds(425, 155, 75, 14);
														panelInformeRangoFecha.add(lblSubarea);
		panelInformes.setLayout(gl_panelInformes);
		panelBusqueda.setLayout(null);

		JPanel panel_Resultado = new JPanel();
		panel_Resultado.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Resultado de Busqueda",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_Resultado.setBounds(0, 155, 820, 437);
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
		tablaResultado.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				if (e.getClickCount() == 2 && !e.isConsumed() && (!(chckbxBuscarSoluciones.isSelected()))) {

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
						
						if(descripcionAdicional.equals(null) || descripcionAdicional.equals("")) {
							descripcionAdicional = "";;
						}
						tiempoInicio = String.valueOf(tablaResultado.getValueAt(fila, 8));
						tiempoFin = String.valueOf(tablaResultado.getValueAt(fila, 9));
						solucion = String.valueOf(tablaResultado.getValueAt(fila, 10));
					}

					ModificacionParo modificacion = new ModificacionParo(new Paro(codigoParo, tiempoInicio, tiempoFin,
							solucion, causa, descripcionAdicional, disciplina), new AdministracionParos(),
							getDesktopPane());

					setVisible(false);
					e.consume();
					modificacion.setVisible(true);
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
				
				
				if(chckbxBuscarSoluciones.isSelected()) {
					mostrarSolucion();
				}
				
				else buscarParo();

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
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setOrientation(SwingConstants.VERTICAL);
		separator_1.setBounds(402, 11, 9, 133);
		panelBusqueda.add(separator_1);
		
		chckbxBuscarSoluciones = new JCheckBox("Buscar Soluciones");
		chckbxBuscarSoluciones.setFont(new Font("Verdana", Font.PLAIN, 12));
		chckbxBuscarSoluciones.setBounds(417, 18, 153, 23);
		chckbxBuscarSoluciones.addItemListener(this);
		panelBusqueda.add(chckbxBuscarSoluciones);
		
		lblCodigoDeEquipo = new JLabel("Codigo de Equipo");
		lblCodigoDeEquipo.setEnabled(false);
		lblCodigoDeEquipo.setFont(new Font("Verdana", Font.PLAIN, 12));
		lblCodigoDeEquipo.setBounds(421, 65, 121, 23);
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
		txtSoluciones.setBounds(549, 63, 270, 29);
		panelBusqueda.add(txtSoluciones);
		txtSoluciones.setColumns(10);
		setBounds(60, 26, 850, 663);

		// Actualiza el JTable en el panel de Busqueda
		modeloParo.fireTableDataChanged();
	}

	private void mostrarSolucion() {
		
		try{
			
			equipoSolucion.buscarSolucion(txtSoluciones.getText());
			tablaResultado.setModel(equipoSolucion);
		}
		
		catch(NullPointerException npe) {
			tablaResultado.setModel(equipoSolucion);
		}
		catch(Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), e.getClass().toString(), JOptionPane.ERROR_MESSAGE);
			log.log(Level.SEVERE, e.toString(), e);
		}
	}
	
	
	private void buscarParo() {
		
		try{
			df = new SimpleDateFormat(formatoFecha);
			
			String fInicio = df.format(busquedaDCFechaDesde.getDate().getTime());
			String fFin = df.format(busquedaDCFechaHasta.getDate().getTime());
			
			Paro paro = new Paro();
			paro.setTiempoInicio(fInicio);
			paro.setTiempoFin(fFin);

			
			if(paro.getTiempoInicio().equals(null) || paro.getTiempoFin().equals(null)) {
				modeloParo.buscarParo(new Paro(), "");
				tablaResultado.setModel(modeloParo);
			}
			else {
				modeloParo.buscarParo(paro, "fecha");
				tablaResultado.setModel(modeloParo);
			}
		}
		
		catch(NullPointerException npe) {
			modeloParo.buscarParo(new Paro(), "");
		}
		catch(Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), e.getClass().toString(), JOptionPane.ERROR_MESSAGE);
			log.log(Level.SEVERE, e.toString(), e);
		}
	}

	private void generarReporte() {
		
		df = new SimpleDateFormat(formatoFecha);

		String fechaInicio = df.format(informeDCFechaDesde.getDate().getTime());
		String fechaFin = df.format(informeDCFechaHasta.getDate().getTime());

		ManipulacionDatos md = new ManipulacionDatos();
		Connection con = md.obtenerConexion();
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
			default:
				JOptionPane.showMessageDialog(null, "Debe elegir un tipo de Paro");
				break;
			}

			try {
				
				parametro.put("fechaInicio", fechaInicio);
				parametro.put("fechaFin", fechaFin);

				JasperReport jr = (JasperReport) JRLoader.loadObjectFromFile(archivo);
				JasperPrint jp = JasperFillManager.fillReport(jr, parametro, con);

				JasperViewer jv = new JasperViewer(jp, false);
				jv.setVisible(true);
				jv.setTitle("Reporte de Paros por " + titulo);
			} 
			catch (JRException ex) {
				log.log(Level.SEVERE, ex.toString(), ex);
				JOptionPane.showMessageDialog(null, ex.getMessage(), ex.getClass().toString(),
						JOptionPane.ERROR_MESSAGE);
			}
		}
		catch (Exception e) {
			log.log(Level.SEVERE, e.toString(), e);
			JOptionPane.showMessageDialog(null, e.getMessage(), e.getClass().toString(),
					JOptionPane.ERROR_MESSAGE);
		}
	}
		
	
	public void itemStateChanged(ItemEvent e) {
	    
	    Object source = e.getItemSelectable();

	    if (source == chckbxFiltrarPor) {
	    	
	    	lblArea.setEnabled(true);
	    	lblSubarea.setEnabled(true);
	    	listSubArea.setEnabled(true);
	    	cBReporteArea.setEnabled(true);
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
	    	listSubArea.setEnabled(false);
	    	cBReporteArea.setEnabled(false);
	    	scrollPaneSubArea.setEnabled(false);
	    	
	    	lblCodigoDeEquipo.setEnabled(false);
	    	txtSoluciones.setEnabled(false);
	    	
	    	busquedaDCFechaDesde.setEnabled(true);
	    	busquedaDCFechaHasta.setEnabled(true);
	    }
	}
}