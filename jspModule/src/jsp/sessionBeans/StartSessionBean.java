package jsp.sessionBeans;

import com.netcracker.students.o3.controller.Controller;
import com.netcracker.students.o3.controller.ControllerImpl;
import com.netcracker.students.o3.model.area.Area;

import java.util.List;

import javax.ejb.Stateless;

@Stateless
public class StartSessionBean
{
    public List<Area> getAreas(){
        Controller controller = ControllerImpl.getInstance();
        List<Area> areas = controller.getAreas();
        return areas;
    }
}
