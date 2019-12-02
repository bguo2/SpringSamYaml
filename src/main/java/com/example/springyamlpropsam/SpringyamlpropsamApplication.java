package com.example.springyamlpropsam;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@SpringBootApplication
public class SpringyamlpropsamApplication {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ConfigProperties config;

    @Autowired
    @Qualifier("testDb1JdbcTemplate")
    private JdbcTemplate testDb;


    public static void main(String[] args) {
        SpringApplication.run(SpringyamlpropsamApplication.class, args);
    }


/*
    public static void main(String[] args) {
        SpringApplication.run(SpringyamlpropsamApplication.class, args).close();
    }

    @Override
    public void run(String... args) {
        /*
        System.out.println("Environment: " + config.getEnvironment());
        System.out.println("Email: " + config.getEmail());

        List<User> users = testDb.query("select * from user", new BeanPropertyRowMapper(User.class));
        if(users.size() > 0)
        {
            for(User user: users) {
                System.out.println(user.getId() + ", " + user.getName() + ", " + user.getEmail());
            }
        }


        asynchTest();
        logger.info("test done");

    }*/

    private void asynchTest() {
        Executor executor = Executors.newFixedThreadPool(10);

        List<CompletableFuture<String>> futures = new ArrayList<>();
        for(int i = 0; i < 10; i++) {
            futures.add(asynchMethod(i, executor));
        }
        CompletableFuture<Void> combinedFuture = CompletableFuture.allOf(
                futures.toArray(new CompletableFuture[futures.size()]));
        combinedFuture.join();

        ((ExecutorService) executor).shutdown();
    }

    private CompletableFuture<String> asynchMethod(int i, Executor executor) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                logger.info("start to run " + i);
                //call your methods
                Thread.sleep(1000);
                logger.info("end to run " + i);
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
            //return values
            return String.format("%d", i);
        }, executor);
    }
}
