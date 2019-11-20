import com.netcracker.students.o3.model.Model;
import com.netcracker.students.o3.model.serializer.Serializer;
import com.netcracker.students.o3.model.serializer.SerializerImpl;
import com.netcracker.students.o3.model.users.CustomerImpl;
import com.netcracker.students.o3.model.users.EmployerImpl;

import java.io.IOException;

public class Main
{
    public static void main(String[] args) throws IOException
    {
        Model model = Model.getInstance();

        model.addEmployee(new EmployerImpl());

        model.addCustomer(new CustomerImpl());
        Serializer serializer = new SerializerImpl();
       serializer.serializeModel();
        serializer.deserializeModel();
    }
}
