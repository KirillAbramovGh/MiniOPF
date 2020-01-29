package jsp.servlets;

import com.netcracker.students.o3.controller.sorters.SortType.*;
import jsp.EmployeeWebOperations;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.math.BigInteger;

@WebServlet("/employeeServlet")
public class EmployeeServlet extends HttpServlet {
    private EmployeeWebOperations employeeWebOperations = EmployeeWebOperations.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BigInteger id = (BigInteger) req.getSession().getAttribute("id");
        employeeWebOperations.start(id);
        cleanSession(req.getSession());
        for (String key : req.getParameterMap().keySet()) {
            if (req.getParameterMap().get(key) != null) {
                parse(key, req, resp);
            }
        }

        forward("/webEmployeeView.jsp", req, resp);
    }

    private void parse(String key, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String[] res = key.split(" ");
        String command = res[0];
        BigInteger id = null;
        if (res.length > 1) {
            id = BigInteger.valueOf(Long.parseLong(res[1]));
        }

        switch (command) {
            case "out":
                forward("/startView.jsp", req, resp);
                break;
            case "save":
                String name = req.getParameter("fio");
                String password = req.getParameter("password");

                employeeWebOperations.changeNameAndPassword(name, password);
                break;
            case "startOrder":
                employeeWebOperations.startOrder(id);
                break;
            case "resumeOrder":
                employeeWebOperations.resumeOrder(id);
                break;
            case "completeOrder":
                employeeWebOperations.completeOrder(id);
                break;
            case "deleteTemplate":
                employeeWebOperations.deleteTemplate(id);
                break;
            case "deleteCustomer":
                employeeWebOperations.deleteCustomer(id);
                break;
            case "deleteEmployee":
                employeeWebOperations.deleteEmployee(id);
                break;
            case "deleteArea":
                employeeWebOperations.deleteArea(id);
                break;
            case "ServiceSortUpByName":
                setAttribute("sortServices", ServiceSortType.UpByName, req);
                break;
            case "ServiceSortDownByName":
                setAttribute("sortServices", ServiceSortType.DownByName, req);
                break;
            case "ServiceSortUpByCost":
                setAttribute("sortServices", ServiceSortType.UpByCost, req);
                break;
            case "ServiceSortDownByCost":
                setAttribute("sortServices", ServiceSortType.DownByCost, req);
                break;
            case "ServiceSortUpById":
                setAttribute("sortServices", ServiceSortType.UpById, req);
                break;
            case "ServiceSortDownById":
                setAttribute("sortServices", ServiceSortType.DownById, req);
                break;
            case "TemplateSortUpByName":
                setAttribute("sortTemplates", TemplateSortType.UpByName, req);
                break;
            case "TemplateSortDownByName":
                setAttribute("sortTemplates", TemplateSortType.DownByName, req);
                break;
            case "TemplateSortUpByCost":
                setAttribute("sortTemplates", TemplateSortType.UpByCost, req);
                break;
            case "TemplateSortDownByCost":
                setAttribute("sortTemplates", TemplateSortType.DownByCost, req);
                break;
            case "TemplateSortUpById":
                setAttribute("sortTemplates", TemplateSortType.UpById, req);
                break;
            case "TemplateSortDownById":
                setAttribute("sortTemplates", TemplateSortType.DownById, req);
                break;
            case "OrderSortUpById":
                setAttribute("sortOrders", OrderSortType.UpById, req);
                break;
            case "OrderSortDownById":
                setAttribute("sortOrders", OrderSortType.DownById, req);
                break;
            case "EmployeeSortUpById":
                setAttribute("sortEmployees", EmployeeSortType.UpById, req);
                break;
            case "EmployeeSortDownById":
                setAttribute("sortEmployees", EmployeeSortType.DownById, req);
                break;
            case "EmployeeSortUpByLogin":
                setAttribute("sortEmployees", EmployeeSortType.UpByLogin, req);
                break;
            case "EmployeeSortDownByLogin":
                setAttribute("sortEmployees", EmployeeSortType.DownByLogin, req);
                break;
            case "EmployeeSortUpByName":
                setAttribute("sortEmployees", EmployeeSortType.UpByName, req);
                break;
            case "EmployeeSortDownByName":
                setAttribute("sortEmployees", EmployeeSortType.DownByName, req);
                break;
            case "CustomerSortUpByBalance":
                setAttribute("sortCustomers", CustomerSortType.UpByBalance, req);
                break;
            case "CustomerSortDownByBalance":
                setAttribute("sortCustomers", CustomerSortType.DownByBalance, req);
                break;
            case "CustomerSortUpById":
                setAttribute("sortCustomers", CustomerSortType.UpById, req);
                break;
            case "CustomerSortDownById":
                setAttribute("sortCustomers", CustomerSortType.DownById, req);
                break;
            case "CustomerSortUpByLogin":
                setAttribute("sortCustomers", CustomerSortType.UpByLogin, req);
                break;
            case "CustomerSortDownByLogin":
                setAttribute("sortCustomers", CustomerSortType.DownByLogin, req);
                break;
            case "CustomerSortUpByName":
                setAttribute("sortCustomers", CustomerSortType.UpByName, req);
                break;
            case "CustomerSortDownByName":
                setAttribute("sortCustomers", CustomerSortType.DownByName, req);
                break;
            case "AreaSortUpByDescription":
                setAttribute("sortAreas", AreaSortType.UpByDescription, req);
                break;
            case "AreaSortDownByDescription":
                setAttribute("sortAreas", AreaSortType.DownByDescription, req);
                break;
            case "AreaSortUpById":
                setAttribute("sortAreas", AreaSortType.UpById, req);
                break;
            case "AreaSortDownById":
                setAttribute("sortAreas", AreaSortType.DownById, req);
                break;
            case "AreaSortUpByName":
                setAttribute("sortAreas", AreaSortType.UpByName, req);
                break;
            case "AreaSortDownByName":
                setAttribute("sortAreas", AreaSortType.DownByName, req);
                break;
            case "serviceRadio":
                setAttribute("serviceRadio",true,req);
                break;
            case "templateRadio":
                setAttribute("templateRadio",true,req);
                break;
            case "orderRadio":
                setAttribute("orderRadio",true,req);
                break;
            case "areaRadio":
                setAttribute("areaRadio",true,req);
                break;
            case "customerRadio":
                setAttribute("customerRadio",true,req);
                break;
            case "employeeRadio":
                setAttribute("employeeRadio",true,req);
                break;
        }
    }

    private void forward(String path, HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ServletContext servletContext = getServletContext();
        RequestDispatcher requestDispatcher = servletContext.getRequestDispatcher(path);

        requestDispatcher.forward(request, response);
    }

    private void cleanSession(HttpSession session) {
        session.setAttribute("sortOrders", null);
        session.setAttribute("sortEmployees", null);
        session.setAttribute("sortCustomers", null);
        session.setAttribute("sortAreas", null);
        session.setAttribute("sortTemplates", null);
        session.setAttribute("sortServices", null);
    }

    private void setAttribute(String key, Object value, HttpServletRequest request) {
        request.getSession().setAttribute(key, value);
    }
}
