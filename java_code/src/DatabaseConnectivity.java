

import java.sql.Connection;

import java.sql.DriverManager;

public class DatabaseConnectivity {
    private Connection connection;

    /**Establishing database connectivity
     * **/

    public Connection getConnection() {
        String database_name="radditdatabase";
        String userName = "root";
        String password = "root";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            // establishing database connectivity
            connection = DriverManager.getConnection("jdbc:mysql://localhost/" + database_name,userName,password);
            System.out.println("Successfully connected!!!!");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
    }
}
