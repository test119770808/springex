package com.zerock.springex.controller;

import com.zerock.springex.dto.TodoDTO;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;

@Controller
@Log4j2
@RequestMapping("/sample")
public class SampleController {

    @GetMapping("/hello")  //GET방식일 때...
    public void hello() {
        log.info("hello.......... ");
    }

    // RequestMapping에서 파생된 어노테이션 : GetMapping, PostMapping....

    // 1. 기본자료형 데이터 자동 수집....
    @GetMapping("/ex1")
    public void ex1(String name, int age) {
        log.info("ex1 .......");
        log.info("name : "+ name);
        log.info("age: "+ (age + 10));
    }

    // 2. @RequestParam  ->  파라미터에 있는 값을 받아 처리하는 어노테이션
    //  name => 매개변수이름 , defaultValue => 기본값 설정(해당 name으로 들어오는 것이 없을 경우 설정값)
    @GetMapping("/ex2")
    public void ex2(@RequestParam(name = "name", defaultValue = "AAA") String name,
                    @RequestParam(name = "age", defaultValue = "20") int age) {
        log.info("ex2.........");
        log.info("name : " + name);
        log.info("age : "+(age+5));
    }

    // 3. Formatter를 이용한 파라미터 커스텀 처리
    // HTTP는 문자열 데이터를 전달합니다. 때문에 컨트롤러는 문자열을 기준으로 특정 클래스 객체로 처리하는
    // 작업이 진행되게 됩니다. 이때 개발에서 가장 문제되는 타입이 날짜 관련 타입입니다.
    // 브라우저에서 '2024-04-19' 과 같은 문자열을 받아서 Date, LocalDate 이나 LocalDateTime 등으로
    // 변환하는 작업은 많이 필요하지만, 이에 대한 파라미터 수집은 에러가 발생합니다.
    // 이럴 때 사용하는 것이 Formatter 입니다.
    @GetMapping("/ex3")
    public void ex3(LocalDate dueDate) {
        log.info("ex3..........");
        log.info("dueDate : "+ dueDate);
    }

    // 4. 객체 자료형 파라미터 수집  -> TodoController

    // 5. Model 객체를 이용한 데이터 처리
    @GetMapping("/ex4")
    public void ex4(Model model) {
        log.info("----------------------------");
        model.addAttribute("message","Hello World!!");
    }

    // 6. java bean과 @ModelAttribute의 사용
//    @GetMapping("/ex4_1")
//    public void ex4Extra(TodoDTO todoDTO, Model model) {
    //    view로 전달시 todoDTO 이름으로 전달
//        log.info(todoDTO);
//    }

    @GetMapping("/ex4_1")
    public void ex4Extra(@ModelAttribute("dto") TodoDTO todoDTO, Model model) {
        // view로 전달시 이름을 dto로 전달
        // @ModelAttribute("dto")  => Model.addAttribute("dto",todoDTO)
        log.info(todoDTO);
    }

    // 7. RedirectAttributes와 리다이렉션
    // POST 방식으로 어떤 처리를 하고, Redirect로 GET방식으로 특정 페이지로 이동하는 패턴
    // (PRG 패턴)에서 스프링에서 RedirectAttributes 타입을 제공
    // Model과 마찮가지로 파라미터를 추가해 주기만 한다면 자동으로 생성하는 방식으로 개발할 수 있음.

    // RedirectAttributes의 중요 메서드
    // - AddAttribute(key, value) : 리다이렉트할 때 쿼리 스트링이 되는 값을 지정
    // - AddFlashAttribute(key, value) : 일회용으로만 데이털르 전달하고 삭제되는 값을 지정할 때

    @GetMapping("/ex5")
    public String ex5(RedirectAttributes redirectAttributes) {
        redirectAttributes.addAttribute("name", "ABC");
        redirectAttributes.addFlashAttribute("result","success");

        return "redirect:/sample/ex6";  // 리다이렉트를 사용하기 위하서 쓰는 접두어 : redirect
    }

    @GetMapping("/ex6")
    public void ex6() {

    }

    // 반환 타입이 void 인 경우에는 uri경로에 값으로 DispatcherServlet에게 전달...
    // 반환 타입이 String 인 경우에는 문자열의 값을 DispatcherServlet에게 전달...
    //   -> redirect : 리다이렉트시 사용, forward : 포워드시 사용... (잘 사용X)
    // 객체나 배열, 기본 자료형...


    // 고의로 예외 발생하는 코드 작성
    @GetMapping("/ex7")
    public void ex7(String p1, int p2) {
        log.info("p1 ................"+ p1);
        log.info("p2................."+ p2);
    }
}
