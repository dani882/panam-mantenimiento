package com.cementopanam.jrivera.vista;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.logging.Logger;

import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JProgressBar;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

import com.cementopanam.jrivera.vista.helper.BarraEstado;
import com.cementopanam.jrivera.vista.helper.TimerThread;
import com.cementopanam.jrivera.vista.internalFrames.AdministracionRegistros;
import com.cementopanam.jrivera.vista.internalFrames.AdministracionUsuarios;
import com.cementopanam.jrivera.vista.internalFrames.Imputaciones;
import com.cementopanam.jrivera.vista.internalFrames.Reportes;

public class Principal extends JFrame implements Runnable {

	/**
	 * 
	 */
	private static final Logger log = Logger.getLogger(Principal.class.getName());
	private static final long serialVersionUID = 5631455790428057770L;
	
	private AdministracionUsuarios admUsuario;
	private AdministracionRegistros admRegistros;
	private Imputaciones imputaciones;
	private Reportes reportes;
	private Autor author;

	final JPopupMenu menuRegistros = new JPopupMenu();
	private JMenuItem itemRegistros;
	private JMenuItem itemUsuario;

	final JPopupMenu menuUsuario = new JPopupMenu();
	private JMenuItem itemCerrarSession;
	private JMenuItem itemModificarUsuario;
	
	public JButton btnImputaciones;
	public JButton btnReportes;
	public JButton btnAdministrar;
	private JButton btnCerrarSession;

	public static JButton usuarioActual = new JButton();

	protected TimerThread timerThread;

	private JPanel panel_norte;
	private JPanel panel_oeste;
	private JPanel contentPane;

	private JLabel lblOperaciones;
	public static JLabel lblStatusBar;
	public BarraEstado statusBar;

	private JDesktopPane desktopPane;
	private Dimension dim;
	private int w, h, x, y;

	public static Icon icono;
	public static JProgressBar pbar;

	static int min = 0;
	static int max = 100;

	// private CapturaUsuario captura = new CapturaUsuario();
	private JButton btnAutor;

	/**
	 * Crea el frame.
	 */
	public Principal() {
		initComponents();
	}

	private void initComponents() {
		/*
		 * // Borra el archivo serializado addWindowListener(new WindowAdapter()
		 * {
		 * 
		 * @Override public void windowClosing(WindowEvent e) {
		 * 
		 * //Borra el archivo serializado Path rutaArchivo =
		 * Paths.get(captura.obtenerNombrePC() +".ser");
		 * 
		 * try { Files.delete(rutaArchivo); } catch (NoSuchFileException x) {
		 * System.err.format("%s: no se encontro el archivo: ", rutaArchivo); }
		 * catch (DirectoryNotEmptyException x) { System.err.format(
		 * "%s no esta vacio%n", rutaArchivo); } catch (IOException x) { //
		 * Problemas de permisos de archivos son capturados aqui
		 * System.err.println(x); }
		 * 
		 * detenerHiloStatusBar(); } });
		 */

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
		x = (dim.width - w) / 2;
		y = (dim.height - h) / 2;

		// Centra la ventana
		setLocation(x, y);

		//Personaliza el JButton del Usuario Autenticado
		usuarioActual.setBackground(null);
		usuarioActual.setMargin(new Insets(0, 0, 0, 0));
		usuarioActual.setContentAreaFilled(false);
		usuarioActual.setBorderPainted(false);
		usuarioActual.setOpaque(false);
		
		usuarioActual.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent evt) {
				// Muestra el popup Menu
				if (!evt.isPopupTrigger()) {
					menuUsuario.show(evt.getComponent(), evt.getX(), evt.getY() - 90);
				}
			}
		});
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

		// Menu Credenciales de Usuario
		menuUsuario.setLightWeightPopupEnabled(false);
		
		// Popup Menu item Usuario
		itemModificarUsuario = new JMenuItem("Cambiar contrase�a");
		itemModificarUsuario.setIcon(new ImageIcon(Principal.class.getResource("/iconos32x32/user32x32.png")));
		menuUsuario.add(itemModificarUsuario);
		
		menuUsuario.addSeparator();
		// Popup Menu item Cerrar Session
		itemCerrarSession = new JMenuItem("Cerrar Session");
		itemCerrarSession.setFont(new Font("Verdana", Font.PLAIN, 12));
		itemCerrarSession.setIcon(new ImageIcon(Principal.class.getResource("/iconos32x32/logout-icon32x32.png")));
		menuUsuario.add(itemCerrarSession);

		itemModificarUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				ModificacionUsuario modificacionUsuario = new ModificacionUsuario();
				modificacionUsuario.setVisible(true);
			}
		});

		itemCerrarSession.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Login login = new Login();
				login.setVisible(true);
				setVisible(false);
			}
		});
		
		// Menu Registros y Usuarios
		menuRegistros.setLightWeightPopupEnabled(false);
		// Popup Menu item Registros
		itemRegistros = new JMenuItem("Registros");
		itemRegistros.setFont(new Font("Verdana", Font.PLAIN, 12));
		itemRegistros.setIcon(new ImageIcon(Principal.class.getResource("/iconos32x32/excavadora32x32.png")));
		menuRegistros.add(itemRegistros);

		menuRegistros.addSeparator();
		// Popup Menu item Usuario
		itemUsuario = new JMenuItem("Usuario");
		itemRegistros.setFont(new Font("Verdana", Font.PLAIN, 12));
		itemUsuario.setIcon(new ImageIcon(Principal.class.getResource("/iconos32x32/user32x32.png")));
		menuRegistros.add(itemUsuario);

		itemUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				admUsuario.setVisible(true);

			}
		});

		itemRegistros.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				admRegistros.setVisible(true);

			}
		});
		btnAdministrar = new JButton("");
		btnAdministrar.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent evt) {
				// Muestra el popup Menu
				if (!evt.isPopupTrigger()) {
					menuRegistros.show(evt.getComponent(), evt.getX() + 20, evt.getY());
				}
			}
		});
		btnAdministrar.setFont(new Font("Verdana", Font.PLAIN, 12));
		btnAdministrar.setToolTipText("Administracion Registros y Usuarios");
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
			 * Este metodo sirve para redimensionar automaticamente la imagen de
			 * fondo dependiendo del tamaño de la pantalla
			 */
			ImageIcon icon = new ImageIcon(getClass().getResource("/imagenes/DSC_0002.JPG"));
			Image image = icon.getImage();

			Image newImage = image.getScaledInstance(2000, 2000, Image.SCALE_SMOOTH);

			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				Graphics2D g2d = (Graphics2D) g;
				g2d.drawImage(newImage, 0, 0, getSize().width, getSize().height, this);
			}
		};

		contentPane.add(desktopPane, BorderLayout.CENTER);
		desktopPane.setLayout(null);

		// Inicio Mostrar Formularios
		imputaciones = new Imputaciones();
		imputaciones.setBounds(12, 12, 1150, 590);
		desktopPane.add(imputaciones);

		reportes = new Reportes();
		reportes.setLocation(60, 5);
		desktopPane.add(reportes);

		admUsuario = new AdministracionUsuarios();
		desktopPane.add(admUsuario);

		admRegistros = new AdministracionRegistros();
		admRegistros.setLocation(50, 2);
		desktopPane.add(admRegistros);

		author = new Autor();

		// Fin Mostrar Formularios

		// pbar = new JProgressBar(min,max);

	}

	// Metodos de Accion
	private void btnImputacionesActionPerformed(ActionEvent e) {
		imputaciones.setVisible(true);
	}

	private void btnReportesActionPerformed(ActionEvent e) {
		if(btnImputaciones.isVisible()) {
			reportes.setVisible(true);
		}
		else {
			reportes.setVisible(true);
			reportes.tabbedPane.setEnabledAt(0, false);
			reportes.tabbedPane.setSelectedIndex(1);
		}
	}

	private void btnAuthorActionPerformed(ActionEvent e) {
		author.setVisible(true);
	}

	private void btnCerrarSessionActionPerformed(ActionEvent e) {

		Login login = new Login();
		login.setVisible(true);
		setVisible(false);
	}

	private void detenerHiloStatusBar() {

		timerThread.setRunning(false);
		System.exit(0);
	}

	public void mostrarMensaje(String mensaje, String alerta) {

		lblStatusBar.setText(mensaje);

		if (alerta.equalsIgnoreCase("advertencia")) {
			lblStatusBar.setIcon(new ImageIcon(getClass().getResource("/iconos16x16/warning-icon.png")));
		} else if (alerta.equalsIgnoreCase("ok")) {
			lblStatusBar.setIcon(new ImageIcon(getClass().getResource("/iconos16x16/ok.png")));
		} else {
			JOptionPane.showMessageDialog(null, "Hay un problema con las fechas");
		}
	}

	public JDesktopPane getDesktopPane() {
		return desktopPane;
	}

	public void setDesktopPane(JDesktopPane desktopPane) {
		this.desktopPane = desktopPane;
	}

	@Override
	public void run() {

		statusBar = new BarraEstado();

		lblStatusBar = new JLabel("Listo");
		lblStatusBar.setIcon(new ImageIcon(getClass().getResource("/iconos16x16/ok.png")));
		statusBar.setLeftComponent(lblStatusBar);

		pbar = new JProgressBar();
		pbar.setStringPainted(true);
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
	
	// Muestra Barra de Progreso
	public static void mostrarProgreso(int duracion) {

		pbar.setMinimum(min);
		pbar.setMaximum(max);

		for (int i = min; i <= max; i++) {
			final int porcentaje = i;

			try {
				SwingUtilities.invokeLater(new Runnable() {
					public void run() {
						pbar.setValue(porcentaje);
					}
				});
				Thread.sleep(duracion);
			} catch (InterruptedException e) {
				JOptionPane.showMessageDialog(null, e.getMessage(), e.getClass().toString(), JOptionPane.ERROR_MESSAGE);
			}
		}
		pbar.setValue(0);
		pbar.setStringPainted(false);
		pbar.repaint();
	}
}