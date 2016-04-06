package com.cementopanam.jrivera.vista.internalFrames;

import java.awt.Font;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

public class AdministracionRegistros extends JInternalFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7802198920491306605L;
	private JTextField txtNombreSubArea;
	private JTextField txtArea;
	private JComboBox cbSubArea;
	private JComboBox cbAreaPertenece;
	private JTextField txtNombreEquipo;
	private JTextField txtCausa;
	private JTextField txtDisciplina;
	private JTextField txtCodEquipo;

	/**
	 * Create the frame.
	 */
	public AdministracionRegistros() {
		getContentPane().setFont(new Font("Verdana", Font.PLAIN, 12));
		setIconifiable(true);
		setClosable(true);
		setTitle("Administracion de Registros");
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 10, 545, 631);

		JPanel panelSubArea = new JPanel();
		panelSubArea.setBounds(280, 22, 238, 164);
		panelSubArea.setBorder(new TitledBorder(null, "SubArea", TitledBorder.LEADING, TitledBorder.TOP, null, null));

		JPanel panelArea = new JPanel();
		panelArea.setBounds(10, 22, 238, 164);
		panelArea.setBorder(new TitledBorder(null, "Area", TitledBorder.LEADING, TitledBorder.TOP, null, null));

		JPanel panelEquipo = new JPanel();
		panelEquipo.setBounds(10, 195, 508, 181);
		panelEquipo.setBorder(new TitledBorder(null, "Equipo", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelEquipo.setLayout(null);

		JLabel lblCodEquipo = new JLabel("Codigo de Equipo");
		lblCodEquipo.setFont(new Font("Verdana", Font.PLAIN, 12));
		lblCodEquipo.setBounds(20, 23, 191, 23);
		panelEquipo.add(lblCodEquipo);

		JLabel lblSubArea = new JLabel("SubArea a que pertenece");
		lblSubArea.setFont(new Font("Verdana", Font.PLAIN, 12));
		lblSubArea.setBounds(20, 89, 191, 23);
		panelEquipo.add(lblSubArea);

		cbSubArea = new JComboBox();
		cbSubArea.setFont(new Font("Verdana", Font.PLAIN, 12));
		cbSubArea.setBounds(20, 116, 459, 33);
		panelEquipo.add(cbSubArea);

		JLabel lblNombreDeEquipo = new JLabel("Nombre de Equipo");
		lblNombreDeEquipo.setFont(new Font("Verdana", Font.PLAIN, 12));
		lblNombreDeEquipo.setBounds(280, 23, 191, 23);
		panelEquipo.add(lblNombreDeEquipo);

		txtNombreEquipo = new JTextField();
		txtNombreEquipo.setFont(new Font("Verdana", Font.PLAIN, 12));
		txtNombreEquipo.setColumns(10);
		txtNombreEquipo.setBounds(280, 49, 199, 30);
		panelEquipo.add(txtNombreEquipo);
		panelArea.setLayout(null);

		JLabel lblArea = new JLabel("Nombre del Area");
		lblArea.setBounds(20, 26, 105, 16);
		panelArea.add(lblArea);
		lblArea.setFont(new Font("Verdana", Font.PLAIN, 12));

		txtArea = new JTextField();
		txtArea.setFont(new Font("Verdana", Font.PLAIN, 12));
		txtArea.setColumns(10);
		txtArea.setBounds(20, 53, 191, 30);
		panelArea.add(txtArea);
		panelSubArea.setLayout(null);

		JLabel lblAreaPertenece = new JLabel("Area a que pertenece");
		lblAreaPertenece.setBounds(20, 93, 137, 16);
		panelSubArea.add(lblAreaPertenece);
		lblAreaPertenece.setFont(new Font("Verdana", Font.PLAIN, 12));

		JLabel lblNombreSubArea = new JLabel("Nombre de SubArea");
		lblNombreSubArea.setBounds(20, 26, 191, 23);
		panelSubArea.add(lblNombreSubArea);
		lblNombreSubArea.setFont(new Font("Verdana", Font.PLAIN, 12));

		cbAreaPertenece = new JComboBox();
		cbAreaPertenece.setBounds(20, 120, 191, 33);
		panelSubArea.add(cbAreaPertenece);
		cbAreaPertenece.setFont(new Font("Verdana", Font.PLAIN, 12));

		txtNombreSubArea = new JTextField();
		txtNombreSubArea.setBounds(20, 52, 191, 30);
		panelSubArea.add(txtNombreSubArea);
		txtNombreSubArea.setFont(new Font("Verdana", Font.PLAIN, 12));
		txtNombreSubArea.setColumns(10);
		getContentPane().setLayout(null);
		getContentPane().add(panelEquipo);
		
		txtCodEquipo = new JTextField();
		txtCodEquipo.setFont(new Font("Verdana", Font.PLAIN, 12));
		txtCodEquipo.setColumns(10);
		txtCodEquipo.setBounds(20, 49, 191, 30);
		panelEquipo.add(txtCodEquipo);
		getContentPane().add(panelArea);
		getContentPane().add(panelSubArea);
		
		JPanel panelCausa = new JPanel();
		panelCausa.setBorder(new TitledBorder(null, "Causa y Disciplina", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelCausa.setBounds(10, 401, 508, 173);
		getContentPane().add(panelCausa);
		panelCausa.setLayout(null);
		
		JLabel lblCausa = new JLabel("Causa");
		lblCausa.setFont(new Font("Verdana", Font.PLAIN, 12));
		lblCausa.setBounds(20, 97, 46, 24);
		panelCausa.add(lblCausa);
		
		txtCausa = new JTextField();
		txtCausa.setFont(new Font("Verdana", Font.PLAIN, 12));
		txtCausa.setColumns(10);
		txtCausa.setBounds(20, 122, 274, 30);
		panelCausa.add(txtCausa);
		
		JLabel lblDisciplina = new JLabel("Disciplina");
		lblDisciplina.setFont(new Font("Verdana", Font.PLAIN, 12));
		lblDisciplina.setBounds(20, 31, 69, 24);
		panelCausa.add(lblDisciplina);
		
		txtDisciplina = new JTextField();
		txtDisciplina.setFont(new Font("Verdana", Font.PLAIN, 12));
		txtDisciplina.setColumns(10);
		txtDisciplina.setBounds(20, 56, 191, 30);
		panelCausa.add(txtDisciplina);

	}
}
