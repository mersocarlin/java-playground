/*
 * PrintableJPanel.java
 *
 * Created on 28 de Agosto de 2008, 15:45
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package imprime;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.IOException;
import java.io.Serializable;
import javax.swing.JPanel;
import javax.swing.RepaintManager;

/**
 *
 * @author apfredrich
 */
class PrintableJPanel extends JPanel implements Printable, Serializable {
 
    public PrintableJPanel() throws IOException{
    }
 
    public int print(Graphics g, PageFormat pf, int pageIndex) throws PrinterException {
        Graphics2D g2 = (Graphics2D)g;
        g2.setColor(Color.black);    //set default foreground color to black
 
        RepaintManager.currentManager(this).setDoubleBufferingEnabled(false);
        Dimension d = this.getSize();    //get size of document
        double panelWidth  = d.width;    //width in pixels
        double panelHeight = d.height;   //height in pixels
        double pageHeight = pf.getImageableHeight();   //height of printer page
        double pageWidth  = pf.getImageableWidth();    //width of printer page
        double scale = pageWidth/panelWidth;
        int totalNumPages = (int)Math.ceil(scale * panelHeight / pageHeight);
 
        if(pageIndex >= totalNumPages) {
            return Printable.NO_SUCH_PAGE;
        }
        g2.translate(pf.getImageableX(), pf.getImageableY());
        g2.translate(0f, -pageIndex*pageHeight);
        g2.scale(scale, scale);
        this.paint(g2);
        return Printable.PAGE_EXISTS;
    }
    public void doPrintActions(){
        PrinterJob pj=PrinterJob.getPrinterJob();
        pj.setPrintable(this);
        pj.printDialog();
        try{
        pj.print();
        }catch (Exception PrintException) {}
    }
}

