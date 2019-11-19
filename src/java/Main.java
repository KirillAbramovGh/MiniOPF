import com.netcracker.students.o3.model.Model;
import com.netcracker.students.o3.model.serializer.Serializer;
import com.netcracker.students.o3.model.serializer.SerializerImpl;
import com.netcracker.students.o3.model.templates.TemplateImpl;
import com.netcracker.students.o3.model.users.CustomerImpl;
import com.netcracker.students.o3.model.users.EmployerImpl;
import com.netcracker.students.o3.view.CustomerView;
import com.netcracker.students.o3.view.View;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;

public class Main
{
    public static void main(String[] args) throws IOException
    {
        View view = new CustomerView();
        view.start();
    }
}
