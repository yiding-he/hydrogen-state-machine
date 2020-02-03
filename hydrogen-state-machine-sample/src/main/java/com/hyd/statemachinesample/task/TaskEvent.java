package com.hyd.statemachinesample.task;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaskEvent {

    private SomeTask task;

    private TaskEventType eventType;
}
