package com.netcracker.students.o3.Exceptions;

import java.io.IOException;

public class PasswordException extends IOException
{
    public PasswordException()
    {
    }

    public PasswordException(final String message)
    {
        super(message);
    }
}
