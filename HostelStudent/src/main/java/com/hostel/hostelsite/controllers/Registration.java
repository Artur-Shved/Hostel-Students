package com.hostel.hostelsite.controllers;


import com.hostel.hostelsite.dao.Role;
import com.hostel.hostelsite.dao.entity.User;
import com.hostel.hostelsite.dao.interfaceSettings.Settings;
import com.hostel.hostelsite.repo.UserDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Collections;
import java.util.Map;

@Controller
public class Registration {
    @Autowired
    Settings settings;

    @Autowired
    UserDataRepository repository;

    @Autowired
    BCryptPasswordEncoder encoder;

    @RequestMapping("/registration")
    public String registration(Model model, User user){
        model.addAttribute("message", "Create An Account");
        return "registration";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String addNewUser(User user, Map<String,Object> model){
    User user1 = repository.findByUsername(user.getUsername());
        if(user1 != null){
        model.put("message", "User alredy exists");
        return "registration";
    }else if(settings.nullUser(user)) {
            user.setActive(true);
            user.setRole(Collections.singleton(Role.ROLE_USER));
            user.setPassword(encoder.encode(user.getPassword()));
            repository.save(user);
            return "redirect:/login";
        }else{
            model.put("message", "wrong user");
            return "registration";
        }
    }
}
