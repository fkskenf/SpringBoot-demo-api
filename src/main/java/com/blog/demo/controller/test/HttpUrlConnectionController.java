package com.blog.demo.controller.test;

import com.blog.demo.client.HttpUrlConnectionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.HashMap;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/httpurlconnection")
public class HttpUrlConnectionController {

    @Autowired
    private HttpUrlConnectionService httpUrlConnectionService;

    @PostMapping("post")
    public ResponseEntity<?> sendPostTest() throws IOException {

        HashMap<String, Object> header = new HashMap<String, Object>();
        header.put("Authorization","Bearer OkADJZV5SrjGK8BHpMW42oQtYeRd94");

        HashMap<String, Object> param = new HashMap<String, Object>(); // POST의 body
        param.put("cno",10051);

        String url = "http://127.0.0.1:7080/cdw/test";
        String response = httpUrlConnectionService.sendPost(url, header, param);
        return ResponseEntity.ok(response);
    }

    @GetMapping("get")
    public ResponseEntity<?> sendGetTest() throws IOException {

        HashMap<String, Object> header = new HashMap<String, Object>();
        header.put("Authorization","Bearer OkADJZV5SrjGK8BHpMW42oQtYeRd94");

        String url = "http://127.0.0.1:7080/cdw/catalogs/ours?cno=10051"; // GET은 QueryString
        String response = httpUrlConnectionService.sendGet(url, header);
        return ResponseEntity.ok(response);
    }
}
