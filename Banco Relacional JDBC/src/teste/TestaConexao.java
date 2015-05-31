/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package teste;

import java.sql.Connection;
import java.sql.SQLException;
import jdbc.ConnectionFactory;

/**
 *
 * @author Hemerson
 */
public class TestaConexao {

    public static void main(String[] args) throws SQLException {
        Connection connection = new ConnectionFactory().getConnection();
        connection.close();
    }
}
