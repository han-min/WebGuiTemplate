package org.hanmin.controller.chart;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.*;

@Slf4j
@Controller
public class ChartJsController {

    private static final int MIN_X = 0;
    private static final int MAX_X = 500;

    /**
     * This required the class to be labelled @Controller
     * Cannot be mixed with @RestController
     */
    @GetMapping("/chart_thymeleaf_static")
    public String getChartStatic(Model model){
        log.info("getChartStatic [{}]", model);
        final Map<Integer, Integer> xyValue = getMapData(MIN_X, MAX_X);
        List<Integer> xArray = new ArrayList<>(xyValue.size());
        List<Integer> yArray = new ArrayList<>(xyValue.size());
        for (int x = MIN_X; x <= MAX_X; x++) {
            xArray.add(x);
            yArray.add(xyValue.get(x));
        }
        model.addAttribute("x_array", xArray);
        model.addAttribute("y_array", yArray);
        return "chart_thymeleaf_static"; // points to templates/chart_thymeleaf_static.html
    }

    private Map<Integer, Integer> getMapData(int from, int to){
        Map<Integer, Integer> r = new HashMap<>();
        Random random = new Random();
        int prev = 0;
        int direction = 0; // +ve to go down, -ve to go up
        for (int x = from; x <= to; x++) {
            if (direction == 0) {
                boolean isPlus = random.nextDouble() > 0.5;
                int numTick = random.nextInt(10);
                direction = (isPlus) ? numTick : -numTick;
            }
            int nextDelta;
            if (direction > 0) {
                nextDelta = -2;
                direction -= 1;
            } else {
                nextDelta = 2;
                direction += 1;
            }
            int y = prev + (nextDelta * Math.abs(direction));
            prev = y;
            r.put(x, y);
        }
        return r;
    }


}
