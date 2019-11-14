package com.nsc.kafkastreamdemo.model;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CustomEvent {
    private String tenantId;
    private String location;
    private EventType eventType;
    private int value;
}
