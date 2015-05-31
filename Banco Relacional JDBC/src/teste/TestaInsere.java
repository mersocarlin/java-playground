/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package teste;

import dao.ContatoDAO;
import java.util.Calendar;
import modelo.Contato;

/**
 *
 * @author Hemerson
 */
public class TestaInsere {

    public static void main(String[] args) {
        // pronto para gravar
        Contato contato = new Contato();
        contato.setNome("Caelum");
        contato.setEmail("contato@caelum.com.br");
        contato.setEndereco("R. Vergueiro 3185 cj57");
        contato.setDataNascimento(Calendar.getInstance());
        // grave nessa conexão!!!
        ContatoDAO dao = new ContatoDAO();
        // método elegante
        dao.adiciona(contato);
        System.out.println("Gravado!");
    }
}
