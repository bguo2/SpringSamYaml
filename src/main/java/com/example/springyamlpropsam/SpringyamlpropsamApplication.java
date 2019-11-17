package com.example.springyamlpropsam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

@SpringBootApplication
public class SpringyamlpropsamApplication {

    @Autowired
    private ConfigProperties config;

    @Autowired
    @Qualifier("testDb1JdbcTemplate")
    private JdbcTemplate cspdb;

    public static void main(String[] args) {
        SpringApplication.run(SpringyamlpropsamApplication.class, args);
    }

    /*
    public static void main(String[] args) {
        SpringApplication.run(SpringyamlpropsamApplication.class, args).close();
    }

    @Override
    public void run(String... args) {
        System.out.println("Environment: " + config.getEnvironment());
        System.out.println("Email: " + config.getEmail());

        List<User> users = cspdb.query("select * from user", new BeanPropertyRowMapper(User.class));
        if(users.size() > 0)
        {
            for(User user: users) {
                System.out.println(user.getId() + ", " + user.getName() + ", " + user.getEmail());
            }
        }

    }
    */
}
