package com.hostel.hostelsite.controllers;

import com.hostel.hostelsite.controllers.models.Dates;
import com.hostel.hostelsite.dao.Role;
import com.hostel.hostelsite.dao.entity.ExceptionLol;
import com.hostel.hostelsite.dao.entity.User;
import com.hostel.hostelsite.dao.interfaceDao.UserDaoInterface;
import com.hostel.hostelsite.dao.interfaceSettings.Settings;
import com.hostel.hostelsite.repo.UserDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@Controller
public class ControllerHostel {
   @Autowired
    Settings settings;

    @Autowired
    UserDaoInterface dao;

    @Autowired
    UserDataRepository repository;

    @Autowired
    BCryptPasswordEncoder encoder;



    @GetMapping("/")
    public String index(@ModelAttribute Dates dates, Model model){
        model.addAttribute("message", new User().getName());
        return "homepage";
    }

    @RequestMapping("/addnewuser")
    @PreAuthorize("hasRole('ROLE_SUPER_ADMIN')")
    public String showform(User user, Model model){
        model.addAttribute("message", "Add new User");
        return "registration";
    }

    @RequestMapping("/failed")
    public String showFormUsersAlredyCreated(Model m){
        m.addAttribute("command", new User());
        return "failed";
    }

    @RequestMapping("/failed1")
    public String showFormWrongRoomNumber(Model m){
        m.addAttribute("command", new User());
        return "failed1";
    }


    @RequestMapping(value = "save", method = RequestMethod.POST)
    public String save(@ModelAttribute User user){
        User user1 = repository.findByUsername(user.getUsername());
        if(settings.nullUser(user) && user1==null && settings.numberRoom(user.getRoom())) {
            user.setActive(true);
            user.setRole(Collections.singleton(Role.ROLE_USER));
            user.setPassword(encoder.encode(user.getPassword()));
            dao.save(user);
            return "redirect:/alluser";
        }else{
            return "redirect:/addnewuser";
        }
    }

    @RequestMapping("/alluser")
    public String allUser(Model model, @ModelAttribute Dates dates){
        model.addAttribute("list", dao.allUsers());
        return "allusers";
    }

    @RequestMapping(value = "/editemp/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_SUPER_ADMIN')")
    public String editUser(@PathVariable Long id, Model m){
        User user = dao.findById(id);
        Set<Role> roles = user.getRole();
        m.addAttribute("command", dao.findById(id));
        m.addAttribute("role", Role.values());
        m.addAttribute("roles", user.getRole());
        return "edit-user";
    }


    @RequestMapping(value = "/editsave", method = RequestMethod.POST)
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_SUPER_ADMIN')")
    public String editSave(@ModelAttribute User user, @RequestParam(value = "checkbox",
            defaultValue = "")List<String> form, Model model){
//        List<String> roles = new ArrayList<>();
//        roles.add("ROLE_ADMIN");
//        roles.add("ROLE_USER");
//        roles.add("ROLE_SUPER_ADMIN");

        if(form.size()==1) {
            user.getRole().clear();
            for(int i =0 ; i < form.size(); i++) {
                user.getRole().add(Role.valueOf(form.get(i)));
            }
        } else if (form.size()>1){
            model.addAttribute("command", dao.findById(user.getId()));
            model.addAttribute("role", Role.values());
            model.addAttribute("roles", user.getRole());
            model.addAttribute("message", "check only one checkbox");
            return "edit-user";
        }



        user.setActive(true);
        dao.editUser(user);

        return "redirect:/alluser";
    }




    @RequestMapping(value="/delete/{id}",method = RequestMethod.GET)
        public String deleteUser(@PathVariable long id){
                dao.deleteUser(id);
                return "redirect:/alluser";

    }




}
