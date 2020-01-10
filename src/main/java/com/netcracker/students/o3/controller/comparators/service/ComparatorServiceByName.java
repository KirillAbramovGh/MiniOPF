package com.netcracker.students.o3.controller.comparators.service;

import com.netcracker.students.o3.model.services.Service;

import java.util.Comparator;

public class ComparatorServiceByName implements Comparator<Service>
{
    private boolean isUp;

    public ComparatorServiceByName(boolean isUp){
        this.isUp = isUp;
    }

    @Override
    public int compare(final Service o1, final Service o2)
    {
        int res = o1.getName().compareTo(o2.getName());

        if(isUp) return res;
        else return (-1)*res;
    }
}
