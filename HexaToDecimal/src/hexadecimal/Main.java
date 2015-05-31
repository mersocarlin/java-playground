/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hexadecimal;

/**
 *
 * @author Hemerson
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        String digitar = "77f01";
        System.out.println("Hexa:" + digitar);
        int digitar1 = Integer.parseInt(digitar, 16);
        System.out.println("Decimal:" + digitar1);
    }
}
