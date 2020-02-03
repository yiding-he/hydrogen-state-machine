package com.hyd.statemachinesample.task;

public class SomeTask {

    private Long id;

    private TaskState state = TaskState.INIT;

    public SomeTask() {
    }

    public SomeTask(Long id, TaskState state) {
        this.id = id;
        this.state = state;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TaskState getState() {
        return state;
    }

    public void setState(TaskState state) {
        this.state = state;
    }
}
