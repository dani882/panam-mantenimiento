package com.cementopanam.jrivera.controlador;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JOptionPane;

import org.apache.log4j.Logger;

public class ComparacionFechas {

	private static final Logger logger = Logger.getLogger(ComparacionFechas.class);
	
	
	/**
	 * Compara las fechas proporcionadas
	 * @param fechaInicio - fecha de inicio a ser comparada
	 * @param fechaFin - fecha de fin a ser comparada
	 * @param formatoFecha - formato de fecha para hacer la comparacion
	 * @return true si la fecha de fin es mayor que la fecha inicio, false si la fecha inicio es mayor que la de fin
	 */
	public boolean compararFechas(String fechaInicio, String fechaFin, String formatoFecha) {

		try {
			/** Obtenemos las fechas enviadas en el formato a comparar */
			SimpleDateFormat formateador = new SimpleDateFormat(formatoFecha);
			Date fechaDate1 = formateador.parse(fechaInicio);
			Date fechaDate2 = formateador.parse(fechaFin);

			if (fechaDate2.before(fechaDate1)) {

				return true;
			}

		} catch (ParseException e) {
			logger.error("La fecha introducida no es valida");
			JOptionPane.showMessageDialog(null, "La fecha introducida no es valida",
					"Fecha invalida", JOptionPane.ERROR_MESSAGE);
		}

		return false;
	}
}