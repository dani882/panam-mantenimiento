package com.cementopanam.jrivera.controlador;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JOptionPane;

public class ComparacionFechas {

	public boolean compararFechas(String fechaInicio, String fechaFin, String formatoFecha) {

		boolean resultado = false;

		try {
			/** Obtenemos las fechas enviadas en el formato a comparar */
			SimpleDateFormat formateador = new SimpleDateFormat(formatoFecha);
			Date fechaDate1 = formateador.parse(fechaInicio);
			Date fechaDate2 = formateador.parse(fechaFin);

			if (fechaDate2.before(fechaDate1)) {

				resultado = true;
			}

		} catch (ParseException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), e.getClass().toString(), JOptionPane.ERROR_MESSAGE);
		}
		return resultado;
	}
}