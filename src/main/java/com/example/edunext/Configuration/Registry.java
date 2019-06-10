package com.example.edunext.Configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class Registry {
    private static final Map<String, Object> registry = new HashMap<String, Object>();

    public static String getdb()
    {
        return "db2";
    }

}
