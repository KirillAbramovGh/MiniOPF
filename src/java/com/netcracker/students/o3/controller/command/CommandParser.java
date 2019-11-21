package com.netcracker.students.o3.controller.command;

public class CommandParser
{
    public static void parse(final String commandStr)
    {
        String[] splitCommand = commandStr.split(" ");

        final Command command = Command.valueOf(splitCommand[0]);


        command.execute(splitCommand);

    }
}
