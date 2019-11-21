package com.netcracker.students.o3.controller.command;

import com.netcracker.students.o3.view.EmployeeView;

public enum Command
{

    CHANGE_BALANCE()
            {
                public void execute(String[] attrs)
                {

                }
            },

    COMPLETE_ORDER()
            {
                public void execute(String[] attrs)
                {

                }
            },

    EXIT
            {
                public void execute(String[] attrs)
                {

                }
            },

    HELP
            {
                public void execute(String[] attrs)
                {
                    //for по массиву стринг
                    EmployeeView.showHelp();

                }
            },

    LOGIN
            {
                public void execute(String[] attrs)
                {

                }
            },

    REGISTER
            {
                public void execute(String[] attrs)
                {

                }
            },

    START
            {
                public void execute(String[] attrs)
                {

                }
            },

    START_ORDER()
            {
                public void execute(String[] attrs)
                {

                }
            },

    STOP_ORDER()
            {
                public void execute(String[] attrs)
                {

                }
            },

    SUSPEND_ORDER()
            {
                public void execute(String[] attrs)
                {

                }
            },

    CREATE_CUSTOMER()
            {
                public void execute(String[] attrs)
                {

                }
            },

    CREATE_EMPLOYEE()
            {
                public void execute(String[] attrs)
                {

                }
            },

    GET_TEMPLATES_BY_AREA_ID()
            {
                public void execute(String[] attrs)
                {

                }
            },

    GET_ALL_TEMPLATES()
            {
                public void execute(String[] attrs)
                {

                }
            },

    GET_ORDERS_BY_CUSTOMER_ID()
            {
                public void execute(String[] attrs)
                {

                }
            },

    GET_ORDERS_BY_EMPLOYEE_ID()
            {
                public void execute(String[] attrs)
                {

                }
            },

    CREATE_TEMPLATE()
            {
                public void execute(String[] attrs)
                {

                }
            },

    CREATE_AREA()
            {
                public void execute(String[] attrs)
                {

                }
            };

    public void execute(String[] attrs){};
}
