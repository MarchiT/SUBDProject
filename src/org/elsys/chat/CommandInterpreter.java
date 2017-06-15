package org.elsys.chat;

import org.elsys.chat.constants.MetaData;
import org.elsys.chat.constants.Tables;
import org.elsys.chat.requests.*;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


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
            messages = new Messages(con);
            solutions = new Solutions(con);

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
                if (line.toLowerCase().equals("end")) break;

                //check to which table changes should be made
                ModelStructure model;
                if (line.contains(Tables.USER)) model = user;
                else if (line.contains(Tables.TYPE_MESSAGE)) model = messageType;
                else if (line.contains(Tables.MESSAGE)) model = messages;
                else if (line.contains(Tables.SOLUTION)) model = solutions;
                else continue;

                //choose operation to be executed
                if (line.contains("read")) {
                    switch (line.substring(line.lastIndexOf(" ")+1)) {
                        case "all": model.readAll(); break;
                        default: model.read(Integer.parseInt(line.substring(line.lastIndexOf(" ")+1))); break;
                    }
                }
                else if (line.contains("delete")) {
                    Pattern pattern = Pattern.compile("([\\w-]+)\\s+delete\\s+(\\d+)");
                    Matcher matcher = pattern.matcher(line);

                    if (matcher.find())
                        model.delete(Integer.parseInt(matcher.group(2)));
                }
                else if (line.contains("update")) {
                    Pattern pattern = Pattern.compile("\\w+\\s+\\w+\\s+(\\d+)\\s+(.+)");
                    Matcher matcher = pattern.matcher(line);

                    if (matcher.find())
                        model.update(Integer.parseInt(matcher.group(1)), matcher.group(2));
                }
                else if (line.contains("create")) {
                    Pattern pattern;
                    if (model instanceof User || model instanceof MessageType)
                            pattern = Pattern.compile("([\\w-]+)\\s+\\w+\\s+(\\d+)?\\s*([\\w\\s]*?)$");
                    else if (model instanceof Messages || model instanceof Solutions)
                        pattern = Pattern.compile("(\\w+)\\s+\\w+\\s+(\\d+)\\s+(\\d+)\\s+(\\[([\\w-]+)\\])?\\s*(.+)(\\d+)?$");
                    else
                        continue;

                    Matcher matcher = pattern.matcher(line);

                    while (matcher.find()) {
                        switch (matcher.group(1)) {
                            case Tables.USER:
                                model.create(matcher.group(3));
                                break;
                            case Tables.TYPE_MESSAGE:
                                if (matcher.group(2) == null) model.create(matcher.group(3));
                                else model.create(matcher.group(3), matcher.group(2));
                                break;
                            case Tables.MESSAGE:
                                if (matcher.group(4) == null) model.create(matcher.group(2), matcher.group(3), matcher.group(6));
                                else model.create(matcher.group(2), matcher.group(3), matcher.group(6), matcher.group(5));
                                break;
                            case Tables.SOLUTION:
                                model.create(matcher.group(2), matcher.group(3), matcher.group(6));
                                break;
                        }
                    }
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
