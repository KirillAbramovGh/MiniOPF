package com.netcracker.students.o3.view;

import com.netcracker.students.o3.controller.command.CommandParser;

import java.io.IOException;
import java.util.Scanner;

public class EmployeeView implements View
{

    public static void start() throws IOException
    {
        Scanner in = new Scanner(System.in);

        System.out.println("welcome");

        while (true)
        {


            System.out.print("Input command: ");
            String command = in.nextLine();

            if (command.equals("exit"))
            {
                break;
            }

            CommandParser.parse(command);
        }

        in.close();
    }

    public static void showHelp(){
        System.out.println("help");
    }
}
