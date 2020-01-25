package jsp;

import com.netcracker.students.o3.controller.sorters.SortType.ServiceSortType;
import com.netcracker.students.o3.controller.sorters.SortType.TemplateSortType;
import com.netcracker.students.o3.model.area.Area;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigInteger;
import java.util.List;

public class CustomerServlet extends HttpServlet {
    CustomerWebOperations customerWebOperations = CustomerWebOperations.getInstance();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BigInteger id = (BigInteger) req.getSession().getAttribute("id");
        customerWebOperations.start(id);

        for (String key : req.getParameterMap().keySet()) {
            if (req.getParameterMap().get(key) != null) {
                if (key.startsWith("disconnect")) {
                    disconnect(key);
                } else if (key.contains("suspend") || key.contains("resume")) {
                    suspendOrResume(key);
                } else if (key.startsWith("connect")) {
                    connect(key);
                } else if (key.equals("change")) {
                    String name = req.getParameter("fio");
                    String password = req.getParameter("password");
                    String area = req.getParameter("area");

                    Area newArea = null;

                    for (Area a : customerWebOperations.getAreas()) {
                        if (a.getName().equals(area)) {
                            newArea = a;
                            break;
                        }
                    }


                    customerWebOperations.changeName(name);
                    customerWebOperations.changePassword(password);
                    customerWebOperations.changeArea(newArea);

                } else if (key.equals("searchButton")) {
                    req.getSession().setAttribute("searchField",req.getParameter("searchField"));
                } else if (key.equals("ServiceSortUpByName")) {
                    req.getSession().setAttribute("sortServices", ServiceSortType.UpByName);
                } else if (key.equals("ServiceSortDownByName")) {
                    req.getSession().setAttribute("sortServices", ServiceSortType.DownByName);
                } else if (key.equals("ServiceSortUpByCost")) {
                    req.getSession().setAttribute("sortServices", ServiceSortType.UpByCost);
                } else if (key.equals("ServiceSortDownByCost")) {
                    req.getSession().setAttribute("sortServices", ServiceSortType.DownByCost);
                } else if (key.equals("TemplateSortUpByName")) {
                    req.getSession().setAttribute("sortTemplates", TemplateSortType.UpByName);
                } else if (key.equals("TemplateSortDownByName")) {
                    req.getSession().setAttribute("sortTemplates", TemplateSortType.DownByName);
                } else if (key.equals("TemplateSortUpByCost")) {
                    req.getSession().setAttribute("sortTemplates", TemplateSortType.UpByCost);
                } else if (key.equals("TemplateSortDownByCost")) {
                    req.getSession().setAttribute("sortTemplates", TemplateSortType.DownByCost);
                } else if (key.toLowerCase().equals("out")) {
                    forward("/startView.jsp",req,resp);
                }
            }
        }

        String textValue = req.getParameter("sum");
        if (textValue != null && !textValue.isEmpty()) {
            customerWebOperations.addBalance(textValue);
        }
    }

    private String selectArea() {
        StringBuilder resultHtml = new StringBuilder();
        List<Area> availableAreas = customerWebOperations.getAreas();

        for (Area area : availableAreas) {
            if (customerWebOperations.getAreaName().equals(area.getName())) {
                resultHtml.append("<option selected value='").append(area.getName()).append("'>").append(area.getName())
                        .append("</option>");
            } else {
                resultHtml.append("<option>").append(area.getName()).append("</option>");
            }
        }
        return resultHtml.toString();
    }

    private void disconnect(String key) {
        String[] res = key.split(" ");
        String numb = res[1];
        if (!numb.equals("")) {
            int value = Integer.parseInt(numb);
            customerWebOperations.disconnectService(BigInteger.valueOf(value));
        }
    }

    private void suspendOrResume(String key) {
        String[] res = key.split(" ");
        String numb = res[1];
        if (!numb.equals("")) {
            int value = Integer.parseInt(numb);
            customerWebOperations.suspendOrResumeService(BigInteger.valueOf(value));
        }
    }

    private void connect(String key) {
        String[] res = key.split(" ");
        String numb = res[1];
        if (!numb.equals("")) {
            int value = Integer.parseInt(numb);
            customerWebOperations.connectService(BigInteger.valueOf(value));
        }
    }
    private void forward(String path, HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ServletContext servletContext = getServletContext();
        RequestDispatcher requestDispatcher = servletContext.getRequestDispatcher(path);

        requestDispatcher.forward(request, response);
    }
}
