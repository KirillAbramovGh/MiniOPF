package com.netcracker.students.o3;

import com.netcracker.students.o3.model.model.Model;
import com.netcracker.students.o3.model.model.ModelBD;
import com.netcracker.students.o3.model.serializer.ExporterJsonToDb;

import java.io.IOException;
import java.sql.SQLException;

public class Main
{
    public static void main(String[] args) throws ClassNotFoundException, IOException, SQLException
    {
        Model model = ModelBD.getInstance();

        ExporterJsonToDb exporterJsonToDb = new ExporterJsonToDb();
        exporterJsonToDb.clearBd();
        exporterJsonToDb.exportToDb();
    }


}
