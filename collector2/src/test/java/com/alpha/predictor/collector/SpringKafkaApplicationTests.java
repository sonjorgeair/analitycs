package com.alpha.predictor.collector;

import com.alpha.predictor.collector.domain.BasicDataContent;
import com.alpha.predictor.collector.domain.DataContent;
import com.alpha.predictor.collector.message.consumer.impl.DataContentConsumer;
import com.alpha.predictor.collector.message.producer.impl.DataContentProducer;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by dfs1 on 08-04-17.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringKafkaApplicationTests {

    @Autowired
    private DataContentProducer sender;

    @Autowired
    private DataContentConsumer receiver;

    private DataContent dataContent;

    @Before
    public void before() {
        Map<String, String> map = new HashMap<>();
        map.put("data1", "value1");
        dataContent = new BasicDataContent(map);
    }

    @Test
    public void testReceive() throws Exception {
        sender.send("helloworld.t", dataContent);

        receiver.getLatch().await(10000, TimeUnit.MILLISECONDS);
        assertThat(receiver.getLatch().getCount()).isEqualTo(0);
    }
}
