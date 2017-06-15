package org.elsys.chat.requests;


import java.sql.*;

import static org.elsys.chat.constants.References.ANSI_BLUE;
import static org.elsys.chat.constants.References.ANSI_RESET;

public class Solutions extends ModelStructure {

    public Solutions(Connection con) {
        super(con);
    }

    @Override
    public int create(String... params) {
        String decodedText = params[2];
        int userId = Integer.parseInt(params[0]);
        int messageId = Integer.parseInt(params[1]);

        try {
            PreparedStatement query = getConnection().prepareStatement("INSERT INTO Solutions VALUES(?,?,?,?)");
            query.setNull(1, Types.INTEGER);
            query.setString(2, decodedText);
            query.setInt(3, userId);
            query.setInt(4, messageId);

            int affectedRows = query.executeUpdate();
            System.out.println(ANSI_BLUE + "Solution to message \'" + messageId + "\' from user \'" + userId + "\' created!" + ANSI_RESET);
            return affectedRows;

        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }

    @Override
    public int update(int id, String... params) {
        String newText = params[0];
        try {
            PreparedStatement query = getConnection().prepareStatement("UPDATE Solutions SET DecodedText=? WHERE Id=?");
            query.setString(1, newText);
            query.setInt(2, id);

            int affectedRows = query.executeUpdate();
            System.out.println(ANSI_BLUE + "Solution is successfully updated!" + ANSI_RESET);
            return affectedRows;
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }


    @Override
    public void read(int id) {
        try {
            PreparedStatement query = getConnection().prepareStatement("SELECT Id, DecodedText, UserId, MessageId " +
                    "FROM Solutions WHERE Id=?");
            query.setInt(1, id);
            ResultSet result = query.executeQuery();

            while(result.next()) {
                String text = result.getString("DecodedText");
                String userId = result.getString("UserId");
                String messageId = result.getString("MessageId");

                System.out.println(ANSI_BLUE + "For id \'" + id + "\' message id is: " + messageId +
                        " user id: "  + userId + " text: " + text + ANSI_RESET);
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
                int id = result.getInt("Id");
                String text = result.getString("DecodedText");
                String userId = result.getString("UserId");
                String messageId = result.getString("MessageId");

                System.out.println(ANSI_BLUE + "Id: " + id + " MessageId: " + messageId +
                        " UserId: "  + userId + " Text: " + text + ANSI_RESET);
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
