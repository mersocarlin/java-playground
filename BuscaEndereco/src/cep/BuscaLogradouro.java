/*
 * BuscaLogradouro.java
 *
 * Criado em 4 de Junho de 2007, 10:27 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package cep;

import javax.swing.JOptionPane;

/**
 *
 * @author Fabio Souza
 */
public class BuscaLogradouro {

    public static void main(String[] args) {
        String cep = JOptionPane.showInputDialog(null,"Digite o CEP (apenas numeros):");
        new BuscaLogradouro(cep);
    }

    public BuscaLogradouro(String cep) {
        try {
            CepWebService cepWebService = new CepWebService(cep);

            if (cepWebService.getResultado()==1) {
                String endereco = "Endereco: " + cepWebService.getTipo_logradouro() + " " + cepWebService.getLogradouro() + "\n"+
                        "Cidade: " + cepWebService.getCidade() + "\n"+
                        "Estado: " + cepWebService.getEstado() + "\n"+
                        "Bairro: " + cepWebService.getBairro() + "\n"+
                        "Resultado: " + cepWebService.getResultado() + "\n"+
                        "Resultado txt: " + cepWebService.getResultado_txt();
                JOptionPane.showMessageDialog(null, endereco);
            }
            else {
                System.out.println("Servidor não está respondendo.");
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
