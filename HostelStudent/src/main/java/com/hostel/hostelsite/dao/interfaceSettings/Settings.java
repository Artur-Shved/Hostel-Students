package com.hostel.hostelsite.dao.interfaceSettings;

import com.hostel.hostelsite.controllers.models.Dates;
import com.hostel.hostelsite.dao.entity.User;
import org.springframework.stereotype.Service;

@Service
public interface Settings {
    boolean userInDb(String name, String lastname);
    boolean numberRoom(int room);
    boolean nullNameOrLastName(String name,String lastname, long room, String date_born);
    boolean userByRoom(int room);
    boolean userInDbByLastName(String lastname);
    boolean userInDbByName(String name);
    boolean numInTheString(String a);
    boolean notEmptyRoom(Dates dates);
    boolean notEmptyDates(Dates dates);
    boolean notEmptyString(Dates dates);
    boolean nullUser(User user);

}
