package com.netcracker.students.o3.model.users;

import java.math.BigInteger;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "employees")
public interface Employee extends User
{
    @Column(name = "login")
    @Override
    String getLogin();

    @Id
    @Override
    BigInteger getId();

    @Column(name = "name")
    @Override
    String getName();

    @Column(name = "password")
    @Override
    String getPassword();
}
