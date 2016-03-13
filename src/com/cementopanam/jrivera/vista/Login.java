package com.cementopanam.jrivera.vista;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.sql.ResultSet;
import java.sql.SQLException;

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
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import com.cementopanam.jrivera.controlador.CapturaUsuario;
import com.cementopanam.jrivera.controlador.ManipulacionDatos;
import com.cementopanam.jrivera.vista.internalFrames.Imputaciones;

/**
 * @autor Jesus Rivera
 * @version 1.0
 */

public class Login extends JFrame {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1788905256991802788L;
	private ManipulacionDatos md;
	private ResultSet rs = null;

	private JPanel contentPane;
	
	private JTextField txtUsuario;
	private JPasswordField passwordField;
	
	private JLabel lblContrasena;
	private JLabel label;
	private JLabel label_1;
	private JLabel lblIncorrecto;
	
	private JButton btnAcceder;
	
	private JSeparator separator;
	
	private Dimension dim;
	private int w, h, x, y;
	
	CapturaUsuario captura = new CapturaUsuario();

	/**
	 * Lanza la aplicacion.
	 */
	public static void main(String[] args) {
		
	//	PlasticLookAndFeel.setPlasticTheme(new DesertBlue());

	    try {
	   // 	UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
	    	UIManager.setLookAndFeel("com.jtattoo.plaf.acryl.AcrylLookAndFeel");
	    //	UIManager.setLookAndFeel("com.jtattoo.plaf.mint.MintLookAndFeel");
	    	  
	    //	UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");

	    	} catch (Exception e) {}		
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					new Login().setVisible(true);
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, e);
				}
			}
		});
	}

	/**
	 * Crea el frame.
	 */
	public Login() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Login.class.getResource("/imagenes/logo-panam.png")));
		initComponents();
	}
	
	public boolean validarUsuario() {
		
		boolean resultado = false;
	
		String usuario = txtUsuario.getText();
		char[] password = passwordField.getPassword();
		
		String clave = "";
		
		for (char c : password) {
			clave += c;
		}
		md = new ManipulacionDatos();
		Principal p = new Principal();
		
		try {
	    	rs = md.autenticarUsuario(usuario, clave);
	    	//si los datos son correctos, entra al formulario
			if(rs.next()) {
				int tipoUsuario = rs.getInt("id_tipo_usuario");
				if (tipoUsuario == 1) { 
					// Interfaz de Administrador
					System.out.println("Es Administrador");
					guardarUsuario();

					SwingUtilities.invokeLater(p);
					md.cerrarConexiones();
	
					p.usuarioActual.setText(getNombreUsuario().toLowerCase());
					p.setVisible(true);
					dispose();
					return resultado = true;
				}
				else if (tipoUsuario == 2) {
					// Interfaz de el Operador
					System.out.println("Es Operador");
					guardarUsuario();
					
					p.usuarioActual.setText(getNombreUsuario().toLowerCase());
					p.btnReportes.setVisible(false);
					p.btnAdministrar.setVisible(false);
		
					SwingUtilities.invokeLater(p);
					p.setVisible(true);
					dispose();
					return resultado = true;
				}
				else if (tipoUsuario == 3) {
					// Interfaz de el Consultor
					System.out.println("Es Consultor");
					
					SwingUtilities.invokeLater(p);
					
					p.usuarioActual.setText(getNombreUsuario().toLowerCase());
					p.btnImputaciones.setVisible(false);
					p.btnAdministrar.setVisible(false);
					p.setVisible(true);
					dispose();
					return resultado = true;
				}
				else {
					lblIncorrecto.setText("El Usuario no esta en rol");
					return resultado;
				}
			}
			else {
				lblIncorrecto.setVisible(true);
				lblIncorrecto.setText("Credenciales invalidos!");
				return resultado;
			    }
		} catch (SQLException e1) {
			JOptionPane.showMessageDialog(null, e1);
			return resultado;
		}		
	}

	private String getNombreUsuario() {
		return txtUsuario.getText();
	}

	private void initComponents() {
		
		setTitle("Iniciar Session");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(552, 305);
		setResizable(false);
		contentPane = new JPanel();
		
		// Obtiene el tamaño de la pantalla
		dim = Toolkit.getDefaultToolkit().getScreenSize();
				 
		// Determina la nueva localizacion de la ventana
		w = getSize().width;
		h = getSize().height;
		x = (dim.width-w)/2;
		y = (dim.height-h)/2;
				 
		// Centra la ventana
		setLocation(x, y);		
		
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblContrasena = new JLabel("Contrase\u00F1a");
		lblContrasena.setFont(new Font("Verdana", Font.PLAIN, 12));
		lblContrasena.setBounds(267, 118, 103, 21);
		contentPane.add(lblContrasena);
				
		txtUsuario = new JTextField();
		txtUsuario.setBounds(363, 76, 167, 30);
		contentPane.add(txtUsuario);
		txtUsuario.setColumns(10);
				
		label = new JLabel("Usuario");
		label.setFont(new Font("Verdana", Font.PLAIN, 12));
		label.setBounds(267, 80, 63, 21);
		contentPane.add(label);

		passwordField = new JPasswordField();
		passwordField.setEchoChar('*');
		//passwordField.setHint("Contrase\u00F1a");
		passwordField.setBounds(363, 111, 167, 30);
		contentPane.add(passwordField);
		passwordField.addKeyListener(new KeyAdapter() {
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
		
		label_1 = new JLabel("");
		label_1.setIcon(new ImageIcon(Login.class.getResource("/imagenes/logo-panam.png")));
		label_1.setBounds(12, 12, 234, 180);
		contentPane.add(label_1);
		
		lblIncorrecto = new JLabel("");
		lblIncorrecto.setForeground(Color.RED);
		lblIncorrecto.setVisible(false);
		lblIncorrecto.setFont(new Font("Nimbus Roman No9 L", Font.BOLD, 16));
		lblIncorrecto.setBounds(12, 178, 518, 21);
		contentPane.add(lblIncorrecto);

	}

    private void passwordFieldKeyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_ENTER) {

			validarUsuario();
		}
    }
    private void btnAccederMouseClicked(MouseEvent e) {
    
		validarUsuario();
    }
    
    private boolean guardarUsuario() {
    	
    	boolean resultado = false;
		//Serializa la clase para retener el valor del usuario
		captura.nombreUsuario = txtUsuario.getText();
		try(FileOutputStream archivoEntrada = new FileOutputStream(captura.obtenerNombrePC() +".ser");
				ObjectOutputStream out = new ObjectOutputStream(archivoEntrada);) {

	        out.writeObject(captura);
	        resultado = true;
	      } catch(IOException e) {
	    	  return resultado;
	      }
		return resultado;
 
    }
}