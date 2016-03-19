package com.cementopanam.jrivera.modelo;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.SwingUtilities;

public class Main extends JPanel {

  JProgressBar pbar;

  static int min = 0;

  static int max = 100;

  public Main() {
    pbar = new JProgressBar();
    pbar.setMinimum(min);
    pbar.setMaximum(max);
    add(pbar);
    
    JFrame frame = new JFrame("Progress Bar Example");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setContentPane(this);
    frame.pack();
    frame.setVisible(true);

    for (int i = min; i <= max; i++) {
      final int percent = i;
      try {
        SwingUtilities.invokeLater(new Runnable() {
          public void run() {
            updateBar(percent);
            
          }
        });
        Thread.sleep(50);
      } catch (InterruptedException e) {
      }
    }
    updateBar(0);
  }

  public void updateBar(int newValue) {
    pbar.setValue(newValue);
    pbar.setStringPainted(true);
  }

  public static void main(String args[]) {
    new Main();
  }
}