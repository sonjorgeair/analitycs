package com.alpha.predictor.collector.service;

import com.alpha.predictor.collector.datasources.webpage.WebPageDataSource;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Jorge1 on 05-04-17.
 */

public interface DataService
{
    List<WebPageDataSource> getDatasources();

    ServiceStatus getStatus();

    void poolDatasources();

}
