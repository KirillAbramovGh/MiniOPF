import com.netcracker.students.o3.model.Model;
import com.netcracker.students.o3.model.serializer.Serializer;
import com.netcracker.students.o3.model.serializer.SerializerImpl;
import com.netcracker.students.o3.model.users.Customer;
import com.netcracker.students.o3.model.users.CustomerImpl;
import com.netcracker.students.o3.model.users.Employee;
import com.netcracker.students.o3.model.users.EmployerImpl;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;

public class Main
{
    public static void main(String[] args) throws IOException
    {
        Model model = Model.getInstance();
        Customer customer = new CustomerImpl();
        customer.setId(BigInteger.valueOf(0));
        customer.setName("fff");
        customer.setAreaId(BigInteger.valueOf(0));
        customer.setPassword("pas");
        customer.setLogin("login");
        customer.setMoneyBalance(BigDecimal.valueOf(0));
        Employee employee = new EmployerImpl();
        employee.setId(BigInteger.valueOf(0));
        employee.setName("fff");
        employee.setPassword("pas");
        employee.setLogin("login");
        model.addEmployee(employee);

        model.addCustomer(customer);
        Serializer serializer = new SerializerImpl();
       serializer.serializeModel();
        serializer.deserializeModel();
    }
}
