package com.blog.demo.controller.test;

import com.blog.demo.controller.testRequest.TransferSalaryRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.io.IOException;
import java.util.HashMap;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/globalExceptionHandler")
public class GlobalExceptionHandlerController {

    @PostMapping("exception")
    public ResponseEntity<?> exceptionTest(@Valid @RequestBody TransferSalaryRequest transferSalaryRequest) throws Exception {

        System.out.println("들어왔나요?");
        return ResponseEntity.ok("");
    }

}
