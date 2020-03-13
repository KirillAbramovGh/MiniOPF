package com.netcracker.students.o3;

import com.netcracker.students.o3.model.area.Area;
import com.netcracker.students.o3.model.area.AreaImpl;
import com.netcracker.students.o3.model.model.Model;
import com.netcracker.students.o3.model.model.ModelBD;
import com.netcracker.students.o3.model.serializer.ExporterJsonToDb;
import com.netcracker.students.o3.model.serializer.XMLLog.XMLRequest;
import com.netcracker.students.o3.model.serializer.XMLLog.XMLRequestsWrapper;
import com.netcracker.students.o3.model.serializer.XMLLog.XMLSerializer;
import com.netcracker.students.o3.model.users.CustomerImpl;

import java.io.IOException;
import java.math.BigInteger;
import java.sql.SQLException;

import javax.xml.bind.JAXBException;

public class Main
{
    public static void main(String[] args) throws ClassNotFoundException, IOException, SQLException, JAXBException
    {

    }

    public static void downloadDataFromJSON() throws SQLException, ClassNotFoundException, IOException
    {
        Model model = ModelBD.getInstance();

        ExporterJsonToDb exporterJsonToDb = new ExporterJsonToDb();
        exporterJsonToDb.clearBd();
        exporterJsonToDb.exportToDb();
    }

}
