package com.hostel.hostelsite.controllers;

import com.hostel.hostelsite.controllers.models.Dates;
import com.hostel.hostelsite.dao.UserFinder;
import com.hostel.hostelsite.dao.entity.User;
import com.hostel.hostelsite.dao.interfaceDao.UserDaoInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SearchController {

    @Autowired
    UserFinder finder;

    @Autowired
    UserDaoInterface dao;



    @RequestMapping("/finder")
    public String getUserByNameAndLastname(@ModelAttribute Dates dates, Model model) {
        if (!dates.getString().isEmpty()) {
            model.addAttribute("list",dao.userBy(dates));
            return "allusers";
        }
        return "redirect:/alluser";

    }


    @RequestMapping("/searchuser")
    public String roomBetwee(Model model){
        model.addAttribute("command", new Dates());
        return "searchuser";
    }

    @RequestMapping("/finderUser")
    public String finderRoom(@ModelAttribute Dates dates, Model model){
        model.addAttribute("list", finder.finderUser(dates));
        return "allusers";
    }


    @RequestMapping("/finduser")
    public String findRoom(@ModelAttribute Dates dates, Model model){
        model.addAttribute("list", finder.findUser(dates));
        return "allusers";
    }


}
