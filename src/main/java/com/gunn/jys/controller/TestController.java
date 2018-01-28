package com.gunn.jys.controller;

import com.gunn.jys.entity.Test;
import com.gunn.jys.mapper.TestMapper;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/test")
public class TestController {

    @Resource
    private TestMapper testMapper;

    @RequestMapping("test")
    public String test() {
        return "hello world!";
    }
}
