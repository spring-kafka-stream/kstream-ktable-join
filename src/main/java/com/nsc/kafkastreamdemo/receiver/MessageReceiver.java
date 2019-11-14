package com.nsc.kafkastreamdemo.receiver;

import com.nsc.kafkastreamdemo.model.Event;
import com.nsc.kafkastreamdemo.sink.EventSink;
import org.apache.kafka.streams.kstream.KTable;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.StreamListener;

@EnableBinding(EventSink.class)
public class MessageReceiver {

    @StreamListener
    public void process(@Input("inputTable") KTable<String, Event> KTable) {
        KTable.toStream()
                .foreach((key, value) -> System.out.println(value));
    }
}
