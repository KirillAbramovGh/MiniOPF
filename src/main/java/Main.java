import com.netcracker.students.o3.model.Model;
import com.netcracker.students.o3.model.serializer.Serializer;
import com.netcracker.students.o3.model.serializer.SerializerImpl;
import com.netcracker.students.o3.view.startConsoleView;

import java.io.IOException;

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
