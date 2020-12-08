package com.hostel.hostelsite.dao;


import com.hostel.hostelsite.controllers.models.Dates;
import com.hostel.hostelsite.dao.entity.User;
import com.hostel.hostelsite.dao.interfaceDao.UserDaoInterface;
import com.hostel.hostelsite.dao.interfaceSettings.Settings;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;


public  class UserSettingsImpl implements Settings {

    @Autowired
    UserDaoInterface daoInterface;

    @Override
    public boolean userInDb(String name, String lastname) {
        List<User> userList = daoInterface.allUsers();
        for (User user : userList) {
            if (user.getName().equals(name) && user.getLastname().equals(lastname)) {
                return false;
            }
        }
        return true;
    }

    public boolean userInDbByName(String name) {
        List<User> userList = daoInterface.allUsers();
        for (User user : userList) {
            if (user.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    public boolean userInDbByLastName(String lastname) {
        List<User> userList = daoInterface.allUsers();
        for (User user : userList) {
            if (user.getLastname().equals(lastname)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean numberRoom(int room) {
        return room >= 1 && room <= 100  ;
    }

    public boolean nullNameOrLastName(String name, String lastname, long room, String date_born) {
        return !name.isEmpty() && !lastname.isEmpty() && date_born.length() >= 6 && room > 0 && room <= 100;
    }

    public boolean userByRoom(int room) {
        List<User> user = daoInterface.allUsers();
        for (User value : user) {
            if (value.getRoom() == room) {
                return true;
            }
        }
        return false;
    }

    public boolean numInTheString(String a) {
        boolean b = true;
        for (int i = 0; i < a.length(); i++) {
            if (Character.isDigit(a.charAt(i))) {
                b = false;
            }
        }
        return b;
    }

    public boolean notEmptyString(Dates dates) {
        return !dates.getString().isEmpty();
    }

    public boolean notEmptyDates(Dates dates){
        return !dates.getDateBorns().isEmpty() && !dates.getDateBorns2().isEmpty();
    }

    public boolean notEmptyRoom(Dates dates){
        return dates.getNumberOne() >0 && dates.getNumberOne()<=100 && dates.getNumberTwo()>0 && dates.getNumberTwo()<=100;
    }

    public boolean nullUser(User user){
        return !user.getUsername().isEmpty() && !user.getPassword().isEmpty() && !user.getName().isEmpty() &&
        !user.getLastname().isEmpty() && user.getRoom()>=1 && user.getRoom()<=100 && !user.getDate().isEmpty();
    }
}
