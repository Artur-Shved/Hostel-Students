package com.hostel.hostelsite.controllers.models;

import org.springframework.stereotype.Component;

@Component
public class Dates {

    private int id;
    private String string;
    private String name;
    private String lastName;
    private int room;
    private String dateBorns;
    private String dateBorns2;
    private int numberOne;
    private int numberTwo;

    public Dates() {
    }

    public Dates(String dateBorns, String dateBorns2) {
        this.dateBorns = dateBorns;
        this.dateBorns2 = dateBorns2;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getNumberOne() {
        return numberOne;
    }

    public void setNumberOne(int numberOne) {
        this.numberOne = numberOne;
    }

    public int getNumberTwo() {
        return numberTwo;
    }

    public void setNumberTwo(int numberTwo) {
        this.numberTwo = numberTwo;
    }

    public int getRoom() {
        return room;
    }

    public void setRoom(int room) {
        this.room = room;
    }

    public String getString() {
        return string;
    }

    public void setString(String string) {
        this.string = string;
    }

    public String getDateBorns() {
        return dateBorns;
    }

    public void setDateBorns(String dateBorns) {
        this.dateBorns = dateBorns;
    }

    public String getDateBorns2() {
        return dateBorns2;
    }

    public void setDateBorns2(String dateBorns2) {
        this.dateBorns2 = dateBorns2;
    }
}
