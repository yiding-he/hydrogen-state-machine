package com.hyd.statemachinesample.mockapi;

import java.util.Random;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

@Service
public class MockApiService {

    private Random random = new Random();

    @Autowired
    private ApplicationContext applicationContext;

    public void request(Long taskId) {
        // todo 需要实现这个方法
        ResultCode[] values = ResultCode.values();
        ResultCode resultCode = values[random.nextInt(values.length)];

    }

    public void verify(Long taskId) {
        // todo 需要实现这个方法
    }
}
