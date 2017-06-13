package org.elsys.chat;

import org.elsys.chat.requests.User;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import static org.elsys.chat.constants.References.*;


public class Main {

    public static void main(String[] args) {

        print_instructions();

        try (InputStreamReader instream = new InputStreamReader(System.in);
             BufferedReader buffer = new BufferedReader(instream)) {

            String line;
            while ((line = buffer.readLine()) != null) {
                if (line.equals("end")) break;


                if (line.contains("user")) {
                    if (line.contains("read")) {
                        switch (line.substring(line.lastIndexOf(" ")+1)) {
                            case "all": User.readAll(); break;
                            default: User.read(Integer.parseInt(line.substring(line.lastIndexOf(" ")+1))); break;
                        }
                    }
                    else if (line.contains("update")) User.update
                            (Integer.parseInt(line.trim().substring(12, line.lastIndexOf(" "))),
                                    line.trim().substring(line.lastIndexOf(" ")+1)); //for now only works with one word names
                    else if (line.contains("create")) User.create(line.substring(12).trim());
                    else if (line.contains("delete")) User.delete(Integer.parseInt(line.substring(12).trim()));
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private static void print_instructions() {
        System.out.println(ANSI_YELLOW + "INSTRUCTIONS" + ANSI_RESET);
        //say briefly what is the app about

        //Create
        System.out.println("user create <name>: for creating a new user");
        System.out.println("create message_type <name> [creator user id]: for creating a new message type");
        System.out.println("create message <text> <to user id> <type id> [uploaded date]: for creating a new possible solution to a message");
        System.out.println("create solution <text> <user id> <message id>: for creating a new message");
        System.out.println();

        //Read
        System.out.println("user read <id>: reads the user with the selected id");
        System.out.println("user read all: for printing all registered users");
        System.out.println();

        //Update
        System.out.println("user update <id> <new name>");
        System.out.println();

        //Delete
        System.out.println("user delete <id>");
        System.out.println();

        System.out.println();
    }
}
