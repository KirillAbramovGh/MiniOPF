package com.netcracker.students.o3.model.serializer;

import java.io.IOException;

public interface Serializer
{
    void serializeModel() throws IOException;
    void deserializeModel() throws IOException;
}
