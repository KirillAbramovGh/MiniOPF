package com.netcracker.students.o3.controller.comparators.service;

import com.netcracker.students.o3.model.services.Service;

import java.util.Comparator;

public class ComparatorServicesByActivationDate implements Comparator<Service>
{
    /**
     * define sort up or down
     */
    private boolean isUp;

    public ComparatorServicesByActivationDate(boolean isUp){
        this.isUp = isUp;
    }

    @Override
    public int compare(final Service o1, final Service o2)
    {
        int res = o1.getActivationDate().compareTo(o2.getActivationDate());

        if(isUp) return res;
        else return (-1)*res;
    }
}
