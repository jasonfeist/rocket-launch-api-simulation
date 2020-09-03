package com.jasonfeist.rocket.controller;

import com.jasonfeist.rocket.model.FloatKeyValue;
import com.jasonfeist.rocket.model.IntegerKeyValue;
import com.jasonfeist.rocket.model.StringKeyValue;
import com.jasonfeist.rocket.service.RocketLaunchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/rocket-launches")
public class LaunchController {
    @Autowired
    RocketLaunchService rocketLaunchService;

    @RequestMapping(value="/average-cost", method = RequestMethod.GET)
    public @ResponseBody FloatKeyValue averageCost(
            @RequestParam(value = "rocketCompany", required = false) String rocketCompany,
            @RequestParam(value = "startDate", required = false) String startDate,
            @RequestParam(value = "endDate", required = false) String endDate) {
        return rocketLaunchService.getAverageLaunchCost(rocketCompany, startDate, endDate);
    }

    @RequestMapping(value="/percent-by-status", method = RequestMethod.GET)
    public @ResponseBody FloatKeyValue percentByStatus(
            @RequestParam(value = "status") String status,
            @RequestParam(value = "rocketCompany", required = false) String rocketCompany,
            @RequestParam(value = "startDate", required = false) String startDate,
            @RequestParam(value = "endDate", required = false) String endDate) {
        return rocketLaunchService.getPercentByStatus(status, rocketCompany, startDate, endDate);
    }

    @RequestMapping(value="/top-month", method = RequestMethod.GET)
    public @ResponseBody StringKeyValue topMonth(
            @RequestParam(value = "rocketCompany", required = false) String rocketCompany,
            @RequestParam(value = "startDate", required = false) String startDate,
            @RequestParam(value = "endDate", required = false) String endDate) {
        return rocketLaunchService.getLaunchCountsByMonth(rocketCompany, startDate, endDate);
    }

    @RequestMapping(value="/count-by-location", method = RequestMethod.GET)
    public @ResponseBody List<IntegerKeyValue> locationCounts(
            @RequestParam(value = "rocketCompany", required = false) String rocketCompany,
            @RequestParam(value = "startDate", required = false) String startDate,
            @RequestParam(value = "endDate", required = false) String endDate) {
        return rocketLaunchService.getLaunchCountsByLocation(rocketCompany, startDate, endDate);
    }

    @RequestMapping(value="/count-by-country", method = RequestMethod.GET)
    public @ResponseBody List<IntegerKeyValue> countryCounts(
            @RequestParam(value = "rocketCompany", required = false) String rocketCompany,
            @RequestParam(value = "startDate", required = false) String startDate,
            @RequestParam(value = "endDate", required = false) String endDate) {
        return rocketLaunchService.getLaunchCountsByCountry(rocketCompany, startDate, endDate);
    }
}
