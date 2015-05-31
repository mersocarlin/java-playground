/*
 * BarCodeCanvas.java
 *
 * Created on 28 de Agosto de 2008, 14:51
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package codigo2d;

import br.com.danhil.BarCode.BarCode2D;
import br.com.danhil.BarCode.BarCode2DRenderer;
import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

/**
 *
 * @author apfredrich
 */

class BarCodeCanvas extends JPanel {

        public BarCode2D barCode;
       
        public BarCodeCanvas(BarCode2D bc) {
                super();
                setBackground(Color.white);
                barCode = bc;
        }

        public void setBarCode2D(BarCode2D bc) {
                barCode = bc;
                repaint();
        }

        public void paintComponent(Graphics g) {
                super.paintComponent(g);
                if(barCode != null){
                    barCode.getRenderer().render(g, 10, 10, 25);
                }
        }
}