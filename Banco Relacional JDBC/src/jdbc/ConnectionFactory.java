/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Hemerson
 */
public class ConnectionFactory {

    public Connection getConnection() {
        System.out.println("Conectando ao banco");
        try {
            return DriverManager.getConnection("jdbc:mysql://localhost:3306/fj21", "root", "root");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
