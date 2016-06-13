package com.cementopanam.jrivera.vista.helper;

import java.awt.Dimension;

import javax.swing.JComboBox;

public class JComboBoxPersonalizado extends JComboBox<String> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public JComboBoxPersonalizado() {
	}

	private boolean layingOut = false;

	// Sobreescribimos este Metodo :)
	public void doLayout() {
		try {
			layingOut = true;
			super.doLayout();
		} finally {
			layingOut = false;
		}
	}

	// Tambien sobreescribimos este otro metodo
	public Dimension getSize() {
		Dimension dim = super.getSize();
		if (!layingOut)
			dim.width = Math.max(dim.width, getPreferredSize().width);
		return dim;
	}
}
