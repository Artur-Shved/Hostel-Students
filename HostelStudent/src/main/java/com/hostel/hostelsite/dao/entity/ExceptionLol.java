package com.hostel.hostelsite.dao.entity;

import org.hibernate.tool.schema.spi.CommandAcceptanceException;
import org.hibernate.tool.schema.spi.ExceptionHandler;

public class ExceptionLol implements ExceptionHandler {

    @Override
    public void handleException(CommandAcceptanceException e) {
        System.out.println("hello");
    }
}
