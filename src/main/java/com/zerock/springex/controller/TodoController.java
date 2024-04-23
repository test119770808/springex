package com.zerock.springex.controller;

import com.zerock.springex.dto.TodoDTO;
import com.zerock.springex.service.TodoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/todo")
@Log4j2
@RequiredArgsConstructor
public class TodoController {

    private final TodoService todoService;

    // /todo/list 접근시....
    @RequestMapping("/list")
    public void list(Model model) {
        log.info("todo list..... ");
        model.addAttribute("dtoList", todoService.getAll());
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public void registerGet() {

        log.info("GET todo register........ ");
    }

    // 4. 객체 자료 수집
    @PostMapping(value = "/register")
    public String registerPost(@Valid TodoDTO todoDTO,
                               BindingResult bindingResult,
                               RedirectAttributes redirectAttributes) {
        log.info("Post todo register...........");

        if(bindingResult.hasErrors()) {
            log.info("has errors......");
            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
            return "redirect:/todo/register";
        }

        log.info(todoDTO);

        todoService.register(todoDTO);

        return "redirect:/todo/list";  //등록후 목록으로 이동...
    }

    // 한개의 Todo 조회하기
    @GetMapping("/read")
    public void read(Long tno, Model model) {
        TodoDTO todoDTO = todoService.getOne(tno);
        log.info(todoDTO);
        model.addAttribute("dto",todoDTO);
    }


}
