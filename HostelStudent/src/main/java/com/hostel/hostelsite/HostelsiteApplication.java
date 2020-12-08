package com.hostel.hostelsite;

import com.hostel.hostelsite.dao.UserSettingsImpl;
import com.hostel.hostelsite.dao.interfaceSettings.Settings;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class HostelsiteApplication  extends SpringBootServletInitializer {

    @Bean
    public Settings settings(){
        return new UserSettingsImpl();
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder springApplicationBuilder){
        return springApplicationBuilder.sources(HostelsiteApplication.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(HostelsiteApplication.class, args);
    }

}
