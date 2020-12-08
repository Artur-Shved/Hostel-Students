package com.hostel.hostelsite.repo;

import com.hostel.hostelsite.dao.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserDataRepository extends CrudRepository<User, Long> {
     List<User> findAllByDateBetween(String date1, String date2);
     List<User> findAllByRoomBetween(int a, int b);
     List<User> findAllByName(String name);
     List<User> findAllByLastname(String lastname);
     List<User> findAllByRoom(int room);
     List<User> findAllByNameAndLastname(String name, String lastName);
     User findByUsername(String username);
     User findByPassword(String password);
     User findByName(String name);


}
