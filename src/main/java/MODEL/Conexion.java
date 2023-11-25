package MODEL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {

    public Connection get_connection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection("", "", "");
           // System.out.println("Conectado");
        } catch (SQLException e) {
            System.out.println(e);
        }
        return connection;
    }

}
