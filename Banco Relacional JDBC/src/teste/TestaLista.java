/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package teste;

import dao.ContatoDAO;
import java.util.List;
import modelo.Contato;

/**
 *
 * @author Hemerson
 */
public class TestaLista {

    public static void main(String[] args) {
        ContatoDAO dao = new ContatoDAO();

        List<Contato> contatos = dao.getLista();

        for (Contato contato : contatos) {
            System.out.println("Nome: " + contato.getNome());
            System.out.println("Email: " + contato.getEmail());
            System.out.println("Endereço: " + contato.getEndereco());
            System.out.println("Data de Nascimento: " + contato.getDataNascimento().getTime() + "\n");
        }
    }
}
