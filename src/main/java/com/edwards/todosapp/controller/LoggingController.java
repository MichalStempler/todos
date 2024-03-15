package com.edwards.todosapp.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoggingController {

    Logger logger = LoggerFactory.getLogger(LoggingController.class);

    @RequestMapping("/logs")
    public String index() {
        logger.info("Welcome to your todo's application");

        return "Check out the Logs to see some important information...";
    }

}
