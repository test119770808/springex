package com.zerock.springex.controller.exception;

import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.util.Arrays;

@ControllerAdvice
@Log4j2
public class CommonExceptionAdvice {


    @ResponseBody   // 데이터를 전달...
    @ExceptionHandler(NumberFormatException.class)   // 어노테이션을 통해서 예외 상황 지정
    public String exceptNumber(NumberFormatException numberFormatException) {
        log.error("----------------------------------------------");
        log.error(numberFormatException.getMessage());

        return "NUMBER FORMAT EXCEPTION";
    }

    // 개발 중에 Debuggin용으로 사용!
    @ResponseBody
    @ExceptionHandler(Exception.class)
    public String exceptCommon(Exception exception) {
         log.error("------------------------------------------");
         log.error(exception.getMessage());

         StringBuffer buffer = new StringBuffer("<ul>");

         buffer.append("<li>"+exception.getMessage()+"</li>");

        Arrays.stream(exception.getStackTrace()).forEach(stackTraceElement -> {
            buffer.append("<li>"+stackTraceElement+"</li>");
        });
        buffer.append("</ul>");

        return buffer.toString();
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String notFound() {
        return "custom404";
    }

}
