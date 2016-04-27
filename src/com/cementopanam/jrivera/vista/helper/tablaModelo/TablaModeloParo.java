package com.cementopanam.jrivera.vista.helper.tablaModelo;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import com.cementopanam.jrivera.controlador.paros.AdministracionParos;
import com.cementopanam.jrivera.controlador.paros.Paro;

public class TablaModeloParo extends AbstractTableModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5597879599705517993L;

	private static final int CODIGO = 0;
	private static final int USUARIO = 1;
	private static final int AREA = 2;
	private static final int SUBAREA = 3;
	private static final int EQUIPO = 4;
	private static final int DISCIPLINA = 5;
	private static final int CAUSA = 6;
	private static final int DESCRIPCION_ADICIONAL = 7;
	private static final int TIEMPO_INICIO = 8;
	private static final int TIEMPO_FIN = 9;
	private static final int SOLUCION = 10;

	String[] columnas = { "Codigo", "Usuario", "Area", "SubArea", "Equipo", "Disciplina", "Causa",
			"Descripcion Adicional", "Tiempo de Inicio", "Tiempo de Fin", "Solucion" };

	ArrayList<Paro> lista = null;
	AdministracionParos paros = new AdministracionParos();

	public TablaModeloParo() {
		lista = paros.mostrarParo(new Paro(), "");
	}

	public void eliminar(int fila) {
		lista.remove(fila);
		fireTableRowsDeleted(fila, fila);
	}

	public void agregar(Paro paro) {
		lista.add(paro);
		fireTableDataChanged();
	}

	public void buscar(Paro paro, String filtro) {
		 lista = paros.mostrarParo(paro, filtro);
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
		Paro paro = lista.get(x);

		switch (y) {
		case CODIGO:
			retorno = paro.getCodigo();
			break;
		case USUARIO:
			retorno = paro.getUsuario();
			break;
		case AREA:
			retorno = paro.getArea();
			break;
		case SUBAREA:
			retorno = paro.getSubArea();
			break;
		case EQUIPO:
			retorno = paro.getEquipo();
			break;
		case DISCIPLINA:
			retorno = paro.getDisciplina();
			break;
		case CAUSA:
			retorno = paro.getCausa();
			break;
		case DESCRIPCION_ADICIONAL:
			retorno = paro.getDescripcionAdicional();
			break;
		case TIEMPO_INICIO:
			retorno = paro.getTiempoInicio();
			break;
		case TIEMPO_FIN:
			retorno = paro.getTiempoFin();
			break;
		case SOLUCION:
			retorno = paro.getSolucion();
			break;
		}

		return retorno;
	}
}
