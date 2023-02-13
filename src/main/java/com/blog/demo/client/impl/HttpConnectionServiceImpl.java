package com.blog.demo.client.impl;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.blog.demo.client.HttpConnectionService;
import com.google.gson.Gson;
import org.apache.commons.lang3.StringEscapeUtils;
import org.springframework.stereotype.Service;

@Service("httpConnection")
public class HttpConnectionServiceImpl implements HttpConnectionService {
    private static final String URL = "https://www.google.com";
    private static final String POST = "POST";
    private static final String GET = "GET";
    private static final String USER_AGENT = "Mozilla/5.0";
    private static final String DATA = "test data";

    public String sendPost(String requestUrl, String method, HashMap<String, Object> Headers, HashMap param) throws IOException {
        URL url = new URL(requestUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection(); // 연결 객체 얻기

        connection.setRequestMethod(POST);
//        connection.setRequestProperty("User-Agent", USER_AGENT); // header 설정
        connection.setDoOutput(true); // post요청시 데이터 넘겨주기

        // 사용자 헤더값 추가 세팅
        if (Headers != null) {
            Iterator HeadersIt = Headers.entrySet().iterator();
            while (HeadersIt.hasNext()) {
                Map.Entry HeadersEntry = (Map.Entry) HeadersIt.next();
                String HeadersKey = String.valueOf(HeadersEntry.getKey());
                String HeadersVal = StringEscapeUtils.unescapeHtml4(String.valueOf(HeadersEntry.getValue())); // 공부필요
                connection.setRequestProperty(HeadersKey, HeadersVal);
            }
        }

        // 사용자 파라미터 세팅
        String paramStr;
        if (method.toUpperCase().equals("GET")) {
            connection.setRequestProperty("Content-Type", "application/json; charset=utf-8");
        } else {
            String contentType = String.valueOf(connection.getRequestProperty("Content-Type"));
            paramStr = "";
            if (contentType.toLowerCase().indexOf("application/json") > -1) {
                Gson gson = new Gson();
                paramStr = gson.toJson(param);
            } else {
                connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded; charset=utf-8");
                if (param != null) {
                    Iterator it = param.entrySet().iterator();
                    int i = 0;

                    while (it.hasNext()) {
                        ++i;
                        Map.Entry entry = (Map.Entry) it.next();
                        Object key = entry.getKey();
                        Object val = entry.getValue();
                        paramStr = paramStr + String.valueOf(key) + "=" + URLEncoder.encode(String.valueOf(val), "UTF-8");
                        if (param.size() > i) {
                            paramStr = paramStr + "&";
                        }
                    }
                }
            }

            DataOutputStream outputStream = new DataOutputStream(connection.getOutputStream());
            outputStream.write(paramStr.getBytes("utf-8"));
            outputStream.flush();
            outputStream.close();
        }

        int responseCode = connection.getResponseCode(); // responseCode 얻기

        /*
         *  response 데이터 얻기
         *  - 응답 데이터를 읽을 수 있는 InputStream 객체 얻기
         *  - 응답을 문자열 타입으로 얻기 위해 BufferedReader 객체 사용
         */
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        StringBuffer stringBuffer = new StringBuffer();
        String inputLine;

        while ((inputLine = bufferedReader.readLine()) != null) {
            stringBuffer.append(inputLine);
        }
        bufferedReader.close();

        String response = "";
        response = stringBuffer.toString();

        return response;
    }

    // TODO
    public String sendGet() throws IOException {
        URL url = new URL(URL);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        connection.setRequestMethod(GET);
        connection.setRequestProperty("User-Agent", USER_AGENT);
        // GET 요청을 할 경우에는 요청 메소드를 GET으로 변경하고 OutputStream을 사용하지 않게끔 코드를 작성하면 됩니다.

        int responseCode = connection.getResponseCode();

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        StringBuffer stringBuffer = new StringBuffer();
        String inputLine;

        while ((inputLine = bufferedReader.readLine()) != null) {
            stringBuffer.append(inputLine);
        }
        bufferedReader.close();

        String response = stringBuffer.toString();

        return response;
    }
}
