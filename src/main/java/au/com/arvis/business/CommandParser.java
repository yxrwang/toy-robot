package au.com.arvis.business;

import au.com.arvis.model.*;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;


public class CommandParser {

    private static final String SPACE = " ";

    private static final String COMMA = ",";

    public static List<Command> getCommands(String fileName) throws Exception {

        List<Command> commands = new ArrayList<>();

        File commandFile = new File(fileName);

        BufferedReader fileReader = new BufferedReader(new FileReader(commandFile));

        String command = fileReader.readLine();

        while (command != null) {

            if (command.startsWith(Operation.PLACE.name())) {

                Command placeCommand = parsePlaceCommand(command);

                if(placeCommand != null)
                    commands.add(placeCommand);

            } else {

                try {

                    commands.add(parseNonArgumentsCommand(command));

                } catch (IllegalArgumentException e) {

                    System.out.println("Skipped parsing the invalid command: "+ command);

                }

            }

            command = fileReader.readLine();
        }

        return commands;
    }

    private static Command parsePlaceCommand(String command) {

        String[] commandWithArgs = command.split(SPACE);

        try {

            String[] placeArgs = commandWithArgs[1].split(COMMA);

            Position position = new Position(Integer.parseInt(placeArgs[0]), Integer.parseInt(placeArgs[1]));

            Facing facing = Facing.valueOf(placeArgs[2]);

            return new PlaceCommand(new PlaceOperationArgument(position, facing));

        } catch (IllegalArgumentException e) {

            System.out.println("Invalid Place command: " + e.getMessage());
        }

        return null;
    }

    private static Command parseNonArgumentsCommand(String command) throws IllegalArgumentException {

        return new Command(Operation.valueOf(command));
    }
}
