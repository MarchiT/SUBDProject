package org.elsys.chat;

import org.elsys.chat.constants.MetaData;
import org.elsys.chat.requests.MessageType;
import org.elsys.chat.requests.ModelStructure;
import org.elsys.chat.requests.User;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class CommandInterpreter implements Runnable {

    private ModelStructure user;
    private ModelStructure messageType;
    private ModelStructure messages;
    private ModelStructure solutions;


    public CommandInterpreter() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            String connectionString = "jdbc:mysql://localhost/CodedChat?useSSL=false";
            Connection con = DriverManager.getConnection(connectionString, "root", "cyan#BesT0color");

            user = new User(con);
            messageType = new MessageType(con);
//            messages
//            solutions
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {

        MetaData.print_instructions();

        try (InputStreamReader instream = new InputStreamReader(System.in);
             BufferedReader buffer = new BufferedReader(instream)) {

            String line;
            while ((line = buffer.readLine()) != null) {
                if (line.equals("end")) break;


                if (line.contains("user")) {
                    if (line.contains("read")) {
                        switch (line.substring(line.lastIndexOf(" ")+1)) {
                            case "all": user.readAll(); break;
                            default: user.read(Integer.parseInt(line.substring(line.lastIndexOf(" ")+1))); break;
                        }
                    }
                    else if (line.contains("update")) user.update
                            (Integer.parseInt(line.trim().substring(12, line.lastIndexOf(" "))),
                                    line.trim().substring(line.lastIndexOf(" ")+1)); //for now only works with one word names
                    else if (line.contains("create")) user.create(line.substring(12).trim());
                    else if (line.contains("delete")) user.delete(Integer.parseInt(line.substring(12).trim()));
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
