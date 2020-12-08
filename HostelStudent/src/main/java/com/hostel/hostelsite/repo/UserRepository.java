package com.hostel.hostelsite.repo;

import com.hostel.hostelsite.dao.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long>{
    User findByName(String name);


}
