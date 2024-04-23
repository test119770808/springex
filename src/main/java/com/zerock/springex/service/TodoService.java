package com.zerock.springex.service;

import com.zerock.springex.dto.TodoDTO;

import java.util.List;

public interface TodoService {

    // Todo 등록 서버스
    void register(TodoDTO todoDTO);

    // Todo 목록 서비스
    List<TodoDTO> getAll();
}
