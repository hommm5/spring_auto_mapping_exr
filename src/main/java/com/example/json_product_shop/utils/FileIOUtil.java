package com.example.json_product_shop.utils;

import java.io.IOException;

public interface FileIOUtil {

    String readFileContent(String filePath) throws IOException;

    void write(String content,String filePath) throws IOException;
}
