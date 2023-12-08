package ihmproject1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBcon {
public static Connection con=null;

public DBcon() {
	loadConnection();
}

private static void loadConnection() {
	try {
		Class.forName("com.mysql.cj.jdbc.Driver");
	} catch (ClassNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	String url="jdbc:mysql://localhost:3306/mydb";
	String root = "root";

	String password="182003";
    try {
    	con=DriverManager.getConnection(url,root,password);
        if (con != null) {
            System.out.println("Database connection established");
        }
    } catch (SQLException e) {
        System.err.println("Failed to establish database connection.");
        e.printStackTrace();
        // Handle the exception appropriately, log it, or throw a custom exception.
    }
}
public static void closeConnection() {
	if (con != null) {
        try {
            con.close();
            System.out.println("Database connection closed");
        } catch (SQLException e) {
            System.err.println("Failed to close database connection.");
            e.printStackTrace();
        }
    }
}
}
