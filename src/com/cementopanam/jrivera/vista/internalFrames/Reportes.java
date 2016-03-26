
package com.cementopanam.jrivera.vista.internalFrames;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyVetoException;
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
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

import com.cementopanam.jrivera.controlador.ManipulacionDatos;
import com.cementopanam.jrivera.controlador.paros.AdministracionParos;
import com.cementopanam.jrivera.controlador.paros.Paro;
import com.cementopanam.jrivera.vista.ModificacionParo;
import com.cementopanam.jrivera.vista.helper.tablaModelo.TablaModeloParo;
import com.toedter.calendar.JDateChooser;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;
import javax.swing.ImageIcon;
import javax.swing.JSplitPane;

public class Reportes extends JInternalFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3425343178839382975L;

	private String formatoFecha = "yyyy-MM-dd";
	
	private JDateChooser busquedaDCFechaDesde;
	private JDateChooser busquedaDCFechaHasta;
	private JDateChooser informeDCFechaDesde;
	private JDateChooser informeDCFechaHasta;
	
	private JComboBox<String> comboBoxTipoReporte;
	
	private SimpleDateFormat df;
	private JTable tablaResultado;
	private TablaModeloParo modeloParo = new TablaModeloParo();
	private JButton btnBuscar;
	
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
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
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
				
				df = new SimpleDateFormat(formatoFecha);
				
				String fechaInicio = df.format(informeDCFechaDesde.getDate().getTime());
				String fechaFin = df.format(informeDCFechaHasta.getDate().getTime());
				
				ManipulacionDatos md = new ManipulacionDatos();
				Connection con = md.obtenerConexion();
				String archivo = "";
				String titulo = "";
				
				try (InputStream in = getClass().getResourceAsStream("/paros.properties");) {
					// Carga las propiedades del archivo
					Properties pros = new Properties();
					pros.load(in);
					
					switch (comboBoxTipoReporte.getSelectedIndex()) {
					case 0:
						//Paros por fecha
						archivo = pros.getProperty("fecha");
						titulo = "Fecha";
						break;
					case 1:
						//Paros por Frecuencia
						archivo = pros.getProperty("frecuencia");
						titulo = "Frecuencia";
						break;
					case 2:
						//Paros por duracion
						archivo = pros.getProperty("duracion");
						titulo = "Duracion";
						break;
					default:
						JOptionPane.showMessageDialog(null, "Debe elegir un tipo de Paro");
						break;
					}
					
					JasperReport jr = null;
					try{
					Map<String, Object> parametro = new HashMap<String, Object>();
					parametro.put("fechaInicio", fechaInicio);
					parametro.put("fechaFin", fechaFin);

					jr = (JasperReport) JRLoader.loadObjectFromFile(archivo);
					JasperPrint jp = JasperFillManager.fillReport(jr,parametro,con);
					
					JasperViewer jv = new JasperViewer(jp, false);
					jv.setVisible(true);
					jv.setTitle("Reporte de Paros por " + titulo);
					}
					catch (JRException ex) {
					Logger.getLogger(Reportes.class.getName()).log(Level.SEVERE, null, ex);
					JOptionPane.showMessageDialog(null, ex.getMessage(), ex.getClass().toString(),
							JOptionPane.ERROR_MESSAGE);
					}
				}
				catch(Exception e) {
					JOptionPane.showMessageDialog(null, e.getMessage(), e.getClass().toString(), 
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		
		JPanel panelInformeRangoFecha = new JPanel();
		panelInformeRangoFecha.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229), 2, true), "Rango de Fecha", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(51, 51, 51)));
		panelInformeRangoFecha.setLayout(null);
		
		JLabel lblTipoDeReporte = new JLabel("Tipo de Reporte");
		lblTipoDeReporte.setFont(new Font("Verdana", Font.BOLD, 12));
		
		comboBoxTipoReporte = new JComboBox<String>();
		comboBoxTipoReporte.setModel(new DefaultComboBoxModel<String>(new String[] {"Rango de Fecha", "Frecuentes", "Duracion"}));
		comboBoxTipoReporte.setSelectedIndex(-1);
		comboBoxTipoReporte.setFont(new Font("Verdana", Font.PLAIN, 12));
		GroupLayout gl_panelInformes = new GroupLayout(panelInformes);
		gl_panelInformes.setHorizontalGroup(
			gl_panelInformes.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelInformes.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panelInformes.createParallelGroup(Alignment.LEADING)
						.addComponent(panelInformeRangoFecha, GroupLayout.PREFERRED_SIZE, 492, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnInformeGenerarReporte)
						.addGroup(gl_panelInformes.createSequentialGroup()
							.addComponent(lblTipoDeReporte)
							.addGap(52)
							.addComponent(comboBoxTipoReporte, GroupLayout.PREFERRED_SIZE, 154, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(327, Short.MAX_VALUE))
		);
		gl_panelInformes.setVerticalGroup(
			gl_panelInformes.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panelInformes.createSequentialGroup()
					.addGap(23)
					.addGroup(gl_panelInformes.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblTipoDeReporte)
						.addComponent(comboBoxTipoReporte, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addComponent(panelInformeRangoFecha, GroupLayout.PREFERRED_SIZE, 112, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 390, Short.MAX_VALUE)
					.addComponent(btnInformeGenerarReporte)
					.addContainerGap())
		);
		
		informeDCFechaHasta = new JDateChooser();
		informeDCFechaHasta.setBounds(153, 61, 155, 28);
		panelInformeRangoFecha.add(informeDCFechaHasta);
		
		JLabel lblFechaDeInicio = new JLabel("Fecha de Inicio");
		lblFechaDeInicio.setFont(new Font("Verdana", Font.BOLD, 12));
		lblFechaDeInicio.setBounds(12, 30, 105, 15);
		panelInformeRangoFecha.add(lblFechaDeInicio);
		
		informeDCFechaDesde = new JDateChooser();
		informeDCFechaDesde.setBounds(153, 21, 155, 28);
		panelInformeRangoFecha.add(informeDCFechaDesde);
		
		JLabel lblFechaFin = new JLabel("Fecha Fin");
		lblFechaFin.setFont(new Font("Verdana", Font.BOLD, 12));
		lblFechaFin.setBounds(12, 61, 105, 15);
		panelInformeRangoFecha.add(lblFechaFin);
		panelInformes.setLayout(gl_panelInformes);
		
		JLabel lblFiltrarPor = new JLabel("Filtrar por:");
		lblFiltrarPor.setEnabled(false);
		lblFiltrarPor.setBounds(10, 16, 66, 30);
		lblFiltrarPor.setFont(new Font("Verdana", Font.PLAIN, 12));
		
		JComboBox<Object> comboBoxFiltrar = new JComboBox<Object>();
		comboBoxFiltrar.setEnabled(false);
		comboBoxFiltrar.setBounds(121, 16, 128, 30);
		comboBoxFiltrar.setModel(new DefaultComboBoxModel<Object>(new String[] {"Tiempo", "Estado"}));
		comboBoxFiltrar.setSelectedIndex(-1);
		comboBoxFiltrar.setFont(new Font("Verdana", Font.PLAIN, 12));
		
		JPanel panelEstado = new JPanel();
		panelEstado.setBounds(259, 11, 232, 56);
		panelEstado.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Estado", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panelEstado.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JRadioButton rdbtnCompletado = new JRadioButton("Completado");
		rdbtnCompletado.setEnabled(false);
		rdbtnCompletado.setFont(new Font("Verdana", Font.BOLD, 12));
		panelEstado.add(rdbtnCompletado);
		
		JRadioButton rdbtnPendiente = new JRadioButton("Pendiente");
		rdbtnPendiente.setEnabled(false);
		rdbtnPendiente.setFont(new Font("Verdana", Font.BOLD, 12));
		panelEstado.add(rdbtnPendiente);
		panelBusqueda.setLayout(null);
		panelBusqueda.add(lblFiltrarPor);
		panelBusqueda.add(comboBoxFiltrar);
		panelBusqueda.add(panelEstado);
		
		JPanel panel_Resultado = new JPanel();
		panel_Resultado.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Resultado de Busqueda", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_Resultado.setBounds(0, 213, 820, 366);
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
		    	
				//Ajusta la columna de la tabla a un tamano adecuado
		    	Component component = super.prepareRenderer(renderer, row, column);
		    	int rendererWidth = component.getPreferredSize().width;
		        TableColumn tableColumn = getColumnModel().getColumn(column);
		        tableColumn.setPreferredWidth(Math.max(rendererWidth + getIntercellSpacing().width, tableColumn.getPreferredWidth()));
		        return component;
		    }
		};
		tablaResultado.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				if (e.getClickCount() == 2 && !e.isConsumed()) {
					
					int fila = tablaResultado.getSelectedRow();
					int columna = tablaResultado.getSelectedColumn();
					
					int codigo = 0;
					String tiempoInicio = "";
					String solucion = "";
					String descripcionAdicional = "";
					String tiempoFin = "";
					String causa = "";
					String disciplina = "";
					
					for(int i = 0; i < columna; i++) {
						
						codigo = (int)tablaResultado.getValueAt(fila, 0);
						disciplina = String.valueOf(tablaResultado.getValueAt(fila, 5));
						causa = String.valueOf(tablaResultado.getValueAt(fila, 6));
						descripcionAdicional = String.valueOf(tablaResultado.getValueAt(fila, 7));
					    tiempoInicio = String.valueOf(tablaResultado.getValueAt(fila, 8));
					    tiempoFin = String.valueOf(tablaResultado.getValueAt(fila, 9));
					    solucion = String.valueOf(tablaResultado.getValueAt(fila, 10));
					}

					ModificacionParo modificacion = new ModificacionParo(new Paro(codigo, tiempoInicio, tiempoFin, 
							solucion, causa, descripcionAdicional, disciplina), new AdministracionParos(),
							getDesktopPane());
					
					modificacion.setVisible(true);
					e.consume();
					
					setVisible(false);

				}
			}
		});
		tablaResultado.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		//Sirve para no permitir que el usuario reordene las columnas
		tablaResultado.getTableHeader().setReorderingAllowed(false);
		tablaResultado.setFont(new Font("Verdana", Font.PLAIN, 12));
		
		scrollPaneResultado.setViewportView(tablaResultado);
		tablaResultado.setModel(modeloParo);
		
		btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				modeloParo.buscar("");
				
				
			}
		});
		btnBuscar.setFont(new Font("Verdana", Font.BOLD, 12));
		btnBuscar.setBounds(10, 175, 89, 23);
		panelBusqueda.add(btnBuscar);
		
		JPanel panelRangoFecha = new JPanel();
		panelRangoFecha.setBorder(new TitledBorder(null, "Rango de Fecha", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelRangoFecha.setBounds(10, 69, 481, 95);
		panelBusqueda.add(panelRangoFecha);
		panelRangoFecha.setLayout(null);
		
		JLabel labelDesde = new JLabel("Desde");
		labelDesde.setEnabled(false);
		labelDesde.setFont(new Font("Verdana", Font.PLAIN, 12));
		labelDesde.setBounds(10, 23, 65, 25);
		panelRangoFecha.add(labelDesde);
		
		JLabel labelHasta = new JLabel("Hasta");
		labelHasta.setEnabled(false);
		labelHasta.setFont(new Font("Verdana", Font.PLAIN, 12));
		labelHasta.setBounds(10, 59, 65, 25);
		panelRangoFecha.add(labelHasta);
		
		busquedaDCFechaHasta = new JDateChooser();
		busquedaDCFechaHasta.setBounds(85, 59, 159, 25);
		busquedaDCFechaHasta.setEnabled(false);
		panelRangoFecha.add(busquedaDCFechaHasta);
		
		busquedaDCFechaDesde = new JDateChooser();
		busquedaDCFechaDesde.setBounds(85, 23, 159, 25);
		busquedaDCFechaDesde.setEnabled(false);
		panelRangoFecha.add(busquedaDCFechaDesde);
		setBounds(60, 26, 850, 663);
		
		//Actualiza el JTable
		modeloParo.fireTableDataChanged();
	}
}