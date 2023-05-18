package org.myexam.controllers;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.myexam.commons.CommonException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice("org.myexam.controllers")
public class CommonController {

    @ExceptionHandler(Exception.class)
    public String errorHandler(Exception e, Model model, HttpServletRequest request, HttpServletResponse response) {

        int status = HttpServletResponse.SC_INTERNAL_SERVER_ERROR;
        if (e instanceof CommonException) {
            CommonException commonException = (CommonException) e;
            status = commonException.getStatus().value();
        }

        response.setStatus(status);
        String URL = request.getRequestURI();
//        System.out.println("URL ::::::::: "+URL);

        model.addAttribute("status", status);
        model.addAttribute("path", URL);
        model.addAttribute("message", e.getMessage());
        model.addAttribute("exception", e);

        e.printStackTrace();    // 오류를 콘솔에서 더 자세히 볼 수 있도록 조치

        return "error/common";
    }
}
