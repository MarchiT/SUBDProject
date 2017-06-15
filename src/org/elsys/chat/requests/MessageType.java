package org.elsys.chat.requests;


import java.sql.*;

import static java.sql.Types.NULL;
import static org.elsys.chat.constants.Colors.ANSI_BLUE;
import static org.elsys.chat.constants.Colors.ANSI_RESET;

//create a new message type
//read a specific message type ?by name
//read all message types
//update by id
//delete by id
public class MessageType extends ModelStructure{


    public MessageType(Connection con) {
        super(con);
    }


    @Override
    public int create(String... params) {
        String name = params[0];
        try {
            PreparedStatement query = getConnection().prepareStatement(
                    "INSERT INTO MessageTypes(Id, Name, CreatorUserId) VALUES(?,?,?)");
            query.setNull(1, Types.INTEGER);
            query.setString(2, name);

            if (params.length > 1) {
                query.setInt(3, Integer.parseInt(params[1]));
            } else {
                query.setNull(3, Types.INTEGER);
            }

            int affectedRows = query.executeUpdate();
            System.out.println(ANSI_BLUE + "Message Type \'" + name + "\' created!" + ANSI_RESET);
            return affectedRows;

        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }

    @Override
    public void read(int id) {
        try {
            PreparedStatement query = getConnection().prepareStatement("SELECT Id, Name FROM MessageTypes WHERE Id=?");
            query.setInt(1, id);
            ResultSet result = query.executeQuery();

            while(result.next()) {
                String name = result.getString("Name");

                System.out.println(ANSI_BLUE + "For id \'" + id + "\' message type is: " + name + ANSI_RESET);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void readAll() {
        try {
            PreparedStatement query = getConnection().prepareStatement("SELECT * FROM MessageTypes");
            ResultSet result = query.executeQuery();

            while(result.next()) {
                System.out.format("Id: %-3d CreatorId: %-13s, Name: %s\n", result.getInt("Id"),
                        (result.getInt("CreatorUserId") == NULL ? "not specified" : result.getInt("CreatorUserId")),
                        result.getString("Name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int update(int id, String... params) {
        String newName = params[0];
        try {
            PreparedStatement query = getConnection().prepareStatement("UPDATE MessageTypes SET Name=? WHERE Id=?");
            query.setString(1, newName);
            query.setInt(2, id);

            int affectedRows = query.executeUpdate();
            System.out.println(ANSI_BLUE + "Message type name successfully changed!" + ANSI_RESET);
            return affectedRows;
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }

    @Override
    public int delete(int id) {
        try {
            PreparedStatement query = getConnection().prepareStatement("DELETE FROM MessageTypes WHERE Id=?");
            query.setInt(1, id);

            int affectedRows = query.executeUpdate();
            System.out.println(ANSI_BLUE + "Message type successfully deleted!" + ANSI_RESET);
            return affectedRows;
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }

}
