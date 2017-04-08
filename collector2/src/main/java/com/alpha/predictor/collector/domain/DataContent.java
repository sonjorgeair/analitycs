package com.alpha.predictor.collector.domain;

import java.util.List;
import java.util.Map;

/**
 * Created by Jorge1 on 05-04-17.
 */
public interface DataContent
{
    Map<String,String> getContent();
    boolean isResponseAvailable();

}
