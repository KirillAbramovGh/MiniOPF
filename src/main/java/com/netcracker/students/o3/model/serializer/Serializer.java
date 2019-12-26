package com.netcracker.students.o3.model.serializer;

import com.netcracker.students.o3.model.Model;
import com.netcracker.students.o3.model.users.Customer;

import java.io.IOException;

public interface Serializer
{
    void serializeModel(Model model) throws IOException;
    void deserializeModel(Model model) throws IOException;
}
