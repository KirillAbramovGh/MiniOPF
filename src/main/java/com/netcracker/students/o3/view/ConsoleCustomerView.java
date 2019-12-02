package com.netcracker.students.o3.view;

import com.netcracker.students.o3.Exceptions.NullInputException;
import com.netcracker.students.o3.Exceptions.PasswordException;
import com.netcracker.students.o3.Exceptions.WrongInputException;
import com.netcracker.students.o3.controller.Controller;
import com.netcracker.students.o3.controller.ControllerImpl;
import com.netcracker.students.o3.model.area.Area;
import com.netcracker.students.o3.model.services.Service;
import com.netcracker.students.o3.model.templates.Template;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ConsoleCustomerView implements View
{
    private final BigInteger customerId;

    public ConsoleCustomerView(BigInteger id)
    {
        this.customerId = id;
    }

    @Override
    public void start()
    {
        String punct = "";
        Scanner scanner = new Scanner(System.in);
        while (!punct.equals("0"))
        {
            showTitle();
            System.out.println("---------------------------------------------------------");
            System.out.println("1 - показать доступные услуги");
            System.out.println("2 - показать неактивные услуги");
            System.out.println("3 - изменить пароль");
            System.out.println("4 - изменить имя");
            System.out.println("5 - изменить район");
            System.out.println("6 - изменить логин");
            System.out.println("7 - пополнить баланс");
            System.out.println("0 - Выход");
            System.out.print("Ваш выбор: ");
            punct = scanner.nextLine();
            try
            {
                switch (punct)
                {
                    case "1":
                        availableTemplates();
                        break;
                    case "2":
                        showInactiveServices();
                        break;
                    case "3":
                        changePassword();
                        break;
                    case "4":
                        changeName();
                        break;
                    case "5":
                        changeArea();
                        break;
                    case "6":
                        changeLogin();
                        break;
                    case "7":
                        changeBalance();
                        break;
                    case "0":
                        System.out.println("Вы уверены?");
                        System.out.println("1 - да");
                        System.out.println("2 - нет");
                        punct = scanner.nextLine();
                        switch (punct)
                        {
                            case "1":
                                punct = "0";
                                break;
                            default:
                                punct = "";
                                break;
                        }
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

    private void changeBalance() throws WrongInputException
    {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите сумму пополнения: ");
        try
        {
            BigDecimal money = BigDecimal.valueOf(Double.parseDouble(scanner.nextLine()));
            if (money.doubleValue() > 0)
            {
                ControllerImpl.getInstance().putOnBalance(customerId, money);
            }
            else
            {
                throw new WrongInputException("Введите положительное число");
            }
        }
        catch (Exception e)
        {
            throw new WrongInputException("Введите положительное число");
        }
    }

    private void changeLogin() throws NullInputException
    {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите новый логин: ");
        String newLogin = scanner.nextLine();
        if (newLogin == null || newLogin.equals(""))
        {
            throw new NullInputException("Логин не может быть пустым");
        }
        if (ControllerImpl.getInstance().checkLogin(newLogin))
        {
            ControllerImpl.getInstance().getCustomer(customerId).setLogin(newLogin);
        }
        else
        {
            System.out.println("Логин занят");
        }
    }

    private void changeName() throws NullInputException
    {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите новое имя: ");
        String name = scanner.nextLine();
        if (name.equals(""))
        {
            throw new NullInputException("Имя не может быть пустым");
        }
        ControllerImpl.getInstance().getCustomer(customerId).setName(name);
    }

    private void changeArea() throws WrongInputException
    {
        Controller controller = ControllerImpl.getInstance();
        Scanner scanner = new Scanner(System.in);
        int number = 1;
        List<Area> areas = controller.getAreas();
        for (Area area : areas)
        {
            System.out.println(number++ + ")" + "" + area);
        }
        int punct = 0;
        try
        {
            punct = Integer.parseInt(scanner.nextLine()) - 1;
            if (punct > areas.size() || punct < 1)
            {
                throw new WrongInputException("");
            }
        }
        catch (Exception e)
        {
            throw new WrongInputException("Неверный ввод!");
        }
        for (Service service : controller.getActiveServices(customerId))
        {
            if (!controller.getTemplate(service.getTemplateId()).getPossibleAreasId()
                    .contains(areas.get(punct).getId()))
            {

            }
        }
        for (Service service : controller.getEnteringServices(customerId))
        {
            if (!controller.getTemplate(service.getTemplateId()).getPossibleAreasId()
                    .contains(areas.get(punct).getId()))
            {

            }
        }
        controller.getCustomer(customerId).setAreaId(areas.get(punct).getId());
    }

    @Override
    public void clearScreen()
    {
        for (int i = 0; i < 100; i++)
        {
            System.out.println();
        }
    }

    private void availableTemplates() throws WrongInputException
    {
        showTemplates(0);
    }

    private void showTemplates(int from) throws WrongInputException
    {
        Scanner scanner = new Scanner(System.in);
        Controller controller = ControllerImpl.getInstance();
        ArrayList<Template> templates = controller.getTemplatesByAreaId(controller.getCustomerAreaId(customerId));
        String punct = "";

        while (!punct.equals("0"))
        {
            System.out.printf("%-10s%-20s%-10s%n", "Name", "Description", "Cost");
            int number = 1;
            for (int i = from; i < 8 + from && i < templates.size(); i++)
            {
                Template template = templates.get(i);
                System.out.print(number + ") ");
                number++;
                System.out
                        .printf("%-10s%-20s%-10s%n", template.getName(), template.getDescription(), template.getCost());
            }
            if (from + 8 < templates.size())
            {
                System.out.println("\n9)Следующая страница");
            }
            System.out.println("0)Предыдущая страница");
            punct = scanner.nextLine();

            switch (punct)
            {
                case "1":
                case "2":
                case "3":
                case "4":
                case "5":
                case "6":
                case "7":
                case "8":
                    ControllerImpl.getInstance().createOrder(templates.get(from+Integer.parseInt(punct)).getId());
                    break;
                case "9":
                    showTemplates(from + 8);
                    break;
                case "0":
                    break;
                default:
                    throw new WrongInputException("Введите номер одного из пунктов!");
            }
        }
    }

    private void changePassword() throws PasswordException
    {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите старый пароль: ");
        String oldPassword = scanner.nextLine();
        if (ControllerImpl.getInstance().checkPassword(customerId, oldPassword))
        {
            System.out.print("Введите новый пароль: ");
            String newPassword = scanner.nextLine();
            checkNewPassword(newPassword);
            ControllerImpl.getInstance().getCustomer(customerId)
                    .setPassword(newPassword);
            System.out.println("Пароль успешно изменён.");
        }
        else
        {
            System.out.println("Неверный пароль!");
        }
    }

    private void checkNewPassword(final String password) throws PasswordException
    {
        if (password == null || password.equals(""))
        {
            throw new PasswordException("Пароль не может быть пустым!");
        }
        for (int i = 0; i < password.length(); i++)
        {
            if (!Character.isLetterOrDigit(password.charAt(i)))
            {
                throw new PasswordException("Пароль может состоять только из букв и цифр!");
            }
        }
    }


    private void showTitle()
    {
        System.out.println(ControllerImpl.getInstance().getCustomer(customerId));
        System.out.println("Active services: ");
        if (ControllerImpl.getInstance().getActiveServices(customerId) != null)
        {
            for (Service service : ControllerImpl.getInstance().getActiveServices(customerId))
            {
                System.out.println(service);
            }
        }
    }

    private void showInactiveServices()
    {
        ArrayList<Service> suspendedServices = ControllerImpl.getInstance().getSuspendedServices(customerId);
        ArrayList<Service> enteringServices = ControllerImpl.getInstance().getEnteringServices(customerId);

        if (suspendedServices.size() > 0)
        {
            System.out.println("Suspended services: ");
            for (Service service : suspendedServices)
            {
                System.out.println(" * " + service);
            }
        }
        else
        {
            System.out.println("Приостановленных услуг нет");
        }

        if (enteringServices.size() > 0)
        {
            System.out.println("Entering services: ");
            for (Service service : enteringServices)
            {
                System.out.println(" * " + service);
            }
        }
        else
        {
            System.out.println("Подключаемых услуг нет");
        }

    }

}
