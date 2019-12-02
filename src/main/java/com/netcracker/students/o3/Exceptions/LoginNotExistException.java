package com.netcracker.students.o3.Exceptions;

import java.io.IOException;

public class LoginNotExistException extends IOException
{
    public LoginNotExistException(){
        super();
    }

    public LoginNotExistException(final String s){
        super(s);
    }
}
