package org.elsys.chat.constants;

import static org.elsys.chat.constants.Colors.*;

public class MetaData {

    public static void print_instructions() {
        System.out.println(ANSI_YELLOW + "INSTRUCTIONS" + ANSI_RESET);

        //Create
        display("user", "create", "<name>");
        display("m-type", "create", "[creator id] <type name>");
        display("m", "create", "<user id> <type id> [[date]] <text>");
        display("s", "create", "<user id> <message id> <text>");
        System.out.println();

        //Read
        display("user", "read", "<id>/all");
        display("m-type", "read", "<id>/all");
        display("m", "read", "<id>/all");
        display("s", "read", "<id>/all");
        System.out.println();

        //Update
        display("user", "update", "<id> <new username>");
        display("m-type", "update", "<id> <new type name>");
        display("m", "update", "<id> <new message text>");
        display("s", "update", "<id> <new translation>");
        System.out.println();

        //Delete
        display("user", "delete", "<id>");
        display("m-type", "delete", "<id>");
        display("m", "delete", "<id>");
        display("s", "delete", "<id>");
        System.out.println();

        //End the program
        System.out.println(ANSI_RED + "end" + ANSI_RESET + " - stops the program");
        System.out.println(ANSI_YELLOW + "-------------------------" + ANSI_RESET);
        System.out.println();
        System.out.println();
    }

    private static void display(String table, String operation, String params) {
        System.out.format("%s%-6s%s%s %s%s %s\n", ANSI_PURPLE, table, ANSI_RESET, ANSI_RED, operation, ANSI_RESET, params);
    }

}
