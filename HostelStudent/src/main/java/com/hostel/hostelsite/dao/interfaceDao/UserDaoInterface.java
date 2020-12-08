package com.hostel.hostelsite.dao.interfaceDao;

import com.hostel.hostelsite.controllers.models.Dates;
import com.hostel.hostelsite.dao.entity.User;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Map;


public interface UserDaoInterface {
     List<User> findUserBy(String columnName, Object a);
     void deleteUser(long id);
     User editUser(User user);
     User findById(long id);
     List<User> allUsers();
     User save(User user);
     List<User> findUserByNameAndLastName(String name, String lastname);
     List<User> findUserByNameAndLastName(String name);
     List<User> findUserByLastName(String name);
     int num(boolean b, String a);
     List<User> userBy(Dates dates);
     List<User> newCriteria(List<String> list, Map map);
     List<String> lists (Map map);
     Map maps(Dates dates);
}
