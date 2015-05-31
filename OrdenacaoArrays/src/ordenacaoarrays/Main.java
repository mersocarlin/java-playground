/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ordenacaoarrays;

import java.util.Arrays;
import java.util.Comparator;

/**
 *
 * @author hlscarlin
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String[] nomes = {"Daniel", "Paulo", "Rafael", "Guilherme", "Jonas", "Augusto", "Anderson"};
        
        System.out.print("Antes: ");
        listar(nomes);

        Arrays.sort(nomes);
//        Arrays.sort( nomes,
//            new Comparator() {
//                public int compare( Object obj1, Object obj2 ) {
//                    return ((String)obj1).compareTo((String)obj2);
//                }
//            }
//        );
        System.out.println("\nDepois: ");
        listar(nomes);
    }

    static void listar(String[] lista) {
        for (int i = 0; i < lista.length; i++) {
            System.out.print(lista[i] + " - ");
        }
    }
}
