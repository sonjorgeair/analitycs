package com.alpha.predictor.collector.datasources.webpage;

import com.alpha.predictor.collector.domain.BasicDataContent;
import com.alpha.predictor.collector.domain.DataContent;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Jorge1 on 06-04-17.
 */
public class BasicWebpageDataSource implements WebPageDataSource
{

    private final String url;
    private static Logger logger = Logger.getLogger(BasicWebpageDataSource.class);


    private BasicWebpageDataSource(String url)
    {
        this.url = url;

    }

    @Override
    public DataContent parse()
    {

        RestTemplate restTemplate = new RestTemplate();
        String response = restTemplate.getForObject(url, String.class);

        ObjectMapper mapper = new ObjectMapper();
        Map<String, String> parsedResponse = null;
        try
        {
            parsedResponse = mapper.readValue(response, HashMap.class);
        }
        catch (IOException e)
        {
            logger.error("Unable to parse the response from "+ url + e.getMessage());
        }

        return  new BasicDataContent(parsedResponse);
    }

    public static class Builder
    {
        private String url;

        public Builder url(String url)
        {
            this.url = url;
            return this;
        }

        public BasicWebpageDataSource build()
        {
            return new BasicWebpageDataSource(url);
        }

    }
}
