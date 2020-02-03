package com.hyd.statemachinesample.task;

import com.hyd.dao.DAO;
import com.hyd.statemachine.StateMachine;
import com.hyd.statemachinesample.mockapi.MockApiService;
import com.hyd.statemachinesample.mockapi.RequestResult;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class SomeTaskService {

    @Autowired
    private DAO dao;

    @Autowired
    private MockApiService mockApiService;

    @Autowired
    private StateMachine<SomeTask, TaskState, TaskEvent, TaskEventType> taskStateMachine;

    public List<SomeTask> queryAll() {
        return dao.query(SomeTask.class, "select * from some_task");
    }

    public void sendRequest() {
        List<SomeTask> taskList = dao.query(
            SomeTask.class, "select * from some_task where state in (?, ?)",
            TaskState.INIT.name(), TaskState.RETRY.name()
        );

        taskList.forEach(task -> {
            RequestResult requestResult = mockApiService.request(task.getId());

        });
    }

    public void verifyRequest() {
        // todo 需要实现这个方法
    }

    public void onRequestSuccess(TaskState source, TaskState target, SomeTask someTask, TaskEvent taskEvent) {
        dao.execute("update some_task set state=? where id=?", target.name(), someTask);
        log.info("任务 {} 状态从 {} 变为 {}", someTask.getId(), source, target);
    }
}
