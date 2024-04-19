package com.zerock.springex.controller;

import com.zerock.springex.dto.TodoDTO;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/todo")
@Log4j2
public class TodoController {

    // /todo/list 접근시....
    @RequestMapping("/list")
    public void list() {
        log.info("todo list..... ");
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public void registerGet() {
        log.info("GET todo register........ ");
    }

    // 4. 객체 자료 수집
    @RequestMapping(value = "/register")
    public  void registerPost(TodoDTO todoDTO) {
        log.info("Post todo register...........");
        log.info(todoDTO);
    }





}
