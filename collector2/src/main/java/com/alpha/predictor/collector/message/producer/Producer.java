package com.alpha.predictor.collector.message.producer;

import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.List;

/**
 * Created by dfs1 on 08-04-17.
 */
public interface Producer<T> {

    void send(String topic, List<T> mesaggeList);

    void send(String topic, T message) throws JsonProcessingException;
}
