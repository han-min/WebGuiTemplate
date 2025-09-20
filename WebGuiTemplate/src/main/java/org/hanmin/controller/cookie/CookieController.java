package org.hanmin.controller.cookie;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.mbeans.SparseUserDatabaseMBean;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Slf4j
@RestController
public class CookieController {

    private static final String COOKIE_KEY = "cookie_value";

    @GetMapping("/api/get_cookie")
    public String getCookieValue(
            @CookieValue(value = COOKIE_KEY, defaultValue = "Chocolate") String cookieValue){
        return cookieValue;
    }

    @PostMapping("/api/set_cookie")
    public String setCookieValue(
            @RequestParam(value=COOKIE_KEY, required = false) String theValueToSet,
            HttpServletResponse response
    ){
        log.info("setCookieValue. Got parameters [{}]", theValueToSet);
        final String value = theValueToSet;
        Cookie c = new Cookie(COOKIE_KEY, value);
        c.setMaxAge(3600); // in minutes (1 hour)
        c.setHttpOnly(true);
        response.addCookie(c);
        return "Cookie value set to: " + value;
    }

}
