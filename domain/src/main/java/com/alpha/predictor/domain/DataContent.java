package com.alpha.predictor.domain;

import java.util.Map;

/**
 * Created by Jorge1 on 05-04-17.
 */
public interface DataContent
{
    Map<String,String> getContent();
    boolean isResponseAvailable();

}
