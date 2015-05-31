/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package javaapplication2;

/**
 *
 * @author hlscarlin
 */
public class NewClass {
    
    public static void main(String args[]){
        String vogais[] = new String[]{"a","e","i","o","u"};
        String consoantes[] = new String[]{"b","c","d","f","g","h","j","k","l","m","n",
                                            "p","q","r","s","t","v","w","x","y","z"};
        
        int aleatorio;
        int min = 0;
        for(int j = 0; j < 10; j++){
            String nome = "";
            for(int i = 0; i < 3; i++){
                aleatorio = 0 + (int)((consoantes.length - min)*Math.random()); 
                nome += consoantes[aleatorio];
                aleatorio = 0 + (int)((vogais.length - min)*Math.random());
                nome += vogais[aleatorio];
            }
            System.out.println(nome);
        }
    }
}
