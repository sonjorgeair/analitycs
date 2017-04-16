package com.alpha.predictor.collector.service;

import com.alpha.predictor.collector.datasources.webpage.BasicWebpageDataSource;
import com.alpha.predictor.collector.datasources.webpage.WebPageDataSource;

import com.alpha.predictor.domain.DataContent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.actuate.metrics.CounterService;
import org.springframework.boot.actuate.metrics.GaugeService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Jorge1 on 05-04-17.
 */
@Service
public class BasicDataService implements DataService
{

    private Logger logger = LoggerFactory.getLogger(BasicDataService.class);

    @Autowired
    private CounterService counterService;

    @Autowired
    private GaugeService gaugeService;

    private List<WebPageDataSource> dataSources;

    @Value("#{'${collector.datasource.urls}'.split(',')}")
    private List<String> datasourcesUrls;

    @PostConstruct
    private void initialize()
    {
        dataSources = datasourcesUrls.stream().filter(url->!url.isEmpty())
                       .map(url-> new BasicWebpageDataSource.Builder().url(url).build())
                       .collect(Collectors.toList());
    }

    @Override
    public List<WebPageDataSource> getDatasources()
    {
        return Collections.unmodifiableList(dataSources);
    }

    @Override
    public ServiceStatus getStatus()
    {
        return buildStatus(dataSources);
    }

    private ServiceStatus buildStatus(List<WebPageDataSource> dataSources)
    {
        return new BasicServiceStatus.Builder().setNumberOfRun(0).setAvailableServices(dataSources).createBasicServiceStatus();
    }

    @Scheduled(initialDelay = 60000, fixedRate = 60000)
    @Override
    public void poolDatasources()
    {
        logger.info("Starting pooling");

        List<DataContent> iterationData = dataSources.stream().map(d -> d.parse()).collect(Collectors.toList());


        captureSomeMetrics(iterationData);

        logger.info("Sending data message to Kafka");
        pushToKafka(iterationData);

        logger.info("Finishing pooling with a total of: " + iterationData.size() + " datasource pulled");

    }

    private void captureSomeMetrics(List<DataContent> iterationData)
    {
        long numberOfSuccessfulPool = iterationData.stream().filter(data -> data.isResponseAvailable()).count();
        long numberOfFailedPool = iterationData.size() - numberOfSuccessfulPool;

        counterService.increment("collector.pools.numberOfRuns");
        gaugeService.submit("collector.pools.successful",numberOfSuccessfulPool);
        gaugeService.submit("collector.pools.failed",numberOfFailedPool);


    }

    private void pushToKafka(List<DataContent> iterationData)
    {

    }
}
