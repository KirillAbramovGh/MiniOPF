package com.netcracker.students.o3.users;

import java.math.BigInteger;

public interface Customer extends User
{
     BigInteger getId();

     void setId(final BigInteger id);

     String getName();

     void setName(final String name);

     String getLogin();

     void setLogin(final String login);

     String getPassword();

     void setPassword(final String password);
}
