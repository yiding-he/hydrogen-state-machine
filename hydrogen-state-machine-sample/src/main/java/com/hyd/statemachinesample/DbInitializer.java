package com.hyd.statemachinesample;

import com.hyd.dao.DAO;
import com.hyd.statemachinesample.task.TaskState;
import javax.annotation.PostConstruct;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Log
public class DbInitializer {

    @Autowired
    private DAO dao;

    @PostConstruct
    public void init() throws Exception {
        dao.execute("create table some_task(id int primary key, state varchar(20))");
        log.info("Table created.");

        dao.execute("insert into some_task(id, state) values(?,?)", 1L, TaskState.INIT.name());
        log.info("Task 1 saved.");
    }
}
