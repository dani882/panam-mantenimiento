package com.cementopanam.jrivera.vista.helper;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import javax.swing.JLabel;
import javax.swing.SwingUtilities;

public class TimerThread extends Thread {

	protected boolean isRunning;

	protected JLabel lblFecha;
	protected JLabel lblTiempo;

	protected SimpleDateFormat formatoFecha = new SimpleDateFormat("EEE, dd 'de' MMMM 'de' yyyy", new Locale("ES"));
	protected SimpleDateFormat formatoTiempo = new SimpleDateFormat("h:mm:ss a");

	public TimerThread(JLabel dateLabel, JLabel timeLabel) {
		this.lblFecha = dateLabel;
		this.lblTiempo = timeLabel;
		this.isRunning = true;
	}

	// TODO Agregar barra de progreso

	@Override
	public void run() {
		while (isRunning) {
			SwingUtilities.invokeLater(new Runnable() {
				@Override
				public void run() {
					Calendar currentCalendar = Calendar.getInstance();
					Date currentTime = currentCalendar.getTime();

					lblFecha.setText(formatoFecha.format(currentTime));
					lblTiempo.setText(formatoTiempo.format(currentTime));
				}
			});

			try {
				Thread.sleep(1000L);
			} catch (InterruptedException e) {
			}
		}
	}

	public void setRunning(boolean isRunning) {
		this.isRunning = isRunning;
	}
}