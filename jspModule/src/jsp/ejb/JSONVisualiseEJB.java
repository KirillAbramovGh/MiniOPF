package jsp.ejb;

import com.netcracker.students.o3.controller.ControllerImpl;
import com.netcracker.students.o3.model.serializer.Serializer;
import com.netcracker.students.o3.model.serializer.SerializerImpl;

import java.io.IOException;
import java.math.BigInteger;

import javax.ejb.Stateless;

@Stateless
public class JSONVisualiseEJB
{
    public String getJsonFromEntity(BigInteger entityId){
        Serializer serializer = new SerializerImpl();

        try
        {
           return serializer.serializeToString(ControllerImpl.getInstance().getEntity(entityId));
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return "error";
    }
}
