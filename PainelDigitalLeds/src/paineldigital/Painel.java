/*
 * Painel.java
 *
 * Created on 4 de Setembro de 2008, 23:52
 */

package paineldigital;

import javax.swing.Icon;
import javax.swing.ImageIcon;

/**
 *
 * @author  Hemerson
 */
public class Painel extends javax.swing.JFrame {
    /** Creates new form Painel */
    public Painel() {
        initComponents();
        this.setLocationRelativeTo(null);
        this.inicio();
        
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTextField1 = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        jLabelNum1 = new javax.swing.JLabel();
        jLabelNum2 = new javax.swing.JLabel();
        jLabelNum3 = new javax.swing.JLabel();
        jLabelNum4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setResizable(false);

        jTextField1.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jTextField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField1KeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField1KeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField1KeyTyped(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabelNum1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabelNum2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabelNum3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabelNum4.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabelNum1, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelNum2, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelNum3, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelNum4, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabelNum1, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(jLabelNum2, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(jLabelNum3, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(jLabelNum4, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jTextField1, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

private void jTextField1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyReleased
    this.mostra();
}//GEN-LAST:event_jTextField1KeyReleased

private void jTextField1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyPressed
    this.mostra();
}//GEN-LAST:event_jTextField1KeyPressed

private void jTextField1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyTyped
    this.mostra();
}//GEN-LAST:event_jTextField1KeyTyped

public void inicio(){
    jLabelNum1.setIcon(new ImageIcon("Numeros1/0.jpg"));
    jLabelNum2.setIcon(new ImageIcon("Numeros1/0.jpg"));
    jLabelNum3.setIcon(new ImageIcon("Numeros1/0.jpg"));
    jLabelNum4.setIcon(new ImageIcon("Numeros1/0.jpg"));
}

public void mostra(){
    if((jTextField1.getText()!= null)&&(jTextField1.getText().length() > 0)){
        String s = jTextField1.getText();
        if(s.length() == 1){
            this.inicio();
            jLabelNum4.setIcon(new ImageIcon("Numeros1/"+s+".jpg"));
        }else{
            char a[] = s.toCharArray();
            this.inicio();
            if(s.length() == 2){
                jLabelNum3.setIcon(new ImageIcon("Numeros1/"+a[0]+".jpg"));
                jLabelNum4.setIcon(new ImageIcon("Numeros1/"+a[1]+".jpg"));
            }else{
                if(s.length() == 3){
                    jLabelNum2.setIcon(new ImageIcon("Numeros1/"+a[0]+".jpg"));
                    jLabelNum3.setIcon(new ImageIcon("Numeros1/"+a[1]+".jpg"));
                    jLabelNum4.setIcon(new ImageIcon("Numeros1/"+a[2]+".jpg"));
                }else{
                    if(s.length() == 4){
                        jLabelNum1.setIcon(new ImageIcon("Numeros1/"+a[0]+".jpg"));
                        jLabelNum2.setIcon(new ImageIcon("Numeros1/"+a[1]+".jpg"));
                        jLabelNum3.setIcon(new ImageIcon("Numeros1/"+a[2]+".jpg"));
                        jLabelNum4.setIcon(new ImageIcon("Numeros1/"+a[3]+".jpg"));
                    }else{
                        this.inicio();
                    }
                }
            }
        }
    }else{
        this.inicio();
    }
}
    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Painel().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabelNum1;
    private javax.swing.JLabel jLabelNum2;
    private javax.swing.JLabel jLabelNum3;
    private javax.swing.JLabel jLabelNum4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables

}
