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

import com.blog.demo.client.HttpUrlConnectionService;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service("httpConnection")
public class HttpUrlConnectionServiceImpl implements HttpUrlConnectionService {
    private final Logger logger = LoggerFactory.getLogger("HttpUrlConnectionTest");

    public String sendPost(String url, HashMap<String, Object> Headers, HashMap param) throws IOException {
        return sendApi(url, "POST", Headers, param);
    }

    public String sendGet(String url, HashMap<String, Object> Headers) throws IOException {
        return sendApi(url, "GET", Headers, new HashMap());
    }

    public String sendApi(String requestUrl, String method, HashMap<String, Object> Headers, HashMap param) throws IOException {
        String response = "";

        try {
            // 1. URL 객체 생성
            URL url = new URL(requestUrl);

            // 2. connection 생성
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            // 3. connection 옵션 설정 (header 세팅)
            connection.setRequestMethod(method.toUpperCase()); // 요청 방식
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(60000);
//        connection.setRequestProperty("User-Agent", "Mozilla/5.0");
//        connection.setRequestProperty("Authorization", StringUtil.fixNull(this.request.getHeader("Authorization")));
//        connection.setRequestProperty("transaction-id", StringUtil.fixNull(this.request.getHeader("transaction-id")));
//        connection.setRequestProperty("client-id", StringUtil.fixNull(this.request.getHeader("client-id")));
//        connection.setRequestProperty("span", StringUtil.fixNull(this.request.getHeader("span"), "1"));
//        connection.setRequestProperty("service", StringUtil.fixNull(this.luluProperties.getProperty("globals.SERVICE_CODE")));
//        connection.setRequestProperty("service-key", encStr);
//        connection.setRequestProperty("service-code", serviceCode);

            // 사용자 header 추가 세팅
            if (Headers != null) {
                Iterator HeadersIt = Headers.entrySet().iterator();
                while (HeadersIt.hasNext()) {
                    Map.Entry HeadersEntry = (Map.Entry) HeadersIt.next();
                    String HeadersKey = String.valueOf(HeadersEntry.getKey());
                    String HeadersVal = String.valueOf(HeadersEntry.getValue());
                    if ("connect_time_out".equals(HeadersKey)) {
                        if (HeadersVal.isEmpty()) {
                            HeadersVal = "5000";
                        }
                        connection.setConnectTimeout(Integer.parseInt(String.valueOf(HeadersVal)));
                        continue;
                    }
                    if ("read_time_out".equals(HeadersKey)) {
                        if (HeadersVal.isEmpty()) {
                            HeadersVal = "60000";
                        }
                        connection.setReadTimeout(Integer.parseInt(String.valueOf(HeadersVal)));
                        continue;
                    }
                    connection.setRequestProperty(HeadersKey, HeadersVal);
                }
            }

            /*
             * GET 일떄  : "application/json; charset=utf-8"
             * POST 일떄 : "application/json" 또는 "application/x-www-form-urlencoded; charset=utf-8"(body에 쿼리스트링 형식)
             */
            // 4. 사용자 파라미터 세팅 (body 세팅)
            connection.setDoOutput(true); // 출력 활성화 설정 (DoInput : 입력설정)

            if (method.toUpperCase().equals("GET")) {
                connection.setRequestProperty("Content-Type", "application/json; charset=utf-8");
            } else {
                String contentType = String.valueOf(connection.getRequestProperty("Content-Type"));
                String paramStr = "";

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

                /*
                 * 연결과 관련된 OutputStream 객체를 가져온 다음, write() 메소드를 사용하여 데이터 쓰면 됩니다.
                 * write() 메소드는 byte의 배열만 쓸 수 있기 때문에 문자 데이터를 쓰기 위해서 OutputStreamWriter를 사용
                 */
                logger.info("Request paramStr >>> " + paramStr);
                DataOutputStream outputStream = new DataOutputStream(connection.getOutputStream());
                outputStream.write(paramStr.getBytes("utf-8")); // 파라미터 write
                outputStream.flush();
                outputStream.close();
            }

            // 5. 요청 전송
            int responseCode = connection.getResponseCode();
            logger.info("Response Code >>> " + responseCode);

            /*
             *  response 데이터 얻기
             *  - 응답 데이터를 읽을 수 있는 InputStream 객체 얻기
             *  - 응답을 문자열 타입으로 얻기 위해 BufferedReader 객체 사용
             */
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream())); // 5. 정상적인 응답이 오면 BufferedReader로 응답읽고 출력
            StringBuffer stringBuffer = new StringBuffer();
            String inputLine;

            while ((inputLine = bufferedReader.readLine()) != null) {
                stringBuffer.append(inputLine);
            }
            bufferedReader.close();

            response = stringBuffer.toString();
        } catch (Exception e) {
            logger.error("******************* Error *******************");
            if (e.getCause() != null) {
                logger.error("Error Message >> " + e.getCause().getMessage());
            } else {
                logger.error("Error Message >> " + e.getMessage());
            }
            logger.error("**********************************************");
            e.printStackTrace();
        }

        return response;
    }
}
