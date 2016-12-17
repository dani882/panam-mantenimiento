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
import javax.swing.border.TitledBorder;

import org.apache.log4j.Logger;

import com.cementopanam.jrivera.controlador.paros.AdministracionParos;
import com.cementopanam.jrivera.controlador.paros.Paro;
import com.cementopanam.jrivera.controlador.usuario.CapturaUsuario;
import com.cementopanam.jrivera.vista.helper.JComboBoxPersonalizado;
import com.cementopanam.jrivera.vista.internalFrames.Reportes;

public class ModificacionParo extends JDialog {

	/**
	 * 
	 */
	private static final Logger logger = Logger.getLogger(ModificacionParo.class);
	private static final long serialVersionUID = -7239938319668117403L;
	private final JPanel contentPanel = new JPanel();
	private JButton btnGuardar;
	private JButton btnCancelar;
	private JLabel lblTiempoInicio;
	private JLabel lblTiempoFin;
	private JTextField txtTiempoInicio;
	private JTextField txtTiempoFin;
	private JTextArea txtDescripcionAdicional;
	private JComboBox<String> cbCausa;
	private JComboBox<String> cbDisciplina;
	private JTextArea txtAreaSolucion;

	private int codigoParo;
	private int codigoCausa;

	private AdministracionParos admParo;
	private JDesktopPane desktopPane;
	private JLabel label;
	private JButton btnBorrar;
	
	private CapturaUsuario captura = new CapturaUsuario();
	private String usuarioLog = Principal.usuarioActual.getText();
	private JComboBox cbEquipo;

	public ModificacionParo(Paro modificacion, AdministracionParos paroDB, JDesktopPane desktopPane) {

		this();
		admParo = paroDB;
		this.desktopPane = desktopPane;

		try {

			// Rellena combo Causa
			ResultSet rs = admParo.rellenarCombo("causa", null);
			while (rs.next()) {
				cbCausa.addItem(rs.getString("tipo_causa"));
			}
			// Rellena combo Disciplina
			rs = admParo.rellenarCombo("disciplina", null);
			while (rs.next()) {
				cbDisciplina.addItem(rs.getString("nombre_disciplina"));
			}

		} catch (SQLException e) {
			logger.error(e.toString(), e);
		}

		codigoParo = modificacion.getCodigo();

		// Obtiene el codigo de Causa
		if (modificacion.getDescripcionAdicional().length() == 0) {

			codigoCausa = admParo.buscarIndiceCausa(modificacion.getCausa(), "");
		} else {
			codigoCausa = admParo.buscarIndiceCausa(modificacion.getCausa(), modificacion.getDescripcionAdicional());
		}

		cbCausa.setSelectedItem(modificacion.getCausa());
		txtAreaSolucion.setText(modificacion.getSolucion());
		txtDescripcionAdicional.setText(modificacion.getDescripcionAdicional());
		txtTiempoInicio.setText(modificacion.getTiempoInicio());
		txtTiempoFin.setText(modificacion.getTiempoFin());
		cbDisciplina.setSelectedItem(modificacion.getDisciplina());
	}

	public ModificacionParo() {
		setResizable(false);

		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {

				mostrarReporte();

				Principal.lblStatusBar.setIcon(new ImageIcon(getClass().getResource("/iconos16x16/warning-icon.png")));
				Principal.lblStatusBar.setText("Operacion cancelada por el usuario");
			}
		});

		getContentPane().setFont(new Font("Verdana", Font.PLAIN, 12));
		setTitle("Modificar Paro");
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
			lblTiempoFin = new JLabel("Tiempo de Fin");
			lblTiempoFin.setHorizontalAlignment(SwingConstants.LEFT);
			lblTiempoFin.setFont(new Font("Verdana", Font.PLAIN, 12));
		}

		txtTiempoInicio = new JTextField();
		txtTiempoInicio.setFont(new Font("Verdana", Font.PLAIN, 12));
		txtTiempoInicio.setColumns(10);

		txtTiempoFin = new JTextField();
		txtTiempoFin.setFont(new Font("Verdana", Font.PLAIN, 12));
		txtTiempoFin.setColumns(10);

		JLabel lblCausa = new JLabel("Causa");
		lblCausa.setHorizontalAlignment(SwingConstants.LEFT);
		lblCausa.setFont(new Font("Verdana", Font.PLAIN, 12));

		cbCausa = new JComboBoxPersonalizado();
		cbCausa.setFont(new Font("Verdana", Font.PLAIN, 12));

		JLabel lblDisciplina = new JLabel("Disciplina");
		lblDisciplina.setFont(new Font("Verdana", Font.PLAIN, 12));

		cbDisciplina = new JComboBoxPersonalizado();
		cbDisciplina.setFont(new Font("Verdana", Font.PLAIN, 12));

		JScrollPane scrollPaneSolucion = new JScrollPane();
		scrollPaneSolucion.setViewportBorder(
				new TitledBorder(null, "Solucion", TitledBorder.LEADING, TitledBorder.TOP, null, null));

		JScrollPane scrollPaneDescripcionAdicional = new JScrollPane();
		scrollPaneDescripcionAdicional.setViewportBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"),
				"Descripcion Adicional", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		
		JLabel lblCodigoEquipo = new JLabel("Codigo Equipo");
		lblCodigoEquipo.setFont(new Font("Verdana", Font.PLAIN, 12));
		
		cbEquipo = new JComboBox();
		cbEquipo.setEnabled(false);
		cbEquipo.setFont(new Font("Verdana", Font.PLAIN, 12));
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
		    gl_contentPanel.createParallelGroup(Alignment.LEADING)
		        .addGroup(gl_contentPanel.createSequentialGroup()
		            .addContainerGap()
		            .addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
		                .addGroup(gl_contentPanel.createSequentialGroup()
		                    .addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
		                        .addComponent(lblTiempoInicio)
		                        .addComponent(lblDisciplina)
		                        .addComponent(lblCodigoEquipo))
		                    .addPreferredGap(ComponentPlacement.UNRELATED)
		                    .addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
		                        .addComponent(cbEquipo, 0, 184, Short.MAX_VALUE)
		                        .addComponent(cbDisciplina, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 184, Short.MAX_VALUE)
		                        .addComponent(txtTiempoInicio, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 184, Short.MAX_VALUE))
		                    .addGap(18)
		                    .addGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING, false)
		                        .addComponent(lblCausa, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
		                        .addComponent(lblTiempoFin))
		                    .addPreferredGap(ComponentPlacement.UNRELATED)
		                    .addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
		                        .addComponent(txtTiempoFin, GroupLayout.DEFAULT_SIZE, 221, Short.MAX_VALUE)
		                        .addComponent(cbCausa, 0, 221, Short.MAX_VALUE))
		                    .addGap(8))
		                .addGroup(gl_contentPanel.createSequentialGroup()
		                    .addComponent(scrollPaneSolucion, GroupLayout.DEFAULT_SIZE, 294, Short.MAX_VALUE)
		                    .addGap(18)
		                    .addComponent(scrollPaneDescripcionAdicional, GroupLayout.DEFAULT_SIZE, 318, Short.MAX_VALUE)
		                    .addContainerGap())))
		);
		gl_contentPanel.setVerticalGroup(
		    gl_contentPanel.createParallelGroup(Alignment.LEADING)
		        .addGroup(gl_contentPanel.createSequentialGroup()
		            .addContainerGap()
		            .addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
		                .addComponent(cbEquipo, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
		                .addComponent(lblCodigoEquipo))
		            .addGap(18)
		            .addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
		                .addGroup(gl_contentPanel.createSequentialGroup()
		                    .addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
		                        .addComponent(lblTiempoInicio)
		                        .addComponent(txtTiempoInicio, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE))
		                    .addPreferredGap(ComponentPlacement.UNRELATED)
		                    .addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
		                        .addComponent(cbDisciplina, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
		                        .addComponent(lblDisciplina)))
		                .addGroup(gl_contentPanel.createSequentialGroup()
		                    .addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
		                        .addComponent(txtTiempoFin, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
		                        .addComponent(lblTiempoFin))
		                    .addPreferredGap(ComponentPlacement.UNRELATED)
		                    .addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
		                        .addComponent(cbCausa, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
		                        .addComponent(lblCausa))))
		            .addPreferredGap(ComponentPlacement.RELATED, 47, Short.MAX_VALUE)
		            .addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
		                .addComponent(scrollPaneSolucion, GroupLayout.PREFERRED_SIZE, 137, GroupLayout.PREFERRED_SIZE)
		                .addComponent(scrollPaneDescripcionAdicional, GroupLayout.PREFERRED_SIZE, 137, GroupLayout.PREFERRED_SIZE))
		            .addContainerGap())
		);

		txtDescripcionAdicional = new JTextArea();
		txtDescripcionAdicional.setFont(new Font("Verdana", Font.PLAIN, 12));
		scrollPaneDescripcionAdicional.setViewportView(txtDescripcionAdicional);

		txtAreaSolucion = new JTextArea();
		txtAreaSolucion.setFont(new Font("Verdana", Font.PLAIN, 12));
		scrollPaneSolucion.setViewportView(txtAreaSolucion);
		contentPanel.setLayout(gl_contentPanel);
		{
			JPanel buttonPaneBotones = new JPanel();
			buttonPaneBotones.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPaneBotones, BorderLayout.SOUTH);
			{
				btnGuardar = new JButton("Guardar");
				btnGuardar.setIcon(new ImageIcon(ModificacionParo.class.getResource("/iconos32x32/ok32x32.png")));
				btnGuardar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {

						actualizarParo();

					}

				});
				btnGuardar.setFont(new Font("Verdana", Font.PLAIN, 12));
				btnGuardar.setActionCommand("OK");
				buttonPaneBotones.add(btnGuardar);
				getRootPane().setDefaultButton(btnGuardar);
			}

			{
				btnCancelar = new JButton("Cancelar");
				btnCancelar.setIcon(new ImageIcon(ModificacionParo.class.getResource("/iconos32x32/fail32x32.png")));
				btnCancelar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {

						mostrarReporte();

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
				btnBorrar.setIcon(new ImageIcon(ModificacionParo.class.getResource("/iconos32x32/delete32x32.png")));
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

		// Coloca el boton de OptonPane en Español
		UIManager.put("OptionPane.yesButtonText", "Si");
		int respuesta = JOptionPane.showConfirmDialog(null, "¿Esta seguro que desea borrar este paro?",
				"Confirmar Borrado", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

		if (respuesta == JOptionPane.NO_OPTION) {
			return;
		} else if (respuesta == JOptionPane.YES_OPTION) {

			try {

				if (admParo.eliminarParo(idCausa) == true) {

					Principal.lblStatusBar.setIcon(new ImageIcon(getClass().getResource("/iconos16x16/ok.png")));
					Principal.lblStatusBar.setText("Paro Eliminado correctamente");

					mostrarReporte();
				} else {
					Principal.lblStatusBar
							.setIcon(new ImageIcon(getClass().getResource("/iconos16x16/warning-icon.png")));
					Principal.lblStatusBar.setText("No se pudo completar la operacion");
				}
			} catch (SQLException e) {
				logger.error(e.toString(), e);
			}
		} else if (respuesta == JOptionPane.CLOSED_OPTION) {
			return;
		}
	}

	/**
	 * Retorna a la interfaz de reporte
	 */
	private void mostrarReporte() {

		Reportes repo = new Reportes();
		desktopPane.add(repo);
		repo.toFront();
		repo.setVisible(true);
		dispose();
	}

	/**
	 * Modifica la informacion de los Paros
	 */
	private void actualizarParo() {

		String codigoEquipo = String.valueOf(cbEquipo.getSelectedItem());
		String tiempoInicio = txtTiempoInicio.getText();
		String disciplina = String.valueOf(cbDisciplina.getSelectedItem());
		String tiempoFin = txtTiempoFin.getText();
		String causa = String.valueOf(cbCausa.getSelectedItem());
		String solucion = txtAreaSolucion.getText();
		String descripcionAdicional = txtDescripcionAdicional.getText();

		// Si el paro fue exitoso
		try {
			if (admParo.modificarParo(
					new Paro(codigoParo, tiempoInicio, tiempoFin, solucion, causa, descripcionAdicional, disciplina),
					codigoCausa) == true) {

				Principal.lblStatusBar.setIcon(new ImageIcon(getClass().getResource("/iconos16x16/ok.png")));
				Principal.lblStatusBar.setText("Paro Actualizado correctamente");
				
				// Guarda la accion en un archivo log
				usuarioLog = Principal.usuarioActual.getText();
				logger.info("Usuario: " + usuarioLog + " conectado en PC: " + captura.obtenerNombrePC()
						+ " actualizo paro correctamente");

				mostrarReporte();
			} else {
				Principal.lblStatusBar.setIcon(new ImageIcon(getClass().getResource("/iconos16x16/warning-icon.png")));
				Principal.lblStatusBar.setText("No se pudo completar la operacion");
			}
		} catch (SQLException e) {
			logger.error(e.toString(), e);
		}
	}
}