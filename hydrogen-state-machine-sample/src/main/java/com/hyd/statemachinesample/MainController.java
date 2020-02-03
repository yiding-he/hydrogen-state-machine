package com.hyd.statemachinesample;

import com.hyd.statemachinesample.task.SomeTask;
import com.hyd.statemachinesample.task.SomeTaskService;
import java.util.List;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@Log
public class MainController {

    @Autowired
    private SomeTaskService someTaskService;

    @RequestMapping("/")
    public ModelAndView index() {
        List<SomeTask> taskList = someTaskService.queryAll();
        log.info(taskList.size() + " tasks found.");
        return new ModelAndView("index").addObject("tasks", taskList);
    }

    @RequestMapping("/action/request")
    public ModelAndView request() {
        someTaskService.sendRequest();
        return new ModelAndView("redirect:/");
    }

    @RequestMapping("/action/verify")
    public ModelAndView verify() {
        someTaskService.verifyRequest();
        return new ModelAndView("redirect:/");
    }
}
