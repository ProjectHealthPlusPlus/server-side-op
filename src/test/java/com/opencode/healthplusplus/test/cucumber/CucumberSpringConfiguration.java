package com.opencode.healthplusplus.test.cucumber;

import com.opencode.healthplusplus.HealthPlusPlusApplication;
import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.boot.test.context.SpringBootContextLoader;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

@CucumberContextConfiguration
@SpringBootTest(classes = HealthPlusPlusApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ContextConfiguration(classes = HealthPlusPlusApplication.class,
        loader = SpringBootContextLoader.class)
public class CucumberSpringConfiguration {
}
