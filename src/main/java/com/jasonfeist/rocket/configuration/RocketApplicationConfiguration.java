package com.jasonfeist.rocket.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@ComponentScan(basePackages = {"com.jasonfeist.rocket"})
@EnableTransactionManagement
public class RocketApplicationConfiguration {

}