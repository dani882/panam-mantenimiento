package com.cementopanam.jrivera.vista.helper.tablaModelo;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import com.cementopanam.jrivera.controlador.listadoEquipos.ListadoEquipos;
import com.cementopanam.jrivera.controlador.listadoEquipos.NombreEquipo;

public class TablaModeloEquipos extends AbstractTableModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5597879599705517993L;
	String[] columnas = {"Codigo Equipo", "Nombre Equipo"};
	ArrayList<NombreEquipo> lista = null;
	ListadoEquipos equipos = new ListadoEquipos();

	public TablaModeloEquipos() {
		lista = equipos.mostrarEquipos("");
	}
	
	public void eliminar(int fila) {
		lista.remove(fila);
		fireTableRowsDeleted(fila, fila);
	}
	
	public void agregar(NombreEquipo equipo) {
		lista.add(equipo);
		fireTableDataChanged();
	}
	
	public void buscar(String codigo) {
		lista = equipos.mostrarEquipos(codigo);
		fireTableDataChanged();
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
