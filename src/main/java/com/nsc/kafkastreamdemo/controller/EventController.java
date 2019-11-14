package com.nsc.kafkastreamdemo.controller;

import com.nsc.kafkastreamdemo.model.Config;
import com.nsc.kafkastreamdemo.sender.MessageSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class EventController {

    @Autowired
    private MessageSender messageSender;

    @RequestMapping(value = "/event", method = RequestMethod.GET)
    public void sendEvent() {
        messageSender.send();
    }

    @RequestMapping(value = "/config", method = RequestMethod.POST)
    public Config sendConfig(@RequestBody Config config) {
        return messageSender.sendConfig(config);
    }
}
