package com.example;

import java.util.Properties;

public class Greeting {
    public static void main(String[] args) {
        Properties props = System.getProperties();
        props.forEach((key, value) -> System.out.println(key + ": " + value));
    }
}
