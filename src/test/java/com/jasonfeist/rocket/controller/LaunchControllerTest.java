package com.jasonfeist.rocket.controller;

import com.jasonfeist.rocket.service.RocketLaunchService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest
@AutoConfigureMockMvc
public class LaunchControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private RocketLaunchService rocketLaunchService;

    @Test
    public void testCountryCount() throws Exception {
        doReturn(null).when(rocketLaunchService).getLaunchCountsByCountry(anyString(), anyString(), anyString());
        this.mockMvc.perform(get("/rocket-launches/count-by-country")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void testAverageCost() throws Exception {
        doReturn(null).when(rocketLaunchService).getAverageLaunchCost(anyString(), anyString(), anyString());
        this.mockMvc.perform(get("/rocket-launches/average-cost")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void testLocationCount() throws Exception {
        doReturn(null).when(rocketLaunchService).getLaunchCountsByLocation(anyString(), anyString(), anyString());
        this.mockMvc.perform(get("/rocket-launches/count-by-location")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void testCountByMonth() throws Exception {
        doReturn(null).when(rocketLaunchService).getLaunchCountsByMonth(anyString(), anyString(), anyString());
        this.mockMvc.perform(get("/rocket-launches/top-month")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void testPercentStatus() throws Exception {
        doReturn(null).when(rocketLaunchService).getPercentByStatus(anyString(), anyString(), anyString(), anyString());
        this.mockMvc.perform(get("/rocket-launches/percent-by-status?status=failure")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        this.mockMvc.perform(get("/rocket-launches/percent-by-status")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(400));
    }
}
