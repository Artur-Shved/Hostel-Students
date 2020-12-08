package com.hostel.hostelsite.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

@Controller
public class HtppErrorController implements ErrorController {
    private final MessageSource messageSource;

    @Autowired
    public HtppErrorController(MessageSource messageSource){
        this.messageSource = messageSource;
    }

    @RequestMapping("/error")
    public String handleError(Locale locale, Model model, HttpServletRequest request, Exception ex){
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        if(status != null){
            int statusCode = Integer.valueOf(status.toString()) ;
            Map<String, String> metaData = new HashMap<>();
            if(statusCode == HttpStatus.FORBIDDEN.value()){
                return "errorPage";
            }else if(statusCode == HttpStatus.NOT_FOUND.value()){
                return "errorPage";
            }
        }
            return "homepage";

    }

    @Override
    public String getErrorPath() {
        return "redirect:/alluser";
    }
}
