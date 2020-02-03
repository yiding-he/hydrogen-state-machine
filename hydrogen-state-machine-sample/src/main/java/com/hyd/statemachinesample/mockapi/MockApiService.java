package com.hyd.statemachinesample.mockapi;

import org.springframework.stereotype.Service;

@Service
public class MockApiService {

    public RequestResult request(Long taskId) {
        // todo 需要实现这个方法
        return new RequestResult();
    }

    public VerifyResult verify(Long taskId) {
        // todo 需要实现这个方法
        return new VerifyResult();
    }
}
