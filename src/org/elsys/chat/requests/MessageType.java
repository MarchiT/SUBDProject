package org.elsys.chat.requests;


import java.sql.*;

import static org.elsys.chat.constants.References.ANSI_BLUE;
import static org.elsys.chat.constants.References.ANSI_RESET;

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
    public int create(String name) {
        return 0;
    }

    @Override
    public void read(int id) {

    }

    @Override
    public void readAll() {

    }

    @Override
    public int update(int id, String newName) {
        return 0;
    }

    @Override
    public int delete(int id) {
        return 0;
    }

}
