package xyz.outerringroad.redbull.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HttpController {

    @GetMapping("/ping")
    public Object ping() {
        return "pong";
    }

}
