package com.zerock.springex.mapper;

import com.zerock.springex.domain.TodoVO;

import java.util.List;

public interface TodoMapper {
    // Todo Mybatis 작업을 위한 인터페이스.....

    String getTime();
    // Todo 등록
    void insert(TodoVO todoVO);

    // Todo 목록
    List<TodoVO> selectAll();

}
