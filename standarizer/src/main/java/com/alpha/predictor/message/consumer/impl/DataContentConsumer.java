package com.alpha.predictor.message.consumer.impl;


import com.alpha.predictor.domain.DataContent;
import com.alpha.predictor.message.consumer.Consumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.concurrent.CountDownLatch;

/**
 * Created by dfs1 on 08-04-17.
 */
@Component
public class DataContentConsumer implements Consumer<DataContent>
{

    private static final Logger LOGGER = LoggerFactory.getLogger(DataContentConsumer.class);

    private CountDownLatch latch = new CountDownLatch(1);

    @KafkaListener(topics = "${kafka.topic.helloworld}")
    @Override
    public void receive(String message) {
        LOGGER.info("received message='{}'", message);
        latch.countDown();
    }

    public CountDownLatch getLatch() {
        return latch;
    }
}
