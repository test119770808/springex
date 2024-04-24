package com.zerock.springex.mapper;

import com.zerock.springex.domain.TodoVO;
import com.zerock.springex.dto.PageRequestDTO;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.List;

@Log4j2
@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = "file:src/main/webapp/WEB-INF/root-context.xml")
public class TodoMapperTests {

    @Autowired(required = false)
    private TodoMapper todoMapper;

    @Test
    public void testGetTime() {
        log.info(todoMapper.getTime());
    }

    @Test
    public void testInsert() {

        TodoVO todoVO = TodoVO.builder()
                .title("test-mybatis")
                .dueDate(LocalDate.of(2024,04,22))
                .writer("user00")
                .build();
        todoMapper.insert(todoVO);

    }

    @Test
    public void selectAllTest() {

        List<TodoVO> voList = todoMapper.selectAll();
        voList.forEach(vo -> log.info(vo));

    }


    @Test
    public void testSelectOne() {
        TodoVO todoVO = todoMapper.selectOne(3L);
        log.info(todoVO);
    }

    @Test
    public void testDelete() {
        todoMapper.delete(1L);
        TodoVO todoVO = todoMapper.selectOne(1L);
        Assertions.assertNull(todoVO);
    }

    @Test
    public void testUpdate() {
        TodoVO todoVO = todoMapper.selectOne(3L);
        TodoVO todoVO1 = TodoVO.builder()
                .tno(todoVO.getTno())
                .title(todoVO.getTitle() + " modify")
                .dueDate(todoVO.getDueDate())
                .finished(!todoVO.isFinished()).build();
        todoMapper.update(todoVO1);
        log.info(todoMapper.selectOne(todoVO.getTno()));
    }

    @Test
    public void testSelectList() {
        // 1. PageRequestDTO를 먼저 만들어요...
        PageRequestDTO pageRequestDTO = PageRequestDTO.builder()
                .page(1)
                .size(10)
                .build();
        // 2. todoMapper.selectList()를 테스트 결과를 List<TodoVO>로 담기
        List<TodoVO> voList = todoMapper.selectList(pageRequestDTO);
        // 3. 내용 출력하기...
        voList.forEach(vo -> log.info(vo));
    }

//    @Test
//    public void testGetCount() {
//        int count = todoMapper.getCount(new PageRequestDTO(1,10));
//        log.info("전체 게시글 수 : "+count);
//    }

    @Test
    public void testSelectSearch() {
        PageRequestDTO pageRequestDTO = PageRequestDTO.builder()
                .page(1)
                .size(10)
                .types(new String[]{"t","w"})
                .finished(true)
                .from(LocalDate.of(2024,4,20))
                .to(LocalDate.of(2024,04,30))
                .keyword("테스트")
                .build();
        List<TodoVO> voList = todoMapper.selectList(pageRequestDTO);

        voList.forEach(vo -> log.info(vo));

        log.info(todoMapper.getCount(pageRequestDTO));
    }
}
