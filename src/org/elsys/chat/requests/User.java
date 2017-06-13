package org.elsys.chat.requests;


import java.sql.*;

import static org.elsys.chat.constants.References.ANSI_BLUE;
import static org.elsys.chat.constants.References.ANSI_RESET;


//create name
//delete id
//update id
//read all/id
public class User extends ModelStructure {

    public User(Connection con) {
        super(con);
    }

    @Override
    public int create(String... params) {
        String name = params[0];
        try {
            PreparedStatement query = getConnection().prepareStatement("INSERT INTO Users(Id, Name) VALUES(?,?)");
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

    @Override
    public void read(int id) {
        try {
            PreparedStatement query = getConnection().prepareStatement("SELECT Id, Name FROM Users WHERE Id=?");
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

    @Override
    public void readAll() {
        try {
            PreparedStatement query = getConnection().prepareStatement("SELECT * FROM Users");
            ResultSet result = query.executeQuery();

            while(result.next()) {
                System.out.println("Id: " + result.getInt("Id") + " Name: " + result.getString("Name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int update(int id, String newName) {
        try {
            PreparedStatement query = getConnection().prepareStatement("UPDATE Users SET Name=? WHERE Id=?");
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

    @Override
    public int delete(int id) {
        try {
            PreparedStatement query = getConnection().prepareStatement("DELETE FROM Users WHERE Id=?");
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
