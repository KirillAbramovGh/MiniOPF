package com.netcracker.students.o3.model.serializer;

import com.netcracker.students.o3.model.Model;

import java.io.IOException;

/**
 * serialise model to json and write to file
 * deserialize model from json
 */
public interface Serializer
{
    /**
     * serialise model to json and write to file
     * @param model we need to serialize
     */
    void serializeModel(Model model) throws IOException;

    /**
     * read json from file, deserialize to model and set it to param
     * @param model we need deserialize to it
     */
    void deserializeModel(Model model) throws IOException;
}
