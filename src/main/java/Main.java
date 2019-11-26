import com.netcracker.students.o3.model.Model;
import com.netcracker.students.o3.model.serializer.Serializer;
import com.netcracker.students.o3.model.serializer.SerializerImpl;
import com.netcracker.students.o3.model.users.Customer;
import com.netcracker.students.o3.model.users.CustomerImpl;
import com.netcracker.students.o3.model.users.Employee;
import com.netcracker.students.o3.model.users.EmployerImpl;
import com.netcracker.students.o3.view.startConsoleView;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;

public class Main
{
    public static void main(String[] args) throws IOException
    {
        Model model = Model.getInstance();


        Serializer serializer = new SerializerImpl();
        serializer.deserializeModel(model);

        new startConsoleView().start();
    }
}
