package com.lagou.io;

import java.io.InputStream;

public class Resources {
    public static InputStream getResourcesAsStrem(String path){
        InputStream resourceAsStream = Resources.class.getClassLoader().getResourceAsStream(path);
        return resourceAsStream;
    }
}
