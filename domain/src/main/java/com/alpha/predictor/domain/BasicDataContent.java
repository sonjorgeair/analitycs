package com.alpha.predictor.domain;

import java.util.Map;

/**
 * Created by Jorge1 on 06-04-17.
 */
public class BasicDataContent implements DataContent
{
    private final Map<String,String> content;

    public BasicDataContent(Map<String,String> newContent)
    {
        this.content = newContent;
    }


    @Override
    public Map<String, String> getContent()
    {
        return content;
    }

    @Override
    public boolean isResponseAvailable()
    {
        return !content.isEmpty();
    }
}
