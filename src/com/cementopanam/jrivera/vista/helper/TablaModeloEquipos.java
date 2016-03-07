package com.cementopanam.jrivera.vista.helper;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import com.cementopanam.jrivera.controlador.NombreEquipo;

public class TablaModeloEquipos extends AbstractTableModel {

	String[] columnas = {"Codigo Equipo", "Nombre Equipo"};
	ArrayList<NombreEquipo> lista = new ArrayList<NombreEquipo>();

	public TablaModeloEquipos() {
		lista.add(new NombreEquipo("211.01", "TRANSPORTADOR DE PLACAS"));
	}
	@Override
	public String getColumnName(int x) {
		return columnas[x];
	}

	@Override
	public int getColumnCount() {
		return columnas.length;
	}

	@Override
	public int getRowCount() {
		return lista.size();
	}

	@Override
	public Object getValueAt(int x, int y) {
		
		Object retorno = null;
		NombreEquipo equipo = lista.get(x);
		
		switch (y) {
		case 0:
			retorno = equipo.getCodigo();
			break;
		case 1:
			retorno = equipo.getNombre();
			break;

		default:
			break;
		}

		return retorno;
	}

}
