/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication4;

import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;

/**
 *
 * @author Hemerson
 */
public class HoraMinSeg extends Thread {

    JLabel segundos;
    private int hora;
    private int minuto;
    private int segundo;
    private String tempo;
    private Date date;

    public HoraMinSeg(JLabel segundos) {
        this.segundos = segundos;
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            date = new Date();
            this.tempo = "";
            this.hora = date.getHours();
            if (this.hora < 10) {
                this.tempo = "0" + this.hora;
            } else {
                this.tempo = "" + hora;
            }
            this.minuto = date.getMinutes();
            if (minuto < 10) {
                this.tempo += ":0" + minuto;
            } else {
                this.tempo += ":" + minuto;
            }
            this.segundo = date.getSeconds();
            if (segundo < 10) {
                this.tempo += ":0" + segundo;
            } else {
                this.tempo += ":" + segundo;
            }
            segundos.setText(tempo);
        }
    }
}
