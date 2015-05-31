/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package principal;

import javax.swing.JOptionPane;
import modelo.Pessoa;
import persistencia.DAO;
import persistencia.DAOPessoa;

/**
 *
 * @author Hemerson
 */
public class Main {

    public Main() {
    }

    public static void main(String[] args) {
        DAO dao = new DAOPessoa();
        JOptionPane.showMessageDialog(null, dao.conecta());
        Pessoa pessoa  = new Pessoa("hemerson", "111", 1);
        JOptionPane.showMessageDialog(null, dao.inserir(pessoa));


        JOptionPane.showMessageDialog(null, dao.desconecta());
    }

}
