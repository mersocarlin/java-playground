/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package visao;

import modelo.Pessoa;
import persistencia.DAO;
import persistencia.DAOPessoa;

/**
 *
 * @author Hemerson
 */
public class Teste {

    public static void main(String[] args) {
        DAO dao = new DAOPessoa();
        
        System.out.println(dao.incluir(new Pessoa(1, "merso", "123")));
        System.out.println(""+ dao.nroObjetos());




    }
}
