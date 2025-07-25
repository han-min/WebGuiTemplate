package org.hanmin.controller.chart;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

@Slf4j
@RestController
public class ChartJsRestController {

    private static final int MAX_X = 500;

    private List<Integer> xPoints;
    private List<Integer> yPoints;
    private double lastRadius = Double.NaN;

    /**
     * This required the class to be labelled @RestController
     */
    @PostMapping("/api/chart_data")
    public List<Integer> getChartData(
            @RequestParam Map<String, String> parameters
    ){
        log.info("getChartData [{}]", parameters);
        Random random = new Random();
        String axis = getParam(parameters, "axis", "x");
        int numPoints = getParam(parameters, "num_points", 10);
        boolean isXAxis = axis.equalsIgnoreCase("x");
        List<Integer> r;
        if (isXAxis) {
            r = xPoints;
        } else {
            r = yPoints;
        }
        double step = 2 * Math.PI / numPoints;
        if (r == null) {
            // first time, populate
            lastRadius = -step;
            r = new ArrayList<>(numPoints);
            for (int i=0; i<numPoints; i++){
                if (isXAxis) {
                    r.add(i);
                } else {
                    lastRadius += step;
                    r.add((int) Math.round(MAX_X * Math.sin(lastRadius)));
                }
            }
            if (isXAxis){ xPoints = r; } else { yPoints = r; }
        } else {
            // following change data
            r.remove(0);
            if (isXAxis) {
                r.add(r.get(r.size()-2) + 1);
            } else {
                lastRadius += step;
                r.add((int) Math.round(MAX_X * Math.sin(lastRadius)));
            }
        }
        return r;
    }

    private int getParam(final Map<String, String> p, final String key, final int defaultValue){
        String v = getParam(p, key, null);
        if (v != null) {
            return Integer.parseInt(v);
        }
        return defaultValue;
    }

    private String getParam(final Map<String, String> p, final String key, final String defaultValue){
        if (p.containsKey(key)){
            return p.get(key);
        }
        return defaultValue;
    }

}
