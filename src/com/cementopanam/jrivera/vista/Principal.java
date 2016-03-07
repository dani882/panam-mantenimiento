package com.cementopanam.jrivera.vista;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.nio.file.DirectoryNotEmptyException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import com.cementopanam.jrivera.controlador.CapturaUsuario;
import com.cementopanam.jrivera.vista.helper.JStatusBar;
import com.cementopanam.jrivera.vista.helper.TimerThread;
import com.cementopanam.jrivera.vista.internalFrames.AdministracionRegistros;
import com.cementopanam.jrivera.vista.internalFrames.Imputaciones;
import com.cementopanam.jrivera.vista.internalFrames.Reportes;


public class Principal extends JFrame implements Runnable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5631455790428057770L;
	private com.cementopanam.jrivera.vista.internalFrames.AdministracionRegistros admRegistros;
	private com.cementopanam.jrivera.vista.internalFrames.Imputaciones imputaciones;
	private com.cementopanam.jrivera.vista.internalFrames.Reportes reportes;
	private com.cementopanam.jrivera.vista.Autor author;

	public JButton btnImputaciones;
	public JButton btnReportes;
	public JButton btnAdministrar;
	
	public JLabel usuarioActual = new JLabel();
	
	private JButton btnCerrarSession;
	
	protected TimerThread timerThread;

	private JPanel panel_norte;
	private JPanel panel_oeste;
	private JPanel contentPane;
	
	private JLabel lblOperaciones;
	public static JLabel lblStatusbar;
	public JStatusBar statusBar;
	
	private JDesktopPane desktopPane;
	private Dimension dim;
	private int w, h, x, y;
	
	public static Icon icono;
	public static JProgressBar pbar;
	static int min = 0;
	static int max = 100;
	
	private CapturaUsuario captura = new CapturaUsuario();
	private JButton btnAutor;

	/**
	 * Crea el frame.
	 */
	public Principal() {
		initComponents();
	}
	
	private void initComponents() {
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				
				//Borra el archivo serializado
				Path rutaArchivo = Paths.get(captura.obtenerNombrePC() +".ser");
				
				try {
				    Files.delete(rutaArchivo);
				} catch (NoSuchFileException x) {
				    System.err.format("%s: no se encontro el archivo: ", rutaArchivo);
				} catch (DirectoryNotEmptyException x) {
				    System.err.format("%s no esta vacio%n", rutaArchivo);
				} catch (IOException x) {
				    // Problemas de permisos de archivos son capturados aqui
				    System.err.println(x);
				}
				
	/*		       if (JOptionPane.showConfirmDialog(null,"Esta seguro que desea cerrar esta ventana?", "Desea cerrar?", 
			               JOptionPane.YES_NO_OPTION,
			               JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION){
			               System.exit(0);
			           }*/
				
				detenerHiloStatusBar();
			}
		});
		setIconImage(Toolkit.getDefaultToolkit().getImage(Principal.class.getResource("/imagenes/logo-panam.png")));
		setTitle("Mantenimiento - Imputaciones de Paro");
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setFont(new Font("Verdana", Font.PLAIN, 12));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1024, 768);
		
		// Obtiene el tamaño de la pantalla
		dim = Toolkit.getDefaultToolkit().getScreenSize();
						 
		// Determina la nueva localizacion de la ventana
		w = getSize().width;
		h = getSize().height;
		x = (dim.width-w)/2;
		y = (dim.height-h)/2;
					 
		// Centra la ventana
		setLocation(x, y);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		panel_oeste = new JPanel();
		contentPane.add(panel_oeste, BorderLayout.WEST);
		panel_oeste.setLayout(new BoxLayout(panel_oeste, BoxLayout.Y_AXIS));
		
		btnImputaciones = new JButton("");
		btnImputaciones.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				btnImputacionesActionPerformed(e);
			}
		});
		btnImputaciones.setToolTipText("Imputaciones");
		btnImputaciones.setIcon(new ImageIcon(Principal.class.getResource("/iconos/icon_lowres.png")));
		btnImputaciones.setFont(new Font("Verdana", Font.PLAIN, 12));
		panel_oeste.add(btnImputaciones);
		
		btnReportes = new JButton("");
		btnReportes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				btnReportesActionPerformed(e);
					
			}
		});
		btnReportes.setIcon(new ImageIcon(Principal.class.getResource("/iconos/reportes.png")));
		btnReportes.setToolTipText("Busqueda y Reporte de Paros");
		btnReportes.setFont(new Font("Verdana", Font.PLAIN, 12));
		panel_oeste.add(btnReportes);
		
		btnCerrarSession = new JButton("");
		btnCerrarSession.setToolTipText("Cerrar Session");
		btnCerrarSession.setIcon(new ImageIcon(Principal.class.getResource("/iconos/logout-icon.png")));
		btnCerrarSession.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				btnCerrarSessionActionPerformed(e);
				
			}
		});
		
		btnAdministrar = new JButton("");
		btnAdministrar.setFont(new Font("Verdana", Font.PLAIN, 12));
		btnAdministrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				btnAdministrarActionPerformed(e);

			}
		});
		btnAdministrar.setToolTipText("Administracion Registros");
		btnAdministrar.setIcon(new ImageIcon(Principal.class.getResource("/iconos/Add-icon.png")));
		panel_oeste.add(btnAdministrar);
		
		btnAutor = new JButton("");
		btnAutor.setFont(new Font("Verdana", Font.PLAIN, 12));
		btnAutor.setToolTipText("Ayuda");
		btnAutor.setIcon(new ImageIcon(Principal.class.getResource("/iconos/help.png")));
		btnAutor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				btnAuthorActionPerformed(e);
			}
		});
		panel_oeste.add(btnAutor);
		btnCerrarSession.setFont(new Font("Verdana", Font.PLAIN, 12));
		panel_oeste.add(btnCerrarSession);
		
		panel_norte = new JPanel();
		panel_norte.setBorder(null);
		contentPane.add(panel_norte, BorderLayout.NORTH);
		panel_norte.setLayout(new GridLayout(0, 3, 0, 0));
		
		lblOperaciones = new JLabel("Operaciones");
		lblOperaciones.setHorizontalAlignment(SwingConstants.LEFT);
		lblOperaciones.setFont(new Font("Verdana", Font.BOLD, 14));
		panel_norte.add(lblOperaciones);
		
		desktopPane = new JDesktopPane() {
			
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			/*
			 * Este metodo sirve para redimensionar automaticamente la imagen de fondo dependiendo del tamaño de la pantalla
			 */
			ImageIcon icon = new ImageIcon(getClass().getResource("/imagenes/DSC_0002.JPG"));
		    Image image = icon.getImage();

		    Image newImage = image.getScaledInstance(2000, 2000, Image.SCALE_SMOOTH);

		    @Override
		    protected void paintComponent(Graphics g)
		    {
		        super.paintComponent(g);
		        Graphics2D g2d = (Graphics2D) g;
		        g2d.drawImage(newImage, 0, 0, getSize().width, getSize().height, this);
		    }
		};
	
		contentPane.add(desktopPane, BorderLayout.CENTER);
		desktopPane.setLayout(null);
		
		//Inicio Mostrar Formularios
		imputaciones = new Imputaciones();
		imputaciones.setBounds(12, 12, 1150, 590);
		desktopPane.add(imputaciones);
		
		reportes = new Reportes();
		reportes.setLocation(60, 5);
		desktopPane.add(reportes);
		
		admRegistros = new AdministracionRegistros();
		admRegistros.setBounds(30, 60, 969, 570);
		desktopPane.add(admRegistros);
		
		author = new Autor();
		
		//Fin Mostrar Formularios
		
//		pbar = new JProgressBar(min,max);
		
	}
	
	//Metodos de Accion
	private void btnImputacionesActionPerformed(ActionEvent e) {   
		imputaciones.setVisible(true);
    }
	
	private void btnReportesActionPerformed(ActionEvent e) {
		reportes.setVisible(true);
    }
	
	private void btnAdministrarActionPerformed(ActionEvent e) {
		admRegistros.setVisible(true);
    }
	
	private void btnAuthorActionPerformed(ActionEvent e) {
		author.setVisible(true);
	}
	private void btnCerrarSessionActionPerformed(ActionEvent e) {
		
		Login login = new Login();
		login.setVisible(true);
		setVisible(false);
		
		
		Path rutaArchivo = Paths.get(captura.obtenerNombrePC() +".ser");
		
		try {
		    Files.delete(rutaArchivo);
		} catch (NoSuchFileException x) {
		    System.err.format("%s: no se encontro el archivo o directorio%n", rutaArchivo);
		} catch (DirectoryNotEmptyException x) {
		    System.err.format("%s no esta vacio%n", rutaArchivo);
		} catch (IOException x) {
		    // File permission problems are caught here.
		    System.err.println(x);
		}
		
    }

	private void detenerHiloStatusBar() {
		
		timerThread.setRunning(false);
		System.exit(0);
	}
	
	public void mostrarMensaje(String mensaje, String alerta) {
		
		lblStatusbar.setText(mensaje);
		
		if(alerta.equalsIgnoreCase("advertencia")){
		lblStatusbar.setIcon(new ImageIcon(getClass().getResource("/iconos16x16/warning-icon.png")));
		}
		else if(alerta.equalsIgnoreCase("ok")) {
			lblStatusbar.setIcon(new ImageIcon(getClass().getResource("/iconos16x16/ok.png")));
		}
		else {JOptionPane.showMessageDialog(null, "Hay un problema con las fechas");}
	}

	@Override
	public void run() {
		
		statusBar = new JStatusBar();
			
		lblStatusbar = new JLabel("Listo");
		lblStatusbar.setIcon(new ImageIcon(getClass().getResource("/iconos16x16/ok.png")));
		statusBar.setLeftComponent(lblStatusbar);
		
		pbar = new JProgressBar();
		statusBar.addRightComponent(pbar);
			
		final JLabel dateLabel = new JLabel();
	    dateLabel.setHorizontalAlignment(JLabel.CENTER);
	    statusBar.addRightComponent(dateLabel);
	 
	    final JLabel timeLabel = new JLabel();
	    timeLabel.setHorizontalAlignment(JLabel.CENTER);
	    statusBar.addRightComponent(timeLabel);
		
		contentPane.add(statusBar, BorderLayout.SOUTH);
		
		JLabel conectar = new JLabel("Conectado como: ");
		statusBar.addRightComponent(conectar);
		
		statusBar.addRightComponent(usuarioActual);
		
		timerThread = new TimerThread(dateLabel, timeLabel);
	    timerThread.start();	
	}
}