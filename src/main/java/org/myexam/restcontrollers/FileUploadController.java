package org.myexam.restcontrollers;

import org.myexam.commons.CommonException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FileUploadController {
    @GetMapping("/file/upload")
    public void upload() {
//        JSON 예외처리 부분
//        boolean result = true;
//        if (result) {
//            throw new CommonException("예외발생", HttpStatus.BAD_REQUEST);
//        }

    }
}
