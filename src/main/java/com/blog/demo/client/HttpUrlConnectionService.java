package com.blog.demo.client;

import java.io.IOException;
import java.util.HashMap;

public interface HttpUrlConnectionService {

    public String sendPost(String requestUrl, HashMap<String, Object> Headers, HashMap param) throws IOException;

    public String sendGet(String requestUrl, HashMap<String, Object> Headers) throws IOException;
}
