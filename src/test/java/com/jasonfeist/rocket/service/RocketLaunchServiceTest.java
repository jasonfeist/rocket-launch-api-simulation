package com.jasonfeist.rocket.service;

import com.jasonfeist.rocket.mapper.MissionStatusMapper;
import com.jasonfeist.rocket.mapper.RocketLaunchMapper;
import com.jasonfeist.rocket.model.FloatKeyValue;
import com.jasonfeist.rocket.model.IntegerKeyValue;
import com.jasonfeist.rocket.model.StringKeyValue;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.sql.Timestamp;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.nullable;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class RocketLaunchServiceTest {
    @Mock
    private RocketLaunchMapper rocketLaunchMapper;
    @Mock
    private MissionStatusMapper missionStatusMapper;
    @InjectMocks
    private RocketLaunchService rocketLaunchService;

    @Test
    public void averageCostTest() {
        when(rocketLaunchMapper.getAverageLaunchCost(nullable(String.class), nullable(Timestamp.class), nullable(Timestamp.class))).thenReturn(222.5F);
        FloatKeyValue result = rocketLaunchService.getAverageLaunchCost(null, null, null);
        assertEquals((Float)222.5F, result.getValue());
        result = rocketLaunchService.getAverageLaunchCost("foo", "1990-08-01", null);
        assertEquals((Float)222.5F, result.getValue());
    }

    @Test
    public void missionStatusTest() {
        when(missionStatusMapper.getIdByStatus(anyString())).thenReturn(1);
        when(rocketLaunchMapper.getPercentByStatus(eq(1), nullable(String.class), nullable(Timestamp.class), nullable(Timestamp.class))).thenReturn(5.5F);
        FloatKeyValue result = rocketLaunchService.getPercentByStatus("Failure",null, null, null);
        assertEquals((Float)5.5F, result.getValue());
    }

    @Test
    public void locationCountTest() {
        List<IntegerKeyValue> mockResults = List.of(new IntegerKeyValue("Florida", 10), new IntegerKeyValue("Texas", 5));
        when(rocketLaunchMapper.getLaunchLocationCounts(nullable(String.class), nullable(Timestamp.class), nullable(Timestamp.class))).thenReturn(mockResults);
        List<IntegerKeyValue> results = rocketLaunchService.getLaunchCountsByLocation(null, null, null);
        assertEquals(2, results.size());
        assertEquals("Florida", results.get(0).getLabel());
        assertEquals((Integer)10, results.get(0).getValue());
        assertEquals("Texas", results.get(1).getLabel());
        assertEquals((Integer)5, results.get(1).getValue());
    }

    @Test
    public void countryCountTest() {
        List<IntegerKeyValue> mockResults = List.of(new IntegerKeyValue("Russia", 100), new IntegerKeyValue("USA", 50));
        when(rocketLaunchMapper.getLaunchCountryCounts(nullable(String.class), nullable(Timestamp.class), nullable(Timestamp.class))).thenReturn(mockResults);
        List<IntegerKeyValue> results = rocketLaunchService.getLaunchCountsByCountry(null, null, null);
        assertEquals(2, results.size());
        assertEquals("Russia", results.get(0).getLabel());
        assertEquals((Integer)100, results.get(0).getValue());
        assertEquals("USA", results.get(1).getLabel());
        assertEquals((Integer)50, results.get(1).getValue());
    }

    @Test
    public void monthCountTest() {
        when(rocketLaunchMapper.getLaunchCountsByMonth(nullable(String.class), nullable(Timestamp.class), nullable(Timestamp.class))).thenReturn(1);
        StringKeyValue result = rocketLaunchService.getLaunchCountsByMonth(null, null, null);
        assertEquals("JANUARY", result.getValue());
    }
}
