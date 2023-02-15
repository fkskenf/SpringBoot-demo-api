package com.blog.demo.client;

import java.io.IOException;
import java.util.HashMap;

public interface HttpConnectionService {

    public String sendPost(String requestUrl, String method, HashMap<String, Object> Headers, HashMap param) throws IOException;

    public String sendGet(String requestUrl, String method, HashMap<String, Object> Headers, HashMap param) throws IOException;
}
