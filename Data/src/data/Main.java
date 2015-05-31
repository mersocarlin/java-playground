/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Hemerson
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Main m = new Main();
        Date d = m.stringToDate("23/04/1980");
        System.out.println(m.dateToString(d));

        Date d1 = m.stringToDate("23/05/1980");
        System.out.println(m.dateToString(d1));

        if(d.before(d1)){
            System.out.println("antes");
        }else{
            System.out.println("depois");
        }
    }

    /**
     * Transforma uma string para um objeto do tipo Date
     * @param string parametro a ser passado. Ex: 25/05/1988
     * @return um objeto do tipo Date
     */
    public Date stringToDate(String string) {
        DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        try {
            Date d = (Date) formatter.parse(string);
            //String str = formatter.format(d); //pega a data e transforma em uma string
            return d;
        } catch (ParseException ex) {
            return null;
        }
    }

    /**
     * Faz o toString de um objeto do tipo data
     * @param date
     * @return
     */
    public String dateToString(Date date) {
        DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        String str = formatter.format(date);
        return str;
    }
}
