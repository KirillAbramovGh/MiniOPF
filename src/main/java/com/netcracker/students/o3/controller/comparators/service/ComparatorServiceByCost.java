package com.netcracker.students.o3.controller.comparators.service;

import com.netcracker.students.o3.model.services.Service;

import java.util.Comparator;

public class ComparatorServiceByCost implements Comparator<Service>
{
    /**
     * define sort up or down
     */
    private boolean isUp;

    public ComparatorServiceByCost(boolean isUp){
        this.isUp = isUp;
    }

    @Override
    public int compare(final Service o1, final Service o2)
    {
        int res = o1.getCost().compareTo(o2.getCost());

        if(isUp) return res;
        else return (-1)*res;
    }
}
