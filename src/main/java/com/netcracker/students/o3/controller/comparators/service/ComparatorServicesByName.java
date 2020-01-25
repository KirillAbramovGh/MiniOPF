package com.netcracker.students.o3.controller.comparators.service;

import com.netcracker.students.o3.model.services.Service;

import java.util.Comparator;

public class ComparatorServicesByName implements Comparator<Service>
{
    /**
     * define sort up or down
     */
    private boolean isUp;

    public ComparatorServicesByName(boolean isUp){
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