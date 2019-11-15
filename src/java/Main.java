import com.netcracker.students.o3.model.Model;
import com.netcracker.students.o3.model.Serializer;
import com.netcracker.students.o3.model.templates.TemplateImpl;
import com.netcracker.students.o3.model.users.CustomerImpl;
import com.netcracker.students.o3.model.users.EmployerImpl;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;

public class Main
{
    public static void main(String[] args) throws IOException
    {
        Model model = Model.getInstance();
        model.addCustomer(new CustomerImpl(BigInteger.valueOf(1), BigDecimal.valueOf(0)));
        model.addCustomer(new CustomerImpl(BigInteger.valueOf(2), BigDecimal.valueOf(1000)));
        model.addCustomer(new CustomerImpl(BigInteger.valueOf(3), BigDecimal.valueOf(5000)));
        model.addCustomer(new CustomerImpl(BigInteger.valueOf(4), BigDecimal.valueOf(100)));
        model.addEmployee(new EmployerImpl(BigInteger.valueOf(5), "Jo"));
        model.addEmployee(new EmployerImpl(BigInteger.valueOf(6), "Erl"));
        model.addTemplate(new TemplateImpl());


        Serializer serializer = new Serializer();
        serializer.serializeModel();
        serializer.deserializeModel();
    }
}
