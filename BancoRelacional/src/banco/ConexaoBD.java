/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package banco;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Hemerson
 */
public class ConexaoBD {

    /* ----- DERBY -----
     * driver: org.apache.derby.jdbc.ClientDriver
     * url: jdbc:derby://localhost:1527/sample
     */
    /* ----- MYSQL -----
     * driver: com.mysql.jdbc.Driver
     * url: "jdbc:mysql://localhost:3306/teste";
     */

    /* ----- POSTGRE -----
     * driver: org.postgresql.Driver
     * url: jdbc:postgresql://localhost:5432/bd1
     */
    public static String DRIVER = "";
    public static String DBURL = "";
    public static String USER = "root";
    public static String PASSWORD = "root";
    public static int tipoBanco = 2;

    public static Connection criarConexao() {
        try {

            switch (tipoBanco) {
                case 0: //Derby
                    DRIVER = "org.apache.derby.jdbc.ClientDriver";
                    DBURL = "jdbc:derby://localhost:1527/sample";
                    break;
                case 1: //MySQL
                    DRIVER = "com.mysql.jdbc.Driver";
                    DBURL = "jdbc:mysql://localhost:3306/pessoa";
                    break;
                case 2: //PostgreSQL
                    DRIVER = "org.postgresql.Driver";
                    DBURL = "jdbc:postgresql://localhost:5432/pessoa";
                    break;
            }
            Class.forName(DRIVER);
            Connection con = null;
            try {
                con = DriverManager.getConnection(DBURL, USER, PASSWORD);
            } catch (SQLException ex) {
                System.out.println("Erro:" + ex);
            }
            return con;
        } catch (ClassNotFoundException ex) {
            System.out.println("Erro:" + ex);
            return null;
        }
    }
}
