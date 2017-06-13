package org.elsys.chat.requests;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.elsys.chat.constants.References.ANSI_BLUE;
import static org.elsys.chat.constants.References.ANSI_RESET;

public class Solutions extends ModelStructure {

    public Solutions(Connection con) {
        super(con);
    }

    @Override
    public int create(String... params) {
        return 0;
    }

    @Override
    public int update(int id, String newName) {
        return 0;
    }


    @Override
    public void read(int id) {
        try {
            PreparedStatement query = getConnection().prepareStatement("SELECT Id, DecodedText, UserId, MessageId " +
                    "FROM Solutions WHERE Id=?");
            query.setInt(1, id);
            ResultSet result = query.executeQuery();

            while(result.next()) {
                String name = result.getString("DecodedText");

//                System.out.println(ANSI_BLUE + "For id \'" + id + "\' user name is: " + name + ANSI_RESET);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void readAll() {
        try {
            PreparedStatement query = getConnection().prepareStatement("SELECT * FROM Solutions");
            ResultSet result = query.executeQuery();

            while(result.next()) {
//                System.out.println("Id: " + result.getInt("Id") + " Name: " + result.getString("Name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int delete(int id) {
        try {
            PreparedStatement query = getConnection().prepareStatement("DELETE FROM Solutions WHERE Id=?");
            query.setInt(1, id);

            int affectedRows = query.executeUpdate();
            System.out.println(ANSI_BLUE + "Solution successfully deleted!" + ANSI_RESET);
            return affectedRows;
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }
}
