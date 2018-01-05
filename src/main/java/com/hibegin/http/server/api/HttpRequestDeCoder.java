package com.hibegin.http.server.api;

import java.util.Map;

public interface HttpRequestDeCoder {

    Map.Entry<Boolean, byte[]> doDecode(byte[] bytes) throws Exception;

    HttpRequest getRequest();
}
