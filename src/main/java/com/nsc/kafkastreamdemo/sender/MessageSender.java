package com.nsc.kafkastreamdemo.sender;

import com.nsc.kafkastreamdemo.model.Config;
import com.nsc.kafkastreamdemo.model.Event;
import com.nsc.kafkastreamdemo.source.EventSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;

import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@EnableBinding(EventSource.class)
public class MessageSender {

    @Autowired
    EventSource eventSource;

    public void send() {
        Runnable runnable = () -> {
            Event eventWhitefield = Event.builder()
                    .tenantId("APOLLO")
                    .location("Whitefield")
                    .build();

            Message<Event> messageWhitefield = MessageBuilder
                    .withPayload(eventWhitefield)
                    .setHeader(KafkaHeaders.MESSAGE_KEY, eventWhitefield.getLocation().getBytes())
                    .build();

            eventSource.eventOutput().send(messageWhitefield);
        };
        Executors.newScheduledThreadPool(1).scheduleAtFixedRate(runnable, 0, 4000, TimeUnit.MILLISECONDS);
    }

    public Config sendConfig(Config config) {

        Message<Config> messageWhitefield = MessageBuilder
                .withPayload(config)
                .setHeader(KafkaHeaders.MESSAGE_KEY, config.getLocation().getBytes())
                .build();

        eventSource.configOut().send(messageWhitefield);

        return config;
    }
}
