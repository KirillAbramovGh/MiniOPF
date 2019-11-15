package com.netcracker.students.o3.model.users;

import java.math.BigInteger;

public interface User
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
