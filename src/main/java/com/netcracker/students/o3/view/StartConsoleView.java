package com.netcracker.students.o3.view;

import com.netcracker.students.o3.Exceptions.IncorrectCredentialsException;
import com.netcracker.students.o3.Exceptions.RegisterException;
import com.netcracker.students.o3.controller.Controller;
import com.netcracker.students.o3.controller.ControllerImpl;
import com.netcracker.students.o3.model.area.Area;
import com.netcracker.students.o3.model.users.Customer;
import com.netcracker.students.o3.model.users.Employee;
import com.netcracker.students.o3.model.users.User;

import java.math.BigInteger;
import java.util.List;
import java.util.Scanner;

import jdk.internal.joptsimple.internal.Strings;

public class StartConsoleView
{

    private Controller controller = ControllerImpl.getInstance();

    public void start()
    {
        Scanner scanner = new Scanner(System.in);
        String choice = null;

        while (!"0".equals(choice))
        {
            System.out.println(
                    "1 - войти\n" +
                            "2 - зарегистрироваться\n" +
                            "0 - выйти");
            System.out.print("Ваш выбор: ");
            choice = scanner.nextLine();

            clearScreen();

            try
            {
                switch (choice)
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
                        System.out.println("Введите номер одного из пунктов!");
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

    private void login() throws IncorrectCredentialsException
    {
        String login = getInputLogin();
        String password = getInputPassword();

        User user = controller.getUserByCredentials(login, password);
        if (user instanceof Customer)
        {
            new ConsoleCustomerView(user.getId()).start();
        }
        else if (user instanceof Employee)
        {
            // todo Кенан вызов вьюхи
        }

    }

    private void register()
    {
        String name = getInputName();
        String login = getInputLogin();
        String password = getInputPassword();
        String userType = getInputTypeOfUser();

        if ("1".equals(userType))
        {
            BigInteger areaId = chooseArea();
            final BigInteger newCustomerId = controller.registerCustomer(login, password, name, areaId);

            new ConsoleCustomerView(newCustomerId).start();
        }
        else if ("2".equals(userType))
        {
            //todo: Кенан вызов EmployeeView.start()
        }
    }

    private String getInputName()
    {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введите имя: ");
        String name = scanner.nextLine();

        try
        {
            checkNull(name, "Имя");
        }
        catch (RegisterException e)
        {
            System.out.println(e.getMessage());
            return getInputName();
        }

        return name;
    }

    private String getInputLogin()
    {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введите логин: ");
        String login = scanner.nextLine();

        try
        {
            checkNull(login, "Логин");
            checkLoginExists(login);
        }
        catch (RegisterException e)
        {
            System.out.println(e.getMessage());
            return getInputLogin();
        }

        return login;
    }

    private void checkLoginExists(final String login) throws RegisterException
    {
        if (!controller.isLoginExists(login))
        {
            throw new RegisterException("Такой логин уже существует");
        }
    }

    private String getInputPassword()
    {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введите пароль: ");
        String password = scanner.nextLine();

        try
        {
            checkNull(password, "Пароль");
        }
        catch (RegisterException e)
        {
            System.out.println(e.getMessage());
            return getInputPassword();
        }

        return password;
    }

    private String getInputTypeOfUser()
    {
        Scanner scanner = new Scanner(System.in);

        System.out.println("1 - Customer");
        System.out.println("2 - Employee");
        System.out.println("Введите тип пользователя: ");

        String choiceTypeOfUser = scanner.nextLine();
        if (!"1".equals(choiceTypeOfUser) && !"2".equals(choiceTypeOfUser))
        {
            System.out.println("Выберите один из пунктов");
            return getInputTypeOfUser();
        }

        return choiceTypeOfUser;
    }

    private void checkNull(final String value, final String nameOfField) throws RegisterException
    {
        if (Strings.isNullOrEmpty(value))
        {
            throw new RegisterException(nameOfField + " не может быть пустым");
        }
    }

    private BigInteger chooseArea()
    {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Выберите район: ");

        List<Area> areas = controller.getAreas();
        for (int i = 0;i<areas.size();i++)
        {
            System.out.println(i+1 + ")" + "" + areas.get(i));
        }

        BigInteger areaId;

        try
        {
            int choice = Integer.parseInt(scanner.nextLine()) - 1;
            areaId = areas.get(choice).getId();
        }
        catch (NumberFormatException e)
        {
            System.out.println("Выберите один из пунктов!");
            return chooseArea();
        }

        return areaId;
    }
}
