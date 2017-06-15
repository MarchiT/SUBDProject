package org.elsys.chat.constants;

import static org.elsys.chat.constants.References.ANSI_RESET;
import static org.elsys.chat.constants.References.ANSI_YELLOW;

public class MetaData {

    public static void print_instructions() {
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

//    m-type update 10 something else

//    user create philip look
//    m-type create morse 2
//    m create 1 1 [date] textty texy bla
//    solution create 1 1 this is the decoded text

}
