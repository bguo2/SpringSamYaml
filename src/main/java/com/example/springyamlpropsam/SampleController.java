package com.example.springyamlpropsam;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.hateoas.server.mvc.ControllerLinkBuilder;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class SampleController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Value("${spring.profiles.active}")
    private String activeProfile;

    @Autowired
    private UserDaoService manager;

    @GetMapping("/ping")
    public String ping() {
        logger.info("hello, active profile: " + activeProfile);
        return "hello";
    }

    @RequestMapping(path = "/queryall", produces = "application/json")
    @Async
    public ResponseEntity<List<UserDto>> getAllUsers() {
        List<UserDto> userDtos = manager.getUsers();
        return  ResponseEntity.ok(userDtos);
    }

}
