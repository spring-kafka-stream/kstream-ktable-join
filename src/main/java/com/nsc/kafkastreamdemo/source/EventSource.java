package com.nsc.kafkastreamdemo.source;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface EventSource {
    String EVENT_OUT = "eventOut";

    String CONFIG_OUT = "configOut";

    @Output(EVENT_OUT)
    MessageChannel eventOutput();

    @Output(CONFIG_OUT)
    MessageChannel configOut();
}
