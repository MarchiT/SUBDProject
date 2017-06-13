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

                //check to which table changes should be made
                ModelStructure model = user;
                if (line.contains("user")) model = user;
                else if (line.contains("m-type")) model = messageType;


                //choose operation to be executed
                if (line.contains("read")) {
                    switch (line.substring(line.lastIndexOf(" ")+1)) {
                        case "all": model.readAll(); break;
                        default: model.read(Integer.parseInt(line.substring(line.lastIndexOf(" ")+1))); break;
                    }
                }
                else if (line.contains("update")) {
                    model.update(Integer.parseInt(line.trim().substring(12, line.lastIndexOf(" "))),
                            line.trim().substring(line.lastIndexOf(" ")+1)); //for now only works with one word names
                }
                else if (line.contains("create")) {
                    System.out.println(line.substring(14).trim()); //12 for user, put regex
                    //put second argument for m-type
                    model.create(line.substring(14).trim());
                }
                else if (line.contains("delete")) model.delete(Integer.parseInt(line.substring(12).trim()));

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
