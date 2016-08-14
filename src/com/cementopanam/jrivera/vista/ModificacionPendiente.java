package com.cementopanam.jrivera.vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDesktopPane;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import javax.swing.border.TitledBorder;

import org.apache.log4j.Logger;

import com.cementopanam.jrivera.controlador.paros.AdministracionParos;
import com.cementopanam.jrivera.controlador.paros.Paro;
import com.cementopanam.jrivera.controlador.usuario.CapturaUsuario;
import com.cementopanam.jrivera.vista.helper.JComboBoxPersonalizado;
import com.cementopanam.jrivera.vista.internalFrames.Imputaciones;

public class ModificacionPendiente extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9176940230032185832L;
	private static final Logger logger = Logger.getLogger(ModificacionPendiente.class);
	private final JPanel contentPanel = new JPanel();
	private JButton btnGuardar;
	private JButton btnCancelar;
	private JLabel lblTiempoInicio;
	private JLabel lblEquipo;
	private JTextField txtTiempoInicio;
	private JTextArea txtDescripcionAdicional;
	private JComboBox<String> cbCausa;
	private JComboBox<String> cbDisciplina;

	private int codigoParo;
	private int codigoCausa;
	private int codigoEquipo;

	private ResultSet informacionPendiente = null;

	private AdministracionParos admParo;
	private JDesktopPane desktopPane;
	private JLabel label;
	private JButton btnBorrar;
	private JComboBox<String> cbEquipo;

	private CapturaUsuario captura = new CapturaUsuario();
	private String usuarioLog = Principal.usuarioActual.getText();

	public ModificacionPendiente(int codigoParo, String subArea, JDesktopPane desktopPane) throws SQLException {

		this();
		this.desktopPane = desktopPane;
		admParo = new AdministracionParos();

		try {

			// Rellena combo Disciplina
			ResultSet rs = admParo.rellenarCombo("disciplina", null);
			while (rs.next()) {
				cbDisciplina.addItem(rs.getString("nombre_disciplina"));
			}
			// Rellena combo Causa
			rs = admParo.rellenarCombo("causa", null);
			while (rs.next()) {
				cbCausa.addItem(rs.getString("tipo_causa"));
			}

			// Rellena combo Equipo
			rs = admParo.rellenarCombo("equipo", subArea);
			while (rs.next()) {
				cbEquipo.addItem(rs.getString("cod_equipo"));
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

		this.codigoParo = codigoParo;

		try {
			informacionPendiente = admParo.mostrarDetallePendiente(codigoParo);
			while (informacionPendiente.next()) {

				cbCausa.setSelectedItem(informacionPendiente.getString("Causa"));
				txtDescripcionAdicional.setText(informacionPendiente.getString("Descripcion Adicional"));
				txtTiempoInicio.setText(informacionPendiente.getString("Tiempo de Inicio"));
				cbDisciplina.setSelectedItem(informacionPendiente.getString("Disciplina"));
				cbEquipo.setSelectedItem(informacionPendiente.getString("Equipo"));

			}

		} catch (SQLException sqle) {
			logger.error("Usuario: " + usuarioLog + " en PC: " + captura.obtenerNombrePC() + ". Exception: "
					+ sqle.toString());
			JOptionPane.showMessageDialog(null, sqle.getMessage(), sqle.getClass().toString(),
					JOptionPane.ERROR_MESSAGE);

		} finally {
			// Obtiene el codigo de Causa
			codigoCausa = admParo.buscarIndiceCausa(String.valueOf(cbCausa.getSelectedItem()),
					txtDescripcionAdicional.getText());

			codigoEquipo = admParo.buscarIndice(String.valueOf(cbEquipo.getSelectedItem()), "equipo");
			admParo.cerrarConexiones();
		}
	}

	public ModificacionPendiente() {
		setResizable(false);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {

				mostrarImputacion();

				Principal.lblStatusBar.setIcon(new ImageIcon(getClass().getResource("/iconos16x16/warning-icon.png")));
				Principal.lblStatusBar.setText("Operacion cancelada por el usuario");
			}
		});

		getContentPane().setFont(new Font("Verdana", Font.PLAIN, 12));
		setTitle("Detalle de Paro Pendiente");
		setModal(true);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setFont(new Font("Verdana", Font.PLAIN, 12));
		setBounds(100, 100, 666, 421);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.NORTH);
		{
			lblTiempoInicio = new JLabel("Tiempo de Inicio");
			lblTiempoInicio.setFont(new Font("Verdana", Font.PLAIN, 12));
		}
		{
			lblEquipo = new JLabel("Equipo");
			lblEquipo.setHorizontalAlignment(SwingConstants.LEFT);
			lblEquipo.setFont(new Font("Verdana", Font.PLAIN, 12));
		}

		txtTiempoInicio = new JTextField();
		txtTiempoInicio.setFont(new Font("Verdana", Font.PLAIN, 12));
		txtTiempoInicio.setColumns(10);

		JLabel lblCausa = new JLabel("Causa");
		lblCausa.setHorizontalAlignment(SwingConstants.LEFT);
		lblCausa.setFont(new Font("Verdana", Font.PLAIN, 12));

		cbCausa = new JComboBoxPersonalizado();
		cbCausa.setFont(new Font("Verdana", Font.PLAIN, 12));

		JLabel lblDisciplina = new JLabel("Disciplina");
		lblDisciplina.setFont(new Font("Verdana", Font.PLAIN, 12));

		cbDisciplina = new JComboBoxPersonalizado();
		cbDisciplina.setFont(new Font("Verdana", Font.PLAIN, 12));

		JScrollPane scrollPaneDescripcionAdicional = new JScrollPane();
		scrollPaneDescripcionAdicional.setViewportBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"),
				"Descripcion de Causa", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));

		cbEquipo = new JComboBox<String>();
		cbEquipo.setFont(new Font("Verdana", Font.PLAIN, 12));
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPanel.createSequentialGroup()
						.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPanel.createSequentialGroup()
										.addContainerGap().addComponent(scrollPaneDescripcionAdicional,
												GroupLayout.DEFAULT_SIZE, 620, Short.MAX_VALUE))
								.addGroup(gl_contentPanel.createSequentialGroup().addGap(8)
										.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
												.addComponent(lblTiempoInicio).addComponent(lblDisciplina))
										.addPreferredGap(ComponentPlacement.UNRELATED)
										.addGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING)
												.addComponent(cbDisciplina, GroupLayout.DEFAULT_SIZE, 179,
														Short.MAX_VALUE)
												.addComponent(txtTiempoInicio, GroupLayout.DEFAULT_SIZE, 179,
														Short.MAX_VALUE))
										.addGap(18)
										.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
												.addGroup(gl_contentPanel.createSequentialGroup()
														.addComponent(lblCausa, GroupLayout.DEFAULT_SIZE, 64,
																Short.MAX_VALUE)
														.addPreferredGap(ComponentPlacement.UNRELATED))
												.addGroup(gl_contentPanel.createSequentialGroup()
														.addComponent(lblEquipo).addGap(31)))
										.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
												.addComponent(cbCausa, 0, 239, Short.MAX_VALUE)
												.addComponent(cbEquipo, 0, 239, Short.MAX_VALUE))))
						.addContainerGap()));
		gl_contentPanel.setVerticalGroup(
				gl_contentPanel.createParallelGroup(Alignment.LEADING).addGroup(gl_contentPanel.createSequentialGroup()
						.addGap(38).addGroup(gl_contentPanel
								.createParallelGroup(Alignment.LEADING).addGroup(gl_contentPanel.createSequentialGroup()
										.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
												.addComponent(lblTiempoInicio).addComponent(txtTiempoInicio,
														GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE))
										.addPreferredGap(ComponentPlacement.UNRELATED)
										.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
												.addComponent(cbDisciplina, GroupLayout.PREFERRED_SIZE, 31,
														GroupLayout.PREFERRED_SIZE)
												.addComponent(lblDisciplina)
												.addComponent(cbCausa, GroupLayout.PREFERRED_SIZE, 31,
														GroupLayout.PREFERRED_SIZE)
												.addComponent(lblCausa)))
								.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
										.addComponent(lblEquipo).addComponent(cbEquipo, GroupLayout.PREFERRED_SIZE, 31,
												GroupLayout.PREFERRED_SIZE)))
						.addGap(18)
						.addComponent(scrollPaneDescripcionAdicional, GroupLayout.DEFAULT_SIZE, 187, Short.MAX_VALUE)
						.addContainerGap()));

		txtDescripcionAdicional = new JTextArea();
		txtDescripcionAdicional.setFont(new Font("Verdana", Font.PLAIN, 12));
		scrollPaneDescripcionAdicional.setViewportView(txtDescripcionAdicional);
		contentPanel.setLayout(gl_contentPanel);
		{
			JPanel buttonPaneBotones = new JPanel();
			buttonPaneBotones.setBorder(new MatteBorder(1, 0, 0, 0, (Color) new Color(0, 0, 0)));
			buttonPaneBotones.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPaneBotones, BorderLayout.SOUTH);
			{
				btnGuardar = new JButton("Guardar");
				btnGuardar.setEnabled(false);
				btnGuardar.setIcon(new ImageIcon(ModificacionPendiente.class.getResource("/iconos32x32/ok32x32.png")));
				btnGuardar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {

						actualizarImputacion();

					}

				});
				btnGuardar.setFont(new Font("Verdana", Font.PLAIN, 12));
				btnGuardar.setActionCommand("OK");
				buttonPaneBotones.add(btnGuardar);
				getRootPane().setDefaultButton(btnGuardar);
			}

			{
				btnCancelar = new JButton("Cancelar");
				btnCancelar
						.setIcon(new ImageIcon(ModificacionPendiente.class.getResource("/iconos32x32/fail32x32.png")));
				btnCancelar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {

						mostrarImputacion();

						Principal.lblStatusBar
								.setIcon(new ImageIcon(getClass().getResource("/iconos16x16/warning-icon.png")));
						Principal.lblStatusBar.setText("Operacion cancelada por el usuario");
					}
				});

				btnBorrar = new JButton("Borrar");
				btnBorrar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						eliminarParo(codigoCausa);
					}
				});
				btnBorrar.setIcon(
						new ImageIcon(ModificacionPendiente.class.getResource("/iconos32x32/delete32x32.png")));
				btnBorrar.setFont(new Font("Verdana", Font.PLAIN, 12));
				buttonPaneBotones.add(btnBorrar);
				btnCancelar.setFont(new Font("Verdana", Font.PLAIN, 12));
				btnCancelar.setActionCommand("Cancel");
				buttonPaneBotones.add(btnCancelar);
			}

			label = new JLabel(" ");
			label.setFont(new Font("Verdana", Font.PLAIN, 12));
			buttonPaneBotones.add(label);
		}
	}

	/**
	 * Elimina el paro seleccionado
	 * 
	 * @param idCausa
	 *            - codigo del paro que se eliminara
	 */
	private void eliminarParo(int idCausa) {

		System.out.println("El codigo de la Causa desde el metodo eliminar es: " + codigoCausa);
		// Coloca el boton de OptonPane en Español
		UIManager.put("OptionPane.yesButtonText", "Si");
		int respuesta = JOptionPane.showConfirmDialog(null, "¿Esta seguro que desea borrar este paro?",
				"Confirmar Borrado", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

		if (respuesta == JOptionPane.NO_OPTION) {
			return;
		} else if (respuesta == JOptionPane.YES_OPTION) {

			try {

				idCausa = codigoCausa;
				if (admParo.eliminarParo(idCausa) == true) {

					Principal.lblStatusBar.setIcon(new ImageIcon(getClass().getResource("/iconos16x16/ok.png")));
					Principal.lblStatusBar.setText("Paro Eliminado correctamente");

					// Guarda la accion en un archivo log
					usuarioLog = Principal.usuarioActual.getText();
					logger.info("Usuario: " + usuarioLog + " conectado en PC: " + captura.obtenerNombrePC()
							+ " elimino paro correctamente");

					mostrarImputacion();
				} else {
					Principal.lblStatusBar
							.setIcon(new ImageIcon(getClass().getResource("/iconos16x16/warning-icon.png")));
					Principal.lblStatusBar.setText("No se pudo completar la operacion");
				}
			} catch (SQLException sqle) {
				logger.error("Usuario: " + usuarioLog + " en PC: " + captura.obtenerNombrePC() + ". Exception: "
						+ sqle.toString());
				JOptionPane.showMessageDialog(null, sqle.getMessage(), sqle.getClass().toString(),
						JOptionPane.ERROR_MESSAGE);

			} catch (Exception e) {
				logger.error("Usuario: " + usuarioLog + " en PC: " + captura.obtenerNombrePC() + ". Exception: "
						+ e.toString());
				JOptionPane.showMessageDialog(null, e.getMessage(), e.getClass().toString(), JOptionPane.ERROR_MESSAGE);

			}
		} else if (respuesta == JOptionPane.CLOSED_OPTION) {
			return;
		}
	}

	/**
	 * Retorna a la interfaz de Imputaciones
	 */
	private void mostrarImputacion() {

		Imputaciones imp = new Imputaciones();
		imp.mostrarPendientes();
		desktopPane.add(imp);
		imp.setVisible(true);
		dispose();
	}

	/**
	 * Modifica la informacion de los Paros
	 */
	private void actualizarImputacion() {

		String tiempoInicio = txtTiempoInicio.getText();
		String disciplina = String.valueOf(cbDisciplina.getSelectedItem());
		String causa = String.valueOf(cbCausa.getSelectedItem());
		String equipo = String.valueOf(cbEquipo.getSelectedItem());
		String descripcionAdicional = txtDescripcionAdicional.getText();

		// Si el paro fue exitoso
		try {
			Paro paro = new Paro();

			paro.setCodigo(codigoParo);
			paro.setTiempoInicio(tiempoInicio);
			paro.setDisciplina(disciplina);
			paro.setCausa(causa);
			paro.setEquipo(equipo);
			paro.setDescripcionAdicional(descripcionAdicional);

			if (admParo.modificarParoPendiente(paro, codigoCausa) == true) {

				Principal.lblStatusBar.setIcon(new ImageIcon(getClass().getResource("/iconos16x16/ok.png")));
				Principal.lblStatusBar.setText("Paro Actualizado correctamente");

				// Guarda la accion en un archivo log
				usuarioLog = Principal.usuarioActual.getText();
				logger.info("Usuario: " + usuarioLog + " conectado en PC: " + captura.obtenerNombrePC()
						+ " actualizo paro correctamente");

				mostrarImputacion();

			} else {
				Principal.lblStatusBar.setIcon(new ImageIcon(getClass().getResource("/iconos16x16/warning-icon.png")));
				Principal.lblStatusBar.setText("No se pudo completar la operacion");
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
	}
}