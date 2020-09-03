package com.jasonfeist.rocket.service;

import com.jasonfeist.rocket.mapper.MissionStatusMapper;
import com.jasonfeist.rocket.mapper.RocketLaunchMapper;
import com.jasonfeist.rocket.model.FloatKeyValue;
import com.jasonfeist.rocket.model.IntegerKeyValue;
import com.jasonfeist.rocket.model.StringKeyValue;
import com.jasonfeist.rocket.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.text.ParseException;
import java.time.Month;
import java.util.List;

@Service
public class RocketLaunchService {
    @Autowired
    RocketLaunchMapper rocketLaunchMapper;
    @Autowired
    MissionStatusMapper missionStatusMapper;

    public FloatKeyValue getAverageLaunchCost(String rocketCompany,
                                              String startDate,
                                              String endDate) {
        try {
            Float averageCost = rocketLaunchMapper.getAverageLaunchCost(rocketCompany,
                    DateUtil.parseStringDate(startDate),
                    DateUtil.parseStringDate(endDate));
            return new FloatKeyValue("Average Launch Cost", averageCost);
        } catch (ParseException pe) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Date must be specified in yyyy-MM-dd format.");
        }
    }

    public FloatKeyValue getPercentByStatus(String status,
                                     String rocketCompany,
                                     String startDate,
                                     String endDate) {
        Integer missionStatusId = missionStatusMapper.getIdByStatus(status);
        if (missionStatusId == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, String.format("'%s' is not a valid mission status.", status));
        }
        try {
            Float percentByStatus = rocketLaunchMapper.getPercentByStatus(missionStatusId,
                    rocketCompany,
                    DateUtil.parseStringDate(startDate),
                    DateUtil.parseStringDate(endDate));
            return new FloatKeyValue(status, percentByStatus);
        } catch (ParseException pe) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Date must be specified in yyyy-MM-dd format.");
        }
    }

    public StringKeyValue getLaunchCountsByMonth(String rocketCompany,
                                                String startDate,
                                                String endDate) {
        try {
            Integer countByMonth = rocketLaunchMapper.getLaunchCountsByMonth(
                    rocketCompany,
                    DateUtil.parseStringDate(startDate),
                    DateUtil.parseStringDate(endDate));
            return new StringKeyValue("Top Month", Month.of(countByMonth).name());
        } catch (ParseException pe) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Date must be specified in yyyy-MM-dd format.");
        }
    }

    public List<IntegerKeyValue> getLaunchCountsByLocation(String rocketCompany,
                                                    String startDate,
                                                    String endDate) {
        try {
            return rocketLaunchMapper.getLaunchLocationCounts(
                    rocketCompany,
                    DateUtil.parseStringDate(startDate),
                    DateUtil.parseStringDate(endDate));
        } catch (ParseException pe) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Date must be specified in yyyy-MM-dd format.");
        }
    }

    public List<IntegerKeyValue> getLaunchCountsByCountry(String rocketCompany,
                                                  String startDate,
                                                  String endDate) {
        try {
            return rocketLaunchMapper.getLaunchCountryCounts(
                    rocketCompany,
                    DateUtil.parseStringDate(startDate),
                    DateUtil.parseStringDate(endDate));
        } catch (ParseException pe) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Date must be specified in yyyy-MM-dd format.");
        }
    }
}
