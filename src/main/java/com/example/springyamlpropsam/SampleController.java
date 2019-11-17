package com.example.springyamlpropsam;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class SampleController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Value("${spring.profiles.active}")
    private String activeProfile;

    @GetMapping("/ping")
    public String ping() {
        logger.info("hello, active profile: " + activeProfile);
        return "hello";
    }
}
