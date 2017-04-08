package com.alpha.predictor.collector.service;

import com.alpha.predictor.collector.datasources.webpage.WebPageDataSource;

import java.util.Date;
import java.util.List;

/**
 * Created by Jorge1 on 07-04-17.
 */
public class BasicServiceStatus implements ServiceStatus
{

    private final Date uptime;
    private final int numberOfRun;
    private final List<WebPageDataSource> availableServices;
    private final List<WebPageDataSource> notAvailableServices;

    private BasicServiceStatus(final Date uptime, final int numberOfRun, final List<WebPageDataSource> availableServices, final List<WebPageDataSource> noAvailableServices)
    {
        this.uptime = uptime;
        this.numberOfRun=numberOfRun;
        this.availableServices = availableServices;
        this.notAvailableServices = noAvailableServices;
    }

    @Override
    public Date getUptime()
    {
        return uptime;
    }

    @Override
    public int getNumberOfRuns()
    {
        return numberOfRun;
    }

    @Override
    public List<WebPageDataSource> getAvailableServices()
    {
        return availableServices;
    }

    @Override
    public List<WebPageDataSource> getNoAvailableServices()
    {
        return notAvailableServices;
    }

    public static class Builder
    {
        private Date uptime;
        private int numberOfRun;
        private List<WebPageDataSource> availableServices;
        private List<WebPageDataSource> noAvailableServices;

        public Builder setUptime(Date uptime)
        {
            this.uptime = uptime;
            return this;
        }

        public Builder setNumberOfRun(int numberOfRun)
        {
            this.numberOfRun = numberOfRun;
            return this;
        }

        public Builder setAvailableServices(List<WebPageDataSource> availableServices)
        {
            this.availableServices = availableServices;
            return this;
        }

        public Builder setNoAvailableServices(List<WebPageDataSource> noAvailableServices)
        {
            this.noAvailableServices = noAvailableServices;
            return this;
        }

        public BasicServiceStatus createBasicServiceStatus()
        {
            return new BasicServiceStatus(uptime, numberOfRun, availableServices, noAvailableServices);
        }
    }

}
