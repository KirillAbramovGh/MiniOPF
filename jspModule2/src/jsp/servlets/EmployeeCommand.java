package jsp.servlets;


import com.netcracker.students.o3.Exceptions.UnpossibleChangeAreaException;
import com.netcracker.students.o3.Exceptions.WrongInputException;
import com.netcracker.students.o3.controller.sorters.SortType.CustomerSortType;
import com.netcracker.students.o3.controller.sorters.SortType.EmployeeSortType;
import com.netcracker.students.o3.controller.sorters.SortType.OrderSortType;
import com.netcracker.students.o3.controller.sorters.SortType.ServiceSortType;
import com.netcracker.students.o3.controller.sorters.SortType.TemplateSortType;
import com.netcracker.students.o3.model.area.Area;

import java.io.IOException;
import java.math.BigInteger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jsp.CustomerWebOperations;
import jsp.EmployeeWebOperations;

public enum EmployeeCommand
{

    updateCustomer{
        public void execute(HttpServletRequest req, final HttpServletResponse resp,
                final ServletContext context, EmployeeWebOperations employeeWebOperations,
                final String key) throws ServletException, IOException
        {
            BigInteger id = getIdFromKey(key);
            req.getSession().setAttribute("updateCustomerId",id);
            req.getSession().setAttribute("nextPage","/UpdateJSP/updateCustomer.jsp");
        }
    },
    updateService{
        public void execute(HttpServletRequest req, final HttpServletResponse resp,
                final ServletContext context, EmployeeWebOperations employeeWebOperations,
                final String key) throws ServletException, IOException
        {
            BigInteger id = getIdFromKey(key);
            req.getSession().setAttribute("updateServiceId",id);
            req.getSession().setAttribute("nextPage","/UpdateJSP/updateService.jsp");
        }
    },
    updateTemplate{
        public void execute(HttpServletRequest req, final HttpServletResponse resp,
                final ServletContext context, EmployeeWebOperations employeeWebOperations,
                final String key) throws ServletException, IOException
        {
            BigInteger id = getIdFromKey(key);
            req.getSession().setAttribute("updateTemplateId",id);
            req.getSession().setAttribute("nextPage","/UpdateJSP/updateTemplate.jsp");
        }
    },
    updateOrder{
        public void execute(HttpServletRequest req, final HttpServletResponse resp,
                final ServletContext context, EmployeeWebOperations employeeWebOperations,
                final String key) throws ServletException, IOException
        {
            BigInteger id = getIdFromKey(key);
            req.getSession().setAttribute("updateOrderId",id);
            req.getSession().setAttribute("nextPage","/UpdateJSP/updateOrder.jsp");
        }
    },
    updateArea{
        public void execute(HttpServletRequest req, final HttpServletResponse resp,
                final ServletContext context, EmployeeWebOperations employeeWebOperations,
                final String key) throws ServletException, IOException
        {
            BigInteger id = getIdFromKey(key);
            req.getSession().setAttribute("updateAreaId",id);
            req.getSession().setAttribute("nextPage","/UpdateJSP/updateArea.jsp");
        }
    },
    updateEmployee{
        public void execute(HttpServletRequest req, final HttpServletResponse resp,
                final ServletContext context, EmployeeWebOperations employeeWebOperations,
                final String key) throws ServletException, IOException
        {
            BigInteger id = getIdFromKey(key);
            req.getSession().setAttribute("updateEmployeeId",id);
            req.getSession().setAttribute("nextPage","/UpdateJSP/updateEmployee.jsp");
        }
    },
    save
            {
                public void execute(HttpServletRequest req, final HttpServletResponse resp,
                        final ServletContext context, EmployeeWebOperations employeeWebOperations,
                        final String key)
                {
                    String name = req.getParameter("fio");
                    String password = req.getParameter("password");

                    employeeWebOperations.changeNameAndPassword(name, password);
                }
            },

    startOrder
            {
                public void execute(HttpServletRequest req, final HttpServletResponse resp,
                        final ServletContext context, EmployeeWebOperations employeeWebOperations,
                        final String key)
                {
                    BigInteger id = getIdFromKey(key);
                    employeeWebOperations.startOrder(id);
                }
            },

    resumeOrder
            {
                public void execute(HttpServletRequest req, final HttpServletResponse resp,
                        final ServletContext context, EmployeeWebOperations employeeWebOperations,
                        final String key)
                {
                    BigInteger id = getIdFromKey(key);
                    employeeWebOperations.resumeOrder(id);
                }
            },

    completeOrder
            {
                public void execute(HttpServletRequest req, final HttpServletResponse resp,
                        final ServletContext context, EmployeeWebOperations employeeWebOperations,
                        final String key)
                {
                    BigInteger id = getIdFromKey(key);
                    employeeWebOperations.completeOrder(id);
                }
            },

    deleteTemplate
            {
                public void execute(HttpServletRequest req, final HttpServletResponse resp,
                        final ServletContext context, EmployeeWebOperations employeeWebOperations,
                        final String key)
                {
                    BigInteger id = getIdFromKey(key);
                    employeeWebOperations.deleteTemplate(id);
                }
            },
    deleteOrder
            {
                public void execute(HttpServletRequest req, final HttpServletResponse resp,
                        final ServletContext context, EmployeeWebOperations employeeWebOperations,
                        final String key)
                {
                    BigInteger id = getIdFromKey(key);
                    employeeWebOperations.deleteOrder(id);
                }
            },
    deleteService
            {
                public void execute(HttpServletRequest req, final HttpServletResponse resp,
                        final ServletContext context, EmployeeWebOperations employeeWebOperations,
                        final String key)
                {
                    BigInteger id = getIdFromKey(key);
                    employeeWebOperations.deleteService(id);
                }
            },
    deleteCustomer
            {
                public void execute(HttpServletRequest req, final HttpServletResponse resp,
                        final ServletContext context, EmployeeWebOperations employeeWebOperations,
                        final String key)
                {
                    BigInteger id = getIdFromKey(key);
                    employeeWebOperations.deleteCustomer(id);
                }
            },

    deleteEmployee
            {
                public void execute(HttpServletRequest req, final HttpServletResponse resp,
                        final ServletContext context, EmployeeWebOperations employeeWebOperations,
                        final String key)
                {
                    BigInteger id = getIdFromKey(key);
                    employeeWebOperations.deleteEmployee(id);
                }
            },

    deleteArea
            {
                public void execute(HttpServletRequest req, final HttpServletResponse resp,
                        final ServletContext context, EmployeeWebOperations employeeWebOperations,
                        final String key)
                {
                    BigInteger id = getIdFromKey(key);
                    employeeWebOperations.deleteArea(id);
                }
            },
    ServiceSortUpByName
            {
                public void execute(HttpServletRequest req, final HttpServletResponse resp,
                        final ServletContext context, EmployeeWebOperations employeeWebOperations,
                        final String key)
                {
                    setAttribute("sortServices", ServiceSortType.UpByName, req);
                }
            },
    ServiceSortDownByName
            {
                public void execute(HttpServletRequest req, final HttpServletResponse resp,
                        final ServletContext context, EmployeeWebOperations employeeWebOperations,
                        final String key)
                {
                    setAttribute("sortServices", ServiceSortType.DownByName, req);
                }
            },

    ServiceSortUpByCost
            {
                public void execute(HttpServletRequest req, final HttpServletResponse resp,
                        final ServletContext context, EmployeeWebOperations employeeWebOperations,
                        final String key)
                {
                    setAttribute("sortServices", ServiceSortType.UpByCost, req);
                }
            },

    ServiceSortDownByCost
            {
                public void execute(HttpServletRequest req, final HttpServletResponse resp,
                        final ServletContext context, EmployeeWebOperations employeeWebOperations,
                        final String key)
                {
                    setAttribute("sortServices", ServiceSortType.DownByCost, req);
                }
            },
    ServiceSortUpById
            {
                public void execute(HttpServletRequest req, final HttpServletResponse resp,
                        final ServletContext context, EmployeeWebOperations employeeWebOperations,
                        final String key)
                {
                    setAttribute("sortServices", ServiceSortType.UpById, req);

                }
            },
    ServiceSortDownById
            {
                public void execute(HttpServletRequest req, final HttpServletResponse resp,
                        final ServletContext context, EmployeeWebOperations employeeWebOperations,
                        final String key)
                {
                    setAttribute("sortServices", ServiceSortType.DownById, req);
                }
            },


    TemplateSortUpByName
            {
                public void execute(HttpServletRequest req, final HttpServletResponse resp,
                        final ServletContext context, EmployeeWebOperations employeeWebOperations,
                        final String key)
                {
                    setAttribute("sortTemplates", TemplateSortType.UpByName, req);
                }
            },

    TemplateSortDownByName
            {
                public void execute(HttpServletRequest req, final HttpServletResponse resp,
                        final ServletContext context, EmployeeWebOperations employeeWebOperations,
                        final String key)
                {
                    setAttribute("sortTemplates", TemplateSortType.DownByName, req);
                }
            },

    TemplateSortUpByCost
            {
                public void execute(HttpServletRequest req, final HttpServletResponse resp,
                        final ServletContext context, EmployeeWebOperations employeeWebOperations,
                        final String key)
                {
                    setAttribute("sortTemplates", TemplateSortType.UpByCost, req);
                }
            },

    TemplateSortDownByCost
            {
                public void execute(HttpServletRequest req, final HttpServletResponse resp,
                        final ServletContext context, EmployeeWebOperations employeeWebOperations,
                        final String key)
                {
                    setAttribute("sortTemplates", TemplateSortType.DownByCost, req);
                }
            },


    TemplateSortUpById
            {
                public void execute(HttpServletRequest req, final HttpServletResponse resp,
                        final ServletContext context, EmployeeWebOperations employeeWebOperations,
                        final String key)
                {
                    setAttribute("sortTemplates", TemplateSortType.UpById, req);
                }
            },
    TemplateSortDownById
            {
                public void execute(HttpServletRequest req, final HttpServletResponse resp,
                        final ServletContext context, EmployeeWebOperations employeeWebOperations,
                        final String key)
                {
                    setAttribute("sortTemplates", TemplateSortType.DownById, req);
                }
            },
    OrderSortUpById
            {
                public void execute(HttpServletRequest req, final HttpServletResponse resp,
                        final ServletContext context, EmployeeWebOperations employeeWebOperations,
                        final String key)
                {
                    setAttribute("sortOrders", OrderSortType.UpById, req);
                }
            },
    OrderSortDownById
            {
                public void execute(HttpServletRequest req, final HttpServletResponse resp,
                        final ServletContext context, EmployeeWebOperations employeeWebOperations,
                        final String key)
                {
                    setAttribute("sortOrders", OrderSortType.DownById, req);
                }
            },
    EmployeeSortUpById
            {
                public void execute(HttpServletRequest req, final HttpServletResponse resp,
                        final ServletContext context, EmployeeWebOperations employeeWebOperations,
                        final String key)
                {
                    setAttribute("sortEmployees", EmployeeSortType.UpById, req);
                }
            },
    EmployeeSortDownById
            {
                public void execute(HttpServletRequest req, final HttpServletResponse resp,
                        final ServletContext context, EmployeeWebOperations employeeWebOperations,
                        final String key)
                {
                    setAttribute("sortEmployees", EmployeeSortType.DownById, req);
                }
            },
    EmployeeSortUpByLogin
            {
                public void execute(HttpServletRequest req, final HttpServletResponse resp,
                        final ServletContext context, EmployeeWebOperations employeeWebOperations,
                        final String key)
                {
                    setAttribute("sortEmployees", EmployeeSortType.UpByLogin, req);
                }
            },
    EmployeeSortDownByLogin
            {
                public void execute(HttpServletRequest req, final HttpServletResponse resp,
                        final ServletContext context, EmployeeWebOperations employeeWebOperations,
                        final String key)
                {
                    setAttribute("sortEmployees", EmployeeSortType.DownByLogin, req);
                }
            },
    EmployeeSortUpByName
            {
                public void execute(HttpServletRequest req, final HttpServletResponse resp,
                        final ServletContext context, EmployeeWebOperations employeeWebOperations,
                        final String key)
                {
                    setAttribute("sortEmployees", EmployeeSortType.UpByName, req);
                }
            },
    EmployeeSortDownByName
            {
                public void execute(HttpServletRequest req, final HttpServletResponse resp,
                        final ServletContext context, EmployeeWebOperations employeeWebOperations,
                        final String key)
                {
                    setAttribute("sortEmployees", EmployeeSortType.DownByName, req);
                }
            },
    CustomerSortUpByBalance
            {
                public void execute(HttpServletRequest req, final HttpServletResponse resp,
                        final ServletContext context, EmployeeWebOperations employeeWebOperations,
                        final String key)
                {
                    setAttribute("sortCustomers", CustomerSortType.UpByBalance, req);
                }
            },
    CustomerSortDownByBalance
            {
                public void execute(HttpServletRequest req, final HttpServletResponse resp,
                        final ServletContext context, EmployeeWebOperations employeeWebOperations,
                        final String key)
                {
                    setAttribute("sortCustomers", CustomerSortType.DownByBalance, req);

                }
            },
    CustomerSortUpById
            {
                public void execute(HttpServletRequest req, final HttpServletResponse resp,
                        final ServletContext context, EmployeeWebOperations employeeWebOperations,
                        final String key)
                {
                    setAttribute("sortCustomers", CustomerSortType.UpById, req);

                }
            },
    CustomerSortDownById
            {
                public void execute(HttpServletRequest req, final HttpServletResponse resp,
                        final ServletContext context, EmployeeWebOperations employeeWebOperations,
                        final String key)
                {
                    setAttribute("sortCustomers", CustomerSortType.DownById, req);

                }
            },
    CustomerSortUpByLogin
            {
                public void execute(HttpServletRequest req, final HttpServletResponse resp,
                        final ServletContext context, EmployeeWebOperations employeeWebOperations,
                        final String key)
                {
                    setAttribute("sortCustomers", CustomerSortType.UpByLogin, req);

                }
            },
    CustomerSortDownByLogin
            {
                public void execute(HttpServletRequest req, final HttpServletResponse resp,
                        final ServletContext context, EmployeeWebOperations employeeWebOperations,
                        final String key)
                {
                    setAttribute("sortCustomers", CustomerSortType.DownByLogin, req);

                }
            },
    CustomerSortUpByName
            {
                public void execute(HttpServletRequest req, final HttpServletResponse resp,
                        final ServletContext context, EmployeeWebOperations employeeWebOperations,
                        final String key)
                {

                }
            },
    CustomerSortDownByName
            {
                public void execute(HttpServletRequest req, final HttpServletResponse resp,
                        final ServletContext context, EmployeeWebOperations employeeWebOperations,
                        final String key)
                {

                }
            },
    AreaSortUpByDescription
            {
                public void execute(HttpServletRequest req, final HttpServletResponse resp,
                        final ServletContext context, EmployeeWebOperations employeeWebOperations,
                        final String key)
                {

                }
            },
    AreaSortDownByDescription
            {
                public void execute(HttpServletRequest req, final HttpServletResponse resp,
                        final ServletContext context, EmployeeWebOperations employeeWebOperations,
                        final String key)
                {

                }
            },
    AreaSortUpById
            {
                public void execute(HttpServletRequest req, final HttpServletResponse resp,
                        final ServletContext context, EmployeeWebOperations employeeWebOperations,
                        final String key)
                {

                }
            },
    AreaSortDownById
            {
                public void execute(HttpServletRequest req, final HttpServletResponse resp,
                        final ServletContext context, EmployeeWebOperations employeeWebOperations,
                        final String key)
                {

                }
            },
    AreaSortUpByName
            {
                public void execute(HttpServletRequest req, final HttpServletResponse resp,
                        final ServletContext context, EmployeeWebOperations employeeWebOperations,
                        final String key)
                {

                }
            },
    AreaSortDownByName
            {
                public void execute(HttpServletRequest req, final HttpServletResponse resp,
                        final ServletContext context, EmployeeWebOperations employeeWebOperations,
                        final String key)
                {

                }
            },
    serviceRadio
            {
                public void execute(HttpServletRequest req, final HttpServletResponse resp,
                        final ServletContext context, EmployeeWebOperations employeeWebOperations,
                        final String key)
                {

                }
            },
    templateRadio
            {
                public void execute(HttpServletRequest req, final HttpServletResponse resp,
                        final ServletContext context, EmployeeWebOperations employeeWebOperations,
                        final String key)
                {

                }
            },
    orderRadio
            {
                public void execute(HttpServletRequest req, final HttpServletResponse resp,
                        final ServletContext context, EmployeeWebOperations employeeWebOperations,
                        final String key)
                {
                    setAttribute("orderRadio", true, req);

                }
            },
    areaRadio
            {
                public void execute(HttpServletRequest req, final HttpServletResponse resp,
                        final ServletContext context, EmployeeWebOperations employeeWebOperations,
                        final String key)
                {
                    setAttribute("areaRadio", true, req);

                }
            },
    customerRadio
            {
                public void execute(HttpServletRequest req, final HttpServletResponse resp,
                        final ServletContext context, EmployeeWebOperations employeeWebOperations,
                        final String key)
                {
                    setAttribute("customerRadio", true, req);

                }
            },
    employeeRadio
            {
                public void execute(HttpServletRequest req, final HttpServletResponse resp,
                        final ServletContext context, EmployeeWebOperations employeeWebOperations,
                        final String key)
                {
                    setAttribute("employeeRadio", true, req);
                }
            },

    out
            {
                public void execute(HttpServletRequest req, final HttpServletResponse resp,
                        final ServletContext context, EmployeeWebOperations employeeWebOperations,
                        final String key) throws ServletException, IOException
                {
                    forward("/startView.jsp", context, req, resp);
                }
            };

    public void execute(HttpServletRequest req, final HttpServletResponse resp,
            final ServletContext context, EmployeeWebOperations employeeWebOperations, final String key)
            throws IOException, ServletException
    {
        System.out.println("standard");
    }

    private static BigInteger getIdFromKey(String key)
    {
        String[] res = key.split(" ");
        long longValue = Long.parseLong(res[1]);
        return BigInteger.valueOf(longValue);
    }

    private static void changeSettings(final HttpServletRequest req, final CustomerWebOperations customerWebOperations)
            throws WrongInputException, UnpossibleChangeAreaException
    {
        String name = req.getParameter("fio");
        String password = req.getParameter("password");
        String area = req.getParameter("area");

        Area newArea = null;

        for (Area a : customerWebOperations.getAreas())
        {
            if (a.getName().equals(area))
            {
                newArea = a;
                break;
            }
        }

        customerWebOperations.changeName(name);
        customerWebOperations.changePassword(password);
        customerWebOperations.changeArea(newArea);
    }

    private static void setAttribute(String key, Object value, HttpServletRequest request)
    {
        request.getSession().setAttribute(key, value);
    }

    private static void forward(String path, ServletContext context, HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException
    {
        RequestDispatcher requestDispatcher = context.getRequestDispatcher(path);

        requestDispatcher.forward(request, response);
    }
}