package com.alpha.predictor.collector.service;

import com.alpha.predictor.collector.datasources.webpage.WebPageDataSource;

import java.util.Date;
import java.util.List;

/**
 * Created by Jorge1 on 05-04-17.
 */
public interface ServiceStatus
{
    Date getUptime();
    int getNumberOfRuns();
    List<WebPageDataSource> getAvailableServices();
    List<WebPageDataSource> getNoAvailableServices();
}
