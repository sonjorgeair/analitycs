package com.alpha.predictor.message.producer.impl;


import com.alpha.predictor.domain.DataContent;
import com.alpha.predictor.message.producer.Producer;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import java.util.List;

/**
 * Created by dfs1 on 08-04-17.
 * <p>
 * <p>
 * Code based on https://www.codenotfound.com/2016/09/spring-kafka-consumer-producer-example.html
 */
@Component
public class DataContentProducer implements Producer<DataContent>
{

    private static final Logger LOGGER = LoggerFactory.getLogger(DataContentProducer.class);

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;


    @Override
    public void send(String topic, List<DataContent> messageList) {
        messageList.forEach(data -> {
            try {
                send(topic, data);
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        });
    }

    @Override
    public void send(String topic, DataContent message) throws JsonProcessingException {
        // the KafkaTemplate provides asynchronous send methods returning a
        // Future
        ListenableFuture<SendResult<String, String>> future = kafkaTemplate.send(topic, Utils.serializeToString(message.getContent()));

        // you can register a callback with the listener to receive the result
        // of the send asynchronously
        future.addCallback(new ListenableFutureCallback<SendResult<String, String>>() {

            @Override
            public void onSuccess(SendResult<String, String> result) {
                LOGGER.info("sent message='{}' with offset={}", message,
                        result.getRecordMetadata().offset());
            }

            @Override
            public void onFailure(Throwable ex) {
                LOGGER.error("unable to send message='{}'", message, ex);
            }
        });

        // alternatively, to block the sending thread, to await the result,
        // invoke the future's get() method
    }
}
