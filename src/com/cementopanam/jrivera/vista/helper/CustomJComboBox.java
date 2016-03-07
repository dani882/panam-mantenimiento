package com.cementopanam.jrivera.vista.helper;

import java.awt.Dimension;

import javax.swing.JComboBox;
  

public class CustomJComboBox extends JComboBox{ 
  
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public CustomJComboBox() { 
    } 
  
    private boolean layingOut = false; 
    // Sobreescribimos este Metodo :)
    public void doLayout(){ 
        try{ 
            layingOut = true; 
            super.doLayout(); 
        }finally{ 
            layingOut = false; 
        } 
    } 
    // Tambien sobreescribimos este otro metodo 
    public Dimension getSize(){ 
        Dimension dim = super.getSize(); 
        if(!layingOut) 
            dim.width = Math.max(dim.width, getPreferredSize().width); 
        return dim; 
    } 
}
