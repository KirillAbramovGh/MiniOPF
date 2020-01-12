package com.netcracker.students.o3.model.users;

import java.math.BigInteger;

/**
 * user
 */
public interface User
{
    /**
     * @return user id
     * */
    BigInteger getId();

    /**
     * set user id
     * */
    void setId(final BigInteger id);

    /**
     * @return user name
     * */
    String getName();

    /**
     * set user name
     * */
    void setName(final String name);

    /**
     * @return user login
     * */
    String getLogin();

    /**
     * set user login
     * */
    void setLogin(final String login);

    /**
     * @return user password
     * */
    String getPassword();

    /**
     * set user password
     * */
    void setPassword(final String password);
}
