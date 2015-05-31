/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package impressao;

/**
 *
 * @author Hemerson
 */
import java.awt.Graphics;
import java.awt.PrintJob;
import java.awt.Toolkit;
import java.awt.Font;
import java.awt.Frame;

public class Impressora {

    public void imprimir() {

        // cria um frame temporário, onde será  desenhado  o texto a ser impresso     
        Frame frame = new Frame("Frame");
        frame.pack();

        // pega o Toolkit do Frame
        Toolkit toolkit = frame.getToolkit();

        // Pega os serviços de impressão existentes no computador,
        // para que seja escolhida uma impressora.
        // Também pode ser uma impressora de rede
        try {
            PrintJob printJob = toolkit.getPrintJob(frame, "print", null);
            // Aqui se inicia a impressão
            if (printJob != null) {
                int posX = 50;
                int posY = 50;
                int enter = 10;
                int numLinha = 1;

                Graphics g = printJob.getGraphics();
                g.setFont(new Font("Courier New", Font.PLAIN, 12));
                g.drawString("Teste de impressão", posX, posY); //titulo da pagina
                posY += enter;
                while (posY < 800) {
                    g.drawString("Linha: " + numLinha + " PosY = " + posY, posX, posY);
                    posY += enter;
                    numLinha++;
                }
                g.setFont(new Font("Courier New", Font.PLAIN, 8));
                g.drawString("página 1", 490, 800); //rodape da pagina

                // libera os recursos gráficos
                g.dispose();
                
                // encerra a impressão
                printJob.end();
            }
        } catch (Exception e) {
            System.out.println("Erro na impressao: " + e);
        }
        // desfaz o frame temporário
        frame.dispose();
    }
    // Método main para teste

    public static void main(String[] args) {
        Impressora imp = new Impressora();
        imp.imprimir();
        System.exit(0);
    }
}