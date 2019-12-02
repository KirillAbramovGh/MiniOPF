package com.netcracker.students.o3.view;

import com.netcracker.students.o3.Exceptions.LoginNotExistException;
import com.netcracker.students.o3.Exceptions.LoginOccupiedException;
import com.netcracker.students.o3.Exceptions.RegisterException;
import com.netcracker.students.o3.Exceptions.WrongInputException;
import com.netcracker.students.o3.controller.ControllerImpl;
import com.netcracker.students.o3.model.area.Area;

import java.math.BigInteger;
import java.util.List;
import java.util.Scanner;

public class startConsoleView
{
    public void start() throws WrongInputException
    {
        Scanner scanner = new Scanner(System.in);
        String punct = "";
        while (!punct.equals("0"))
        {
            System.out.println("1 - войти\n" +
                    "2 - зарегистрироваться\n" +
                    "0 - выйти");
            System.out.print("Ваш выбор: ");
            punct = scanner.nextLine();
            try
            {
                switch (punct)
                {
                    case "1":
                        login();
                        break;
                    case "2":
                        register();
                        break;
                    case "0":
                        break;
                    default:
                        throw new WrongInputException("Введите номер одного из пунктов!");
                }
            }
            catch (Exception e)
            {
                System.out.println(e.getMessage());
            }
        }


    }

    private void clearScreen()
    {
        for (int i = 0; i < 100; i++)
        {
            System.out.println();
        }
    }

    private void login() throws LoginOccupiedException, LoginNotExistException
    {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите логин: ");
        String login = scanner.nextLine();
        System.out.print("Введите пароль: ");
        String password = scanner.nextLine();
        BigInteger id = ControllerImpl.getInstance().login(login, password);
        if (id == null)
        {
            throw new LoginNotExistException("Неправильный логин или пароль!");
        }
        else
        {
            if (ControllerImpl.getInstance().isCustomer(id))
            {
                new ConsoleCustomerView(id).start();
            }
            else if (ControllerImpl.getInstance().isEmployee(id))
            {

            }
        }
    }

    private void register() throws RegisterException, WrongInputException
    {
        Scanner scanner = new Scanner(System.in);
        System.out.println("1 - Customer");
        System.out.println("2 - Employee");
        System.out.println("Введите тип пользователя: ");
        String userType = scanner.nextLine();
        int type;
        try
        {
            type = Integer.parseInt(userType);
            if (type != 1 && type != 2)
            {
                throw new WrongInputException();
            }
        }
        catch (Exception e)
        {
            throw new WrongInputException("Выберите один из пунктов!");
        }
        System.out.print("Введите имя: ");
        String name = scanner.nextLine();
        checkNull(name, "Имя");
        System.out.print("Введите логин: ");
        String login = scanner.nextLine();
        checkNull(login, "Логин");
        System.out.print("Введите пароль: ");
        String password = scanner.nextLine();
        checkNull(password, "Пароль");
        if (!ControllerImpl.getInstance().checkLogin(login))
        {
            throw new RegisterException("Такой логин уже существует");
        }
        if (type == 1)
        {
            System.out.println("Выберите район: ");
            int number = 1;
            List<Area> areas = ControllerImpl.getInstance().getAreas();
            for (Area area : areas)
            {
                System.out.println(number++ + ")" + "" + area);
            }
            BigInteger areaId = null;
            try
            {
                int punct = Integer.parseInt(scanner.nextLine()) - 1;
                areaId = areas.get(punct).getId();
            }
            catch (Exception e)
            {
                throw new WrongInputException("Недопустимы ввод!");
            }
            new ConsoleCustomerView(ControllerImpl.getInstance().registerCustomer(login, password, name, areaId))
                    .start();
        }
        else
        {
            // new ConsoleEmployeeView(ControllerImpl.getInstance().registerEmployee(login,password,name)).start();
        }
    }

    public void checkNull(final String value, final String nameOfField) throws RegisterException
    {
        if (value.equals(""))
        {
            throw new RegisterException(nameOfField + " не может быть пустым");
        }
    }
}
