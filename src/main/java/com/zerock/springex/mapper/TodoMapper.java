package com.zerock.springex.mapper;

import com.zerock.springex.domain.TodoVO;

public interface TodoMapper {
    // Todo Mybatis 작업을 위한 인터페이스.....

    String getTime();

    void insert(TodoVO todoVO);
}
