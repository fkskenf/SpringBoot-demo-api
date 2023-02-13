package com.blog.demo.controller.test;

import com.blog.demo.client.HttpConnectionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.HashMap;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/test")
public class HttpConnectionController {

    @Autowired
    private HttpConnectionService httpConnectionService;

    @PostMapping("httpconnection")
    public ResponseEntity<?> httpconnectionTest() throws IOException {

        HashMap<String, Object> header = new HashMap<String, Object>();
        HashMap<String, Object> param = new HashMap<String, Object>();

        // 공공데이터 오픈 API 활용
        String url = "http://apis.data.go.kr/1360000/BeachInfoservice/getTwBuoyBeach?serviceKey=dwchvY9aMDuvIyEXpAng/VzGX7arSKOVVYWQuukBnQc86OWYba52QhO22gXDo0g7L6eywWqCcwkGEu0Bcz0tvg==&numOfRows=1&pageNo=10&dataType=json&beach_num=1&searchTime=202301021700";
        String response = httpConnectionService.sendPost(url, "POST", header, param);
        return ResponseEntity.ok(response);
    }
}
