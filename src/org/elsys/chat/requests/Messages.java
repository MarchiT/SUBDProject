package org.elsys.chat.requests;

import java.sql.*;
import java.util.Date;

import static org.elsys.chat.constants.References.ANSI_BLUE;
import static org.elsys.chat.constants.References.ANSI_RESET;

public class Messages extends ModelStructure {

    public Messages(Connection con) {
        super(con);
    }

    @Override
    public int create(String... params) {
        String text = params[0];
        int toUserId = Integer.parseInt(params[1]);
        int messageId = Integer.parseInt(params[2]);

        try {
            PreparedStatement query = getConnection().prepareStatement("INSERT INTO Messages VALUES(?,?,?,?)");
            query.setNull(1, Types.INTEGER);
            query.setString(2, text);
            query.setInt(4, toUserId);
            query.setInt(5, messageId);

            if (params.length > 3) {
                query.setInt(3, Integer.parseInt(params[1]));
            } else {
                query.setNull(3, Types.INTEGER);
            }

            int affectedRows = query.executeUpdate();
            System.out.println(ANSI_BLUE + "Message Id \'" + messageId + "\' to user:\'" + toUserId + "\' created!" + ANSI_RESET);
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
            PreparedStatement query = getConnection().prepareStatement("UPDATE Messages SET Text=? WHERE Id=?");
            query.setString(1, newText);
            query.setInt(2, id);

            int affectedRows = query.executeUpdate();
            System.out.println(ANSI_BLUE + "Message is successfully updated!" + ANSI_RESET);
            return affectedRows;
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }


    @Override
    public void read(int id) {
        try {
            PreparedStatement query = getConnection().prepareStatement("SELECT * FROM Messages WHERE Id=?");
            query.setInt(1, id);
            ResultSet result = query.executeQuery();

            while(result.next()) {
                String text = result.getString("Text");
                Date date = result.getDate("UploadedDate");
                int toUserId = result.getInt("ToUserId");
                int typeId = result.getInt("TypeId");

                System.out.println(ANSI_BLUE + "Id: " + id + " ToUserId: " + toUserId
                        + " TypeId: " + typeId + " Text: " + text +
                        " Date: " + date + ANSI_RESET);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void readAll() {
        try {
            PreparedStatement query = getConnection().prepareStatement("SELECT * FROM Messages");
            ResultSet result = query.executeQuery();

            while(result.next()) {
                int id = result.getInt("Id");
                String text = result.getString("Text");
                Date date = result.getDate("UploadedDate");
                int toUserId = result.getInt("ToUserId");
                int typeId = result.getInt("TypeId");

                System.out.println(ANSI_BLUE + "Id: " + id + " ToUserId: " + toUserId
                        + " TypeId: " + typeId + " Text: " + text +
                        " Date: " + date + ANSI_RESET);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int delete(int id) {
        try {
            PreparedStatement query = getConnection().prepareStatement("DELETE FROM Messages WHERE Id=?");
            query.setInt(1, id);

            int affectedRows = query.executeUpdate();
            System.out.println(ANSI_BLUE + "Message successfully deleted!" + ANSI_RESET);
            return affectedRows;
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }
}
