package com.cementopanam.jrivera.vista.main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.ExecutionException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import org.apache.log4j.Logger;

import com.cementopanam.jrivera.controlador.ManipulacionDatos;
import com.cementopanam.jrivera.controlador.usuario.CapturaUsuario;
import com.cementopanam.jrivera.vista.Principal;
import com.jtattoo.plaf.acryl.AcrylLookAndFeel;

/**
 * @autor Jesus Rivera
 */

public class Login extends JFrame {

	/**
	 * 
	 */
	private static final Logger logger = Logger.getLogger(Login.class);
	private static final long serialVersionUID = 1788905256991802788L;
	private ManipulacionDatos md;
	private ResultSet rs = null;

	private JPanel contentPane;

	private JTextField txtUsuario;
	private JPasswordField pwdClave;

	private JLabel lblClave;
	private JLabel lblUsuario;
	private JLabel lblLogo;
	private JLabel lblIncorrecto;

	private JButton btnAcceder;

	private JSeparator separator;

	private Dimension dim;
	private int w, h, x, y;

	private CapturaUsuario captura = new CapturaUsuario();

	/**
	 * Lanza la aplicacion.
	 */
	public static void main(String[] args) {

		// PlasticLookAndFeel.setPlasticTheme(new DesertBlue());

		try {

			Properties props = new Properties();

			props.put("logoString", "Opciones");
			// props.put("licenseKey", "INSERT YOUR LICENSE KEY HERE");

			// props.put("selectionBackgroundColor", "180 240 197");
			props.put("menuSelectionBackgroundColor", "180 240 197");

			props.put("controlColor", "218 254 230");
			props.put("controlColorLight", "218 254 230");
			props.put("controlColorDark", "180 240 197");

			props.put("buttonColor", "218 230 254");
			props.put("buttonColorLight", "255 255 255");
			props.put("buttonColorDark", "244 242 232");

			props.put("rolloverColor", "218 254 230");
			props.put("rolloverColorLight", "218 254 230");
			props.put("rolloverColorDark", "180 240 197");

			// props.put("windowTitleForegroundColor", "0 0 0");
			// props.put("windowTitleBackgroundColor", "180 240 197");
			// props.put("windowTitleColorLight", "218 254 230");
			// props.put("windowTitleColorDark", "180 240 197");
			props.put("windowBorderColor", "218 254 230");

			// set your theme
			AcrylLookAndFeel.setCurrentTheme(props);
			// select the Look and Feel
			// UIManager.setLookAndFeel("com.jtattoo.plaf.acryl.AcrylLookAndFeel");

			// UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			UIManager.setLookAndFeel("com.jtattoo.plaf.acryl.AcrylLookAndFeel");
			// UIManager.setLookAndFeel("com.jtattoo.plaf.mint.MintLookAndFeel");

			// UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");

		} catch (Exception e) {
			logger.error(e.toString(), e);
		}

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					new Login().setVisible(true);

				} catch (Exception e) {
					logger.error(e.toString(), e);
					JOptionPane.showMessageDialog(null, e.getMessage());
					JOptionPane.showMessageDialog(null, "Se va a cerrar la aplicacion");
					System.exit(0);
				}
			}
		});
	}

	/**
	 * Crea el frame.
	 */
	public Login() {
		initComponents();
	}

	private void initComponents() {

		setTitle("Iniciar Sesion");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(552, 305);
		setResizable(false);
		contentPane = new JPanel();
		setIconImage(Toolkit.getDefaultToolkit().getImage(Login.class.getResource("/imagenes/logo-panam.png")));

		// Obtiene la dimension de la pantalla
		dim = Toolkit.getDefaultToolkit().getScreenSize();

		// Determina la nueva localizacion de la ventana
		w = getSize().width;
		h = getSize().height;
		x = (dim.width - w) / 2;
		y = (dim.height - h) / 2;

		// Centra la ventana
		setLocation(x, y);

		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		lblClave = new JLabel("Contrase\u00F1a");
		lblClave.setFont(new Font("Verdana", Font.PLAIN, 12));
		lblClave.setBounds(267, 118, 103, 21);
		contentPane.add(lblClave);

		lblLogo = new JLabel("");
		lblLogo.setIcon(new ImageIcon(Login.class.getResource("/imagenes/logo-panam.png")));
		lblLogo.setBounds(12, 12, 234, 180);
		contentPane.add(lblLogo);

		lblIncorrecto = new JLabel("");
		lblIncorrecto.setForeground(Color.RED);
		lblIncorrecto.setVisible(false);
		lblIncorrecto.setFont(new Font("Nimbus Roman No9 L", Font.BOLD, 16));
		lblIncorrecto.setBounds(12, 178, 518, 21);
		contentPane.add(lblIncorrecto);

		txtUsuario = new JTextField();
		txtUsuario.setBounds(363, 76, 167, 30);
		contentPane.add(txtUsuario);
		txtUsuario.setColumns(10);

		lblUsuario = new JLabel("Usuario");
		lblUsuario.setFont(new Font("Verdana", Font.PLAIN, 12));
		lblUsuario.setBounds(267, 80, 63, 21);
		contentPane.add(lblUsuario);

		pwdClave = new JPasswordField();
		pwdClave.setEchoChar('*');
		pwdClave.setBounds(363, 111, 167, 30);
		contentPane.add(pwdClave);
		pwdClave.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				passwordFieldKeyPressed(e);
			}
		});

		separator = new JSeparator();
		separator.setBounds(12, 211, 518, 2);
		contentPane.add(separator);

		btnAcceder = new JButton("Acceder");
		btnAcceder.setFont(new Font("Verdana", Font.PLAIN, 12));
		btnAcceder.setIcon(new ImageIcon(Login.class.getResource("/iconos32x32/ok32x32.png")));
		btnAcceder.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				btnAccederMouseClicked(e);
			}
		});

		btnAcceder.setBounds(363, 224, 167, 42);
		contentPane.add(btnAcceder);
	}

	/**
	 * Verifica los credenciales del usuario proporcionado
	 */
	private void validarUsuario() {

		SwingWorker<String, String> worker = new SwingWorker<String, String>() {

			/**
			 * Metodo que realiza la tarea pesada
			 */
			@Override
			protected String doInBackground() throws Exception {

				lblIncorrecto.setVisible(true);
				publish();

				txtUsuario.setEnabled(false);
				btnAcceder.setEnabled(false);
				btnAcceder.setVisible(false);
				String usuario = txtUsuario.getText();
				char[] password = pwdClave.getPassword();

				String clave = "";

				for (char c : password) {
					clave += c;
				}

				pwdClave.setEnabled(false);
				md = new ManipulacionDatos();
				Principal principal = new Principal();

				try {
					rs = md.autenticarUsuario(usuario, clave);
					// si los datos son correctos, entra al formulario
					if (rs.next()) {
						int tipoUsuario = rs.getInt("id_tipo_usuario");
						String estadoUsuario = rs.getString("estatus");
						String nombreUsuario = rs.getString("nombre_usuario");

						if (tipoUsuario == 1 && estadoUsuario.equalsIgnoreCase("activo")) {
							// Interfaz de Administrador
							logger.info("Usuario: " + usuario + " conectado como Administrador en PC: "
									+ captura.obtenerNombrePC());
							// Desactiva la ventana imputacion en el usuario
							// admin
							if (nombreUsuario.equalsIgnoreCase("admin")) {
								principal.btnImputaciones.setVisible(false);
							}
							// guardarUsuario();

							// Ejecuta la Pantalla Principal con el Thread de la
							// Barra de Estado
							SwingUtilities.invokeLater(principal);

							Principal.usuarioActual.setText(getNombreUsuario());
							principal.setVisible(true);
							dispose();

							// return true;
						} else if (tipoUsuario == 2 && estadoUsuario.equalsIgnoreCase("activo")) {
							// Interfaz de el Operador
							logger.info("Usuario: " + usuario + " conectado como Operador en PC: "
									+ captura.obtenerNombrePC());
							// guardarUsuario();
							SwingUtilities.invokeLater(principal);

							Principal.usuarioActual.setText(getNombreUsuario());
							principal.btnReportes.setVisible(false);
							principal.btnAdministrar.setVisible(false);

							principal.setVisible(true);
							dispose();

							// return true;
						} else if (tipoUsuario == 3 && estadoUsuario.equalsIgnoreCase("activo")) {
							// Interfaz de el Consultor
							logger.info("Usuario: " + usuario + " conectado como Consultor en PC: "
									+ captura.obtenerNombrePC());
							SwingUtilities.invokeLater(principal);

							Principal.usuarioActual.setText(getNombreUsuario());
							principal.btnImputaciones.setVisible(false);
							principal.btnAdministrar.setVisible(false);
							principal.setVisible(true);
							dispose();

							// return true;
						} else {
							lblIncorrecto.setVisible(true);
							// lblIncorrecto.setText("El Usuario no esta
							// activo");
							return "El Usuario no esta activo";
						}
					}

					else {
						lblIncorrecto.setVisible(true);
						// lblIncorrecto.setText("Credenciales invalidos!");
						return "Credenciales invalidos!";
					}
				} catch (SQLException sqle) {
					logger.error(sqle.toString(), sqle);
					JOptionPane.showMessageDialog(null, sqle.getMessage(), sqle.getClass().toString(),
							JOptionPane.ERROR_MESSAGE);

				} catch (Exception e) {
					logger.error(e.toString(), e);
					JOptionPane.showMessageDialog(null, e.getMessage(), e.getClass().toString(),
							JOptionPane.ERROR_MESSAGE);
					JOptionPane.showMessageDialog(null, "Se va a cerrar la aplicacion");
					System.exit(0);
					// return false;
				}
				return "";
			}

			@Override
			// Can safely update the GUI from this method.
			protected void process(List<String> chunks) {
				// Here we receive the values that we publish().
				// They may come grouped in chunks.
				// int mostRecentValue = chunks.get(chunks.size()-1);

				lblIncorrecto.setIcon(new ImageIcon(getClass().getResource("/iconos16x16/loading.gif")));
				lblIncorrecto.setText("Comprobando credenciales. Espere...");
				// lblIncorrecto.setText(chunks.toString());
				// countLabel1.setText(Integer.toString(mostRecentValue));
			}

			// Can safely update the GUI from this method.
			@Override
			protected void done() {
				String estado;
				try {
					// Retrieve the return value of doInBackground.
					estado = get();
					lblIncorrecto.setText(estado);
					lblIncorrecto.setIcon(null);
					txtUsuario.setEnabled(true);
					pwdClave.setEnabled(true);
					btnAcceder.setEnabled(true);
					btnAcceder.setVisible(true);

				} catch (InterruptedException e) {
					// This is thrown if the thread's interrupted.
				} catch (ExecutionException e) {
					// This is thrown if we throw an exception
					// from doInBackground.
				} finally {
					// Cierra la conexion
					md.cerrarConexiones();
				}
			}

		};

		worker.execute();
	}

	private String getNombreUsuario() {
		return txtUsuario.getText().toLowerCase();
	}

	private void passwordFieldKeyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {

			validarUsuario();
		}
	}

	private void btnAccederMouseClicked(MouseEvent e) {

		validarUsuario();
	}

	/*
	 * private boolean guardarUsuario() {
	 * 
	 * //Serializa la clase para retener el valor del usuario
	 * captura.nombreUsuario = txtUsuario.getText(); try(FileOutputStream
	 * archivoEntrada = new FileOutputStream(captura.obtenerNombrePC() +".ser");
	 * ObjectOutputStream out = new ObjectOutputStream(archivoEntrada);) {
	 * 
	 * out.writeObject(captura); } catch(IOException e) { return false; } return
	 * true; }
	 */
}