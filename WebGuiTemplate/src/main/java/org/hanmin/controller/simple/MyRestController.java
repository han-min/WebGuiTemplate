package org.hanmin.controller.simple;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
public class MyRestController {

    @GetMapping("/helloworld")
    public String helloWorld(){
        return "Hello World";
    }

    // url using /helloworld/xxx
    @GetMapping("/helloworld/{name}")
    public String helloWorld(
            @PathVariable("name") String name
    ){
        return "Hello " + name;
    }

    // url using /helloworldparam?first=xxx&last=xxx
    @GetMapping("/helloworldparam")
    public String helloWorldParam(
            @RequestParam(name="first", required=false) String firstName,
            @RequestParam(name="last", required=false) String lastName
    ){
        return "Hello " + firstName + " " + lastName;
    }

    @PostMapping("/helloworldpost")
    public List<String> getListData(
            @RequestParam Map<String, String> parameters
    ){
        List<String> r = new LinkedList<>();
        for (Map.Entry<String, String> e : parameters.entrySet()){
            r.add(e.getKey() + "=" + e.getValue());
        }
        r.add("Updated: " + (new Date()));
        return r;
    }

    @PostMapping("/api/helloworldpost")
    public List<String> getApiListData(
            @RequestParam Map<String, String> parameters
    ){
        log.info("getApiListData [{}]", parameters);
        List<String> r = new LinkedList<>();
        for (Map.Entry<String, String> e : parameters.entrySet()){
            r.add(e.getKey() + "=" + e.getValue());
        }
        return r;
    }

}
