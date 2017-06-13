package org.elsys.chat.requests;


import java.sql.*;

import static org.elsys.chat.constants.References.ANSI_BLUE;
import static org.elsys.chat.constants.References.ANSI_RESET;


//create name
//delete id
//update id
//read all/id
public class User {

    public static int create(String name) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            String connectionString = "jdbc:mysql://localhost/CodedChat?useSSL=false";
            Connection con = DriverManager.getConnection(connectionString, "root", "cyan#BesT0color");

            PreparedStatement query = con.prepareStatement("INSERT INTO Users(Id, Name) VALUES(?,?)");
            query.setNull(1, Types.INTEGER);
            query.setString(2, name);

            int affectedRows = query.executeUpdate();
            System.out.println(ANSI_BLUE + "User \'" + name + "\' created!" + ANSI_RESET);
            return affectedRows;

        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }

    public static void read(int id) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            String connectionString = "jdbc:mysql://localhost/CodedChat?useSSL=false";
            Connection con = DriverManager.getConnection(connectionString, "root", "cyan#BesT0color");

            PreparedStatement query = con.prepareStatement("SELECT Id, Name FROM Users WHERE Id=?");
            query.setInt(1, id);
            ResultSet result = query.executeQuery();

            while(result.next()) {
                String name = result.getString("Name");

                System.out.println(ANSI_BLUE + "For id \'" + id + "\' user name is: " + name + ANSI_RESET);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void readAll() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            String connectionString = "jdbc:mysql://localhost/CodedChat?useSSL=false";
            Connection con = DriverManager.getConnection(connectionString, "root", "cyan#BesT0color");

            PreparedStatement query = con.prepareStatement("SELECT * FROM Users");
            ResultSet result = query.executeQuery();

            while(result.next()) {
                System.out.println("Id: " + result.getInt("Id") + " Name: " + result.getString("Name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public static int update(int id, String newName) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            String connectionString = "jdbc:mysql://localhost/CodedChat?useSSL=false";
            Connection con = DriverManager.getConnection(connectionString, "root", "cyan#BesT0color");

            PreparedStatement query = con.prepareStatement("UPDATE Users SET Name=? WHERE Id=?");
            query.setString(1, newName);
            query.setInt(2, id);

            int affectedRows = query.executeUpdate();
            System.out.println(ANSI_BLUE + "User name successfully changed!" + ANSI_RESET);
            return affectedRows;
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }

    public static int delete(int id) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            String connectionString = "jdbc:mysql://localhost/CodedChat?useSSL=false";
            Connection con = DriverManager.getConnection(connectionString, "root", "cyan#BesT0color");

            PreparedStatement query = con.prepareStatement("DELETE FROM Users WHERE Id=?");
            query.setInt(1, id);

            int affectedRows = query.executeUpdate();
            System.out.println(ANSI_BLUE + "User successfully deleted!" + ANSI_RESET);
            return affectedRows;
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }

}
