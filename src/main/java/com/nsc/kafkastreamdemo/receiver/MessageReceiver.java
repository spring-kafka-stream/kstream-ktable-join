package com.nsc.kafkastreamdemo.receiver;

import com.nsc.kafkastreamdemo.model.Config;
import com.nsc.kafkastreamdemo.model.CustomEvent;
import com.nsc.kafkastreamdemo.model.Event;
import com.nsc.kafkastreamdemo.sink.EventSink;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.kstream.Joined;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.KTable;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.kafka.support.serializer.JsonSerde;

@EnableBinding(EventSink.class)
public class MessageReceiver {

    @StreamListener
    public void process(@Input(EventSink.EVENT_IN) KStream<String, Event> eventKStream,
                        @Input(EventSink.CONFIG_IN) KTable<String, Config> KTable) {

        JsonSerde<Event> eventJsonSerde = new JsonSerde<>(Event.class);
        JsonSerde<Config> configJsonSerde = new JsonSerde<>(Config.class);

        KStream<String, CustomEvent> customEventStream = eventKStream.leftJoin(KTable, (leftValue, rightValue) -> CustomEvent.builder()
                .tenantId(leftValue.getTenantId())
                .location(leftValue.getLocation())
                .value(rightValue.getValue())
                .build(), Joined.with(Serdes.String(), eventJsonSerde, configJsonSerde));

        customEventStream.foreach((key, value) -> System.out.println(value));
    }
}
