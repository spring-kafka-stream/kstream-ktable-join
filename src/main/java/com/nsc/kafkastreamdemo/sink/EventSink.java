package com.nsc.kafkastreamdemo.sink;

import com.nsc.kafkastreamdemo.model.Config;
import com.nsc.kafkastreamdemo.model.Event;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.KTable;
import org.springframework.cloud.stream.annotation.Input;

public interface EventSink {

    String EVENT_IN = "eventIn";

    String CONFIG_IN = "configIn";

    @Input(EVENT_IN)
    KStream<String, Event> eventIn();

    @Input(CONFIG_IN)
    KTable<String, Config> configIn();


}
