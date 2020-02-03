package com.hyd.statemachinesample;

import com.hyd.statemachine.StateMachine;
import com.hyd.statemachine.StateMachineBuilder;
import com.hyd.statemachinesample.task.SomeTask;
import com.hyd.statemachinesample.task.SomeTaskService;
import com.hyd.statemachinesample.task.TaskEvent;
import com.hyd.statemachinesample.task.TaskEventType;
import com.hyd.statemachinesample.task.TaskState;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StateMachineConfigurator {

    @Bean
    public StateMachine<SomeTask, TaskState, TaskEvent, TaskEventType> taskStateMachine(
        SomeTaskService someTaskService
    ) {
        return StateMachineBuilder
            .newBuilder(
                SomeTask.class, TaskState.class, TaskEvent.class, TaskEventType.class
            )
            .setEventTypeExtractor(TaskEvent::getEventType)
            .setStateExtractor(SomeTask::getState)
            .addRule(TaskState.INIT, TaskState.SUBMITTING, TaskEventType.REQUEST_SUCCESS)
            .addRule(TaskState.SUBMITTING, TaskState.SUCCESS, TaskEventType.VERIFY_SUCCESS)
            .addHandler(null, TaskState.SUBMITTING, null, someTaskService::onRequestSuccess)
            .build();
    }
}
