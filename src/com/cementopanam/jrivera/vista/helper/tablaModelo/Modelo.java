package com.cementopanam.jrivera.vista.helper.tablaModelo;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

import com.cementopanam.jrivera.controlador.Imputacion;

public class Modelo extends AbstractTableModel{

	String[] encabezado = {"Usuario", "Equipo", "SubArea", "Area", "TiempoInicio", "TiempoFinal"};
	
	ArrayList<Imputacion> listado = new ArrayList<Imputacion>();
	
	@Override
	public String getColumnName(int column) {
		// TODO Auto-generated method stub
		return encabezado[column];
	}

	@Override
	public int getColumnCount() {
		return encabezado.length;
	}

	@Override
	public int getRowCount() {
		return listado.size();
	}
	
	/**
	 * Agregar pacientes a la lista
	 * @param el paciente que se va a agregar
	 */
	public void agregar(Imputacion imp) {
		listado.add(imp);
		fireTableDataChanged();
		
	}
	
	/**
	 * Elimina un elemento de la lista
	 * @param la fila donde esta ubicado el elemento que se desee eliminar de la lista
	 */
	public void eliminar(int fila) {
		listado.remove(fila);
		fireTableRowsDeleted(fila, fila);
	}

	@Override
	public Object getValueAt(int row, int column) {
		
		Object retorno = null;
		Imputacion imp = listado.get(row);
		
		switch (column) {
		case 0:
			retorno = imp.getUsuario();
			break;
		case 1:
			retorno = imp.getEquipo();
			break;
		case 2:
			retorno = imp.getSubArea();
			break;
		case 3:
			retorno = imp.getArea();
			break;
		case 4:
			retorno = imp.getTiempoInicio();
			break;
		
		default:
			break;
		}
		
		return retorno;
	}
}