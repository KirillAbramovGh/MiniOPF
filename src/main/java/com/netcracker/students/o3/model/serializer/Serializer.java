package com.netcracker.students.o3.model.serializer;

import com.netcracker.students.o3.model.model.ModelJson;

import java.io.IOException;

/**
 * serialise model to json and write to file
 * deserialize model from json
 */
public interface Serializer
{
    /**
     * serialise model to json and write to file
     * @param modelJson we need to serialize
     */
    void serializeModel(ModelJson modelJson) throws IOException;

    /**
     * read json from file, deserialize to model and set it to param
     * @param modelJson we need deserialize to it
     */
    void deserializeModel(ModelJson modelJson) throws IOException;
}
