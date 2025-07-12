package org.hanmin.controller.dynamic.simple;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Date;

@Slf4j
@Controller
public class HelloWorldController {

    @GetMapping("/dynamic/helloworld")
    public String getHelloWorld(Model model){
        log.info("getHelloWorld [{}]", model);
        model.addAttribute("message", "Hello World");
        model.addAttribute("update_time", (new Date()).toString());
        return "dynamic_helloworld"; // points to templates/dynamic_helloworld.html
    }

}
