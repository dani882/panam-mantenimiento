package com.cementopanam.jrivera.vista.helper.tablaModelo;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import com.cementopanam.jrivera.controlador.equipo.NombreEquipo;
import com.cementopanam.jrivera.controlador.equipo.SolucionEquipos;

public class TablaModeloEquiposSolucion extends AbstractTableModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5597879599705517993L;
	
	private static final int CODIGO_EQUIPO = 0;
	private static final int NOMBRE_EQUIPO = 1;
	private static final int NOMBRE_AREA = 2;
	private static final int NOMBRE_SUBAREA = 3;
	private static final int SOLUCION = 4;
	
	String[] columnas = { "Codigo Equipo", "Nombre Equipo", "Nombre Area", "Nombre SubArea", "Solucion" };
	
	ArrayList<NombreEquipo> lista = null;
	SolucionEquipos equipos = new SolucionEquipos();

	public TablaModeloEquiposSolucion() {
		lista = equipos.mostrarSoluciones("");
	}

	public void buscarSolucion(String codigo) {
		lista = equipos.mostrarSoluciones(codigo);
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
		case CODIGO_EQUIPO:
			retorno = equipo.getCodigoEquipo();
			break;
		case NOMBRE_EQUIPO:
			retorno = equipo.getNombreEquipo();
			break;
		case NOMBRE_AREA:
			retorno = equipo.getNombreArea();
			break;
		case NOMBRE_SUBAREA:
			retorno = equipo.getNombreSubArea();
			break;
		case SOLUCION:
			retorno = equipo.getSolucion();
			break;
		}

		return retorno;
	}
}