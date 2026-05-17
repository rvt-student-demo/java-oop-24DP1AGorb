package rvt;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.PreparedStatement;

public class sqlite{
    public static void main(String[] args) {
        
        //SQLite connection string
        //This creates or opens a file called "app.db"
        String url = "jdbc:sqlite:app.db";

        // try-with-recources automatically closes the connection
        try(
            Connection conn = DriverManager.getConnection(url)
        ) {
            System.out.println("Connected to SQLite.");

            //SQL command to create a table
            // IF NOT EXISTS prevents errors if table already exists
            String createTable = """
                    CREATE TABLE IF NOT EXISTS users(
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    name TEXT NOT NULL,
                    age INTEGER
                    );
                    """;
        //Statement is used for simple SQL commands
        Statement stmt = conn.createStatement();

        // Execute table creation
        stmt.execute(createTable);

        System.out.println("Table created.");

        //SQL command woth placeholders(?)
        //Placeholders prevent SQL injection
        String insertSql = "INSERT INTO users(name, age) VALUES(?, ?)";
        
        //PreparedStatement lets us safley insert values
        PreparedStatement insertStmt = conn.prepareStatement(insertSql);

        //REplace first ? with "John"
        insertStmt.setString(1, "John");

        //Replace second 2 with 25
        insertStmt.setInt(2, 25);

        //Execute insert command
        insertStmt.executeUpdate();

        System.out.println("User inserted.");

        //SQL query to read all users
        String selectSql = "SELECT id, name, age FROM users";

        //Execute query and store the results
        ResultSet rs = stmt.executeQuery(selectSql);

        System.out.println("\nUsers:");

        //Loop through every returned row
        while (rs.next()){

            //Read values from current row
            int id = rs.getInt("id");
            String name = rs.getString("name");
            int age = rs.getInt("age");

            // Print row data
            System.out.println(
                "ID : " + id +
                " | Name: "+ name +
                " | Age: "+ age
            );
        }
        // Connection automatically closes here
        }
        catch(Exception e){

            // Print full error if something fails
            e.printStackTrace();
        }
    }
}