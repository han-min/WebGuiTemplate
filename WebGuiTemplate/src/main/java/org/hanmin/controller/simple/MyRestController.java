package org.hanmin.controller.simple;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyRestController {

    @GetMapping("/helloworld")
    public String helloWorld(){
        return "Hello World";
    }

    @GetMapping("/helloworld/{name}")
    public String helloWorld(
            @PathVariable("name") String name
    ){
        return "Hello " + name;
    }

}
